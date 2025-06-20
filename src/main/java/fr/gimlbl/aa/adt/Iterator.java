package fr.gimlbl.aa.adt;

import java.util.NoSuchElementException;

/**
 * An iterator, which allows the traversal of a data structure.
 * @param <T> Type of data in the data structure
 */
public interface Iterator<T> {
    boolean hasNext();
    T next() throws NoSuchElementException;
}
