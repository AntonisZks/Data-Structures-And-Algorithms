import DataStructures.Lists.SimplyLinkedList.ListNode;
import DataStructures.Lists.SimplyLinkedList.SimplyLinkedList;


public class Test1 {
    public static void main(String[] args) {
        ListNode<Integer> myNode = new ListNode<Integer>(10, null);
        ListNode<Integer> myNode2 = new ListNode<Integer>(15, null);

        myNode.setNextNode(myNode2);

        myNode.print();
        myNode.getNextNode().print();
    }
}
