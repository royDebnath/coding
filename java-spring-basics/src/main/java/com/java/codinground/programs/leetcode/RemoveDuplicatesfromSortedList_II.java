package com.java.codinground.programs.leetcode;


import com.java.codinground.programs.support.ListNode;

/**
 * Given the head of a sorted linked list, delete all nodes that have duplicate numbers,
 * leaving only distinct numbers from the original list.
 * Return the linked list sorted as well.
 * <p>
 * Input: head = [1,2,3,3,4,4,5]
 * Output: [1,2,5]
 */
public class RemoveDuplicatesfromSortedList_II {
    public static void main(String[] args) {

    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode current = head;
        ListNode before = new ListNode(0);
        before.next = head;
        ListNode finalList = before;
        while (current != null && current.next != null) {
            if (current.val == current.next.val) {
                while (current.next != null && current.val == current.next.val) {
                    current = current.next;
                }
                current = current.next;
                before.next = current;
            } else {
                current = current.next;
                before = before.next;
            }
        }
        return finalList.next;
    }
}