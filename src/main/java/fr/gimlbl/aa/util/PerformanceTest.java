package fr.gimlbl.aa.util;

import fr.gimlbl.aa.adt.ConnectedComponent;
import fr.gimlbl.aa.adt.Iterator;
import fr.gimlbl.aa.adt.Pair;
import fr.gimlbl.aa.adt.graph.AdjacencyListGraph;
import fr.gimlbl.aa.adt.graph.AdjacencyMatrixGraph;
import fr.gimlbl.aa.adt.graph.Edge;
import fr.gimlbl.aa.adt.list.LinkedList;
import fr.gimlbl.aa.adt.list.List;
import fr.gimlbl.aa.kruskal.GraphParser;
import fr.gimlbl.aa.kruskal.Kruskal;

public class PerformanceTest {

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        List<Integer> integerList = new LinkedList<>();
        integerList.addToTail(5);
        integerList.addToTail(10);
        integerList.addToTail(20);
        integerList.addToTail(50);

        Iterator<Integer> iterator = integerList.iterator();
        long sumTimeMatrix = 0;
        long sumTimeList = 0;
        while (iterator.hasNext()) {
            int numberVertices = iterator.next();
            String[] args = new String[1];
            args[0] = String.valueOf(numberVertices);
            String graph = GraphGenerator.generateRandomGraph(args);
            System.out.println("Graph test: number vertices: "+numberVertices+" graph:"+graph);
            {
                long startTime = System.currentTimeMillis();
                AdjacencyMatrixGraph adjacencyMatrixGraph = GraphParser.parseString(graph, new AdjacencyMatrixGraph.Builder());

                List<ConnectedComponent> connectedComponentList = adjacencyMatrixGraph.getAllConnectedComponent();

                Iterator<ConnectedComponent> componentIterator = connectedComponentList.iterator();

                List<Pair<List<Edge>, Integer>> allAbr = new LinkedList<>();

                while (componentIterator.hasNext()) {
                    allAbr.addToTail(Kruskal.kruskal(componentIterator.next(), new AdjacencyMatrixGraph.Builder(), adjacencyMatrixGraph.vertexCount()));
                }
                long endTime = System.currentTimeMillis();
                long elapsedTime = endTime - startTime;
                System.out.println("Elapsed time for matrix: "+elapsedTime+" ms");
                sumTimeMatrix+=elapsedTime;
                System.out.println("Sum elapsed time for matrix: "+sumTimeMatrix+" ms");
            }
            {
                long startTime = System.currentTimeMillis();
                AdjacencyListGraph adjacencyListGraph = GraphParser.parseString(graph, new AdjacencyListGraph.Builder());

                List<ConnectedComponent> connectedComponentList = adjacencyListGraph.getAllConnectedComponent();

                Iterator<ConnectedComponent> componentIterator = connectedComponentList.iterator();

                List<Pair<List<Edge>, Integer>> allAbr = new LinkedList<>();

                while (componentIterator.hasNext()) {
                    allAbr.addToTail(Kruskal.kruskal(componentIterator.next(), new AdjacencyListGraph.Builder(), adjacencyListGraph.vertexCount()));
                }
                long endTime = System.currentTimeMillis();
                long elapsedTime = endTime - startTime;
                System.out.println("Elapsed time for list: "+elapsedTime+" ms");
                sumTimeList += elapsedTime;
                System.out.println("Sum elapsed time for list: "+sumTimeList+" ms");
            }

        }
        System.out.println("Total elapsed time: Matrix "+sumTimeMatrix+" ms list "+sumTimeList+" ms");
    }

}
