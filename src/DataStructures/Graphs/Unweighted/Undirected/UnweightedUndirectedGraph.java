package DataStructures.Graphs.Unweighted.Undirected;

import DataStructures.Lists.SimplyLinkedList.SimplyLinkedList;
import DataStructures.Lists.SimplyLinkedList.SimplyLinkedListNode;

/**
 * Supporting class to define the node list type
 * @author Antonis Zikas
 */
class NodeList<node_t> extends SimplyLinkedList<UnweightedUndirectedGraphNode<node_t>>
{
    /**
     * Returns the graph node that containing the given data
     * @param data the data to find the node containing it
     * @return the graph that contains the given data, null if not found
     */
    public UnweightedUndirectedGraphNode<node_t> getNodeOfData(node_t data)
    {
        // Initialize a current list node and set it to be the first node in the neighbors list
        SimplyLinkedListNode<UnweightedUndirectedGraphNode<node_t>> currentNode = this.head;

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
public class UnweightedUndirectedGraph<graph_t> implements UnweightedUndirectedGraphMethods<graph_t>
{
    private final NodeList<graph_t> nodes;
    private int numberOfNodes;

    /**
     * Constructor of the unweighted undirected graph
     */
    public UnweightedUndirectedGraph() {
        this.nodes = new NodeList<>();
        this.numberOfNodes = 0;
    }

    /**
     * Adds a new node to this graph with the given data and no neighbors
     * @param data the data to be inserted
     */
    @Override
    public boolean addNode(graph_t data)
    {
        // Create a new node with the given data and add it to the graph
        UnweightedUndirectedGraphNode<graph_t> newNode = new UnweightedUndirectedGraphNode<>(data, null);

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
        for (UnweightedUndirectedGraphNode<graph_t> currentNode : this.nodes)
        {
            response.append(currentNode.getData()).append(": ");
            response.append(currentNode.getNeighbors().toString()).append("\n");
        }

        return response.toString();
    }

    @Override
    public boolean connectNodes(graph_t data1, graph_t data2)
    {
        // Initialize the two nodes to connect
        UnweightedUndirectedGraphNode<graph_t> node1, node2;

        // Check if there are such nodes that contain the given data
        if ((node1 = this.nodes.getNodeOfData(data1)) == null || (node2 = this.nodes.getNodeOfData(data2)) == null) {
            return false;
        }

        // Connect the two nodes by adding each one as a neighbor to the other
        node1.addNeighbor(node2);
        node2.addNeighbor(node1);

        return true;
    }

    /**
     * Main function to test the unweighted undirected graph class and its functionality
     * @param args some arguments fot command line executions
     * @author Antonis Zikas
     */
    public static void main(String[] args)
    {
        UnweightedUndirectedGraph<Integer> graph = new UnweightedUndirectedGraph<>();

        for (int i = 0; i < 5; i++) {
            graph.addNode(i + 1);
        }

        System.out.println(graph.connectNodes(1, 2));
        System.out.println(graph.connectNodes(2, 3));
        System.out.println(graph.connectNodes(5, 1));

        System.out.println(graph);
    }
}
