package fr.gimlbl.aa.adt;

import fr.gimlbl.aa.adt.graph.Edge;
import fr.gimlbl.aa.adt.list.List;

public class ConnectedComponent {
    private final List<Integer> vertexList;
    private final List<Edge> edgeList;

    public ConnectedComponent(List<Integer> vertexList, List<Edge> edgeList) {
        this.vertexList = vertexList;
        this.edgeList = edgeList;
    }

    public List<Edge> getEdgeList() {
        return edgeList;
    }

    public List<Integer> getVertexList() {
        return vertexList;
    }
}
