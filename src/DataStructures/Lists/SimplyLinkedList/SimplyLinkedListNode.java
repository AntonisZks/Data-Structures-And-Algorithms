package DataStructures.Lists.SimplyLinkedList;

/**
 * A class that represents a simply linked list node.
 * @author Antonis Zikas
 */
public class SimplyLinkedListNode <node_t>
{
    private node_t data;
    private SimplyLinkedListNode<node_t> nextNode;
    
    /**
     * Constructor of the simply linked list node
     * @param data the data of the node
     * @param nextNode the next node in the sequence
     */
    public SimplyLinkedListNode(node_t data, SimplyLinkedListNode<node_t> nextNode) {
        this.data = data;
        this.nextNode = nextNode;
    }

    /**
     * Sets the data of the node
     * @param data the new data of the node
     */
    public void setData(node_t data) {
        this.data = data;
    }

    /**
     * Sets the next node of the current one
     * @param nextNode the next node in the sequence
     */
    public void setNextNode(SimplyLinkedListNode<node_t> nextNode) {
        this.nextNode = nextNode;
    }

    /**
     * Returns the data of the node
     * @return the node's data
     */
    public node_t getData() {
        return this.data;
    }

    /**
     * Returns the next node of the current one
     * @return the next node in the sequence
     */
    public SimplyLinkedListNode<node_t> getNextNode() {
        return this.nextNode;
    }

    /**
     * Prints the contents of the node
     */
    public void print() {
        System.out.print(this.data);
    }
}
