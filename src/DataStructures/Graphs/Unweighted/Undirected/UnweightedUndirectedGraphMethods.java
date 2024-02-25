package DataStructures.Graphs.Unweighted.Undirected;

/**
 * Public interface for the unweighted undirected graph's methods
 * @author Antonis Zikas
 */
public interface UnweightedUndirectedGraphMethods<graph_t>
{
    /* Basic Methods */
    boolean addNode(graph_t data);
    boolean connectNodes(graph_t data1, graph_t data2);
    boolean disconnectNodes(graph_t data1, graph_t data2);
    boolean areConnected(graph_t data1, graph_t data2);
}
