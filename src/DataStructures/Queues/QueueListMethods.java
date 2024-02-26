package DataStructures.Queues;

/**
 * Public interface for the queue list methods
 */
public interface QueueListMethods<queue_t>
{
    int getSize();

    boolean enqueue(queue_t data);
    queue_t dequeue();
}
