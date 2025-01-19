package fr.gimlbl.aa.adt.list;

import fr.gimlbl.aa.adt.Iterator;

public interface List<T> {
    int size();
    T getElementByPosition(int position);
    void addToHead(T element);
    void addToTail(T element);
    boolean addElementByPosition(T element, int position);
    boolean updateElementByPosition(T element, int position);
    boolean hasValue(T element);
    boolean removeElement(T element);
    boolean removeElementByPosition(int position);
    ListElement<T> getHead();
    ListElement<T> getTail();
    Iterator<T> iterator();
}
