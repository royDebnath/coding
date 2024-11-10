package com.roydebnath.coding.leetcode.neetode.linkedlist;

import com.roydebnath.coding.leetcode.support.ListNode;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * You must solve the problem without modifying the values in the list's nodes
 * (i.e., only nodes themselves may be changed.)
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
 *  *
 *  * Intuition
 *  * Traverse the list and swap pairs of nodes one by one.
 *  *
 *  * Approach
 *  * The node "ans" is to point to the head of the original list.
 *  It then uses a "curr" node to traverse the list and swap pairs of nodes.
 *  The loop continues as long as there are at least two more nodes to swap.
 *  *
 *  * Inside the loop, the solution uses two temporary nodes, "t1" and "t2",
 *  to hold the first and second nodes of the pair.
 *  Then, it updates the pointers to swap the nodes, and moves "curr" two nodes ahead.
 *  At the end, it returns the modified list starting from the next node of the "ans" node.
 */
public class Q24_SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode ans =new ListNode(0);
        ans.next=head;

        ListNode curr=head;
        while (curr.next != null && curr.next.next != null) {
            //Get the pair of nodes t1, t2 to swap, t1 -> t2
            ListNode t1 = curr.next; // first node
            ListNode t2 = curr.next.next; // second node
           //swap first and second node of the pair
            curr.next = t2; // bring the second node to first position
            t1.next = t2.next; // make first node point to third node (second's next)
            t2.next = t1; // make the second point to first

            // now order is changed to second -> first , t2 -> t1
           //move two steps for the next loop
            curr = curr.next.next;
        }
        return ans.next;
    }
}
