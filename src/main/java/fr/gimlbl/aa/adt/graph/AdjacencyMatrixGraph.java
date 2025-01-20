package fr.gimlbl.aa.adt.graph;

import fr.gimlbl.aa.adt.Iterator;
import fr.gimlbl.aa.adt.list.List;
import fr.gimlbl.aa.adt.list.LinkedList;

public class AdjacencyMatrixGraph extends WeightedGraph {

    private final List<List<Edge>> matrix = new LinkedList<>();
    private final List<Edge> edgeList = new LinkedList<>();

    public static class Builder implements GraphBuilder<AdjacencyMatrixGraph> {
        @Override
        public AdjacencyMatrixGraph createGraph(int vertexCount) {
            return new AdjacencyMatrixGraph(vertexCount);
        }
    }

    public AdjacencyMatrixGraph(int point) {

        for(int i = 1; i <= point; i++) {
            LinkedList<Edge> linkedList = new LinkedList<>();
            for(int j = 1; j <= point; j++){
                linkedList.addToTail(null);
            }
            matrix.addToTail(linkedList);
        }
        for(int i = 1; i <= point; i++) {
            for(int j = i; j <= point; j++){
                Edge edge = new Edge(i,j, Integer.MAX_VALUE);
                matrix.getElementByPosition(i).updateElementByPosition(edge, j);
                matrix.getElementByPosition(j).updateElementByPosition(edge, i);
                this.edgeList.addToTail(edge);
            }
        }
    }

    @Override
    public int vertexCount() {
        return matrix.size();
    }

    @Override
    public List<Edge> getEdges() {
        List<Edge> edges = new LinkedList<>();
        Iterator<Edge> edgeIterator = this.edgeList.iterator();
        while (edgeIterator.hasNext()) {
            Edge currentElement = edgeIterator.next();
            if(currentElement.getWeight() != Integer.MAX_VALUE)
                edges.addToTail(currentElement);
        }
        return edges;
    }

    @Override
    public int edgeCount() {
        return getEdges().size();
    }

    @Override
    public int edgeWeight(int from, int to){
        return matrix.getElementByPosition(from).getElementByPosition(to).getWeight();
    }

    @Override
    public void addEdge(int from, int to, int cost){
        List<Edge> listInstance = matrix.getElementByPosition(from);
        if(listInstance == null) {
            return;
        }
        Edge matriceLink = listInstance.getElementByPosition(to);
        if(matriceLink == null) {
            return ;
        }
        matriceLink.setWeight(cost);
    }

    @Override
    public List<Edge> getIncidentEdges(int from){
        List<Edge> incidentEdges = new LinkedList<>();

        List<Edge> listInstance = matrix.getElementByPosition(from);
        if(listInstance == null) {
            return incidentEdges;
        }

        Iterator<Edge> edgeIterator = listInstance.iterator();
        while(edgeIterator.hasNext()) {
            Edge outElement = edgeIterator.next();
            if(outElement.getWeight() != Integer.MAX_VALUE) {
                incidentEdges.addToTail(outElement);
            }
        }
        return incidentEdges;
    }

    @Override
    public ConnectedComponent getConnectedComponent(int startVertex) {
        List<Edge> edgesInCC = new LinkedList<>();        // Edges in Connected Component
        List<Integer> verticesInCC = new LinkedList<>();  // Vertices in Connected Component

        verticesInCC.addToTail(startVertex);

        Iterator<Integer> vertexIterator = verticesInCC.iterator();
        while(vertexIterator.hasNext()) {
            Integer vertexPoint = vertexIterator.next();
            List<Edge> edges = this.matrix.getElementByPosition(vertexPoint);

            Iterator<Edge> edgeIterator = edges.iterator();
            while(edgeIterator.hasNext()) {
                Edge edge = edgeIterator.next();

                if (edge.getWeight() != Integer.MAX_VALUE) {
                    int oppositeVertex = edge.getOppositeVertex(vertexPoint);
                    if (!verticesInCC.hasValue(oppositeVertex)) {
                        verticesInCC.addToTail(oppositeVertex);
                    }
                    if (!edgesInCC.hasValue(edge)) {
                        edgesInCC.addToTail(edge);
                    }
                }
            }
        }

        return new ConnectedComponent(verticesInCC, edgesInCC);
    }
}
