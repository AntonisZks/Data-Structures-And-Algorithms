package DataStructures.Graphs.Unweighted.Undirected;

import DataStructures.Graphs.Unweighted.UnweightedGraphEdge;
import DataStructures.Lists.SimplyLinkedList.SimplyLinkedList;
import DataStructures.Lists.SimplyLinkedList.SimplyLinkedListNode;

/**
 * Supporting class to define the edge list type
 * @author Antonis Zikas
 */
class EdgeList<node_t> extends SimplyLinkedList<UnweightedGraphEdge<node_t>> 
{
    /**
     * Removes an edge with an endNode containing the given data
     * @param data the data to remove
     * @return true if the deletion was successful, false otherwise
     */
    public boolean removeEdgeWithEndNodeData(node_t data)
    {
        // Initialize a current node to iterate through the edges list
        SimplyLinkedListNode<UnweightedGraphEdge<node_t>> currentNode = this.head, previousNode = this.head;
        
        // Checking if the list is empty
        if (this.head == null) {
        	return false;
        }
        
        // Check if the given data is in the first node
        if (this.head.getData().getEndNode().getData() == data) {
            this.head = this.head.getNextNode();
            return true;
        }
        
        // Otherwise loop through the edges and find the node containing the given data
        while (currentNode != null) 
        {
            node_t currentData = currentNode.getData().getEndNode().getData();
            
            // Check if the current data is equal to the given data
            if (currentData == data) {
                previousNode.setNextNode(currentNode.getNextNode());
                return true;
            }
            
            previousNode = currentNode;
            currentNode = currentNode.getNextNode();
        }
        return false;
    }
}

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
     * @param otherNode the node to add to the new edge
     */
    public void addNeighbor(UnweightedUndirectedGraphNode<node_t> otherNode)
    {
        // Initialize a new edge with the given end node, and add it to the edge list of this node
        UnweightedGraphEdge<node_t> newEdge = new UnweightedGraphEdge<>(otherNode);
        this.edges.insertDataAtEnd(newEdge);
    }
    
    /**
     * Removes an edge from this node that contains the given node
     * @param otherNode the node to remove from this node's neighbors
     * @return true if the deletion was successful, false otherwise
     */
    public boolean removeNeighbor(UnweightedUndirectedGraphNode<node_t> otherNode)
    {   
        node_t dataToRemove = otherNode.getData();
        return this.edges.removeEdgeWithEndNodeData(dataToRemove);
    }
    
    /**
     * Determins whether this node has another node as a neighbor
     * @param otherNode the node to check is it is a neighbor of this one
     * @return true if the given node is a neighbor, false otherwise
     */
    public boolean hasNeighbor(UnweightedUndirectedGraphNode<node_t> otherNode)
    {
    	// Loop through every edge this node has and search if it has the given node as a neighbor
    	for (UnweightedGraphEdge<node_t> currentEdge : this.edges) {
    		if (otherNode.getData() == currentEdge.getEndNode().getData()) {
    			return true;
    		}
    	}
    	return false;
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
