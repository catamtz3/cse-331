package graph.junitTests;

import graph.MapClass;
import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MapClassTest {

    private final String nodes_a = "a";
    private final String nodes_b = "b";
    private final String edge_aa = "aa";
    private final String edge_ab = "ab";
    private final String edge_ba = "ba";

    private MapClass<String, String, String> graphSet;

    @Before
    public void setUp() {
        graphSet = new MapClass<>();
    }

    @Test
    public void testSizeEmptyGraph(){
        Assert.assertEquals(0, graphSet.size());
    }
    @Test
    public void toStringEmptyGraph(){
        assertToStringWorks("");
    }
    @Test
    public void addOneNode(){
        graphSet.addNode(nodes_a);
    }
    @Test
    public void addNullNode(){
        graphSet.addNode(null);
    }
    @Test
    public void addTwoNodes(){
        graphSet.addNode(nodes_a);
        graphSet.addNode(nodes_b);
    }
    @Test
    public void toStringOneNode(){
        addOneNode();
        assertToStringWorks("a");
    }

    private void assertToStringWorks(String s) {
        Assert.assertEquals(s, String.valueOf(s));
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
        graphSet.addEdge(nodes_a, nodes_b, edge_ab);
    }
    @Test
    public void addEdgeNullNode(){
        graphSet.addEdge(null, nodes_b, edge_ab);
    }
    @Test
    public void addEdgeBothNodesNull(){
        graphSet.addEdge(null, null, edge_ab);
    }
    @Test
    public void addEdgeCorrect(){
        addTwoNodes();
        graphSet.addEdge(nodes_a, nodes_b, edge_ab);
    }
    @Test
    public void addEdgeSameNode(){
        addOneNode();
        graphSet.addEdge(nodes_a, nodes_a, edge_aa);
    }
    @Test
    public void addTwoEdges(){
        addTwoNodes();
        graphSet.addEdge(nodes_a, nodes_b, edge_ab);
        graphSet.addEdge(nodes_a, nodes_a, edge_aa);
    }
    @Test
    public void getChildrenEmptyGraph(){
        graphSet.getChildren(nodes_a);
    }
    @Test
    public void getChildrenWithNodeNoChildren(){
        addOneNode();
        graphSet.getChildren(nodes_a);
    }
    @Test
    public void getChildrenNodeWithOneChild(){
        addEdgeCorrect();
        graphSet.getChildren(nodes_a);
    }
    @Test
    public void getChildrenNodeWithTwoChildren(){
        addTwoEdges();
        graphSet.getChildren(nodes_a);
    }
    @Test
    public void removeNodeOneNode(){
        addOneNode();
        assertTrue(graphSet.removeNode(nodes_a));
    }
    @Test
    public void removeNodeEmptyGraph(){
        assertFalse(graphSet.removeNode(nodes_a));
    }
    @Test
    public void removeNodeNodeDoesntExist(){
        addOneNode();
        assertFalse(graphSet.removeNode(nodes_b));
    }
    @Test
    public void containsWhenEmpty(){
        assertFalse(graphSet.contains(nodes_a));
    }
    @Test
    public void containsNodeDoesntExist(){
        addOneNode();
        assertFalse(graphSet.contains(nodes_b));
    }
    @Test
    public void containsCorrectNode(){
        addOneNode();
        assertTrue(graphSet.contains(nodes_a));
    }
    @Test
    public void listNodesOneNode(){
        addOneNode();
        graphSet.listNodes();
    }
    @Test
    public void listNodesEmptyGraph(){
        graphSet.listNodes();
    }
    @Test
    public void listNodesTwoNodes(){
        addTwoNodes();
        graphSet.listNodes();
    }
    @Test
    public void listNodesNullNode(){
        addNullNode();
        graphSet.listNodes();
    }
}

