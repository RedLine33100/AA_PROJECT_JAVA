package fr.gimlbl.aa.adt.graph;

/**
 * A graph, which consists of n vertices (numbered from 1 to n) and several edges.
 */
public interface Graph {
    /**
     * Returns the number of vertices.
     */
    int vertexCount();

    /**
     * Returns the weight of the edge linking given vertices. Returns {@link Integer#MAX_VALUE} if there is no such edge.
     */
    int edgeWeight(int vertex1, int vertex2);

    /**
     * Add an edge linking given vertices, of given weight.
     */
    void addEdge(int vertex1, int vertex2, int weight);
}
