package DataStructures.Stacks;

import DataStructures.Lists.SimplyLinkedList.SimplyLinkedList;

/**
 * Public interface for the stack list methods
 */
interface StackListMethods<stack_t>
{
    int getSize();

    boolean push(stack_t data);
    stack_t pop();
}

/**
 * A class that represents a stack data structure that uses a simply linked list.
 * @author Antonis Zikas
 * @since 25/03/2024
 */
public class StackList<stack_t> implements StackListMethods<stack_t>
{
    private int size;
    private SimplyLinkedList<stack_t> list;

    /**
     * Constructor of the stacklist
     */
    public StackList() {
        this.size = 0;
        this.list = new SimplyLinkedList<>();
    }

    /**
     * Returns the size of the stacklist
     * @return how many elements the stacklist has
     */
    @Override
    public int getSize() {
        return this.size;
    }

    /**
     * Inserts new data to the end of the stack
     * @param data the data to insert
     * @return true if the insertion was successfull, false otherwise
     */
    @Override
    public boolean push(stack_t data)
    {
        // Insert the data at the end of the list
        if (this.list.insertDataAtEnd(data)) {
            this.size++;
            return true;
        } 
        return false;
    }

    /**
     * Removes and returns the data at the beginning of the stacklist
     * @return the first item of the stacklist, null otherwise
     */
    @Override
    public stack_t pop() {
        // Remove the last item in the list and return it
        stack_t dataToReturn;
        if ((dataToReturn = this.list.removeDataFromEnd()) != null) {
            this.size--;
            return dataToReturn;
        }
        return null;
    }

    /**
     * Overloading method for printing the stacklist object
     * @return the stacklist as a string
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
        for (stack_t data : this.list) {
            response.append(data).append(", ");
        }

        response.replace(response.length() - 2, response.length(), ")");
        return response.toString();
    }

    /**
     * Main function to test the stacklist
     * @param args some arguments for command line executions
     */
    public static void main(String[] args) 
    {
        StackList<Integer> queue = new StackList<>();

        for (int i = 0; i < 5; i++) {
            queue.push(i + 1);
        }

        System.out.println(queue);
        System.out.println("Data removed is " + queue.pop());
        System.out.println("Data removed is " + queue.pop());
        System.out.println(queue);
        queue.push(11);
        System.out.println(queue);
    }
}
