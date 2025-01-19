package fr.gimlbl.aa.adt.list;

import fr.gimlbl.aa.adt.Compare;
import fr.gimlbl.aa.adt.Iterator;

import java.util.function.BiFunction;

/**
 * A read-only view of a list. Each method which mutates the list throws {@link UnsupportedOperationException}.
 * @param <T> Type of data in the list
 */
public class ReadonlyList<T> implements List<T> {
    private final List<T> list;

    /**
     * Wraps the given list in a {@link ReadonlyList}.
     */
    public ReadonlyList(List<T> list) {
        this.list = list;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public T getElementByPosition(int position) {
        return list.getElementByPosition(position);
    }

    @Override
    public void addToHead(T element) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addToTail(T element) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addElementByPosition(T element, int position) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean updateElementByPosition(T element, int position) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasValue(T element) {
        return list.hasValue(element);
    }

    @Override
    public boolean removeElement(T element) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeElementByPosition(int position) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public void addElement(T element, Compare<T, T> comparator) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> clone(){
        return new ReadonlyList<>(list);
    }
}
