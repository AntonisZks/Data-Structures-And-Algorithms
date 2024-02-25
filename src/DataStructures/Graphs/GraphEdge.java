package DataStructures.Graphs;

/**
 * A class that represents an unweighted graph edge
 * @author Antonis Zikas
 */
public class GraphEdge<node_t>
{
    // Enumeration to define the weight mode of the edge
    public static enum WeightMode { WEIGHTED, UNWEIGHTED }

    private WeightMode weightMode;
    private GraphNode<node_t> endNode;
    private double weight;

    /**
     * Constructor of the unweighted graph edge
     * @param endNode the end node of this edge
     */
    public GraphEdge(GraphNode<node_t> endNode) 
    {
        this.endNode = endNode;
        this.weight = 0;
        this.weightMode = GraphEdge.WeightMode.UNWEIGHTED;
    }

    /**
     * Second Constructor of the graph edge
     * @param endNode the end node of this edge
     * @param weight the weight of the edge
     */
    public GraphEdge(GraphNode<node_t> endNode, double weight) 
    {
        this.endNode = endNode;
        this.weight = weight;
        this.weightMode = GraphEdge.WeightMode.WEIGHTED;
    }

    /**
     * Sets the endNode of this edge
     * @param endNode the node to be set
     */
    public void setEndNode(GraphNode<node_t> endNode) {
        this.endNode = endNode;
    }

    /**
     * Returns the endNode of this edge
     * @return the node at the end
     */
    public GraphNode<node_t> getEndNode() {
        return this.endNode;
    }

    /**
     * Sets the weight of the edge
     * @param weight the weight to be set
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Returns the edge's weight
     * @return the weight of the edge
     */
    public double getWeight() {
        return this.weight;
    }

    @Override
    public String toString()
    {
        StringBuilder response = new StringBuilder();
        response.append(this.endNode.toString()).append(", ");

        if (this.weightMode == GraphEdge.WeightMode.WEIGHTED) {
            response.append(this.weight);
        }
        
        return response.toString();
    }
}
