package DataStructures.Graphs.AdjacencyListGraph;

/**
 * Public interface for the unweighted undirected graph's methods
 * @author Antonis Zikas
 */
public interface GraphMethods<graph_t>
{
    /* Basic Methods */
    boolean addNode(graph_t data);
    boolean connectNodes(graph_t data1, graph_t data2);
    boolean connectNodes(graph_t data1, graph_t data2, double weight);
    boolean disconnectNodes(graph_t data1, graph_t data2);
    boolean areConnected(graph_t data1, graph_t data2);

    /* Other Methods */
    GraphNode<graph_t> getNodeOfData(graph_t data);
    int getNodeIndex(GraphNode<graph_t> node);
}
