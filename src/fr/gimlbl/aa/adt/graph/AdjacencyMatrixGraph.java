package fr.gimlbl.aa.adt.graph;

import fr.gimlbl.aa.adt.Iterator;
import fr.gimlbl.aa.adt.ConnectedComponent;
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
    public List<Integer> getVerticesInConnectedComponent(int startVertex){
        List<Integer> abrPoint = new LinkedList<>();
        abrPoint.addToTail(startVertex);
        Iterator<Integer> pointIterator = abrPoint.iterator();
        while(pointIterator.hasNext()) {
            Integer vertexPoint = pointIterator.next();
            List<Edge> listInstance = this.matrix.getElementByPosition(vertexPoint);
            Iterator<Edge> edgeIterator = listInstance.iterator();

            while(edgeIterator.hasNext()) {
                Edge edge = edgeIterator.next();
                int vertexOpposite = edge.getOppositeVertex(vertexPoint);
                if(edge.getWeight() != Integer.MAX_VALUE) {
                    if(!abrPoint.hasValue(vertexOpposite)) {
                        abrPoint.addToTail(vertexOpposite);
                    }
                }
            }
        }

        return abrPoint;
    }

    @Override
    public List<Edge> getEdgesInConnectedComponent(int startVertex){
        List<Edge> edgeList1 = new LinkedList<>();
        List<Integer> abrPoint = new LinkedList<>();

        abrPoint.addToTail(startVertex);

        Iterator<Integer> pointIterator = abrPoint.iterator();
        while(pointIterator.hasNext()) {

            Integer vertexPoint = pointIterator.next();
            List<Edge> listInstance = this.matrix.getElementByPosition(vertexPoint);

            Iterator<Edge> edgeIterator = listInstance.iterator();
            while(edgeIterator.hasNext()) {
                Edge edge = edgeIterator.next();

                if(edge.getWeight() == Integer.MAX_VALUE) {
                    continue;
                }

                int vertexOpposite = edge.getOppositeVertex(vertexPoint);
                if(!abrPoint.hasValue(vertexOpposite)) {
                    abrPoint.addToTail(vertexOpposite);
                }
                if(!edgeList1.hasValue(edge))
                    edgeList1.addToTail(edge);
            }
        }

        return edgeList1;
    }

    public ConnectedComponent getConnectedComponent(int startVertex){
        List<Edge> edgeList1 = new LinkedList<>();
        List<Integer> abrPoint = new LinkedList<>();

        abrPoint.addToTail(startVertex);

        Iterator<Integer> pointIterator = abrPoint.iterator();
        while(pointIterator.hasNext()) {

            Integer vertexPoint = pointIterator.next();
            List<Edge> listInstance = this.matrix.getElementByPosition(vertexPoint);

            Iterator<Edge> edgeIterator = listInstance.iterator();
            while(edgeIterator.hasNext()) {
                Edge edge = edgeIterator.next();

                if(edge.getWeight() == Integer.MAX_VALUE) {
                    continue;
                }

                int vertexOpposite = edge.getOppositeVertex(vertexPoint);
                if(!abrPoint.hasValue(vertexOpposite)) {
                    abrPoint.addToTail(vertexOpposite);
                }
                if(!edgeList1.hasValue(edge))
                    edgeList1.addToTail(edge);
            }
        }

        return new ConnectedComponent(abrPoint, edgeList1);
    }

}
