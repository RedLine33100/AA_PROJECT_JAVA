package fr.gimlbl.aa.kruskal;

import fr.gimlbl.aa.adt.Compare;
import fr.gimlbl.aa.adt.Iterator;
import fr.gimlbl.aa.adt.Pair;
import fr.gimlbl.aa.adt.graph.Edge;
import fr.gimlbl.aa.adt.list.LinkedList;
import fr.gimlbl.aa.adt.list.List;

public class Util {
    public static void checkArgs(String[] args) {
        if (args.length < 2 || args.length > 3) {
            System.err.println("Arguments attendus :\n" +
                    "* Nom du fichier contenant la description du graphe\n" +
                    "* Sommet de départ à partir duquel l'arbre recouvrant de poids minimum est calculé\n" +
                    "* Nom de fichier où écrire le résultat (facultatif)");
        }
    }

    public static void printUsage(List<Pair<List<Edge>, Integer>> toPrint) {
        if(toPrint.size() == 1){
            System.out.println("LE GRAPHE EST CONNEXE");
        }else{
            System.out.println("LE GRAPHE N'EST PAS CONNEXE");
        }

        Iterator<Pair<List<Edge>, Integer>> it = toPrint.iterator();
        int counter = 1;
        System.out.print("Coût");
        while(it.hasNext()){
            Pair<List<Edge>, Integer> pair = it.next();
            System.out.print(" Arbre "+counter+": "+pair.getSecond());
            counter++;
        }

        System.out.println();

        Iterator<Pair<List<Edge>, Integer>> it2 = toPrint.iterator();
        int counter2 = 1;
        while(it2.hasNext()){
            Pair<List<Edge>, Integer> pair = it2.next();
            List<Edge> unorderList = pair.getFirst();

            System.out.println("Arbre "+counter2+") ");

            // Sort like asked
            Compare<Edge, Edge> order = new Compare<Edge, Edge>() {
                @Override
                public boolean compare(Edge edge, Edge edge2) {
                    if(edge.getVertex1() > edge2.getVertex1())
                        return true;
                    return edge.getVertex2() >= edge2.getVertex2();
                }
            };

            List<Edge> sortedList = new LinkedList<>();
            Iterator<Edge> edgeIterator = unorderList.iterator();
            while(edgeIterator.hasNext()){
                sortedList.addElement(edgeIterator.next(), order);
            }

            Iterator<Edge> edgeIterator2 = sortedList.iterator();
            while(edgeIterator2.hasNext()){
                Edge edge = edgeIterator2.next();
                System.out.println("     ("+edge.getVertex1()+" -> "+edge.getVertex2()+" : "+edge.getWeight()+")");
            }

            counter2++;
        }

    }

}
