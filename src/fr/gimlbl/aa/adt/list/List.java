package fr.gimlbl.aa.adt.list;

import fr.gimlbl.aa.adt.Compare;
import fr.gimlbl.aa.adt.Iterator;

import java.util.function.BiFunction;

public interface List <T>{
    int size();
    T getElementByPosition(int position);
    void addToHead(T element);
    void addToTail(T element);
    boolean addElementByPosition(T element, int position);
    void addElement(T element, Compare<T, T> comparator);
    boolean updateElementByPosition(T element, int position);
    boolean hasValue(T element);
    boolean removeElement(T element);
    boolean removeElementByPosition(int position);
    Iterator<T> iterator();

    List<T> clone();
}
