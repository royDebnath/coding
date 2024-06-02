package com.java.codinground.leetcode.categories.fast_slow_pointers;

import com.java.codinground.support.ListNode;

/**
 * Statement
 * Given the head of a singly linked list, return the middle node of the linked list.
 * If the number of nodes in the linked list is even, there will be two middle nodes,
 * so return the second one.
 *
 * Solution :
 *
 * The slow pointer traverses the linked list one step at a time,
 * while the fast pointer takes two steps at a time.
 * This makes the fast pointer reach the end of the linked list in n/2 iterations,
 * and the slow pointer, by this time, reaches the middle of the linked list.
 */
public class Q876_MiddleOfLinkedList {
        public static ListNode middleNode(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

        // Driver code
}
