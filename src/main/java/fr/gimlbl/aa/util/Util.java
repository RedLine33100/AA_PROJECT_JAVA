package fr.gimlbl.aa.util;

import fr.gimlbl.aa.adt.Compare;
import fr.gimlbl.aa.adt.Iterator;
import fr.gimlbl.aa.adt.Pair;
import fr.gimlbl.aa.adt.graph.Edge;
import fr.gimlbl.aa.adt.list.LinkedList;
import fr.gimlbl.aa.adt.list.List;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Util {
    public static void checkArgs(String[] args) {
        if (args.length < 2 || args.length > 3) {
            System.err.println("Arguments attendus :\n" +
                    "* Nom du fichier contenant la description du graphe\n" +
                    "* Sommet de départ à partir duquel l'arbre recouvrant de poids minimum est calculé\n" +
                    "* Nom de fichier où écrire le résultat (facultatif)");
        }
    }

    public static void writeLog(String log, FileWriter fw) throws IOException {
        if(fw != null)
            fw.write(log);
        System.out.print(log);
    }

    public static void printUsage(List<Pair<List<Edge>, Integer>> toPrint, String fileName, long execTime) {
        try {
            FileWriter writer = null;
            if(fileName != null) {
                File file = new File(fileName);
                if (!file.exists())
                    if(!file.createNewFile())
                        throw new IOException("Erreur du fichier de l'utilisateur");

                writer = new FileWriter(file);
            }
            if (toPrint.size() == 1) {
                writeLog("LE GRAPHE EST CONNEXE\n", writer);
            } else {
                writeLog("LE GRAPHE N'EST PAS CONNEXE\n", writer);
            }

            Iterator<Pair<List<Edge>, Integer>> it = toPrint.iterator();
            int counter = 1;
            writeLog("Coût", writer);
            while (it.hasNext()) {
                Pair<List<Edge>, Integer> pair = it.next();
                writeLog(" Arbre " + counter + ": " + pair.getSecond(), writer);
                counter++;
            }

            writeLog("\n", writer);

            Iterator<Pair<List<Edge>, Integer>> it2 = toPrint.iterator();
            int counter2 = 1;
            while (it2.hasNext()) {
                Pair<List<Edge>, Integer> pair = it2.next();
                List<Edge> unorderList = pair.getFirst();

                writeLog("Arbre " + counter2 + ") \n", writer);

                // Sort like asked
                Compare<Edge, Edge> order = new Compare<Edge, Edge>() {
                    @Override
                    public boolean compare(Edge edge, Edge edge2) {
                        if (edge.getVertex1() > edge2.getVertex1())
                            return true;
                        return edge.getVertex2() >= edge2.getVertex2();
                    }
                };

                List<Edge> sortedList = new LinkedList<>();
                Iterator<Edge> edgeIterator = unorderList.iterator();
                while (edgeIterator.hasNext()) {
                    sortedList.addElement(edgeIterator.next(), order);
                }

                Iterator<Edge> edgeIterator2 = sortedList.iterator();
                while (edgeIterator2.hasNext()) {
                    Edge edge = edgeIterator2.next();
                    writeLog("     (" + edge.getVertex1() + " -> " + edge.getVertex2() + " : " + edge.getWeight() + ")\n", writer);
                }
                writeLog("\n", writer);
                counter2++;
            }

            writeLog("Temps d'execution: " + execTime + "ms environ "+execTime/1000+"sec\n", writer);

            if(writer != null)
                writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
