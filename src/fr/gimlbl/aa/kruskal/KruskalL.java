package fr.gimlbl.aa.kruskal;

import fr.gimlbl.aa.adt.ConnectedComponent;
import fr.gimlbl.aa.adt.Iterator;
import fr.gimlbl.aa.adt.graph.AdjacencyListGraph;
import fr.gimlbl.aa.adt.list.List;

import java.io.IOException;

import static fr.gimlbl.aa.kruskal.Util.checkArgs;

public class KruskalL {

    public KruskalL(String[] args) throws IOException {
        AdjacencyListGraph adjacencyListGraph = GraphParser.parse(args[0], new AdjacencyListGraph.Builder());

        List<ConnectedComponent> connectedComponentList = adjacencyListGraph.getAllConnectedComponent();

        Iterator<ConnectedComponent> iterator = connectedComponentList.iterator();
        while(iterator.hasNext()){
            Kruskal.kruskal2(iterator.next(), new AdjacencyListGraph.Builder(), adjacencyListGraph.vertexCount());
        }

    }

    public static void main(String[] args) throws IOException {
        checkArgs(args);
        new KruskalL(args);
    }
}
