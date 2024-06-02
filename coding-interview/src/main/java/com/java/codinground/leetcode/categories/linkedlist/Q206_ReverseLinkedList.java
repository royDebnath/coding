package com.java.codinground.leetcode.categories.linkedlist;

import com.java.codinground.leetcode.support.ListNode;

/**
 * Intuition
 * To reverse the linked list, we iterate over the original list and rearrange the next pointers
 * without creating a new list. The intuition behind this solution is to take
 * each node and move it to the beginning of the new reversed list as we traverse through the original list.
 * We maintain a temporary node,  referred to as a 'reversedHead' node, which initially points to null,
 * as it will eventually become the tail of the reversed list once all nodes are reversed.
 *
 * */
public class Q206_ReverseLinkedList {

    /**
     * Reverses the given linked list.
     *
     * @param head The head of the original singly-linked list.
     * @return The head of the reversed singly-linked list.
     */
    public ListNode reverseList(ListNode head) {
        // reversedHead node that will help in reversing the list.
        ListNode reversedHead = null;

        // Pointer to traverse the original list.
        ListNode current = head;

        // Iterating through each node in the list.
        while (current != null) {
            // Temporary store the next node.
            ListNode next = current.next;

            // Reversing the link so that current.next points to the new head (reversedHead).
            current.next = reversedHead;

            // Move the reversedHead's next to the current node making it the new head of the reversed list.
            reversedHead = current;

            // Move to the next node in the original list.
            current = next;
        }

        // Return the reversed linked list which is pointed by reversedHead's next.
        return reversedHead;
    }
}
