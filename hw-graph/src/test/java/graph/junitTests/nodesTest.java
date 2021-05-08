package graph.junitTests;
import graph.Nodes;
import org.junit.Test;

public class nodesTest {
    @Test
    public void testNodesConstruct(){
        new Nodes<>("a");
    }
    @Test
    public void testNodesConstructNull(){
        new Nodes<>(null);
    }
    @Test
    public void testEdgeConstruct(){
        new Nodes<>("a", "b", "ab");
    }
    @Test
    public void testEdgeConstructNull(){
        new Nodes<>(null, "b", "ab");
    }
    @Test
    public void testEdgeConstructNoEdgeName(){
        new Nodes<>("a", "b", null);
    }
}
