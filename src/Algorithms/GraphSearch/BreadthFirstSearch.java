package Algorithms.GraphSearch;

import DataStructures.Graphs.AdjacencyListGraph.Graph;
import DataStructures.Graphs.AdjacencyListGraph.GraphNode;
import DataStructures.Lists.SimplyLinkedList.SimplyLinkedList;
import DataStructures.Queues.QueueList;

/**
 * Abstract class that represents the Breadth First Search Algorithm
 * @author Antonis Zikas
 * @since 26/02/2024
 */
public abstract class BreadthFirstSearch
{
    /**
     * Solves the BFS algorithm and returns the search path pf the algorithm execution
     * @param <graph_t> the graph data type
     * @param queue the supporting queue data structure
     * @param visitedNodes the supported list data structure
     * @return the search path of the process
     */
    private static <graph_t> SimplyLinkedList<GraphNode<graph_t>> solveForSearchPath(
        QueueList<GraphNode<graph_t>> queue, SimplyLinkedList<GraphNode<graph_t>> visitedNodes) 
    {
        SimplyLinkedList<GraphNode<graph_t>> searchPath = new SimplyLinkedList<>(); // Create list to store the path of the search process

        // BFS execution
        while (queue.getSize() != 0)
        {
            GraphNode<graph_t> currentNode = queue.dequeue();
            searchPath.insertDataAtEnd(currentNode); // Add every node to the search path list

            for (GraphNode<graph_t> node : currentNode.getNeighbors())
            {
                if (!visitedNodes.contains(node)) {
                    visitedNodes.insertDataAtEnd(node);
                    queue.enqueue(node);
        }}}

        return searchPath; // Return the search path after the search process
    }

    /**
     * Solves the BFS algorithm and helps the calculation process fot the shortest path after the search
     * @param <graph_t> the graph data type
     * @param graph the graph to search
     * @param queue the supporting queue data structure
     * @param visitedNodes the supported list data structure
     */
    private static <graph_t> void solveForShortestPath(
        Graph<graph_t> graph, QueueList<GraphNode<graph_t>> queue, SimplyLinkedList<GraphNode<graph_t>> visitedNodes, GraphNode<graph_t>[] previousNode)
    {
        // BFS execution
        while (queue.getSize() != 0)
        {
            GraphNode<graph_t> currentNode = queue.dequeue();

            for (GraphNode<graph_t> node : currentNode.getNeighbors()) 
            {
                if (!visitedNodes.contains(node)) 
                {
                    visitedNodes.insertDataAtEnd(node);
                    queue.enqueue(node);
                    previousNode[graph.getNodeIndex(node)] = currentNode; // Keep every the parent of every node
        }}}
    }

    /**
     * Searches the given graph using BFS algorithm and prints the visited nodes
     * @param <graph_t> data type of the graph
     * @param graph the graph to search
     * @param data the node's data to start searching
     * @return the search path of the process
     */
    public static <graph_t> SimplyLinkedList<GraphNode<graph_t>> searchGraph(Graph<graph_t> graph, graph_t data)
    {
        // Get the node containing the given data
        GraphNode<graph_t> startingNode = graph.getNodeOfData(data);

        // Initialize the appropriate data structures for the search process
        SimplyLinkedList<GraphNode<graph_t>> visitedNodes = new SimplyLinkedList<>();
        QueueList<GraphNode<graph_t>> queue = new QueueList<>();

        // Add the starting node to the structures
        visitedNodes.insertDataAtEnd(startingNode);
        queue.enqueue(startingNode);

        // Execute the search process and print each node visited
        return BreadthFirstSearch.solveForSearchPath(queue, visitedNodes);
    }

    /**
     * Returns the shortest path between two nodes in the graph, according to their data
     * @param <graph_t> graph data type
     * @param graph the graph to search in
     * @param startData the data of the starting node
     * @param endData the data of the end node
     * @return a list containing the shortest path between the two nodes
     */
    public static <graph_t> SimplyLinkedList<GraphNode<graph_t>> shortestPathBetween(Graph<graph_t> graph, graph_t startData, graph_t endData)
    {
        // Get the nodes containing the given data
        GraphNode<graph_t> startingNode = graph.getNodeOfData(startData);
        GraphNode<graph_t> endingNode = graph.getNodeOfData(endData);
        
        // Initialize the appropriate data structures for the search process
        SimplyLinkedList<GraphNode<graph_t>> visitedNodes = new SimplyLinkedList<>();
        QueueList<GraphNode<graph_t>> queue = new QueueList<>();

        // Add the starting node to the structures
        visitedNodes.insertDataAtEnd(startingNode);
        queue.enqueue(startingNode);

        @SuppressWarnings("unchecked")
        GraphNode<graph_t>[] previousNode = new GraphNode[graph.getSize()];

        for (int i = 0; i < graph.getSize(); i++) {
            previousNode[i] = null;
        }

        // Solve the search by searching for shortest path
        BreadthFirstSearch.solveForShortestPath(graph, queue, visitedNodes, previousNode);

        // Construct the shortest path
        SimplyLinkedList<GraphNode<graph_t>> shortestPath = new SimplyLinkedList<>();
        GraphNode<graph_t> at = endingNode;

        // Loop through the parent of each node in the hierarchy
        while (at != null) {
            shortestPath.insertDataAtEnd(at);
            at = previousNode[graph.getNodeIndex(at)];
        }
        
        return shortestPath.getReveresedVersion();
    }

    /**
     * Main function to test the Breadth First Search Algorithm
     * @param args some arguments for command line executions
     */
    public static void main(String[] args)
    {
        Graph<Integer> graph = new Graph<>(Graph.DirectionMode.UNDIRECTED, Graph.WeightMode.UNWEIGHTED);

        for (int i = 0; i < 9; i++) {
            graph.addNode(i + 1);
        }

        graph.connectNodes(1, 2);
        graph.connectNodes(1, 3);

        graph.connectNodes(2, 7);

        graph.connectNodes(3, 4);
        graph.connectNodes(3, 5);

        graph.connectNodes(4, 5);
        graph.connectNodes(4, 6);
        graph.connectNodes(4, 7);

        graph.connectNodes(6, 8);

        graph.connectNodes(7, 9);

        graph.connectNodes(8, 9);

        graph.disconnectNodes(3, 4);
        graph.disconnectNodes(9, 8);
        graph.disconnectNodes(7, 4);
        
        
        System.out.println(graph);
        System.out.println("Graph Search Path: " + BreadthFirstSearch.searchGraph(graph, 1));
        System.out.println("Shortest Path From 1 to 8: " + BreadthFirstSearch.shortestPathBetween(graph, 1, 8));
    }
}
