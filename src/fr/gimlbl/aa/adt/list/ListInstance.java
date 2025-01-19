package fr.gimlbl.aa.adt.list;

import fr.gimlbl.aa.adt.Compare;
import fr.gimlbl.aa.adt.Iterator;

import java.util.NoSuchElementException;

public class ListInstance<T> implements List<T>{

    private ListElement<T> head;
    private ListElement<T> tail;
    private int size = 0;

    public int size() {
        return this.size;
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
    public Iterator<T> iterator() {
        return new LinkedListIterator<>(this);
    }

    @Override
    public String toString() {
        ListElement<T> current = this.head;
        StringBuilder sb = new StringBuilder();
        int count = 1;
        while(current != null){
            sb.append("Element ").append(count).append(") ").append(current.getElement()).append(" ");
            current = current.getNext();
            count++;
        }
        return sb.toString();
    }

    @Override
    public void addElement(T element, Compare<T, T> comparator){
        ListElement<T> current = this.head;
        int pos = 1;
        while(current != null){
            if(!comparator.compare(element, current.getElement())){
                this.addElementByPosition(element, pos);
                return;
            }
            pos++;
            current = current.getNext();
        }
        this.addToTail(element);
    }

    @Override
    public List<T> clone(){
        List<T> newList = new ListInstance<>();
        Iterator<T> iterator = this.iterator();
        while(iterator.hasNext()){
            newList.addToTail(iterator.next());
        }
        return newList;
    }

    private static class LinkedListIterator<T> implements Iterator<T> {
        private ListElement<T> current;
        private ListElement<T> previous;

        public LinkedListIterator(ListInstance<T> list) {
            current = list.head;
            previous = current;
        }

        @Override
        public boolean hasNext() {
            return current != null || previous.getNext() != null;
        }

        @Override
        public T next() throws NoSuchElementException {
            if (current == null) {
                if(previous.getNext() == null){
                    throw new NoSuchElementException();
                }
                current = previous.getNext();
            }

            T result = current.getElement();
            previous = current;
            current = current.getNext();
            return result;
        }
    }
}
