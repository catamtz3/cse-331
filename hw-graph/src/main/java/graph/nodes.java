package graph;

import javax.xml.namespace.QName;

/**
 * This class represents the edges between two nodes
 *
 * Specification fields:
 *  @spec.specfield nodes : strings  // The nodes in the graph
 *
 */
public class nodes {
    /**
     * Abstract function:
     *      Nodes are never null.
     * Representation Invariant:
     *      Node in graph = a
     *
     *
     * @spec.requires nodes != null
     * @spec.modifies this
     * @spec.effects creates links between two nodes that'll become the edges
     */

    /**
     * Constructs the initial node
     * @param initial will create a single node
     * @spec.effects creates a node in the graph
     */
    // constructs a node with the given name and null link
    public nodes (String initial){
        throw new IllegalArgumentException();
    }

    /**
     * Constructs the initial node
     * @param initial node that will be the starting node
     * @param secondary node that will be the destination
     * @param edgeLabel will link the two nodes and create the edge connecting them
     * @spec.effects creates an edge in the graph between two nodes
     */
    public nodes (String initial, String secondary, String edgeLabel){
        throw new IllegalArgumentException();
    }
}
