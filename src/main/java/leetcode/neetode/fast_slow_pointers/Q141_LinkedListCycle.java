package leetcode.neetode.fast_slow_pointers;

import leetcode.support.ListNode;

/**
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 *
 * There is a cycle in a linked list if there is some node in the list that can be reached
 * again by continuously following the next pointer. Internally, pos is used to denote the index
 * of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
 *
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 *
 * Solution :
 *
 *
 */
public class Q141_LinkedListCycle {

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(0);
        ListNode n3 = new ListNode(4);
        head.next=n1;n1.next=n2;n2.next=n3;
        //n3.next=n1;
        System.out.println(hasCycle(head));
    }
    public static boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;

        /**
         * fast pointer is moving one step ahead of slow pointer.
         * if there is no cycle and it is linear, fast.next will be null at some point.
         * if there is a cycle the while will always be true.
         * But, since fast pointer is one step ahead, at some point it will catch the slow pointer.
         * The if condition inside will be true and return.
         * If no cycle the while loop will be false at the end of the list and come out of it.
         * default false will be retured.
         */
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast)
                return true;
        }

        return false;
    }
}
