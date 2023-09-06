package com.java.codinground.educative.linkedlist;

import com.java.codinground.support.ListNode;

/**
 * To reverse the linked list, we will follow these steps:
 *
 * Initialize three pointers: prev, next, and curr. The prev and next pointers are initialized as NULL,
 * while the curr pointer is initialized to the head of the linked list.
 *
 * Iterate over the linked list. While iterating, perform the following steps:
 *
 * 		- Before changing the next of curr, store the next node using the following line of code next = curr.next.
 *
 * 		- Now, we will assign the next pointer of curr to the prev pointer using the following line of code curr.next = prev.
 * 		  The effect of this line of code is that it will reverse the pointer from forward to backward to reverse the linked list.
 *
 * 		- After reversing the pointer, we’ll update prev as curr and curr as next using prev = curr and curr = next respectively.
 *
 * After reversing the whole linked list, we’ll change the head pointer to the prev pointer because prev will be pointing to the new head node.
 *
 */
class ReverseLinkedList {
    public static ListNode reverse(ListNode head) {
        // initialize prev and next pointer to NULL
        ListNode prev = null;
        ListNode next = null;
        // set current pointer to the head node
        ListNode curr = head;
        // while the current pointer is not NULL
        while (curr != null) {
            // set the next pointer to the next node in the list
            next = curr.next;
            // reverse the current node's pointer to point to the previous node
            curr.next = prev;
            // set the previous pointer to the current node
            prev = curr;
            // move the current pointer to the next node
            curr = next;
        }
        // set the head pointer to the last node, which is the new first node after reversal
        head = prev;
        // return the new head pointer
        return head;
    }
}