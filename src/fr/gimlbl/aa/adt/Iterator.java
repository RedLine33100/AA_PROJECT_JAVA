package fr.gimlbl.aa.adt;

/**
 * An iterator, which allows the traversal of a data structure.
 * @param <T> Type of data in the data structure
 */
public interface Iterator<T> {
    boolean hasNext();
    T next();
}
