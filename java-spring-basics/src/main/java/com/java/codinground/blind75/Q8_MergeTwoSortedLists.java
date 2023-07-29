package com.java.codinground.blind75;

import com.java.codinground.support.ListNode;

/**
 * You are given the heads of two sorted linked lists list1 and list2.
 *
 * Merge the two lists in a one sorted list.
 * The list should be made by splicing together the nodes of the first two lists.
 *
 * Return the head of the merged linked list.
 */
public class Q8_MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(0);

        if (list1 == null && list2 == null) return null;  //null checking
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        if (list1.val > list2.val) { //comparing and merging
            head = list2;
            list2 = list2.next;
        } else {
            head = list1;
            list1 = list1.next;
        }

        head.next = mergeTwoLists(list1, list2);
        return head;
    }
}
