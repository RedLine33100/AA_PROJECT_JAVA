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

    public boolean isConnexe(){
        return getAllConnectedComponent().size() == 1;
    }

    public abstract ConnectedComponent getConnectedComponent(int startVertex);

    public List<ConnectedComponent> getAllConnectedComponent(){
        List<Integer> remainCalcul = new LinkedList<>();
        List<ConnectedComponent> subAbrInstance = new LinkedList<>();

        for(int i = 1; i <= this.vertexCount(); i++) {
            remainCalcul.addToTail(i);
        }

        while(remainCalcul.size() != 0) {

            int vertex = remainCalcul.getElementByPosition(1);
            remainCalcul.removeElementByPosition(1);
            ConnectedComponent pointInAbr = getConnectedComponent(vertex);



            subAbrInstance.addToTail(pointInAbr);

            Iterator<Integer> pointIterator = pointInAbr.getVertexList().iterator();
            while(pointIterator.hasNext()) {
                remainCalcul.removeElement(pointIterator.next());
            }

        }

        return subAbrInstance;
    }

}
