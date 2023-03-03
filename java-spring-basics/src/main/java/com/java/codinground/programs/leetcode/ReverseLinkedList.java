package com.java.codinground.programs.leetcode;

import com.java.codinground.programs.support.ListNode;

/**
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 */
public class ReverseLinkedList {
    public static void main(String[] args) {

    }

    public ListNode reverseList(ListNode head) {
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
        }
        return prev;
    }
    /**
     * 1.  prev(null) | head -> next
     * 2.  prev(null)<-head
     * 3.  null<-prev
     * 4.  null<-prev<-head
     */
}
