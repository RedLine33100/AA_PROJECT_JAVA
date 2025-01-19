package fr.gimlbl.aa.adt.graph;

import fr.gimlbl.aa.adt.ConnectedComponent;
import fr.gimlbl.aa.adt.Iterator;
import fr.gimlbl.aa.adt.list.List;
import fr.gimlbl.aa.adt.list.LinkedList;
import fr.gimlbl.aa.adt.list.ReadonlyList;

public class AdjacencyListGraph extends WeightedGraph {
    public static class Builder implements GraphBuilder<AdjacencyListGraph> {
        @Override
        public AdjacencyListGraph createGraph(int vertexCount) {
            return new AdjacencyListGraph(vertexCount);
        }
    }

    private final int vertexCount;
    private final List<List<Edge>> adjacencyList;

    public AdjacencyListGraph(int vertexCount) {
        this.vertexCount = vertexCount;

        adjacencyList = new LinkedList<>();
        for (int i = 1; i <= vertexCount; i++) {
            adjacencyList.addToTail(new LinkedList<>());
        }
    }

    @Override
    public int vertexCount() {
        return vertexCount;
    }

    @Override
    public int edgeCount() {
        int count = 0;

        Iterator<List<Edge>> iterator = adjacencyList.iterator();
        while (iterator.hasNext()) {
            count += iterator.next().size();
        }

        assert count % 2 == 0;
        return count / 2;
    }

    @Override
    public List<Edge> getIncidentEdges(int vertex) {
        if (vertex <= 0 || vertex > vertexCount) {
            throw new IndexOutOfBoundsException();
        }

        return new ReadonlyList<>(adjacencyList.getElementByPosition(vertex));
    }

    @Override
    public List<Edge> getEdges() {
        List<Edge> edges = new LinkedList<>();

        Iterator<List<Edge>> iterator = adjacencyList.iterator();
        int i = 1;
        while (iterator.hasNext()) {
            Iterator<Edge> edgeIterator = iterator.next().iterator();
            while (edgeIterator.hasNext()) {
                Edge edge = edgeIterator.next();
                if (edge.getVertex1() == i) {
                    // Add the vertex once, not twice!
                    edges.addToTail(edge);
                }
            }
            ++i;
        }

        return edges;
    }

    @Override
    public int edgeWeight(int vertex1, int vertex2) {
        if (vertex1 <= 0 || vertex2 <= 0 || vertex1 > vertexCount || vertex2 > vertexCount || vertex1 == vertex2) {
            throw new IllegalArgumentException();
        }

        Iterator<Edge> edgeIterator = adjacencyList.getElementByPosition(vertex1).iterator();
        while (edgeIterator.hasNext()) {
            Edge edge = edgeIterator.next();
            if (edge.getVertex2() == vertex2) {
                return edge.getWeight();
            }
        }

        return Integer.MAX_VALUE;
    }

    @Override
    public void addEdge(int vertex1, int vertex2, int weight) {
        if (vertex1 <= 0 || vertex2 <= 0 || vertex1 > vertexCount || vertex2 > vertexCount || vertex1 == vertex2) {
            throw new IllegalArgumentException();
        }

        Edge edge = new Edge(vertex1, vertex2, weight);

        adjacencyList.getElementByPosition(vertex1).addToTail(edge);
        adjacencyList.getElementByPosition(vertex2).addToTail(edge);
    }

    @Override
    public List<Integer> getVerticesInConnectedComponent(int vertex) {
        return getConnectedComponent(vertex).getVertexList();
    }

    @Override
    public List<Edge> getEdgesInConnectedComponent(int vertex) {
        return getConnectedComponent(vertex).getEdgeList();
    }

    @Override
    public ConnectedComponent getConnectedComponent(int vertex) {
        List<Integer> verticesInConnectedComponent = new LinkedList<>();
        List<Edge> edgesInConnectedComponent = new LinkedList<>();

        List<Boolean> inConnectedComponent = new LinkedList<>();
        List<Integer> toVisit = new LinkedList<>();
        List<Boolean> visited = new LinkedList<>();

        for (int i = 1; i <= vertexCount; i++) {
            inConnectedComponent.addToTail(false);
            visited.addToTail(false);
        }

        toVisit.addToHead(vertex);

        while (toVisit.size() > 0) {
            int v = toVisit.getElementByPosition(1);
            toVisit.removeElementByPosition(1);
            inConnectedComponent.updateElementByPosition(true, v);
            visited.updateElementByPosition(true, v);
            Iterator<Edge> edgeIterator = adjacencyList.getElementByPosition(v).iterator();
            while (edgeIterator.hasNext()) {
                Edge edge = edgeIterator.next();
                int w = edge.getOppositeVertex(v);
                if (!visited.getElementByPosition(w)) {
                    toVisit.addToHead(w);
                }
                if(!edgesInConnectedComponent.hasValue(edge))
                    edgesInConnectedComponent.addToTail(edge);
            }
        }

        int i = 1;
        Iterator<Boolean> iterator = inConnectedComponent.iterator();
        while (iterator.hasNext()) {
            if (iterator.next()) {
                verticesInConnectedComponent.addToTail(i);
            }
            ++i;
        }

        return new ConnectedComponent(verticesInConnectedComponent, edgesInConnectedComponent);
    }

}
