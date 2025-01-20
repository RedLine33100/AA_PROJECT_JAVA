package fr.gimlbl.aa.adt.graph;

import fr.gimlbl.aa.adt.Pair;

/**
 * An undirected edge, with a weight.
 */
public class Edge {
    private final int vertex1;
    private final int vertex2;
    private int weight;

    /**
     * Create an edge incident to given vertices (which must be different), and with the given weight.
     */
    public Edge(int vertex1, int vertex2, int weight) {
        if (vertex1 < 1 || vertex2 < 1) {
            throw new IllegalArgumentException("Les nœuds incidents doivent être différents et supérieurs à 0.");
        }

        this.vertex1 = Math.min(vertex1, vertex2);
        this.vertex2 = Math.max(vertex1, vertex2);
        this.weight = weight;
    }

    /**
     * Returns first incident vertex. {@code getVertex1()} returns a value lower than {@link #getVertex2()}.
     */
    public int getVertex1() {
        return vertex1;
    }

    /**
     * Returns second incident vertex. {@code getVertex2()} returns a value higher than {@link #getVertex1()}.
     */
    public int getVertex2() {
        return vertex2;
    }

    /**
     * Returns incident vertices. The first one is lower than the second.
     */
    public Pair<Integer, Integer> getVertices() {
        return new Pair<>(vertex1, vertex2);
    }

    /**
     * Returns the weight of the edge.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Return the other Vertex
     */

    public int getOppositeVertex(int vertex1){
        if(vertex1 == this.vertex1)
            return this.vertex2;
        return this.vertex1;
    }

    /**
     * Change the Edge Weight
     */

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
