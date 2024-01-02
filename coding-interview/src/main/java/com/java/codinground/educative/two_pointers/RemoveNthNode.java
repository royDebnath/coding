package com.java.codinground.educative.two_pointers;


import com.java.codinground.support.ListNode;

/**
 * Statement
 * Given a singly linked list, remove the nth node from the end of the list and return its head.
 *
 * Solution summary
 * Two pointers, right and left, are set at the head node.
 * Move the right pointer n steps forward.
 * If right reaches NULL, return head's next node.
 * Move both right and left pointers forward till right reaches the last node.
 * Relink the left node to the node at left's next to the next node.
 * Return head.
 * Time complexity
 * The time complexity is O(n), where n  is the number of nodes in the linked list.
 *
 * Space complexity
 * The space complexity is O(1)
 *  because we use constant space to store two pointers.
 */
public class RemoveNthNode {

    public static ListNode removeNthLastNode(ListNode head, int n) {
        ListNode right = head;
        ListNode left = head;

        for (int i = 0; i < n; i++) {
            right = right.next;
        }

        if (right == null) {
            return head.next;
        }

        while (right.next != null) {
            right = right.next;
            left = left.next;
        }

        left.next = left.next.next;

        return head;
    }
}
