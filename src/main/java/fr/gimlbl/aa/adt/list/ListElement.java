package fr.gimlbl.aa.adt.list;

/**
 * An element of a {@link LinkedList}, which has a reference to the next element and the previous element of the
 * list.
 * @param <T> Type of elements in the list
 */
class ListElement<T> {

    private T element;
    private ListElement<T> next = null;
    private ListElement<T> previous = null;

    protected ListElement(T element) {
        this.element = element;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public ListElement<T> getNext() {
        return next;
    }

    public void setNext(ListElement<T> next) {
        this.next = next;
    }

    public ListElement<T> getPrevious() {
        return this.previous;
    }

    public void setPrevious(ListElement<T> previous) {
        this.previous = previous;
    }
}
