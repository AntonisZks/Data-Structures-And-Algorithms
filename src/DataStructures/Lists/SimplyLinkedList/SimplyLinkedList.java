/* Filename: SimplyLinkedList.java */

package DataStructures.Lists.SimplyLinkedList;

import java.util.Iterator;

/**
 * Public Interface for the simply linked list's methods
 * @author Antonis Zikas
 */
interface SimplyLinkedListMethods<list_t>
{
    /* Inserting Methods */
    boolean insertDataAtEnd(list_t data);
    boolean insertDataAtStart(list_t data);
    boolean insertDataAtIndex(list_t data, int index);

    /* Removing Methods */
    list_t removeDataFromEnd();
    list_t removeDataFromStart();
    list_t removeDataFromIndex(int index);

    /* Other Methods */
    boolean contains(list_t data);
    int indexOf(list_t data);
    SimplyLinkedList<list_t> getReveresedVersion();

    /* Printing Methods */
    void print(String seperator);
    void print();
}

/**
 * A class that represents a simply linked list data structure
 * @author Antonis Zikas
 * @since 24/02/2024
 */
public class SimplyLinkedList<list_t> implements SimplyLinkedListMethods<list_t>, Iterable<list_t>
{
    // TODO: Implement replace methods.
    // TODO: Implement other methods like getSize(), etc.

    private static final String DEFAULT_SEPERATOR = " -> "; // Default seperator character for the data printing

    // Data of the simply linked list
    protected SimplyLinkedListNode<list_t> head;
    protected int size;

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
    public final boolean insertDataAtEnd(list_t data)
    {
        // Creating a new simply linked list node
        SimplyLinkedListNode<list_t> newNode = new SimplyLinkedListNode<>(data, null);

        // Checking if the list is empty. If so add the new node and update the size of the list
        if (this.head == null) {
            this.head = newNode; this.size++;
            return true;
        }

        // Initialize a current node and loop until the very end of the list
        SimplyLinkedListNode<list_t> currentNode = this.head;

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
    public final boolean insertDataAtStart(list_t data)
    {
        // Creating a new simply linked list node
        SimplyLinkedListNode<list_t> newNode = new SimplyLinkedListNode<>(data, null);

        // Checking if the list is empty. If so add the new node and update the size of the list
        if (this.head == null) {
            this.head = newNode; this.size++;
            return true;
        }
        // Get the first node of the list and insert the new node before that
        SimplyLinkedListNode<list_t> firstNode = this.head;
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
    public final boolean insertDataAtIndex(list_t data, int index)
    {
        // Creating a new simply linked list node
        SimplyLinkedListNode<list_t> newNode = new SimplyLinkedListNode<>(data, null);

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
        SimplyLinkedListNode<list_t> currentNode = this.head;

        for (int i = 0; i < index - 1; i++) {
            currentNode = currentNode.getNextNode();
        }

        // Store the next node in the sequence and store the new node between the current and the next ones
        SimplyLinkedListNode<list_t> nextNode = currentNode.getNextNode();
        currentNode.setNextNode(newNode);
        newNode.setNextNode(nextNode);

        return true;
    }

    /**
     * Removes and returns the data at the end of the simply linked list
     */
    @Override
    public final list_t removeDataFromEnd()
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
        SimplyLinkedListNode<list_t> currentNode = this.head;
        SimplyLinkedListNode<list_t> previousNode = this.head;

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
     * Removes and returns the data from the beginning of the simply linked list
     * @return the first item in the list, null otherwise
     */
    @Override
    public final list_t removeDataFromStart()
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
        SimplyLinkedListNode<list_t> secondNode = this.head.getNextNode();
        list_t removedData = this.head.getData();
        this.head = secondNode; this.size--;

        return removedData;
    }

    /**
     * Removes and returns the data at the given index inside the simply linked list
     * @param index the index from which the data will be removed
     */
    @Override
    public final list_t removeDataFromIndex(int index)
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
        SimplyLinkedListNode<list_t> currentNode = this.head;
        SimplyLinkedListNode<list_t> previousNode = this.head;

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
     * Overloading method for printing the simply linked list object
     * @return the list as a string
     */
    @Override
    public String toString()
    {
        // Create a result string variable
        StringBuilder response = new StringBuilder("[");
        SimplyLinkedListNode<list_t> currentNode = this.head;

        // Loop through every node in the list and store its data to the result string
        while (currentNode != null)
        {
            response.append(currentNode.getData().toString());
            if (currentNode.getNextNode() != null) {
                response.append(SimplyLinkedList.DEFAULT_SEPERATOR);
            }
            currentNode = currentNode.getNextNode();
        }
        response.append("]");

        return response.toString();
    }

    /**
     * Advanced printing of the simply linked list's sequence of data
     * @param seperator a character seperator for the data printing
     */
    @Override
    public final void print(String seperator)
    {
        System.out.print("[");

        // Checking if the list is empty
        if (this.head == null) { System.out.println("]"); return; }

        SimplyLinkedListNode<list_t> currentNode = this.head;

        // Loop through every node in the list and print its content
        while (currentNode != null) 
        {
            System.out.print(currentNode.getData());
            if (currentNode.getNextNode() == null) { // Printing a seperator character if necessary
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
    public final void print() {
        this.print(SimplyLinkedList.DEFAULT_SEPERATOR);
    }

    /**
     * Searches for the give data in the list
     * @return true is data found, false if data not found
     */
    @Override
    public boolean contains(list_t data)
    {
        // Initialize a current node
        SimplyLinkedListNode<list_t> currentNode = this.head;

        // Search for the given data in the list and if found return true
        while (currentNode != null) {
            if (currentNode.getData() == data) {
                return true;
            }
            currentNode = currentNode.getNextNode();
        }
        return false; // Otherwise, no data found
    }

    /**
     * Returns the index of the given data in the list, if exist
     * @return the index of the data in the list, -1 if not exist
     */
    @Override
    public int indexOf(list_t data)
    {
        // Initialize a current node and a counter variable
        SimplyLinkedListNode<list_t> currentNode = this.head;
        int indexCounter = 0;

        // Search for the given data in the list and if found return its index
        while (currentNode != null) {
            if (currentNode.getData().equals(data)) {
                return indexCounter;
            }
            currentNode = currentNode.getNextNode();
            indexCounter++;
        }
        return -1; // Otherwise return -1 indicating that the data was not found in the list
    }

    /**
     * Iterable method to iterate the list using 'foreach' loop
     * @return an iterator object
     */
    @Override
    public Iterator<list_t> iterator()
    {
        // Construct a new iterator object and return it
        return new Iterator<>() {
            private SimplyLinkedListNode<list_t> currentNode = head;

            @Override
            public boolean hasNext() {
                return currentNode != null; // Determine whether there is a next node in the sequence
            }

            @Override
            public list_t next() {
                // Return the data of the current node, and set it to the next one
                list_t dataToReturn = currentNode.getData();
                currentNode = currentNode.getNextNode();

                return dataToReturn;
            }
        };
    }

    /**
     * Returns the same list but reversed
     * @return the reversed version of this list
     */
    @Override
    public SimplyLinkedList<list_t> getReveresedVersion()
    {
        // Initialize a new simply linked list and a current node to iterate
        SimplyLinkedList<list_t> resultList = new SimplyLinkedList<>();
        SimplyLinkedListNode<list_t> currentNode = this.head;

        // Loop through every node in the list and add it in the beginning of the reversed one
        while (currentNode != null) {
            resultList.insertDataAtStart(currentNode.getData());
            currentNode = currentNode.getNextNode();
        }

        return resultList;
    }

    /**
     * Main function to test the Simply linked list class and its functionality
     * @param args some arguments for command line executions
     * @author Antonis Zikas
     */
    public static void main(String[] args) 
    {
        SimplyLinkedList<Integer> list = new SimplyLinkedList<>();

        for (Integer data : list) {
            System.out.println(data);
        }
    }
}
