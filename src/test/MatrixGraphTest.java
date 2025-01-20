package test;

import fr.gimlbl.aa.adt.ConnectedComponent;
import fr.gimlbl.aa.adt.graph.AdjacencyMatrixGraph;
import fr.gimlbl.aa.adt.list.List;
import fr.gimlbl.aa.kruskal.GraphParser;
import org.junit.Assert;
import org.junit.Test;

public class MatrixGraphTest {

    @Test
    public void testVertexCount() {
        try {
            AdjacencyMatrixGraph adjacencyMatrixGraph = GraphParser.parse("testfilenc3abr.txt", new AdjacencyMatrixGraph.Builder());

            Assert.assertEquals(10, adjacencyMatrixGraph.vertexCount());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testEdgeCount() {
        try {
            AdjacencyMatrixGraph adjacencyMatrixGraph = GraphParser.parse("testfilenc3abr.txt", new AdjacencyMatrixGraph.Builder());

            Assert.assertEquals(9, adjacencyMatrixGraph.edgeCount());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testIncidentEdge() {
        try {
            AdjacencyMatrixGraph adjacencyMatrixGraph = GraphParser.parse("testfileconnexe.txt", new AdjacencyMatrixGraph.Builder());

            Assert.assertEquals(3, adjacencyMatrixGraph.getIncidentEdges(1).size());
            Assert.assertEquals(2, adjacencyMatrixGraph.getIncidentEdges(2).size());
            Assert.assertEquals(3, adjacencyMatrixGraph.getIncidentEdges(3).size());
            Assert.assertEquals(2, adjacencyMatrixGraph.getIncidentEdges(4).size());
            Assert.assertEquals(2, adjacencyMatrixGraph.getIncidentEdges(5).size());
            Assert.assertEquals(2, adjacencyMatrixGraph.getIncidentEdges(6).size());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testGetEdges() {
        try {
            AdjacencyMatrixGraph adjacencyMatrixGraph = GraphParser.parse("testfilenc3abr.txt", new AdjacencyMatrixGraph.Builder());

            Assert.assertEquals(9, adjacencyMatrixGraph.getEdges().size());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testEdgeWeight() {
        try {
            AdjacencyMatrixGraph adjacencyMatrixGraph = GraphParser.parse("testfilenc3abr.txt", new AdjacencyMatrixGraph.Builder());

            Assert.assertEquals(15, adjacencyMatrixGraph.edgeWeight(2, 6));
            Assert.assertEquals(5, adjacencyMatrixGraph.edgeWeight(5, 9));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testAddEdges() {
        try {
            AdjacencyMatrixGraph adjacencyMatrixGraph = GraphParser.parse("testfilenc3abr.txt", new AdjacencyMatrixGraph.Builder());

            adjacencyMatrixGraph.addEdge(1, 7, 8);

            Assert.assertEquals(8, adjacencyMatrixGraph.edgeWeight(1, 7));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testIsConnexe() {
        try {
            AdjacencyMatrixGraph adjacencyMatrixGraph = GraphParser.parse("testfilenc3abr.txt", new AdjacencyMatrixGraph.Builder());

            Assert.assertFalse(adjacencyMatrixGraph.isConnexe());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            AdjacencyMatrixGraph adjacencyMatrixGraph = GraphParser.parse("testfileconnexe.txt", new AdjacencyMatrixGraph.Builder());

            Assert.assertTrue(adjacencyMatrixGraph.isConnexe());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testConnectedComponent() {
        try {
            AdjacencyMatrixGraph adjacencyMatrixGraph = GraphParser.parse("testfilenc3abr.txt", new AdjacencyMatrixGraph.Builder());

            ConnectedComponent connectedComponent = adjacencyMatrixGraph.getConnectedComponent(3);

            Assert.assertEquals(4, connectedComponent.getVertexList().size());
            Assert.assertEquals(4, connectedComponent.getEdgeList().size());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testGetAllConnectedComponents() {
        try {
            AdjacencyMatrixGraph adjacencyMatrixGraph = GraphParser.parse("testfilenc3abr.txt", new AdjacencyMatrixGraph.Builder());

            List<ConnectedComponent> connectedComponentList = adjacencyMatrixGraph.getAllConnectedComponent();

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
