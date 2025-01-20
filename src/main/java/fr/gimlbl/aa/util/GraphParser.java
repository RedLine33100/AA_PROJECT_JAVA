package fr.gimlbl.aa.util;

import fr.gimlbl.aa.adt.graph.WeightedGraph;
import fr.gimlbl.aa.adt.graph.GraphBuilder;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class GraphParser {
    /**
     * Parses a file describing a weighted graph.
     *
     * <p>Format: <code>n u1 v1 w1 v2 w2 ... 0 u2 v3 w3 ...</code>, where <code>n</code> is the number of vertices,
     * <code>v1, v2 ...</code> are adjacent to <code>u1</code>, <code>w1</code> is the weight of the edge linking
     * <code>u1</code> to <code>v1</code>. 0 separates <code>u1</code> from <code>u2</code>.</p>
     *
     * @param filename Name of the file describing the graph
     * @param graphBuilder Graph builder instance, required to instantiate the graph
     * @return Parsed graph
     * @param <G> Type of the graph (class which implements {@link WeightedGraph})
     * @throws IOException if failure while reading the file
     * @throws RuntimeException if the file doesn't have the required format
     */
    public static <G extends WeightedGraph> G parse(String filename, GraphBuilder<G> graphBuilder)
            throws IOException, RuntimeException {
        try (Scanner scanner = new Scanner(new File(filename))) {
            G graph = graphBuilder.createGraph(scanner.nextInt());

            while (scanner.hasNextInt()) {
                int vertex1 = scanner.nextInt();

                int vertex2 = scanner.nextInt();
                while (vertex2 != 0) {
                    int weight = scanner.nextInt();
                    if (weight == 0) {
                        throw new IllegalArgumentException("Poids nul");
                    }

                    graph.addEdge(vertex1, vertex2, weight);

                    vertex2 = scanner.nextInt();
                }
            }

            return graph;
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier " + filename);
            e.printStackTrace();
            throw e;
        } catch (NoSuchElementException | IllegalArgumentException e) {
            System.err.println("Le fichier n'a pas le format attendu.");
            throw e;
        }
    }

    /**
     * Parses a string describing a weighted graph.
     *
     * <p>Format: <code>n u1 v1 w1 v2 w2 ... 0 u2 v3 w3 ...</code>, where <code>n</code> is the number of vertices,
     * <code>v1, v2 ...</code> are adjacent to <code>u1</code>, <code>w1</code> is the weight of the edge linking
     * <code>u1</code> to <code>v1</code>. 0 separates <code>u1</code> from <code>u2</code>.</p>
     *
     * @param graphBuilder Graph builder instance, required to instantiate the graph
     * @return Parsed graph
     * @param <G> Type of the graph (class which implements {@link WeightedGraph})
     * @throws RuntimeException if the string doesn't have the required format
     */
    public static <G extends WeightedGraph> G parseString(String value, GraphBuilder<G> graphBuilder)
            throws RuntimeException {
        try (Scanner scanner = new Scanner(value)) {
            G graph = graphBuilder.createGraph(scanner.nextInt());

            while (scanner.hasNextInt()) {
                int vertex1 = scanner.nextInt();

                int vertex2 = scanner.nextInt();
                while (vertex2 != 0) {
                    int weight = scanner.nextInt();
                    if (weight == 0) {
                        throw new IllegalArgumentException("Poids nul");
                    }

                    graph.addEdge(vertex1, vertex2, weight);

                    vertex2 = scanner.nextInt();
                }
            }

            return graph;
        } catch (NoSuchElementException | IllegalArgumentException e) {
            System.err.println("Le fichier n'a pas le format attendu.");
            throw e;
        }
    }
}
