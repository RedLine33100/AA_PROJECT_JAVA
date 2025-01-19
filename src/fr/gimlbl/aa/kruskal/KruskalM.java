package fr.gimlbl.aa.kruskal;

import fr.gimlbl.aa.adt.Iterator;
import fr.gimlbl.aa.adt.Pair;
import fr.gimlbl.aa.adt.graph.AdjacencyMatrixGraph;
import fr.gimlbl.aa.adt.graph.Edge;
import fr.gimlbl.aa.adt.list.List;
import fr.gimlbl.aa.adt.list.ListInstance;

import java.io.IOException;

import static fr.gimlbl.aa.kruskal.Util.checkArgs;

public class KruskalM {
    public KruskalM(String[] args) throws IOException {
        AdjacencyMatrixGraph adjacencyMatrixGraph = GraphParser.parse(args[0], new AdjacencyMatrixGraph.Builder());

        List<Pair<List<Integer>, Integer>> listKruskal = new ListInstance<>();

        int count1 = 1;
        if (adjacencyMatrixGraph.isConnexe()) {
            listKruskal.addToTail(this.kruskalOnVertex(adjacencyMatrixGraph, new ListInstance<>(), adjacencyMatrixGraph.vertexCount(), Integer.parseInt(args[1]), 0));
        }else{
            List<List<Integer>> listList = adjacencyMatrixGraph.getSubConnexeAbr();
            Iterator<List<Integer>> iterator = listList.iterator();
            while(iterator.hasNext()){
                List<Integer> list = iterator.next();
                System.out.println("ABR: "+count1+": "+list);
                listKruskal.addToTail(this.kruskalOnVertex(adjacencyMatrixGraph, new ListInstance<>(), list.size(), list.getElementByPosition(1), 0));
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

    public Pair<List<Integer>, Integer> kruskalOnVertex(AdjacencyMatrixGraph adjacencyMatrixGraph, List<Integer> visitedVertex, int numberVertex, int toVisitVertex, int sumWeight){
        visitedVertex.addToTail(toVisitVertex);
        if(visitedVertex.size() == numberVertex)
            return new Pair<>(visitedVertex, sumWeight);
        Iterator<Edge> edgeList = adjacencyMatrixGraph.getIncidentEdges(toVisitVertex).iterator();
        while (edgeList.hasNext()){
            Edge edge = edgeList.next();
            int opposite = edge.getOppositeVertex(toVisitVertex);
            if(visitedVertex.hasValue(opposite))
                continue;

            Pair<List<Integer>, Integer> pair = kruskalOnVertex(adjacencyMatrixGraph, visitedVertex.clone(),numberVertex, opposite, sumWeight+edge.getWeight());
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
