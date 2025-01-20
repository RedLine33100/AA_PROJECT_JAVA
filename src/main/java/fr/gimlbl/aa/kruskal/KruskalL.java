package fr.gimlbl.aa.kruskal;

import fr.gimlbl.aa.adt.ConnectedComponent;
import fr.gimlbl.aa.adt.Iterator;
import fr.gimlbl.aa.adt.Pair;
import fr.gimlbl.aa.adt.graph.AdjacencyListGraph;
import fr.gimlbl.aa.adt.graph.Edge;
import fr.gimlbl.aa.adt.list.LinkedList;
import fr.gimlbl.aa.adt.list.List;
import fr.gimlbl.aa.util.Util;

import java.io.IOException;

import static fr.gimlbl.aa.util.Util.checkArgs;

public class KruskalL {

    public static void kruskalL(String[] args) throws IOException {
        long ft = System.currentTimeMillis();
        AdjacencyListGraph adjacencyListGraph = GraphParser.parse(args[0], new AdjacencyListGraph.Builder());

        List<ConnectedComponent> connectedComponentList = adjacencyListGraph.getAllConnectedComponent();

        Iterator<ConnectedComponent> iterator = connectedComponentList.iterator();

        List<Pair<List<Edge>, Integer>>allAbr = new LinkedList<>();

        while(iterator.hasNext()){
            allAbr.addToTail(Kruskal.kruskal(iterator.next(), new AdjacencyListGraph.Builder(), adjacencyListGraph.vertexCount()));
        }

        long st = System.currentTimeMillis();

        Util.printUsage(allAbr, args.length == 3 ? args[2] : null, st-ft);

    }

    public static void main(String[] args) throws IOException {
        checkArgs(args);
        kruskalL(args);
    }
}
