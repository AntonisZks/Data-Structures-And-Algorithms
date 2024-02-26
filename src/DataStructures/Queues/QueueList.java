package DataStructures.Queues;

import DataStructures.Lists.SimplyLinkedList.SimplyLinkedList;

/**
 * A class that represents a queue data structure that uses a simply linked list.
 * @author Antonis Zikas
 * @since 26/02/2024
 */
public class QueueList<queue_t> implements QueueListMethods<queue_t>
{
    private int size;
    private SimplyLinkedList<queue_t> list;

    /**
     * Constructor of the queuelist
     */
    public QueueList() {
        this.size = 0;
        this.list = new SimplyLinkedList<>();
    }

    /**
     * Returns the size of the queuelist
     * @return how many elements the queuelist has
     */
    @Override
    public int getSize() {
        return this.size;
    }

    /**
     * Inserts new data to the end of the queue
     * @param data the data to insert
     * @return true if the insertion was susseful, false otherwise
     */
    @Override
    public boolean enqueue(queue_t data)
    {
        // Insert the data at the end of the list
        if (this.list.insertDataAtEnd(data)) {
            this.size++;
            return true;
        }
        return false;
    }

    /**
     * Removes and returns the data at the beginning of the queuelist
     * @return the first item of the queuelist, null otherwise
     */
    @Override
    public queue_t dequeue()
    {
        // Remove the first item in the list and return it
        queue_t dataToReturn;
        if ((dataToReturn = this.list.removeDataFromStart()) != null) {
            this.size--;
            return dataToReturn;
        }
        return null;
    }

    /**
     * Overloading method for printing the queuelist object
     * @return the queuelist as a string
     */
    @Override
    public String toString()
    {
        // Create a result string variable
        StringBuilder response = new StringBuilder("(");

        // Checking if the queuelist is empty
        if (this.size == 0) {
            return response.append(")").toString();
        }

        // Loop through every itam in the queue list and store its data to the result string
        for (queue_t data : this.list) {
            response.append(data).append(", ");
        }

        response.replace(response.length() - 2, response.length(), ")");
        return response.toString();
    }

    /**
     * Main function to test the queuelist
     * @param args some arguments for command line executions
     */
    public static void main(String[] args) 
    {
        QueueList<Integer> queue = new QueueList<>();

        for (int i = 0; i < 1; i++) {
            queue.enqueue(i + 1);
        }

        System.out.println(queue);
        System.out.println("Data removed is " + queue.dequeue());
        System.out.println("Data removed is " + queue.dequeue());
        System.out.println(queue);
        queue.enqueue(11);
        System.out.println(queue);
    }
}
