package fr.gimlbl.aa.kruskal;

import fr.gimlbl.aa.adt.graph.ConnectedComponent;
import fr.gimlbl.aa.adt.Iterator;
import fr.gimlbl.aa.adt.Pair;
import fr.gimlbl.aa.adt.graph.AdjacencyMatrixGraph;
import fr.gimlbl.aa.adt.graph.Edge;
import fr.gimlbl.aa.adt.list.LinkedList;
import fr.gimlbl.aa.adt.list.List;
import fr.gimlbl.aa.util.GraphParser;
import fr.gimlbl.aa.util.Util;

import java.io.IOException;

import static fr.gimlbl.aa.util.Util.checkArgs;

public class KruskalM {
    public static void kruskalM(String[] args) throws IOException {
        AdjacencyMatrixGraph adjacencyMatrixGraph = GraphParser.parse(args[0], new AdjacencyMatrixGraph.Builder());

        long ft = System.currentTimeMillis();

        List<ConnectedComponent> connectedComponentList = adjacencyMatrixGraph.getAllConnectedComponent();

        Iterator<ConnectedComponent> iterator = connectedComponentList.iterator();

        List<Pair<List<Edge>, Integer>>allAbr = new LinkedList<>();

        while(iterator.hasNext()){
            allAbr.addToTail(
                    Kruskal.kruskal(
                            iterator.next(),
                            new AdjacencyMatrixGraph.Builder(),
                            adjacencyMatrixGraph.vertexCount()
                    )
            );
        }

        long st = System.currentTimeMillis();

        Util.printUsage(allAbr, args.length == 2 ? args[1] : null, st - ft);
    }

    public static void main(String[] args) throws IOException {
        checkArgs(args);
        kruskalM(args);
    }
}
