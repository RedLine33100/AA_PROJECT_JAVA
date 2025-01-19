package fr.gimlbl.aa.kruskal;

import fr.gimlbl.aa.adt.ConnectedComponent;
import fr.gimlbl.aa.adt.Iterator;
import fr.gimlbl.aa.adt.Pair;
import fr.gimlbl.aa.adt.graph.AdjacencyListGraph;
import fr.gimlbl.aa.adt.graph.AdjacencyMatrixGraph;
import fr.gimlbl.aa.adt.graph.Edge;
import fr.gimlbl.aa.adt.graph.WeightedGraph;
import fr.gimlbl.aa.adt.list.List;

import java.io.IOException;

import static fr.gimlbl.aa.kruskal.Util.checkArgs;

public class KruskalM {
    public KruskalM(String[] args) throws IOException {
        AdjacencyMatrixGraph adjacencyMatrixGraph = GraphParser.parse(args[0], new AdjacencyMatrixGraph.Builder());

        List<ConnectedComponent> connectedComponentList = adjacencyMatrixGraph.getAllConnectedComponent();

        Iterator<ConnectedComponent> iterator = connectedComponentList.iterator();
        while(iterator.hasNext()){
            Kruskal.kruskal2(iterator.next(), new AdjacencyMatrixGraph.Builder(), adjacencyMatrixGraph.vertexCount());
        }

    }

    public static void main(String[] args) throws IOException {
        checkArgs(args);
        new KruskalM(args);
    }
}
