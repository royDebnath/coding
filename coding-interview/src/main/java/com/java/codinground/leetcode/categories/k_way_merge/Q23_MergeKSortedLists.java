package com.java.codinground.leetcode.categories.k_way_merge;

import com.java.codinground.leetcode.support.ListNode;

import java.util.PriorityQueue;

public class Q23_MergeKSortedLists {

    public ListNode mergeKListsMinHeap(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(lists.length, (a, b) -> a.val - b.val);
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;

        /**
         * Add all the head/lists to heap.
         * By default the smallest will be the first element, as all the lists are already sorted
         * and priority queue will put the smallest of all heads first
         */
        for (ListNode head : lists) {
            if (head != null)
                minHeap.add(head);
        }


        /**
         * now the heads of linked lists i.e all the smallest of the lists are there in the heap.
         * poll will give the smallest of them all. add this to the result.
         * take the smallest.next
         * push to the heap, it will automatically again calculate the new smallest between
         * the smallest.next and all other heads(smallest of lists)
         * poll the front and get the smallest again.
         * repeat this till the queue is empty.
         */
        while (!minHeap.isEmpty()) {
            prev.next = minHeap.poll();
            prev = prev.next;

            if (prev.next != null) {
                minHeap.add(prev.next);
            }
        }
        return dummy.next;
    }

    /**
     * Without priority queue, using recursion
     */

    public ListNode mergeKLists(ListNode[] lists) {
        // corner case
        if (lists == null || lists.length == 0) return null;

        ListNode head = merge(lists, 0, lists.length - 1);
        return head;
    }

    // return new head after merging list[left : right]
    public ListNode merge(ListNode[] lists, int left, int right) {
        // base case
        if (left == right) return lists[left];

        int mid = left + (right - left) / 2;
        ListNode l1 = merge(lists, left, mid); // merge [left : mid]
        ListNode l2 = merge(lists, mid + 1, right); // merge [mid + 1 : right]

        return mergeTwoLists(l1, l2);
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        ListNode cur1 = l1;
        ListNode cur2 = l2;
        while (cur1 != null || cur2 != null) {
            if (cur1 == null) {
                cur.next = cur2;
                break;
            } else if (cur2 == null) {
                cur.next = cur1;
                break;
            } else {
                if (cur1.val <= cur2.val) {
                    cur.next = cur1;
                    cur1 = cur1.next;
                } else {
                    cur.next = cur2;
                    cur2 = cur2.next;
                }
                cur = cur.next;
            }

        }

        return dummy.next;
    }
}
