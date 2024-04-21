package com.java.codinground.leetcode.categories.linkedlist;

import com.java.codinground.support.ListNode;

/**
 * Medium
 * Topics
 * Companies
 * Given the head of a singly linked list, group all the nodes with odd indices together
 * followed by the nodes with even indices, and return the reordered list.
 *
 * The first node is considered odd, and the second node is even, and so on.
 *
 * Note that the relative order inside both the even and odd groups should remain as it was in the input.
 *
 * You must solve the problem in O(1) extra space complexity and O(n) time complexity.
 *
 *
 *
 */
public class Q328_OddEvenLinkedList {
    public ListNode oddEvenList(ListNode head) {
        // If the list is empty, return null.
        if (head == null) {
            return null;
        }

        // Initialize pointers for manipulation.
        // 'odd' points to the last node in the odd-indexed list.
        // 'even' points to the last node in the even-indexed list.
        // 'evenHead' points to the first node of the even-indexed list.
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;

        // Iterate over the list to rewire nodes.
        while (even != null && even.next != null) {
            // Link the next odd node.
            odd.next = even.next;
            // Move the 'odd' pointer to the next odd node.
            odd = odd.next;

            // Link the next even node.
            even.next = odd.next;
            // Move the 'even' pointer to the next even node.
            even = even.next;
        }

        // After reordering, attach the even-indexed list to the end of the odd-indexed list.
        odd.next = evenHead;

        // Return the head of the modified list.
        return head;
    }
}
