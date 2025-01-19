package fr.gimlbl.aa.adt.graph;

import fr.gimlbl.aa.adt.Iterator;
import fr.gimlbl.aa.adt.list.List;
import fr.gimlbl.aa.adt.list.ListInstance;

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
     * Retourne la liste des elements dans le sous-arbre commencant par le vertex
     */
    public abstract List<Integer> calculateAbrFromVertex(int vertex);

    public boolean isConnexe(){
        return calculateAbrFromVertex(1).size() == vertexCount();
    }

    public List<List<Integer>> getSubConnexeAbr(){
        List<Integer> remainCalcul = new ListInstance<>();
        List<List<Integer>> subAbrInstance = new ListInstance<>();

        for(int i = 1; i <= this.vertexCount(); i++) {
            remainCalcul.addToTail(i);
        }

        while(remainCalcul.size() != 0) {

            int vertex = remainCalcul.getElementByPosition(1);
            remainCalcul.removeElementByPosition(1);
            List<Integer> pointInAbr = calculateAbrFromVertex(vertex);



            subAbrInstance.addToTail(pointInAbr);

            Iterator<Integer> pointIterator = pointInAbr.iterator();
            while(pointIterator.hasNext()) {
                remainCalcul.removeElement(pointIterator.next());
            }

        }

        return subAbrInstance;
    }
}
