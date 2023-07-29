package com.java.codinground.blind75;

/**
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 * Example 2:
 *
 * Input: head = [1], n = 1
 * Output: []
 * Example 3:
 *
 * Input: head = [1,2], n = 1
 * Output: [1]
 *
 * Solution :
 *
 * We are given a linked list and a number n,
 * and we are required to remove the nth from the end of the list.
 * Since we are removing the nth node, we need to link next pointer of (n - 1)th node to the (n + 1)th node.
 *
 * Once we remove the node, we need to return the head of the modified list.
 *
 * 1. Initialize two pointers slow and fast, pointing to the head of the linked list.
 * 2. Move fast pointer n steps ahead.
 * 3. Now, move both slow and fast one step at a time unless fast reaches to the end. The fast pointer will definitely reach to the end before slow because it is ahead.
 * 4. When we fast pointer reaches to the end, the slow pointer will be n steps behind it i.e., n steps behind the end of the list.
 * 5. At that point, we will remove the node and link it to the next of current node.
 */
public class Q6_RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Two pointers - fast and slow
        ListNode slow = head;
        ListNode fast = head;
        // Move fast pointer n steps ahead
        for (int i = 0; i < n; i++) {
            if (fast.next == null) {
                // If n is equal to the number of nodes, delete the head node
                if (i == n - 1) {
                    head = head.next;
                }
                return head;
            }
            fast = fast.next;
        }
        // Loop until we reach to the end.
        // Now we will move both fast and slow pointers
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        // Delink the nth node from last
        if (slow.next != null) {
            slow.next = slow.next.next;
        }
        return head;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }
}

