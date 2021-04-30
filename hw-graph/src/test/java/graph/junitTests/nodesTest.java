package graph.junitTests;
import graph.nodes;
import org.junit.Test;
public class nodesTest {

    @Test
    public void testNodesConstruct(){
        new nodes("a");
    }
    @Test
    public void testNodesConstructNull(){
        new nodes(null);
    }
    @Test
    public void testEdgeConstruct(){
        new nodes("a", "b", "ab");
    }
    @Test
    public void testEdgeConstructNull(){
        new nodes(null, "b", "ab");
    }
    @Test
    public void testEdgeConstructNoEdgeName(){
        new nodes("a", "b", "ab");
    }
}
