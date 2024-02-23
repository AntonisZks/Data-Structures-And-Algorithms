/* Filename: SimplyLinkedList.java */

package DataStructures.Lists.SimplyLinkedList;

/**
 * A class that represents a simply linked list data structure
 * @author Antonis Zikas
 */
public class SimplyLinkedList<list_t> implements SimplyLinkedListMethods<list_t>
{
    private static final String DEFAULT_SEPERATOR = " -> "; // Default seperator character for the data printing

    // Data of the simply linked list
    private ListNode<list_t> head;
    private int size;

    /**
     * Constructor of the singly linked list
     */
    public SimplyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Inserts data to the end of the simply linked list
     * @param data the data to be inserted
     */
    @Override
    public void insertDataAtEnd(list_t data)
    {
        // Creating a new simply linked list node
        ListNode<list_t> newNode = new ListNode<>(data, null);
        
        // Checking if the list is empty, If so add the new node and update the size of the list
        if (this.head == null) {
            this.head = newNode; this.size++;
            return;
        } 
        // Initialize a current node and loop until the very end of the list
        ListNode<list_t> currentNode = this.head;

        while (currentNode.getNextNode() != null) {
            currentNode = currentNode.getNextNode();
        }
        // Set the last node of the list and update its size
        currentNode.setNextNode(newNode); this.size++;
    }

    /**
     * Advanced printing of the simply linked list's sequence of data
     * @param seperator a character seperator for the data printing
     */
    @Override
    public void print(String seperator)
    {
        System.out.print("[");

        // Checking if the list is empty
        if (this.head == null) { System.out.println("]"); return; }

        ListNode<list_t> currentNode = this.head;

        // Loop through every node in the list and print its content
        while (currentNode != null) 
        {
            System.out.print(currentNode.getData());
            if (currentNode.getNextNode() != null) { // Printing a seperator character if necessary
                System.out.print(seperator); 
            }

            currentNode = currentNode.getNextNode();
        }
        System.out.println("]");
    }

    /**
     * Default printing of the simply linked list's sequence of data
     */
    @Override
    public void print() {
        this.print(SimplyLinkedList.DEFAULT_SEPERATOR);
    }
}
