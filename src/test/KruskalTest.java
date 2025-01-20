package test;

import fr.gimlbl.aa.adt.ConnectedComponent;
import fr.gimlbl.aa.adt.Pair;
import fr.gimlbl.aa.adt.graph.AdjacencyListGraph;
import fr.gimlbl.aa.adt.graph.Edge;
import fr.gimlbl.aa.adt.list.List;
import fr.gimlbl.aa.kruskal.GraphParser;
import fr.gimlbl.aa.kruskal.Kruskal;
import org.junit.Assert;
import org.junit.Test;

public class KruskalTest {

    @Test
    public void testKruskal() {
        try {
            AdjacencyListGraph adjacencyListGraph = GraphParser.parse("testfileconnexe.txt", new AdjacencyListGraph.Builder());

            ConnectedComponent connectedComponent = adjacencyListGraph.getConnectedComponent(1);

            Pair<List<Edge>, Integer> pair = Kruskal.kruskal(connectedComponent, new AdjacencyListGraph.Builder(), adjacencyListGraph.edgeCount());
            Assert.assertEquals((Integer) 181, pair.getSecond());
            Assert.assertEquals(5, pair.getFirst().size());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
