package com.java.codinground.educative.linkedlist;

import com.java.codinground.support.ListNode;

/**
 * 24. Swap Nodes in Pairs
 * Medium
 * 11.1K
 * 401
 * Companies
 * Given a linked list, swap every two adjacent nodes and return its head.
 * You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
 *
 * Input: head = [1,2,3,4]
 * Output: [2,1,4,3]
 * Example 2:
 *
 * Input: head = []
 * Output: []
 * Example 3:
 *
 * Input: head = [1]
 * Output: [1]
 *
 * Solution :
 *
 * Intuition
 * Traverse the list and swap pairs of nodes one by one.
 *
 * Approach
 * The node "ans" is to point to the head of the original list. It then uses a "curr" node to traverse the list and swap pairs of nodes. The loop continues as long as there are at least two more nodes to swap.
 *
 * Inside the loop, the solution uses two temporary nodes, "t1" and "t2", to hold the first and second nodes of the pair. Then, it updates the pointers to swap the nodes, and moves "curr" two nodes ahead. At the end, it returns the modified list starting from the next node of the "ans" node.
 */
public class SwapNodesInPairs {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public ListNode swapPairs(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode ans =new ListNode(0);
            ans.next=head;
            ListNode curr=ans;
            while (curr.next != null && curr.next.next != null) {
                ListNode t1 = curr.next;
                ListNode t2 = curr.next.next;
                curr.next = t2;
                t1.next = t2.next;
                t2.next = t1;
                curr = curr.next.next;
            }
            return ans.next;
        }
    }
}
