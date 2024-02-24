package DataStructures.Graphs.Unweighted.Undirected;

import DataStructures.Graphs.Unweighted.UnweightedGraphEdge;
import DataStructures.Lists.SimplyLinkedList.SimplyLinkedList;

/**
 * Supporting class to define the edge list type
 * @author Antonis Zikas
 */
class EdgeList<node_t> extends SimplyLinkedList<UnweightedGraphEdge<node_t>> {}

/**
 * Supporting class to define the neighbor list type
 * @author Antonis Zikas
 */
class NeighborList<node_t> extends SimplyLinkedList<UnweightedUndirectedGraphNode<node_t>> {}

/**
 * A class that represents an undirected unweighted graph node.
 * @author Antonis Zikas
 */
public class UnweightedUndirectedGraphNode<node_t>
{
    private node_t data;
    private EdgeList<node_t> edges;

    /**
     * Returns the neighbors of this node
     * @return a list containing the nodes that are neighbors to this one
     */
    public NeighborList<node_t> getNeighbors() // TODO: Store every neighbor in a list whenever addNeighbor() method is called
    {
        // Initialize a new list for the neighbors
        NeighborList<node_t> neighbors = new NeighborList<>();
        
        // Loop through the edges of the node and store every end node as a neighbor of this node
        for (UnweightedGraphEdge<node_t> currentEdge : this.edges) 
        {
            UnweightedUndirectedGraphNode<node_t> currentEndNode = currentEdge.getEndNode();
            neighbors.insertDataAtEnd(currentEndNode);
        }

        return neighbors;
    }

    /**
     * Adds a new edge to this node with the given end node. Basically it connects those two nodes
     * @param endNode the node to add to the new edge
     */
    public void addNeighbor(UnweightedUndirectedGraphNode<node_t> endNode)
    {
        // Initialize a new edge with the given end node, and add it to the edge list of this node
        UnweightedGraphEdge<node_t> newEdge = new UnweightedGraphEdge<>(endNode);
        this.edges.insertDataAtEnd(newEdge);
    }

    /**
     * Overloading method for printing the unweighted undirected graph node
     * @return the node data as a string
     */
    @Override
    public final String toString() {
        return this.data.toString();
    }

    /**
     * Constructor of the undirected unweighted graph node
     * @param data the data of the node
     * @param edges the edges of this node
     */
    public UnweightedUndirectedGraphNode(node_t data, EdgeList<node_t> edges) {
        this.data = data;
        this.edges = (edges == null) ? new EdgeList<>() : edges;
    }

    /**
     * Sets the data of this node
     * @param data the data to be set
     */
    @SuppressWarnings("unused")
    public void setData(node_t data) {
        this.data = data;
    }

    /**
     * Sets the neighbors of this node
     * @param edges the list of edges to be set
     */
    @SuppressWarnings("unused")
    public void setNeighbors(EdgeList<node_t> edges) {
        this.edges = edges;
    }

    /**
     * Returns the dat of this node
     * @return the node's data
     */
    public node_t getData() {
        return this.data;
    }

    /**
     * Returns the neighbors of this node
     * @return a list of edges of this node
     */
    @SuppressWarnings("unused")
    public EdgeList<node_t> getEdges() {
        return this.edges;
    }
}
