package DataStructures.Graphs.AdjacencyListGraph;

import DataStructures.Lists.SimplyLinkedList.SimplyLinkedList;
import DataStructures.Lists.SimplyLinkedList.SimplyLinkedListNode;

/**
 * Supporting class to define the node list type
 * @author Antonis Zikas
 */
class NodeList<node_t> extends SimplyLinkedList<GraphNode<node_t>>
{
    /**
     * Returns the graph node that containing the given data
     * @param data the data to find the node containing it
     * @return the graph that contains the given data, null if not found
     */
    public GraphNode<node_t> getNodeOfData(node_t data)
    {
        // Initialize a current list node and set it to be the first node in the neighbors list
        SimplyLinkedListNode<GraphNode<node_t>> currentNode = this.head;

        // Loop through every node in the neighbor list to find the node containing the given data
        while (currentNode != null) 
        {
            if (currentNode.getData().getData() == data) {
                return currentNode.getData();
            }
            currentNode = currentNode.getNextNode();
        }

        // Otherwise there is not such a node, so return null 
        return null;
    }
}

/**
 * A class that represents an unweighted undirected graph data structure
 * @author Antonis Zikas
 * @since 24/02/2024
 */
public class Graph<graph_t> implements GraphMethods<graph_t>
{
    // Enumerations to define the direction and weight modes of the graph
    public static enum DirectionMode { DIRECTED, UNDIRECTED }
    public static enum WeightMode    { WEIGHTED, UNWEIGHTED }

    private final NodeList<graph_t> nodes;
    private int numberOfNodes;

    private DirectionMode directionMode;
    private WeightMode weightMode;

    /**
     * Default Constructor of the unweighted undirected graph
     */
    public Graph() 
    {
        this.nodes = new NodeList<>();
        this.numberOfNodes = 0;

        // Set the default mode of the graph (unweighted and undirected)
        this.directionMode = DirectionMode.UNDIRECTED;
        this.weightMode = WeightMode.UNWEIGHTED;
    }

    /**
     * Complex Constructor of the graph
     * @param directionMode the mode of the graph, whether it is directed or undirected
     * @param weightMode the mode of the graph, whether it is weighted or unweighted
     */
    public Graph(DirectionMode directionMode, WeightMode weightMode)
    {
        this.nodes = new NodeList<>();
        this.numberOfNodes = 0;

        // Set the mode of the graph according to the inputs
        this.directionMode = directionMode;
        this.weightMode = weightMode;
    }

    /**
     * Returns the graph node containing the given data
     * @return the node in the graph that contains the given data
     */
    @Override
    public GraphNode<graph_t> getNodeOfData(graph_t data) {
        return this.nodes.getNodeOfData(data);
    }

    /**
     * Returns the index of the given node data in the grqph
     * @return the index of the data in the graph
     */
    @Override
    public int getNodeIndex(GraphNode<graph_t> node) {
        return this.nodes.indexOf(node);
    }

    public int getSize() {
        return this.numberOfNodes;
    }

    /**
     * Adds a new node to this graph with the given data and no neighbors
     * @param data the data to be inserted
     */
    @Override
    public boolean addNode(graph_t data)
    {
        // Create a new node with the given data and add it to the graph
        GraphNode<graph_t> newNode = new GraphNode<>(data, null);
        if (this.weightMode == Graph.WeightMode.WEIGHTED) {
            newNode.setConnectionMode(GraphNode.ConnectionMode.WEIGHTED);
        }
        
        // Checking if the node already exists in the graph
        for (GraphNode<graph_t> currentNode : this.nodes) {
        	if (currentNode.getData() == data) {
        		return false;
        	}
        }
        // Try to insert the new node in the graph and return success code if it worked
        if (this.nodes.insertDataAtEnd(newNode)) {
            this.numberOfNodes++;
            return true;
        }
        
        return false; // Otherwise return error code
    }

    /**
     * Overloading method for printing the unweighted undirected graph object
     * @return the graph as a string
     */
    @Override
    public final String toString()
    {
        // Create a result string variable
        StringBuilder response = new StringBuilder();

        // Loop through every node in the graph and store its neighbors to the result string
        for (GraphNode<graph_t> currentNode : this.nodes)
        {
            response.append(currentNode.getData()).append(": ");
            response.append(currentNode.getEdges().toString()).append("\n");
        }

        return response.toString();
    }

    /**
     * Connects two nodes in the graph according to their data
     * @param data1 the data of the first node
     * @param data2 the data of the second node
     * @return true if the connection was succeded, false otherwise
     */
    @Override
    public boolean connectNodes(graph_t data1, graph_t data2)
    {
        // Initialize the two nodes to connect
        GraphNode<graph_t> node1, node2;

        // Check if there are such nodes that contain the given data
        if ((node1 = this.nodes.getNodeOfData(data1)) == null || (node2 = this.nodes.getNodeOfData(data2)) == null) {
            return false;
        }

        // Check if the two nodes are already connected
        if (this.areConnected(data1, data2)) {
            return false;
        }
        
        // Checking if the two data are equal. If so, add only to the first node
        if (data1 == data2) {
        	node1.addNeighbor(node2);
        	return true;
        }

        // Connect the two nodes according to the graph's direction mode
        if (this.directionMode == Graph.DirectionMode.UNDIRECTED) {
            node1.addNeighbor(node2);
            node2.addNeighbor(node1);
            
        } else {
            node1.addNeighbor(node2);
        }

        return true;
    }

    @Override
    public boolean connectNodes(graph_t data1, graph_t data2, double weight)
    {
        // Checking if the graph is weighted
        if (this.weightMode == Graph.WeightMode.UNWEIGHTED) {
            System.err.println("The Graph is unweighted. Try to connect nodes without weights");
            return false;
        }

        // Initialize the two nodes to connect
        GraphNode<graph_t> node1, node2;

        // Check if there are such nodes that contain the given data
        if ((node1 = this.nodes.getNodeOfData(data1)) == null || (node2 = this.nodes.getNodeOfData(data2)) == null) {
            return false;
        }

        // Check if the two nodes are already connected
        if (this.areConnected(data1, data2)) {
            return false;
        }
        
        // Checking if the two data are equal. If so, add only to the first node
        if (data1 == data2) {
        	node1.addNeighbor(node2, weight);
        	return true;
        }

        // Connect the two nodes according to the graph's direction mode
        if (this.directionMode == Graph.DirectionMode.UNDIRECTED) {
            node1.addNeighbor(node2, weight);
            node2.addNeighbor(node1, weight);
            
        } else {
            node1.addNeighbor(node2, weight);
        }

        return true;
    }

    /**
     * Disconnects two nodes in the graph according to their data
     * @param data1 the data of the first node
     * @param data2 the data of the second node
     * @return true if the disconnection was successful, false otherwise
     */
    @Override
    public boolean disconnectNodes(graph_t data1, graph_t data2)
    {
        // Initialize the two nodes to disconnect
        GraphNode<graph_t> node1, node2;

        // Check if there are suck nodes with the given data
        if ((node1 = this.nodes.getNodeOfData(data1)) == null || (node2 = this.nodes.getNodeOfData(data2)) == null) {
            return false;
        }

        // Disconnect the nodes according to the graph direction mode
        if (this.directionMode == Graph.DirectionMode.UNDIRECTED) {
            return node1.removeNeighbor(node2) && node2.removeNeighbor(node1);
            
        } else {
            return node1.removeNeighbor(node2);
        }
    }
    
    /**
     * Determins whether two nodes in the graph are connected, acoording to their data
     * @param data1 the data of the first node
     * @param data2 the data of the second node
     * @return true if the two nodes are connected, false otherwise
     */
    @Override
    public boolean areConnected(graph_t data1, graph_t data2)
    {
    	// Initialize the two nodes to check if they are connected
    	GraphNode<graph_t> node1, node2;
    	
    	// Check if there are such nodes with the given data
    	if ((node1 = this.nodes.getNodeOfData(data1)) == null || (node2 = this.nodes.getNodeOfData(data2)) == null) {
    		return false;
    	}
    	
    	// Check if the two nodes are connected
    	return (node1.hasNeighbor(node2)) && (node2.hasNeighbor(node1));
    }

    /**
     * Main function to test the unweighted undirected graph class and its functionality
     * @param args some arguments fot command line executions
     * @author Antonis Zikas
     */
    public static void main(String[] args)
    {
        Graph<Integer> graph = new Graph<>(Graph.DirectionMode.UNDIRECTED, Graph.WeightMode.WEIGHTED);

        for (int i = 0; i < 10; i++) {
            graph.addNode(i + 1);
        }

        graph.connectNodes(1, 2, 5.92);
        graph.connectNodes(2, 1, 10.43);
        graph.connectNodes(3,  9, 2.02);
        graph.connectNodes(4,  2, 1.94);
        graph.connectNodes(5,  1, 9.83);
        graph.connectNodes(5,  6, 2.01);

        System.out.println(graph);
        
        for (GraphNode<Integer> node : graph.getNodeOfData(1).getNeighbors()) {
            System.out.println(node);
        }

        System.out.println("Index: " + graph.getNodeIndex(graph.getNodeOfData(5)));
    }
}
