package fr.gimlbl.aa.kruskal;

import fr.gimlbl.aa.adt.ConnectedComponent;
import fr.gimlbl.aa.adt.Iterator;
import fr.gimlbl.aa.adt.Pair;
import fr.gimlbl.aa.adt.graph.AdjacencyMatrixGraph;
import fr.gimlbl.aa.adt.graph.Edge;
import fr.gimlbl.aa.adt.list.LinkedList;
import fr.gimlbl.aa.adt.list.List;

import java.io.IOException;

import static fr.gimlbl.aa.kruskal.Util.checkArgs;

public class KruskalM {
    public static void kruskalM(String[] args) throws IOException {
        AdjacencyMatrixGraph adjacencyMatrixGraph = GraphParser.parse(args[0], new AdjacencyMatrixGraph.Builder());

        List<ConnectedComponent> connectedComponentList = adjacencyMatrixGraph.getAllConnectedComponent();

        Iterator<ConnectedComponent> iterator = connectedComponentList.iterator();

        List<Pair<List<Edge>, Integer>>allAbr = new LinkedList<>();

        while(iterator.hasNext()){
            allAbr.addToTail(Kruskal.kruskal(iterator.next(), new AdjacencyMatrixGraph.Builder(), adjacencyMatrixGraph.vertexCount()));
        }

        Util.printUsage(allAbr);

    }

    public static void main(String[] args) throws IOException {
        checkArgs(args);
        kruskalM(args);
    }
}
