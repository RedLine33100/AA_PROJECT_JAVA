package fr.gimlbl.aa.adt.graph;

import fr.gimlbl.aa.adt.Iterator;
import fr.gimlbl.aa.adt.ConnectedComponent;
import fr.gimlbl.aa.adt.list.List;
import fr.gimlbl.aa.adt.list.LinkedList;

/**
 * A weighted graph, which consists of n vertices (numbered from 1 to n) and several edges.
 */
public abstract class WeightedGraph {
    /**
     * Returns the number of vertices.
     */
    public abstract int vertexCount();

    /**
     * Returns the number of edges.
     */
    public abstract int edgeCount();

    /**
     * Returns the list of edges which are incident to the given vertex.
     */
    public abstract List<Edge> getIncidentEdges(int vertex);

    /**
     * Returns all edges.
     */
    public abstract List<Edge> getEdges();

    /**
     * Returns the weight of the edge linking given vertices. Returns {@link Integer#MAX_VALUE} if there is no such edge.
     */
    public abstract int edgeWeight(int vertex1, int vertex2);

    /**
     * Add an edge linking given vertices, of given weight.
     */
    public abstract void addEdge(int vertex1, int vertex2, int weight);

    /**
     * Returns true if the graph is connected, false if it contains several connected components.
     */
    public boolean isConnected() {
        return getAllConnectedComponent().size() == 1;
    }

    /**
     * Returns the connected component which contains the given vertex.
     */
    public abstract ConnectedComponent getConnectedComponent(int startVertex);

    /**
     * Computes all connected components in the graph.
     */
    public List<ConnectedComponent> getAllConnectedComponent() {
        List<Integer> remainingVertices = new LinkedList<>();
        List<ConnectedComponent> connectedComponents = new LinkedList<>();

        for (int i = 1; i <= this.vertexCount(); i++) {
            remainingVertices.addToTail(i);
        }

        while (remainingVertices.size() != 0) {
            int vertex = remainingVertices.getElementByPosition(1);
            remainingVertices.removeElementByPosition(1);
            ConnectedComponent cc = getConnectedComponent(vertex);

            connectedComponents.addToTail(cc);

            Iterator<Integer> vertexIterator = cc.getVertexList().iterator();
            while (vertexIterator.hasNext()) {
                remainingVertices.removeElement(vertexIterator.next());
            }
        }

        return connectedComponents;
    }

}
