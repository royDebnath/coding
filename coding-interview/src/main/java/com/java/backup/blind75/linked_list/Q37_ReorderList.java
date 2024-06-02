package com.java.backup.blind75.linked_list;

import com.java.codinground.leetcode.support.ListNode;

/**
 * 143. Reorder List
 * Medium
 *
 * You are given the head of a singly linked-list. The list can be represented as:
 *
 * L0 → L1 → … → Ln - 1 → Ln
 * Reorder the list to be on the following form:
 *
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * You may not modify the values in the list's nodes. Only nodes themselves may be changed.
 *
 *
 */
public class Q37_ReorderList {
    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        ListNode n7 = new ListNode(7);
        head.next=n1;n1.next=n2;n2.next=n3;n3.next=n4;n4.next=n5;n5.next=n6;n6.next=n7;
        printLinkedList(head);
        reorderList(head);
        System.out.println("\nOutput\n");
        printLinkedList(head);
    }

    public static void reorderList(ListNode head) {
        if (head == null) {
            return;
        }

        // Find the middle node
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Reverse the second half
        ListNode head2 = reverse(slow.next);
        slow.next = null;

        /**
         * At this point:
         * head : 0->1-2->3
         * head2 : 7->6->5->4
         */
        // Link the two halves together
        while (head != null && head2 != null) {
            ListNode tmp1 = head.next; //tmp1=1
            ListNode tmp2 = head2.next; // tmp2=6
            head2.next = head.next; //head2.next=1
            head.next = head2; // head.next = 7
            // at this point
            // head : 0->7->1
            head = tmp1; // head : 1->2->3
            head2 = tmp2; // head2 : 6->5->4
        }
    }

    private static ListNode reverse(ListNode n) {
        ListNode prev = null;
        ListNode cur = n;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }
        return prev;
    }
    private static void printLinkedList(ListNode head) {
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
