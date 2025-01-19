package fr.gimlbl.aa.adt.graph;

import fr.gimlbl.aa.adt.Pair;

/**
 * An undirected edge, with a weight.
 */
public class Edge {
    private final int from;
    private final int to;
    private final int weight;

    /**
     * Create an edge incident to given vertices (which must be different), and with the given weight.
     */
    public Edge(int from, int to, int weight) {
        if (from == to || from < 1 || to < 1) {
            throw new IllegalArgumentException("Les nœuds incidents doivent être différents et supérieurs à 0.");
        }

        this.from = Math.min(from, to);
        this.to = Math.max(from, to);
        this.weight = weight;
    }

    /**
     * Returns first incident vertex. {@code getFrom()} returns a value lower than {@link #getTo()}.
     */
    public int getFrom() {
        return from;
    }

    /**
     * Returns second incident vertex. {@code getTo()} returns a value higher than {@link #getFrom()}.
     */
    public int getTo() {
        return to;
    }

    /**
     * Returns incident vertices. The first one is lower than the second.
     */
    public Pair<Integer, Integer> getVertices() {
        return new Pair<>(from, to);
    }

    /**
     * Returns the weight of the edge.
     */
    public int getWeight() {
        return weight;
    }
}
