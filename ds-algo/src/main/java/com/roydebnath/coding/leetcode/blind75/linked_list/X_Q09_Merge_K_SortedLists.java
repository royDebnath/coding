package com.roydebnath.coding.leetcode.blind75.linked_list;

import com.roydebnath.coding.leetcode.support.ListNode;

/**
 * 23. Merge k Sorted Lists
 * Hard
 *
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 *
 * Merge all the linked-lists into one sorted linked-list and return it.
 *
 *
 *
 * Example 1:
 *
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 * Example 2:
 *
 * Input: lists = []
 * Output: []
 * Example 3:
 *
 * Input: lists = [[]]
 * Output: []
 *
 * Solution :
 *
 * Merge sort is an example of Divide and Conquer approach where
 * we first divide the problem into the smallest unit possible,
 * solve all the smallest parts and then conquer (merge) the results to arrive at the final solution.
 *
 * We will follow the following steps -
 *
 * 1. Divide the list of lists into the smallest unit possible i.e. a single list.
 * 2. Take two lists at a time and arrange their respective elements in sorted order.
 * 3. Repeat this process for all the pairs of lists
 * 4. Merge these sorted lists
 * 5. The resultant list will be the required answer.
 *
 * Time Complexity
 * There are k lists and let’s say N is the total number of nodes in all the lists,
 * then the time complexity will be O(N * log k).
 *
 * Time Complexity
 * Since we are discarding one half of the array after every iteration, the time complexity will be O(log n).
 *
 * Space Complexity
 * We are not using any data structure for intermediate calculations, hence the space complexity would be O(1).
 */
public class X_Q09_Merge_K_SortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        // Base condition
        if (lists == null || lists.length == 0) {
            return null;
        }
        return mergeKLists(lists, 0, lists.length - 1);
    }

    private ListNode mergeKLists(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        // Mid of list of lists
        int mid = start + (end - start) / 2;
        // Recursive call for left sublist
        ListNode left = mergeKLists(lists, start, mid);
        // Recursive call for right sublist
        ListNode right = mergeKLists(lists, mid + 1, end);
        // Merge the left and right sublist
        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right) {
        // Create a dummy node
        ListNode head = new ListNode(-1);
        // Temp node
        ListNode temp = head;
        // Loop until any of the list becomes null
        while (left != null && right != null) {
            // Choose the value from the left and right which is smaller
            if (left.val < right.val) {
                temp.next = left;
                left = left.next;
            } else {
                temp.next = right;
                right = right.next;
            }
            temp = temp.next;
        }
        // Take all nodes from left list if remaining
        while (left != null) {
            temp.next = left;
            left = left.next;
            temp = temp.next;
        }
        // Take all nodes from right list if remaining
        while (right != null) {
            temp.next = right;
            right = right.next;
            temp = temp.next;
        }
        return head.next;
    }
}
