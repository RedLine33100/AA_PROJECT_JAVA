package fr.gimlbl.aa.util;

import fr.gimlbl.aa.adt.ConnectedComponent;
import fr.gimlbl.aa.adt.Iterator;
import fr.gimlbl.aa.adt.Pair;
import fr.gimlbl.aa.adt.graph.AdjacencyListGraph;
import fr.gimlbl.aa.adt.graph.AdjacencyMatrixGraph;
import fr.gimlbl.aa.adt.graph.Edge;
import fr.gimlbl.aa.adt.list.LinkedList;
import fr.gimlbl.aa.adt.list.List;
import fr.gimlbl.aa.kruskal.Kruskal;

public class PerformanceTest {

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        List<Integer> integerList = new LinkedList<>();
        integerList.addToTail(5);
        integerList.addToTail(10);
        integerList.addToTail(15);
        integerList.addToTail(20);
        integerList.addToTail(25);
        integerList.addToTail(30);
        integerList.addToTail(50);
        integerList.addToTail(100);
        integerList.addToTail(150);
        integerList.addToTail(200);
        integerList.addToTail(250);

        List<Integer> averageList = new LinkedList<>();
        averageList.addToTail(10);
        averageList.addToTail(30);
        averageList.addToTail(50);
        averageList.addToTail(80);


        Iterator<Integer> iterator = integerList.iterator();
        while (iterator.hasNext()) {
            subTest(iterator.next(), averageList);
        }
    }

    private static void subTest(int numberVertices, List<Integer> averageList) {
        System.out.println("====================");
        Iterator<Integer> iterator = averageList.iterator();

        long sumTimeMatrix = 0;
        long sumTimeList = 0;
        while (iterator.hasNext()) {
            Integer average = iterator.next();
            String[] args = new String[2];
            args[0] = String.valueOf(numberVertices);
            args[1] = String.valueOf(average);
            String graph = GraphGenerator.generateRandomGraph(args);


            System.out.println("Graph test: number vertices: "+numberVertices+" avg: "+average);

            {
                AdjacencyMatrixGraph adjacencyMatrixGraph = GraphParser.parseString(graph, new AdjacencyMatrixGraph.Builder());
                long startTime = System.nanoTime();
                List<ConnectedComponent> connectedComponentList = adjacencyMatrixGraph.getAllConnectedComponent();

                Iterator<ConnectedComponent> componentIterator = connectedComponentList.iterator();

                List<Pair<List<Edge>, Integer>> allAbr = new LinkedList<>();

                while (componentIterator.hasNext()) {
                    allAbr.addToTail(Kruskal.kruskal(componentIterator.next(), new AdjacencyMatrixGraph.Builder(), adjacencyMatrixGraph.vertexCount()));
                }
                long endTime = System.nanoTime();
                long elapsedTime = endTime - startTime;
                System.out.println("Elapsed time for matrix: "+elapsedTime+" ns");
                sumTimeMatrix+=elapsedTime;
                System.out.println("Sum elapsed time for matrix: "+sumTimeMatrix+" ns");
            }
            {
                AdjacencyListGraph adjacencyListGraph = GraphParser.parseString(graph, new AdjacencyListGraph.Builder());
                long startTime = System.nanoTime();
                List<ConnectedComponent> connectedComponentList = adjacencyListGraph.getAllConnectedComponent();

                Iterator<ConnectedComponent> componentIterator = connectedComponentList.iterator();

                List<Pair<List<Edge>, Integer>> allAbr = new LinkedList<>();

                while (componentIterator.hasNext()) {
                    allAbr.addToTail(Kruskal.kruskal(componentIterator.next(), new AdjacencyListGraph.Builder(), adjacencyListGraph.vertexCount()));
                }
                long endTime = System.nanoTime();
                long elapsedTime = endTime - startTime;
                System.out.println("Elapsed time for list: "+elapsedTime+" ns");
                sumTimeList += elapsedTime;
                System.out.println("Sum elapsed time for list: "+sumTimeList+" ns");
            }

            System.out.println("-----");

        }

        System.out.println("Total time for matrix: "+sumTimeMatrix+" ns");
        System.out.println("Total time for list: "+sumTimeList+" ns");

        System.out.println("====================");
    }

}
