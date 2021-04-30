package graph.junitTests;

import graph.mapClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class mapClassTest {

    private final String nodes_a = "a";
    private final String nodes_b = "b";
    private final String edge_aa = "aa";
    private final String edge_ab = "ab";
    private final String edge_ba = "ba";

    @Test
    public void testSizeEmptyGraph(){
        assertEquals(0, mapClass.size());
    }
    @Test
    public void toStringEmptyGraph(){
        assertToStringWorks("");
    }
    @Test
    public void addOneNode(){
        mapClass.addNode(nodes_a);
    }
    @Test
    public void addNullNode(){
        mapClass.addNode(null);
    }
    @Test
    public void addTwoNodes(){
        mapClass.addNode(nodes_a);
        mapClass.addNode(nodes_b);
    }
    @Test
    public void toStringOneNode(){
        addOneNode();
        assertToStringWorks("a");
    }

    private void assertToStringWorks(String s) {
        assertEquals(s, s.valueOf(s).toString());
    }

    @Test
    public void toStringNodeAndEdge(){
        addEdgeSameNode();
        assertToStringWorks("a[aa]");
    }
    @Test
    public void toStringTwoNodes(){
        addTwoNodes();
        assertToStringWorks("a, b");
    }
    @Test
    public void toStringTwoNodesAndEdge(){
        addEdgeCorrect();
        assertToStringWorks("a[ab], b");
    }
    @Test
    public void toStringTwoNodesTwoEdges(){
        addTwoEdges();
        assertToStringWorks("a[aa, ab], b");
    }
    @Test
    public void addEdgeEmptyGraph(){
        mapClass.addEdge(nodes_a, nodes_b, edge_ab);
    }
    @Test
    public void addEdgeNullNode(){
        mapClass.addEdge(null, nodes_b, edge_ab);
    }
    @Test
    public void addEdgeBothNodesNull(){
        mapClass.addEdge(null, null, edge_ab);
    }
    @Test
    public void addEdgeCorrect(){
        addTwoNodes();
        mapClass.addEdge(nodes_a, nodes_b, edge_ab);
    }
    @Test
    public void addEdgeSameNode(){
        addOneNode();
        mapClass.addEdge(nodes_a, nodes_a, edge_aa);
    }
    @Test
    public void addTwoEdges(){
        addTwoNodes();
        mapClass.addEdge(nodes_a, nodes_b, edge_ab);
        mapClass.addEdge(nodes_a, nodes_a, edge_aa);
    }
    @Test
    public void removeEdgeEmptyGraph(){
        assertFalse(mapClass.removeEdge(edge_ba));
    }
    @Test
    public void removeEdgeCorrect(){
        addEdgeCorrect();
        assertTrue(mapClass.removeEdge(edge_ab));
    }
    @Test
    public void removeEdgeWrongLabel(){
        addEdgeCorrect();
        assertFalse(mapClass.removeEdge(edge_ba));
    }
    @Test
    public void removeEdgeSameNode(){
        addEdgeSameNode();
        assertTrue(mapClass.removeEdge(edge_aa));
    }
    @Test
    public void getChildrenEmptyGraph(){
        mapClass.getChildren(nodes_a);
    }
    @Test
    public void getChildrenWithNodeNoChildren(){
        addOneNode();
        mapClass.getChildren(nodes_a);
    }
    @Test
    public void getChildrenNodeWithOneChild(){
        addEdgeCorrect();
        mapClass.getChildren(nodes_a);
    }
    @Test
    public void getChildrenNodeWithTwoChildren(){
        addTwoEdges();
        mapClass.getChildren(nodes_a);
    }
    @Test
    public void removeNodeOneNode(){
        addOneNode();
        assertTrue(mapClass.removeNode(nodes_a));
    }
    @Test
    public void removeNodeEmptyGraph(){
        assertFalse(mapClass.removeNode(nodes_a));
    }
    @Test
    public void removeNodeNodeDoesntExist(){
        addOneNode();
        assertFalse(mapClass.removeNode(nodes_b));
    }
    @Test
    public void containsWhenEmpty(){
        assertFalse(mapClass.contains(nodes_a));
    }
    @Test
    public void containsNodeDoesntExist(){
        addOneNode();
        assertFalse(mapClass.contains(nodes_b));
    }
    @Test
    public void containsCorrectNode(){
        addOneNode();
        assertTrue(mapClass.contains(nodes_a));
    }
    @Test
    public void findEdgeCorrect(){
        addEdgeCorrect();
        assertTrue(mapClass.findEdge(edge_ab));
    }
    @Test
    public void findEdgeNoEdgesInGraph(){
        assertFalse(mapClass.findEdge(edge_aa));
    }
    @Test
    public void findEdgeWrongName(){
        addEdgeCorrect();
        assertFalse(mapClass.findEdge(edge_ba));
    }
    @Test
    public void getParentNoParent(){
        addOneNode();
        mapClass.getParent(nodes_a);
    }
    @Test
    public void getParentOneParent(){
        addEdgeCorrect();
        mapClass.getParent(nodes_b);
    }
    @Test
    public void getParentTwoParents(){
        addTwoEdges();
        mapClass.getParent(nodes_a);
    }
    @Test
    public void getParentEmptyGraph(){
        mapClass.getParent(nodes_a);
    }
}
