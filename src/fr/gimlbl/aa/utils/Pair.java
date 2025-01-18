package fr.gimlbl.aa.utils;

public class Pair<T, U> {
    private T first;
    private U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    T getFirst() {
        return first;
    }

    void setFirst(T first) {
        this.first = first;
    }

    U getSecond() {
        return second;
    }

    void setSecond(U second) {
        this.second = second;
    }
}
