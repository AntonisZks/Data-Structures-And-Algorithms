package DataStructures.Graphs.Unweighted;

import DataStructures.Graphs.Unweighted.Undirected.UnweightedUndirectedGraphNode;

/**
 * A class that represents an unweighted graph edge
 * @author Antonis Zikas
 */
public class UnweightedGraphEdge<node_t>
{
    private UnweightedUndirectedGraphNode<node_t> endNode;

    /**
     * Constructor of the unweighted graph edge
     * @param endNode the end node of this edge
     */
    public UnweightedGraphEdge(UnweightedUndirectedGraphNode<node_t> endNode) {
        this.endNode = endNode;
    }

    /**
     * Sets the endNode of this edge
     * @param endNode the node to be set
     */
    @SuppressWarnings("unused")
    public void setEndNode(UnweightedUndirectedGraphNode<node_t> endNode) {
        this.endNode = endNode;
    }

    /**
     * Returns the endNode of this edge
     * @return the node at the end
     */
    public UnweightedUndirectedGraphNode<node_t> getEndNode() {
        return this.endNode;
    }
}
