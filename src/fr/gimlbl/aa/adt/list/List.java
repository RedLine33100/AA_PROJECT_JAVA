package fr.gimlbl.aa.adt.list;

public interface List <T>{
    int size();
    T getElementByPosition(int position);
    void addToHead(T element);
    void addToTail(T element);
    boolean addElementByPosition(T element, int position);
    boolean updateElementByPosition(T element, int position);
    boolean hasValue(T element);
    boolean removeElement(T element);
    boolean removeElementByPosition(int position);
}
