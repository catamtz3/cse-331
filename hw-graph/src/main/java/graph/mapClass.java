package graph;

import java.util.Map;
import java.util.Set;

/**
 * This class represents a mutable graph
 */

public class mapClass {

    /**
//     * Abstract function:
//     *      The graph and its edges and nodes are never null.
//     *      If the node exists and an edge exists between the node,
//     *      the node must be in the edge.
//     * Representation Invariant:
//     *      Empty graph = {}
//     *      Node in graph = a
//     *      Edge in graph corresponding to a: a =[b, ..] b = [c, ...] ..
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

    public mapClass() {
        throw new IllegalArgumentException();
    }

    /**
     * Returns the size of the map (number of nodes in the map)
     * @return number of nodes in the map
     */
    public static int size(){
        throw new IllegalArgumentException();
    }

    /**
     * Adds a node to the graph
     * @param a String node that will be added to the graph
     * @spec.effects adds node to the existing graph
     * @spec.modifies this
     */
    public static void addNode(String a) {
        throw new IllegalArgumentException();
    }

    /**
     * Removes a node from the graph
     * @param a string that matches a node in the graph
     * @spec.effects removes an existing node from the graph
     * @spec.modifies this
     * @return true if node was successfully removed
     */
    public static boolean removeNode(String a) {
        throw new IllegalArgumentException();
    }


    /**
     * creates an edge between two existing nodes
     * @param initial node that will be the parent node
     * @param secondary node that will be the child of the initial
     * @param labels the edge that will link these two nodes together
     * @spec.effects builds an edge with a specific label between two nodes in the graph
     * @spec.modifies this
     * @return true if edge is added
     *
     */
    public static boolean addEdge(String initial, String secondary, String labels){
        throw new IllegalArgumentException();
    }
    /**
     * removes an edge between two existing nodes
     * @param labels the edge that links the two nodes together
     * @spec.effects removes an existing edge with a specific label between two nodes in the graph
     * @spec.modifies this
     * @throws IllegalArgumentException if the given nodes don't exist or if the edge between
     *         the given nodes don't exist
     *
     * @return true if edge is removed
     */
    public static boolean removeEdge(String labels){
        throw new IllegalArgumentException();
    }

    /**
     * Determines if a given node exists in the map
     * @param a node
     * @spec.requires node a exists
     * @return true if node exists in the map
     *
     */
    public static boolean contains(String a){
        throw new IllegalArgumentException();
    }

    /**
     * returns the children of a given parent node
     * @param a node
     * @spec.requires node a exists
     * @return children nodes of the specified node
     */
    public static String getChildren(String a) {
        throw new IllegalArgumentException();
    }

    /** returns the parent of a given child node
     * @param a node
     * @return parent nodes of the specified node
     */
    public static String getParent(String a){
        throw new IllegalArgumentException();
    }

    /**
     * Checks to see if an edge exists in the graph
     * @param label of the edge that it wants to check if it exists
     * @return true if the specified edge exists
     */
    public static boolean findEdge(String label){
        throw new IllegalArgumentException();
    }
    /**
     * returns a set of the elements contained in the graph
     * @return set of elements in the graph
     */
    public Set<Map.Entry<String, String>> entrySet(){
        throw new IllegalArgumentException();
    }

    /**
     * returns string representation of the graph
     * @return string representation of the graph
     */
    public String toString(){
        throw new IllegalArgumentException();
    }
}
