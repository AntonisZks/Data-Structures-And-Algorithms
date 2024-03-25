package Algorithms.GraphSearch;

import DataStructures.Graphs.AdjacencyListGraph.Graph;
import DataStructures.Graphs.AdjacencyListGraph.GraphNode;
import DataStructures.Lists.SimplyLinkedList.SimplyLinkedList;
import DataStructures.Stacks.StackList;

/**
 * Abstract class that represents the Depth First Search Algorithm
 * @author Antonis Zikas
 * @since 25/02/2024
 */
public class DepthFirstSearch 
{
    /**
     * Searches the given graph using DFS algorithm and prints the visited nodes
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
        StackList<GraphNode<graph_t>> stack = new StackList<>();

        // Add the starting node to the structures
        visitedNodes.insertDataAtEnd(startingNode);
        stack.push(startingNode);

        /* Execute the search process */
        SimplyLinkedList<GraphNode<graph_t>> searchPath = new SimplyLinkedList<>(); // Create a list to store the path of the search process

        // DFS Execution
        while (stack.getSize() != 0)
        {
            GraphNode<graph_t> currentNode = stack.pop();
            searchPath.insertDataAtEnd(currentNode);

            for (GraphNode<graph_t> node : currentNode.getNeighbors())
            {
                if (!visitedNodes.contains(node)) {
                    visitedNodes.insertDataAtEnd(node);
                    stack.push(node);
        }}}

        return searchPath;
    }

    /**
     * Main function to test the Depth First Search Algorithm
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
        System.out.println("Graph Search Path: " + DepthFirstSearch.searchGraph(graph, 1));
    }
}
