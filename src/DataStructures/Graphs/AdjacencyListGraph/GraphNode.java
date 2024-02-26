package DataStructures.Graphs.AdjacencyListGraph;

import DataStructures.Lists.SimplyLinkedList.SimplyLinkedList;
import DataStructures.Lists.SimplyLinkedList.SimplyLinkedListNode;

/**
 * Supporting class to define the edge list type
 * @author Antonis Zikas
 */
class EdgeList<node_t> extends SimplyLinkedList<GraphEdge<node_t>> 
{
    /**
     * Removes an edge with an endNode containing the given data
     * @param data the data to remove
     * @return true if the deletion was successful, false otherwise
     */
    public boolean removeEdgeWithEndNodeData(GraphNode<node_t> node)
    {
        // Initialize a current node to iterate through the edges list
        SimplyLinkedListNode<GraphEdge<node_t>> currentNode = this.head, previousNode = this.head;
        
        // Checking if the list is empty
        if (this.head == null) {
        	return false;
        }
        
        // Check if the given data is in the first node
        if (this.head.getData().getEndNode().equals(node)) {
            this.head = this.head.getNextNode();
            return true;
        }
        
        // Otherwise loop through the edges and find the node containing the given data
        while (currentNode != null) 
        {
            GraphNode<node_t> currentEndNode = currentNode.getData().getEndNode();
            
            // Check if the current data is equal to the given data
            if (currentEndNode.equals(node)) {
                previousNode.setNextNode(currentNode.getNextNode());
                return true;
            }
            
            previousNode = currentNode;
            currentNode = currentNode.getNextNode();
        }
        return false;
    }

    @Override
    public final String toString()
    {
        SimplyLinkedListNode<GraphEdge<node_t>> currentNode = this.head;
        StringBuilder response = new StringBuilder();

        while (currentNode != null)
        {
            response.append("(").append(currentNode.getData().getEndNode().toString());
            
            if (currentNode.getData().getEndNode().connectionMode == GraphNode.ConnectionMode.WEIGHTED) {
                response.append(", ").append(currentNode.getData().getWeight());
            }

            response.append(")");

            if (currentNode.getNextNode() != null) {
                response.append(", ");
            }
            
            currentNode = currentNode.getNextNode();
        }

        return response.toString();
    }
}

/**
 * Supporting class to define the neighbor list type
 * @author Antonis Zikas
 */
class NeighborList<node_t> extends SimplyLinkedList<GraphNode<node_t>> {}

/**
 * A class that represents an undirected unweighted graph node.
 * @author Antonis Zikas
 */
public class GraphNode<node_t>
{
    // Enumeration to define the connection with other nodes (with or without weights)
    public static enum ConnectionMode { WEIGHTED, UNWEIGHTED }

    private node_t data;
    private EdgeList<node_t> edges;
    public ConnectionMode connectionMode;

    /**
     * Returns the neighbors of this node
     * @return a list containing the nodes that are neighbors to this one
     */
    public SimplyLinkedList<GraphNode<node_t>> getNeighbors() // TODO: Store every neighbor in a list whenever addNeighbor() method is called
    {
        // Initialize a new list for the neighbors
        NeighborList<node_t> neighbors = new NeighborList<>();
        
        // Loop through the edges of the node and store every end node as a neighbor of this node
        for (GraphEdge<node_t> currentEdge : this.edges) 
        {
            GraphNode<node_t> currentEndNode = currentEdge.getEndNode();
            neighbors.insertDataAtEnd(currentEndNode);
        }

        return neighbors;
    }

    /**
     * Adds a new edge to this node with the given end node. Basically it connects those two nodes
     * @param otherNode the node to add to the new edge
     */
    public void addNeighbor(GraphNode<node_t> otherNode)
    {
        // Initialize a new edge with the given end node, and add it to the edge list of this node
        GraphEdge<node_t> newEdge = new GraphEdge<>(otherNode, 0.0f);
        this.edges.insertDataAtEnd(newEdge);
    }

    public void addNeighbor(GraphNode<node_t> otherNode, double weight)
    {
        // Initialize a new edge with the given end node, and add it to the edge list of this node
        GraphEdge<node_t> newEdge = new GraphEdge<>(otherNode, weight);
        this.edges.insertDataAtEnd(newEdge);
    }
    
    /**
     * Removes an edge from this node that contains the given node
     * @param otherNode the node to remove from this node's neighbors
     * @return true if the deletion was successful, false otherwise
     */
    public boolean removeNeighbor(GraphNode<node_t> otherNode) {   
        return this.edges.removeEdgeWithEndNodeData(otherNode);
    }
    
    /**
     * Determins whether this node has another node as a neighbor
     * @param otherNode the node to check is it is a neighbor of this one
     * @return true if the given node is a neighbor, false otherwise
     */
    public boolean hasNeighbor(GraphNode<node_t> otherNode)
    {
    	// Loop through every edge this node has and search if it has the given node as a neighbor
    	for (GraphEdge<node_t> currentEdge : this.edges) {
    		if (currentEdge.getEndNode().equals(otherNode)) {
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
    public GraphNode(node_t data, EdgeList<node_t> edges) {
        this.data = data;
        this.edges = (edges == null) ? new EdgeList<>() : edges;
        this.connectionMode = GraphNode.ConnectionMode.UNWEIGHTED;
    }

    /**
     * Sets the data of this node
     * @param data the data to be set
     */
    public void setData(node_t data) {
        this.data = data;
    }

    /**
     * Sets the neighbors of this node
     * @param edges the list of edges to be set
     */
    public void setNeighbors(EdgeList<node_t> edges) {
        this.edges = edges;
    }

    /**
     * Sets the connection mode if the graph node
     * @param connectionMode the mode of connection (weighted or unweighted)
     */
    public void setConnectionMode(GraphNode.ConnectionMode connectionMode) {
        this.connectionMode = connectionMode;
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
    public EdgeList<node_t> getEdges() {
        return this.edges;
    }

    /**
     * Overloading method for equality check between two unweighted undirected graph nodes
     * @param other the other object to check
     * @return true, if they have the same data, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        // Check if we are comparing the same objects using reference equality
        if (this == other) {
            return true;
        }

        // Check if the other object is null or of a different class
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        // Cast the other object to UnweightedUndirectedGraphNode
        @SuppressWarnings("unchecked")
        GraphNode<node_t> otherNode = (GraphNode<node_t>) other;

        // Compare the data fields of the two nodes using the == operator
        return this.data == otherNode.data;
    }

    /**
     * Main function to check node functionality
     * @param args some arguments for command line executions
     */
    public static void main(String[] args) {
        GraphNode<Integer> node1 = new GraphNode<>(1, null);
        GraphNode<Integer> node2 = new GraphNode<>(1, null);
    
        System.out.println(node1.equals(node2)); // true
    }
}
