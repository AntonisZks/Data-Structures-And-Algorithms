import DataStructures.Lists.SimplyLinkedList.SimplyLinkedList;

public class Test1
{
    public static void main(String[] args)
    {
        SimplyLinkedList<Integer> myList = new SimplyLinkedList<>();

        for (int i = 0; i < 10; i++) {
            myList.insertDataAtEnd(i + 1);
        }

        myList.print();
        myList.print(", ");
    }
}
