package fr.gimlbl.aa.adt.graph;

/**
 * A class which instantiates a graph of given type
 * @param <G> Type of graph (class which implements {@link WeightedGraph})
 */
public interface GraphBuilder<G extends WeightedGraph> {
    /**
     * Instantiate a graph.
     * @param vertexCount Number of vertices
     * @return Instantiated graph
     */
    G createGraph(int vertexCount);
}
