package com.roydebnath.coding.leetcode.blind75.linked_list;

import com.roydebnath.coding.leetcode.helper.ListNode;

/**
 *206. Reverse Linked List
 * Easy
 *
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 *
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 */
public class Q44_ReverseLinkedList {
    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        head.next=n1;n1.next=n2;n2.next=n3;n3.next=n4;
        pirntLinkedList(head);
        System.out.println("\n\n========output==========\n");
        pirntLinkedList(reverseList(head));
    }
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            // nextNode to record head.next.
            ListNode nextNode = head.next; // prev(null) | head -> next
            //reverse current head pointer to prev.
            head.next = prev; // prev(null)<-head
            //move prev to head.
            prev = head; // null<-prev
            // move head to next node.
            head = nextNode; // null<-prev<-head
            // at each iteration, head moves one step right
            //and previous moves one step right
            //head points to right. previous points to left(reverse)
            //thus at the end of the loop previous points the linked list in the reverse order
            //and head becomes null
        }
        return prev;
    }
    /**
     * 1.  prev(null) | head -> next
     * 2.  prev(null)<-head
     * 3.  null<-prev
     * 4.  null<-prev<-head
     */

    private static void pirntLinkedList(ListNode head) {
        ListNode current = head;
        System.out.print(current.val + "->");
        while (current.next != null) {
            if (current.next.next == null) {
                System.out.print(current.next.val + "->|");
            } else {
                System.out.print(current.next.val + "->");
            }
            current = current.next;
        }
    }
}
