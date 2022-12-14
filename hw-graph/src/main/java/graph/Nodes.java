package graph;

import java.util.Comparator;

/**
 * This class represents the edges between two nodes
 *
 * Specification fields:
 *  @spec.specfield nodes : strings  // The nodes in the graph
 *
 */
public class Nodes<A, S, L> {

//      Abstract function:
//           Nodes are never null.
//      Representation Invariant:
//           Node in graph = a
//           Node with an edge : a (parent node) b(child node) l (label of the edge)

    public A a;
    public S b;
    public L l;

     /**
     * @spec.requires nodes != null
     * @spec.modifies this
     * @spec.effects creates links between two nodes that'll become the edges
     */

    /**
     * Constructs the initial node
     * @param initial will create a single node
     * @spec.effects creates a node in the graph
     */
    public Nodes (A initial){
        this.a = initial;
    }

    /**
     * Constructs the edge between two nodes
     * @param initial node that will be the starting node
     * @param secondary node that will be the destination
     * @param edgeLabel will link the two nodes and create the edge connecting them
     * @spec.effects creates an edge in the graph between two nodes
     */
    public Nodes (A initial, S secondary, L edgeLabel){
        this.a = initial;
        this.b = secondary;
        this.l = edgeLabel;
    }

    public L getL() {
        return l;
    }

    public S getB(){
        return b;
    }

    public String toString(){
        String result = l.toString();
        return result;
    }

    public String getEdge(){
        String r = b.toString() + "(" + l.toString() + ")";
        return r;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Nodes<?, ?, ?>)) {
            return false;
        }
        Nodes<?, ?, ?> le = (Nodes<?, ?, ?>) obj;
        return this.b.equals(le.b) && this.l.equals(le.l);
    }

    /**
     * Standard hashCode function.
     *
     * @return an int that all objects equal to this will also return
     */
    @Override
    public int hashCode() {
        return this.b.hashCode() * this.l.hashCode();
    }

}
