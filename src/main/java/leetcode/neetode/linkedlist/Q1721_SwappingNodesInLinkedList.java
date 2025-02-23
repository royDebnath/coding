package leetcode.neetode.linkedlist;

import leetcode.support.ListNode;

/**
 * 1721. Swapping Nodes in a Linked List
 * Medium
 * 5K
 * 165
 * Companies
 * You are given the head of a linked list, and an integer k.
 *
 * Return the head of the linked list after swapping the values of the kth node from the beginning and the kth node from the end (the list is 1-indexed).
 *
 *
 *
 * Example 1:
 *
 * Input: head = [1,2,3,4,5], k = 2
 *
 * Output: [1,4,3,2,5]
 * Example 2:
 *
 * Input: head = [7,9,6,6,7,8,3,0,9,5], k = 5
 * Output: [7,9,6,6,8,7,3,0,9,5]
 *
 * Solution :
 *
 * The one-pass approach:
 *
 * In the two-pass approach, before finding the end node (the
 * �
 * �
 * ℎ
 * k
 * th
 *
 *  node from the end), we first need to find the length of the linked list by traversing the complete list. We can optimize this by finding the end node without calculating the length of the linked list. We traverse the linked list using two pointers, end and curr, by keeping the end pointer k positions behind the curr pointer. When the curr pointer reaches the last node, the end pointer is at the
 * �
 * �
 * ℎ
 * k
 * th
 *
 *  last node.
 *
 * Let’s look at the algorithm of the approach discussed above:
 *
 * Initialize the count variable with
 * 0
 * 0
 * .
 * Set the front and end pointers to NULL and the curr pointer to the head node.
 * Traverse the linked list using the curr pointer and increment count on every step.
 * When count becomes equal to k, it means that we’ve reached
 * �
 * �
 * ℎ
 * k
 * th
 *
 *  node from the start. At this point, we perform the following two steps:
 * We set the front pointer to curr node.
 * We set the end pointer to the head node. After doing this, the end node is exactly
 * �
 * k
 *  nodes behind the curr node.
 * We continue traversing the linked list, but along with the curr pointer, we move the end pointer too.
 * When curr reaches the end of the linked list, the end pointer will be pointing to the
 * �
 * �
 * ℎ
 * k
 * th
 *
 *  node from the end of the linked list.
 * We swap the values of the front and end nodes and return the head of the linked list.
 *
 *
 *
 */
public class Q1721_SwappingNodesInLinkedList {
     
    public static void swap(ListNode node1, ListNode node2) {
            int temp = node1.val;
            node1.val = node2.val;
            node2.val = temp;
        }

        public static ListNode swapNodes(ListNode head, int k) {
            if (head == null) {
                return head;
            }
            int count = 0;

            // front and end pointers will be used to track the kth node from
            // the start and end of the linked list, respectively
            ListNode front = null;
            ListNode end = null;
            ListNode curr = head;

            while (curr != null) {
                count += 1;
                // if end is not null, it means the kth node from the beginning has
                // been found, we can start moving the end pointer to find the
                // kth node from the end of the linked list
                if (end != null) {
                    end = end.next;
                }
                // if the count has become equal to k, it means the curr is
                // pointing the kth node at the begining of the linked list
                if (count == k) {
                    front = curr;
                    end = head;
                }
                curr = curr.next;

            }
            // swap the values of two nodes
            swap(front, end);
            return head;
        }
}
