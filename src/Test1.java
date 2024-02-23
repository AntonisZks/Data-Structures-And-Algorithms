import DataStructures.Lists.SimplyLinkedList.SimplyLinkedList;

public class Test1
{
    public static void main(String[] args)
    {
        SimplyLinkedList<Integer> myList = new SimplyLinkedList<>();

        for (int i = 0; i < 3; i++) {
            if (!myList.insertDataAtIndex(i + 1, i)) {
                System.err.println("Error on inserting!");
                break;
            }
        }

        myList.print(", ");

        Integer data = myList.removeDataFromIndex(0);
        if (data == null) {
            System.out.println("No data existing!");
        }

        myList.print(", ");
    }
}
