/* Filename: SimplyLinkedListMethods.java */

package DataStructures.Lists.SimplyLinkedList;

/**
 * Public Interface for the simply linked list's methods
 * @author Antonis Zikas
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

    /* Other Methods */
    boolean contains(list_t data);
    int indexOf(list_t data);

    /* Printing Methods */
    void print(String seperator);
    void print();
}
