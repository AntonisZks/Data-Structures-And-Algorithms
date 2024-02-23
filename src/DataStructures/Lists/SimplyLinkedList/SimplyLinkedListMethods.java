/* Filename: SimplyLinkedListMethods.java */

package DataStructures.Lists.SimplyLinkedList;

/**
 * Public Interface for the simply linked list's methods
 */
public interface SimplyLinkedListMethods<list_t>
{
    /* Inserting Methods */
    boolean insertDataAtEnd(list_t data);
    boolean insertDataAtStart(list_t data);
    boolean insertDataAtIndex(list_t data, int index);

    /* Removing Methods */
    list_t removeDataFromEnd();
    list_t removeDataFromStart();
    list_t removeDataFromIndex(int index);

    /* Printing Methods */
    void print(String seperator);
    void print();
}