package fr.gimlbl.aa.utils.list;

public class ListInstance<T> implements List<T>{

    private ListElement<T> head;
    private ListElement<T> tail;
    private int size = 0;

    public int size() {
        return this.size;
    }

    public ListElement<T> getHead() {
        return head;
    }

    public ListElement<T> getTail() {
        return tail;
    }

    private ListElement<T> getElement(T element){
        ListElement<T> current = this.head;
        while(current != null){
            if(current.getElement().equals(element)){
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    private ListElement<T> getElementByPositionInternal(int position){
        ListElement<T> current = this.head; // id = 1

        if(position <= 0 || position > this.size)
            return null;

        int i = 1;
        while (i != position){
            current = current.getNext();
            i++;
        }
        return current;
    }

    @Override
    public T getElementByPosition(int position){
        ListElement<T> current = this.getElementByPositionInternal(position);
        if(current == null)
            return null;
        return current.getElement();
    }

    @Override
    public void addToHead(T element){
        this.size++;
        if(this.head == null){
            this.head = new ListElement<>(element);
            this.tail = this.head;
            return;
        }
        ListElement<T> newHead = new ListElement<>(element);
        newHead.setNext(this.head);
        this.head.setPrevious(newHead);
        this.head = newHead;
    }

    @Override
    public void addToTail(T element){
        if(this.tail == null){
            this.addToHead(element);
            return;
        }
        this.size++;
        ListElement<T> newTail = new ListElement<>(element);
        this.tail.setNext(newTail);
        newTail.setPrevious(this.tail);
        this.tail = newTail;
    }

    @Override
    public boolean addElementByPosition(T element, int position){
        ListElement<T> current = this.getElementByPositionInternal(position);

        if(current == null){
            return false;
        }

        if(current.getPrevious() == null){
            this.addToHead(element);
            return true;
        }

        if(current.getNext() == null){
            this.addToTail(element);
            return true;
        }

        this.size++;

        ListElement<T> newElement = new ListElement<>(element);
        current.getPrevious().setNext(newElement);
        newElement.setPrevious(current.getPrevious());
        current.setPrevious(newElement);
        newElement.setNext(current);

        return true;

    }

    @Override
    public boolean updateElementByPosition(T element, int position){
        ListElement<T> current = this.getElementByPositionInternal(position);

        if(current == null){
            return false;
        }

        current.setElement(element);
        return true;

    }

    @Override
    public boolean hasValue(T element){
        return getElement(element) != null;
    }

    private void removeElement(ListElement<T> current){
        this.size--;

        if(current.getPrevious() != null){
            current.getPrevious().setNext(current.getNext());
        }else this.head = current.getNext();

        if(current.getNext() != null){
            current.getNext().setPrevious(current.getPrevious());
        }else this.tail = current.getPrevious();
    }

    @Override
    public boolean removeElement(T element){
        ListElement<T> current = this.getElement(element);
        if(current == null)
            return false;

        this.removeElement(current);
        return true;
    }

    public boolean removeElementByPosition(int position){
        ListElement<T> current = this.getElementByPositionInternal(position);
        if(current == null)
            return false;

        this.removeElement(current);
        return true;
    }

    @Override
    public String toString() {
        ListElement<T> current = this.head;
        StringBuilder sb = new StringBuilder();
        int count = 1;
        while(current != null){
            sb.append(count).append(") ").append(current.getElement());
            current = current.getNext();
            count++;
        }
        return sb.toString();
    }

}
