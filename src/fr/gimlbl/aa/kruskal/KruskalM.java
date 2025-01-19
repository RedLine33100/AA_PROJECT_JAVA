package fr.gimlbl.aa.kruskal;

import fr.gimlbl.aa.adt.Iterator;
import fr.gimlbl.aa.adt.Pair;
import fr.gimlbl.aa.adt.graph.AdjacencyMatrixGraph;
import fr.gimlbl.aa.adt.graph.Edge;
import fr.gimlbl.aa.adt.graph.WeightedGraph;
import fr.gimlbl.aa.adt.list.List;

import java.io.IOException;

import static fr.gimlbl.aa.kruskal.Util.checkArgs;

public class KruskalM {
    public KruskalM(String[] args) throws IOException {
        AdjacencyMatrixGraph adjacencyMatrixGraph = GraphParser.parse(args[0], new AdjacencyMatrixGraph.Builder());

        if (adjacencyMatrixGraph.isConnexe()) {
            Kruskal.kruskal(adjacencyMatrixGraph, new AdjacencyMatrixGraph.Builder(), Integer.parseInt(args[1]));
        }else{
            List<List<Integer>> listList = adjacencyMatrixGraph.getAllSubComponent();
            Iterator<List<Integer>> iterator = listList.iterator();
            while(iterator.hasNext()){
                List<Integer> list = iterator.next();
                Kruskal.kruskal(adjacencyMatrixGraph, new AdjacencyMatrixGraph.Builder(), list.getElementByPosition(1));
            }
        }

    }
    /*
    public KruskalM(String[] args) throws IOException {
        AdjacencyMatrixGraph adjacencyMatrixGraph = GraphParser.parse(args[0], new AdjacencyMatrixGraph.Builder());

        List<Pair<List<Integer>, Integer>> listKruskal = new LinkedList<>();

        int count1 = 1;
        if (adjacencyMatrixGraph.isConnexe()) {
            listKruskal.addToTail(this.kruskalOnVertex(adjacencyMatrixGraph, new LinkedList<>(), adjacencyMatrixGraph.vertexCount(), Integer.parseInt(args[1]), 0));
        }else{
            List<List<Integer>> listList = adjacencyMatrixGraph.getAllSubComponent();
            Iterator<List<Integer>> iterator = listList.iterator();
            while(iterator.hasNext()){
                List<Integer> list = iterator.next();
                System.out.println("ABR: "+count1+": "+list);
                listKruskal.addToTail(this.kruskalOnVertex(adjacencyMatrixGraph, new LinkedList<>(), list.size(), list.getElementByPosition(1), 0));
                count1++;
            }
        }

        System.out.println(listKruskal.size() == 1 ? "Arbre CONNEXE" : "Arbre non Connexe: "+listKruskal.size());
        Iterator<Pair<List<Integer>, Integer>> iterator = listKruskal.iterator();

        int count = 1;

        while(iterator.hasNext()){
            Pair<List<Integer>, Integer> pair = iterator.next();
            System.out.println("Arbre "+count);

            if(pair == null){
                System.out.println("Pas de kruskal");
            }else System.out.println(pair.getSecond());

            count++;
        }
    }
    */

    public Pair<List<Integer>, Integer> kruskalOnVertex(WeightedGraph weightedGraph, List<Integer> visitedVertex, int numberVertex, int toVisitVertex, int sumWeight){
        visitedVertex.addToTail(toVisitVertex);
        if(visitedVertex.size() == numberVertex)
            return new Pair<>(visitedVertex, sumWeight);
        Iterator<Edge> edgeList = weightedGraph.getIncidentEdges(toVisitVertex).iterator();
        while (edgeList.hasNext()){
            Edge edge = edgeList.next();
            int opposite = edge.getOppositeVertex(toVisitVertex);
            if(visitedVertex.hasValue(opposite))
                continue;

            Pair<List<Integer>, Integer> pair = kruskalOnVertex(weightedGraph, visitedVertex.clone(),numberVertex, opposite, sumWeight+edge.getWeight());
            if(pair != null)
                return pair;
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        checkArgs(args);
        new KruskalM(args);
    }
}
