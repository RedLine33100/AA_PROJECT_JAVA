package fr.gimlbl.aa.adt.list;

import fr.gimlbl.aa.adt.Compare;
import fr.gimlbl.aa.adt.Iterator;

/**
 * An ordered list of elements. The first index of a list is 1 (not 0 as usual).
 * @param <T> Type of elements in the list
 */
public interface List<T> extends Cloneable {
    /**
     * Returns the number of elements contained in the list.
     */
    int size();

    /**
     * Returns the element at given index.
     * @param position Index (1-based) of the element to retrieve
     * @return Element at given index, null if the index is out of bounds.
     */
    T getElementByPosition(int position);

    /**
     * Returns true if given element is present in the list, false otherwise.
     */
    boolean hasValue(T element);

    /**
     * Inserts given element at the beginning of the list.
     */
    void addToHead(T element);

    /**
     * Inserts given element at the end of the list.
     */
    void addToTail(T element);

    /**
     * Inserts given element at given index (1-based).
     * @return true if the element was inserted, false otherwise.
     */
    boolean addElementByPosition(T element, int position);

    /**
     * Inserts given element at the first position where the given comparator evaluates to true.
     * This allows to build a sorted list.
     * @param element Element to insert
     * @param comparator Comparison predicate: the first parameter is an element from the list and the second parameter
     *                   is the element to insert.
     */
    void addElement(T element, Compare<T, T> comparator);

    /**
     * Replace the element at given position by given element.
     * @param element Element to put in the list
     * @param position Index where to put given element
     * @return true if the list was updated, false otherwise.
     */
    boolean updateElementByPosition(T element, int position);

    /**
     * Removes given element from the list.
     * @return true if the list was updated, false otherwise.
     */
    boolean removeElement(T element);

    /**
     * Removes element at given position
     * @param position Position of the element to remove (1-based)
     * @return true if the list was updated, false otherwise.
     */
    boolean removeElementByPosition(int position);

    /**
     * Returns an iterator of the list.
     */
    Iterator<T> iterator();

    List<T> clone();
}
