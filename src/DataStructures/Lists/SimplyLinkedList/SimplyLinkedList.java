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
    public boolean insertDataAtEnd(list_t data)
    {
        // Creating a new simply linked list node
        ListNode<list_t> newNode = new ListNode<>(data, null);
        
        // Checking if the list is empty. If so add the new node and update the size of the list
        if (this.head == null) {
            this.head = newNode; 
            this.size++;
            return true;
        } 
        // Initialize a current node and loop until the very end of the list
        ListNode<list_t> currentNode = this.head;

        while (currentNode.getNextNode() != null) {
            currentNode = currentNode.getNextNode();
        }
        // Set the last node of the list and update its size
        currentNode.setNextNode(newNode); this.size++;

        return true;
    }

    /**
     * Inserts data to the beginning of the simply linked list
     * @param data the data to be inserted
     */
    @Override
    public boolean insertDataAtStart(list_t data)
    {
        // Creating a new simply linked list node
        ListNode<list_t> newNode = new ListNode<>(data, null);

        // Checking if the list is empty. If so add the new node and update the size of the list
        if (this.head == null) {
            this.head = newNode; 
            this.size++;
            return true;
        }
        // Get the first node of the list and insert the new node before that
        ListNode<list_t> firstNode = this.head;
        this.head = newNode;
        newNode.setNextNode(firstNode);
        this.size++;

        return true;
    }

    /**
     * Inserts the given data to the given index of the simply linked list
     * @param data the data to be inserted
     * @param index the index in witch the data is going to be inserted
     */
    @Override
    public boolean insertDataAtIndex(list_t data, int index)
    {
        // Creating a new simply linked list node
        ListNode<list_t> newNode = new ListNode<>(data, null);

        // Checking if the given index is equal to the size of the list, or zero and do the appropriates
        if (index == this.size) { 
            return this.insertDataAtEnd(data); 
        }
        else if (index == 0) { 
            return this.insertDataAtStart(data); 
        } 

        // Checking if the given index is valid according to the list size
        if (index < 0 || index > this.size - 1) {
            System.err.println("Error accessing index " + index + ". List has " + this.size + " elements.");
            return false;
        }
        // Otherwise insert the data to the given index inside the list
        ListNode<list_t> currentNode = this.head;

        for (int i = 0; i < index - 1; i++) {
            currentNode = currentNode.getNextNode();
        }
        
        // Store the next node in the sequence and store the new node between the current and the next ones
        ListNode<list_t> nextNode = currentNode.getNextNode();
        currentNode.setNextNode(newNode);
        newNode.setNextNode(nextNode);

        return true;
    }

    /**
     * Removes and returns the data at the end of the simply linked list
     */
    @Override
    public list_t removeDataFromEnd()
    {
        // Checking if the list is empty. If so return null
        if (this.head == null) {
            return null;
        }

        // Checking if the list has only one node. If so then just empty the list
        if (this.size == 1) {
            list_t removedData = this.head.getData();
            this.head = null; this.size--;

            return removedData;
        }

        // Otherwise head over to the last node and delete its data and return it
        ListNode<list_t> currentNode = this.head;
        ListNode<list_t> previousNode = this.head;

        while (currentNode.getNextNode() != null) {
            previousNode = currentNode;
            currentNode = currentNode.getNextNode();
        }
        
        // Keep the removed data and delete the last node
        list_t removedData = currentNode.getData();
        previousNode.setNextNode(null); this.size--;

        return removedData;
    }

    /**
     * Removes the data from the beginning of the simply linked list
     */
    @Override
    public list_t removeDataFromStart()
    {
        // Checking if the list is empty. If so return null
        if (this.head == null) {
            return null;
        }

        // Checking if the list has only one node. If so then just empty the list
        if (this.size == 1) {
            list_t removedData = this.head.getData();
            this.head = null; this.size--;

            return removedData;
        }

        // Otherwise store the second node in the sequence and remove the first node
        ListNode<list_t> secondNode = this.head.getNextNode();
        list_t removedData = this.head.getData();
        this.head = secondNode; this.size--;

        return removedData;
    }

    @Override
    public list_t removeDataFromIndex(int index)
    {
        // Checking if the list is empty. If so return null
        if (this.head == null) {
          return null;
        }

        // Checking if the given index is equal to the size of the list, or zero and do the appropriates
        if (index == this.size - 1) { 
            return this.removeDataFromEnd(); 
        }
        else if (index == 0) { 
            return this.removeDataFromStart(); 
        }

        // Checking if the given index is valid according to the list size
        if (index < 0 || index > this.size - 1) {
            System.err.println("Error accessing index " + index + ". List has " + this.size + " elements.");
            return null;
        }

        // Otherwise head to the node at the given index, store its data and delete it
        ListNode<list_t> currentNode = this.head;
        ListNode<list_t> previousNode = this.head;

        for (int i = 0; i < index; i++) {
            previousNode = currentNode;
            currentNode = currentNode.getNextNode();
        }

        list_t removedData = currentNode.getData();
        previousNode.setNextNode(currentNode.getNextNode());
        this.size--;

        return removedData;
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
