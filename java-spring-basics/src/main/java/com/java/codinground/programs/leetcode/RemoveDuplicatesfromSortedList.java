package com.java.codinground.programs.leetcode;

import com.java.codinground.support.ListNode;

/**
 * Given the head of a sorted linked list,
 * delete all duplicates such that each element appears only once.
 * Return the linked list sorted as well.
 * <p>
 * Input: head = [1,1,2]
 * Output: [1,2]
 */
public class RemoveDuplicatesfromSortedList {
    public static void main(String[] args) {

    }

    private static ListNode removeDuplicatesFromLinkedList(ListNode head) {
        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.val == current.next.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }
}

