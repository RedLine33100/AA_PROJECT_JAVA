package fr.gimlbl.aa.adt.graph;

import fr.gimlbl.aa.adt.Iterator;
import fr.gimlbl.aa.adt.list.List;
import fr.gimlbl.aa.adt.list.ListInstance;

import java.util.function.BiFunction;

public class AdjacencyMatrixGraph implements WeightedGraph {

    private final List<List<Edge>> matrix = new ListInstance<>();
    private final List<Edge> edgeList = new ListInstance<>();

    public static class Builder implements GraphBuilder<AdjacencyMatrixGraph> {
        @Override
        public AdjacencyMatrixGraph createGraph(int vertexCount) {
            return new AdjacencyMatrixGraph(vertexCount);
        }
    }

    public AdjacencyMatrixGraph(int point) {

        for(int i = 1; i <= point; i++) {
            ListInstance<Edge> listInstance = new ListInstance<>();
            for(int j = 1; j <= point; j++){
                listInstance.addToTail(null);
            }
            matrix.addToTail(listInstance);
        }
        for(int i = 1; i <= point; i++) {
            for(int j = i; j <= point; j++){
                Edge edge = new Edge(i,j, -1);
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
        List<Edge> edges = new ListInstance<>();
        Iterator<Edge> edgeIterator = this.edgeList.iterator();
        while (edgeIterator.hasNext()) {
            Edge currentElement = edgeIterator.next();
            if(currentElement.getWeight() != -1)
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
        List<Edge> incidentEdges = new ListInstance<>();

        List<Edge> listInstance = matrix.getElementByPosition(from);
        if(listInstance == null) {
            return incidentEdges;
        }


        BiFunction<Edge, Edge, Boolean> biFunction = (edge1, edge2) -> edge1.getWeight() > edge2.getWeight();
        Iterator<Edge> edgeIterator = listInstance.iterator();
        while(edgeIterator.hasNext()) {
            Edge outElement = edgeIterator.next();
            if(outElement.getWeight() != -1) {
                incidentEdges.addElement(outElement, biFunction);
            }
        }
        return incidentEdges;
    }

    public boolean isConnexe(){
        int connexeSize = calculateAbrFrom(1).size();
        return connexeSize == vertexCount();
    }

    public List<Integer> calculateAbrFrom(int startVertex){
        List<Integer> abrPoint = new ListInstance<>();
        abrPoint.addToTail(startVertex);
        Iterator<Integer> pointIterator = abrPoint.iterator();
        while(pointIterator.hasNext()) {
            Integer vertexPoint = pointIterator.next();
            List<Edge> listInstance = this.matrix.getElementByPosition(vertexPoint);
            Iterator<Edge> edgeIterator = listInstance.iterator();

            while(edgeIterator.hasNext()) {
                Edge edge = edgeIterator.next();
                int vertexOpposite = edge.getOppositeVertex(vertexPoint);
                if(edge.getWeight() != -1) {
                    if(!abrPoint.hasValue(vertexOpposite)) {
                        abrPoint.addToTail(vertexOpposite);
                    }
                }
            }
        }

        return abrPoint;
    }

    /**
     *
     * @return List of List of point in abr
     */
    public List<List<Integer>> getSubConnexeAbr(){
        List<Integer> remainCalcul = new ListInstance<>();
        List<List<Integer>> subAbrInstance = new ListInstance<>();

        for(int i = 1; i <= this.matrix.size(); i++) {
            remainCalcul.addToTail(i);
        }

        while(remainCalcul.size() != 0) {

            int vertex = remainCalcul.getElementByPosition(1);
            remainCalcul.removeElementByPosition(1);
            List<Integer> pointInAbr = calculateAbrFrom(vertex);



            subAbrInstance.addToTail(pointInAbr);

            Iterator<Integer> pointIterator = pointInAbr.iterator();
            while(pointIterator.hasNext()) {
                remainCalcul.removeElement(pointIterator.next());
            }

        }

        return subAbrInstance;
    }

}
