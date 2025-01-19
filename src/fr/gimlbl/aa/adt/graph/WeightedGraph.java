package fr.gimlbl.aa.adt.graph;

import fr.gimlbl.aa.adt.list.List;

/**
 * A weighted graph, which consists of n vertices (numbered from 1 to n) and several edges.
 */
public interface WeightedGraph {
    /**
     * Returns the number of vertices.
     */
    int vertexCount();

    /**
     * Returns the number of edges.
     */
    int edgeCount();

    /**
     * Returns the list of edges which are incident to the given vertex.
     */
    List<Edge> getIncidentEdges(int vertex);

    /**
     * Returns all edges.
     */
    List<Edge> getEdges();

    /**
     * Returns the weight of the edge linking given vertices. Returns {@link Integer#MAX_VALUE} if there is no such edge.
     */
    int edgeWeight(int vertex1, int vertex2);

    /**
     * Add an edge linking given vertices, of given weight.
     */
    void addEdge(int vertex1, int vertex2, int weight);
}
