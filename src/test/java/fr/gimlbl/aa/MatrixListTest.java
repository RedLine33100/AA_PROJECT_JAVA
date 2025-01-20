package fr.gimlbl.aa;

import fr.gimlbl.aa.adt.ConnectedComponent;
import fr.gimlbl.aa.adt.graph.AdjacencyListGraph;
import fr.gimlbl.aa.adt.list.List;
import fr.gimlbl.aa.kruskal.GraphParser;
import org.junit.Assert;
import org.junit.Test;

public class MatrixListTest {

    @Test
    public void testVertexCount() {
        try {
            AdjacencyListGraph adjacencyListGraph = GraphParser.parse("testfilenc3abr.txt", new AdjacencyListGraph.Builder());

            Assert.assertEquals(10, adjacencyListGraph.vertexCount());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testEdgeCount() {
        try {
            AdjacencyListGraph adjacencyListGraph = GraphParser.parse("testfilenc3abr.txt", new AdjacencyListGraph.Builder());

            Assert.assertEquals(9, adjacencyListGraph.edgeCount());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testIncidentEdge() {
        try {
            AdjacencyListGraph adjacencyListGraph = GraphParser.parse("testfileconnexe.txt", new AdjacencyListGraph.Builder());

            Assert.assertEquals(3, adjacencyListGraph.getIncidentEdges(1).size());
            Assert.assertEquals(2, adjacencyListGraph.getIncidentEdges(2).size());
            Assert.assertEquals(3, adjacencyListGraph.getIncidentEdges(3).size());
            Assert.assertEquals(2, adjacencyListGraph.getIncidentEdges(4).size());
            Assert.assertEquals(2, adjacencyListGraph.getIncidentEdges(5).size());
            Assert.assertEquals(2, adjacencyListGraph.getIncidentEdges(6).size());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testGetEdges() {
        try {
            AdjacencyListGraph adjacencyListGraph = GraphParser.parse("testfilenc3abr.txt", new AdjacencyListGraph.Builder());

            Assert.assertEquals(9, adjacencyListGraph.getEdges().size());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testEdgeWeight() {
        try {
            AdjacencyListGraph adjacencyListGraph = GraphParser.parse("testfilenc3abr.txt", new AdjacencyListGraph.Builder());

            Assert.assertEquals(15, adjacencyListGraph.edgeWeight(2, 6));
            Assert.assertEquals(5, adjacencyListGraph.edgeWeight(5, 9));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testAddEdges() {
        try {
            AdjacencyListGraph adjacencyListGraph = GraphParser.parse("testfilenc3abr.txt", new AdjacencyListGraph.Builder());

            adjacencyListGraph.addEdge(1, 7, 8);

            Assert.assertEquals(8, adjacencyListGraph.edgeWeight(1, 7));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testIsConnexe() {
        try {
            AdjacencyListGraph adjacencyListGraph = GraphParser.parse("testfilenc3abr.txt", new AdjacencyListGraph.Builder());

            Assert.assertFalse(adjacencyListGraph.isConnected());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            AdjacencyListGraph adjacencyListGraph = GraphParser.parse("testfileconnexe.txt", new AdjacencyListGraph.Builder());

            Assert.assertTrue(adjacencyListGraph.isConnected());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testConnectedComponent() {
        try {
            AdjacencyListGraph adjacencyListGraph = GraphParser.parse("testfilenc3abr.txt", new AdjacencyListGraph.Builder());

            ConnectedComponent connectedComponent = adjacencyListGraph.getConnectedComponent(3);

            Assert.assertEquals(4, connectedComponent.getVertexList().size());
            Assert.assertEquals(4, connectedComponent.getEdgeList().size());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testGetAllConnectedComponents() {
        try {
            AdjacencyListGraph adjacencyListGraph = GraphParser.parse("testfilenc3abr.txt", new AdjacencyListGraph.Builder());

            List<ConnectedComponent> connectedComponentList = adjacencyListGraph.getAllConnectedComponent();

            ConnectedComponent firstCo = connectedComponentList.getElementByPosition(1);
            Assert.assertEquals(5, firstCo.getVertexList().size());
            Assert.assertEquals(5, firstCo.getEdgeList().size());
            ConnectedComponent secondComponent = connectedComponentList.getElementByPosition(2);
            Assert.assertEquals(4, secondComponent.getVertexList().size());
            Assert.assertEquals(4, secondComponent.getEdgeList().size());
            ConnectedComponent thirdComponent = connectedComponentList.getElementByPosition(3);
            Assert.assertEquals(1, thirdComponent.getVertexList().size());
            Assert.assertEquals(0, thirdComponent.getEdgeList().size());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }



}
