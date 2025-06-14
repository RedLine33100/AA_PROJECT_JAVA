package fr.gimlbl.aa.adt;

/**
 * A pair of elements.
 * @param <T> Type of the first element
 * @param <U> Type of the second element
 */
public class Pair<T, U> {
    private final T first;
    private final U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }
}
