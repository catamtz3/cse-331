package graph;

import java.util.*;

/**
 * This class represents a mutable graph
 */

public class MapClass<initial, secondary, edgeLabel> {
    // Rep Invariant:
    // nodes != null, edges != null, graph != null
    // if a node is included in an edge, the node must exist

    // Abstract Function:
    // empty graph: []
    // graph with nodes [a, b, c, ...]
    // graph with nodes and edges [a[ab ac] b[ba] ...]

    private final Map<initial, Set<Nodes<initial, secondary, edgeLabel>>> graphSet;

    /**
     *
     *
     * @spec.requires graph != null, nodes != null, edges !=null
     * @spec.modifies this
     * @spec.effects implements nodes into the graph and creates edges between them
     */

    /**
     * Constructs the initial graph
     * @spec.effects creates an empty graph
     */
    public MapClass() {
        graphSet = new HashMap<>();
        checkRep();
    }

    /**
     * Returns the size of the map (number of nodes in the map)
     * @return number of nodes in the map
     */
    public int size(){
        return graphSet.size();
    }

    /**
     * Adds a node to the graph
     * @param a String node that will be added to the graph
     * @spec.effects adds node to the existing graph
     * @spec.modifies this
     */
    public void addNode(initial a) {
        if (!graphSet.containsKey(a) && a != null) {
            graphSet.put(a, new HashSet<>());
            checkRep();
        }
    }

    /**
     * Removes a node from the graph
     * @param a string that matches a node in the graph
     * @spec.effects removes an existing node from the graph
     * @spec.modifies this
     * @return true if node was successfully removed
     */
    public boolean removeNode(initial a) {
        if (a!= null && graphSet.containsKey(a)) {
            graphSet.remove(a, graphSet.get(a));
            checkRep();
            return true;
        }
        return false;
    }


    /**
     * creates an edge between two existing nodes
     * @param a node that will be the parent node
     * @param b node that will be the child of the initial
     * @param labels the edge that will link these two nodes together
     * @spec.effects builds an edge with a specific label between two nodes in the graph
     * @spec.modifies this
     * @return true if edge is added
     *
     */
    public boolean addEdge(initial a, secondary b, edgeLabel labels){
        if (a != null && b != null && graphSet.containsKey(a) && graphSet.containsKey(b)){
            HashSet<Nodes<initial, secondary, edgeLabel>> getEdge = new HashSet<>(graphSet.get(a));
            Nodes<initial, secondary, edgeLabel> edge = new Nodes<>(a, b, labels);
            if (!getEdge.contains(edge)){
                graphSet.get(a).add(edge);
                checkRep();
                return true;
            }
        }
        return false;
    }

    /**
     * Determines if a given node exists in the map
     * @param a node
     * @spec.requires node a exists
     * @return true if node exists in the map
     *
     */
    public boolean contains(initial a){
        if (a != null) {
            checkRep();
            return graphSet.containsKey(a);
        }
        return false;
    }

    /**
     * returns the children of a given parent node
     * @param a node
     * @spec.requires node a exists
     * @return children nodes of the specified node
     */
    public String getChildren(initial a) {
        if (a != null && graphSet.containsKey(a)) {
            String getEdge = graphSet.get(a).toString();
            checkRep();
            return getEdge;
        }
        return null;
    }

    /**
     * returns a set of all the nodes in the graph
     * @return the nodes in the graph
     */
    public String listNodes(){
        String result =  "";
        for (Map.Entry<initial, Set<Nodes<initial, secondary, edgeLabel>>> entry : graphSet.entrySet()) {
            result += entry.getKey() + " ";
        }
        return result;
    }

    /**
     * returns a set of the elements contained in the graph
     * @return set of elements in the graph
     */
    public Set<Map.Entry<initial, Set<Nodes<initial, secondary, edgeLabel>>>> entrySet(){
        return graphSet.entrySet();
    }

    /**
     * returns string representation of the graph
     * @return string representation of the graph
     */
    public String toString(){
        return graphSet.toString();
    }

    // ensures that the rep invariant hasn't been exposed
    private void checkRep(){
        assert this.graphSet != null;
        Set<initial> initialNodes = graphSet.keySet();
        for (initial j : initialNodes) {
            if (j == null) {
                throw new IllegalArgumentException();
            }
        }
    }
}