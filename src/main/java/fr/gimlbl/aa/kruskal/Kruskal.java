package fr.gimlbl.aa.kruskal;

import fr.gimlbl.aa.adt.Compare;
import fr.gimlbl.aa.adt.graph.ConnectedComponent;
import fr.gimlbl.aa.adt.Iterator;
import fr.gimlbl.aa.adt.Pair;
import fr.gimlbl.aa.adt.graph.Edge;
import fr.gimlbl.aa.adt.graph.GraphBuilder;
import fr.gimlbl.aa.adt.graph.WeightedGraph;
import fr.gimlbl.aa.adt.list.List;
import fr.gimlbl.aa.adt.list.LinkedList;

public class Kruskal {

    public static <G extends WeightedGraph> Pair<List<Edge>, Integer> kruskal(ConnectedComponent connectedComponent, GraphBuilder<G> graphBuilder, int numberVertexGlobal) {

        List<Edge> edges = connectedComponent.getEdgeList(); // All edges in given subgraph

        // Sorting edges
        List<Edge> orderedEdges = new LinkedList<>();
        {
            Compare<Edge, Edge> compare = (a, b) -> a.getWeight() > b.getWeight();
            Iterator<Edge> edgesIterator = edges.iterator();
            while (edgesIterator.hasNext()) {
                orderedEdges.addElement(edgesIterator.next(), compare); // List already allow to sort at insertion
            }
        }

        // Create a new graph
        int totalWeight = 0;
        G newGraph = graphBuilder.createGraph(numberVertexGlobal);

        List<Integer> parent = new LinkedList<>();
        for (int i = 1; i <= numberVertexGlobal; i++) {
            parent.addToTail(i);
        }

        Iterator<Edge> orderedEdgesIterator = orderedEdges.iterator();
        while (orderedEdgesIterator.hasNext()) {
            Edge e = orderedEdgesIterator.next(); // e = (vertex1, vertex2, weight)
            int firstParent = parent.getElementByPosition(e.getVertex1());
            int secondParent = parent.getElementByPosition(e.getVertex2());
            if (firstParent != secondParent) { // vertex1 and vertex2 are not in the same connected component
                // Union of connected components of vertex1 and vertex2
                int minParent = Math.min(firstParent, secondParent);
                int maxParent = Math.max(firstParent, secondParent);

                // Replace all occurrences of maxParent by minParent
                for (int x = 1; x <= numberVertexGlobal; x++) {
                    if (parent.getElementByPosition(x) == maxParent) {
                        parent.updateElementByPosition(minParent, x);
                    }
                }

                // Add the edge in the new graph
                newGraph.addEdge(e.getVertex1(), e.getVertex2(), e.getWeight());
                totalWeight += e.getWeight();
            }
        }
        return new Pair<>(newGraph.getEdges(), totalWeight);
    }

}
