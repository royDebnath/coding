package com.roydebnath.coding.leetcode.neetode.linkedlist;

import com.roydebnath.coding.leetcode.support.ListNode;

/**
 * 2074. Reverse Nodes in Even Length Groups
 * Medium
 * 570
 * 301
 * Companies
 * You are given the head of a linked list.
 *
 * The nodes in the linked list are sequentially assigned to non-empty groups whose lengths form the sequence of the natural numbers (1, 2, 3, 4, ...). The length of a group is the number of nodes assigned to it. In other words,
 *
 * The 1st node is assigned to the first group.
 * The 2nd and the 3rd nodes are assigned to the second group.
 * The 4th, 5th, and 6th nodes are assigned to the third group, and so on.
 * Note that the length of the last group may be less than or equal to 1 + the length of the second to last group.
 *
 * Reverse the nodes in each group with an even length, and return the head of the modified linked list.
 *
 *
 * Input: head = [5,2,6,3,9,1,7,3,8,4]
 * Output: [5,6,2,3,9,1,4,8,3,7]
 * Explanation:
 * - The length of the first group is 1, which is odd, hence no reversal occurs.
 * - The length of the second group is 2, which is even, hence the nodes are reversed.
 * - The length of the third group is 3, which is odd, hence no reversal occurs.
 * - The length of the last group is 4, which is even, hence the nodes are reversed.
 * Example 2:
 *
 * Input: head = [1,1,0,6]
 * Output: [1,0,1,6]
 * Explanation:
 * - The length of the first group is 1. No reversal occurs.
 * - The length of the second group is 2. The nodes are reversed.
 * - The length of the last group is 1. No reversal occurs.
 * Example 3:
 *
 * Input: head = [1,1,0,6,5]
 * Output: [1,0,1,5,6]
 * Explanation:
 * - The length of the first group is 1. No reversal occurs.
 * - The length of the second group is 2. The nodes are reversed.
 * - The length of the last group is 2. The nodes are reversed.
 *
 * Solution
 * We need to reverse the groups in the linked list with an even number of nodes. For this, we use the in-place reversal of the linked list. The first task is to identify these groups, and once they are identified, we can safely reverse the nodes present in these groups. Once all nodes are processed, we return the head of the modified linked list.
 *
 * Following is the algorithm we will follow to achieve the solution to this problem:
 *
 * We first initialize two variables, prev and groupLen, to point to the current group’s previous node and the current group’s length, respectively. We will set groupLen to 2 at the beginning, as the first node of the linked list cannot be reversed since its group’s length is odd, i.e., 1.
 *
 * Iterate the nodes of the linked list, and in each iteration, we set a variable node to track the current node and numNodes to account for the number of nodes in the current group.
 *
 * Next, we check the current group and count the nodes in it using numNodes. If the current group’s length is odd, it means that the group needs to be skipped, so the prev pointer is moved to the current node to start the next group’s processing.
 *
 * For groups with an even number of nodes, we will follow this procedure to reverse the nodes:
 *
 * We maintain three pointers, i.e., prev, curr, and reverse to point to the node immediately before the current group, to the first node of the current group, and to the next node of the current group, respectively.
 *
 * We loop over the length of the group to reverse the nodes. For each node in the group, we save the reference to the next node, currNext, since we will change curr’s next pointer.
 *
 * Next, make curr.next point to the previous node reverse. This step effectively reverses the pointer direction.
 *
 * Move reverse to the current node curr because it will be the next node in the reversed group.
 *
 * Then, move curr to the next node in the original order currNext to continue the reversal process.
 *
 * Once the whole group is reversed, we update the prev.next to the last node and prev to the first node of the current group.
 */
public class Q2074_ReverseNodesEvenLengthGroups {

    public static ListNode reverseEvenLengthGroups(ListNode head) {
        // Node immediately before the current group
        ListNode prev = head;
        ListNode node, reverse, currNext, curr, prevNext = null;

        // The head doesn't need to be reversed since it's a group of one node, 
        // so starts with length 2
        int groupLen = 2;
        int numNodes = 0;
        while (prev.next != null) {
            node = prev;
            numNodes = 0;

            // traversing all the nodes of the current group
            for (int i = 0; i < groupLen; i++) {
                if (node.next == null)
                    break;
                numNodes += 1;
                node = node.next;
            }

            // odd length
            if (numNodes % 2 != 0) {
                prev = node;
            }

            // even length
            else {
                reverse = node.next;
                curr = prev.next;
                for (int j = 0; j < numNodes; j++) {
                    currNext = curr.next;
                    curr.next = reverse;
                    reverse = curr;
                    curr = currNext;
                }

                // updating the prev pointer after reversal of the even group
                prevNext = prev.next;
                prev.next = node;
                prev = prevNext;
            }

            // increment 1 by one and move to the next group
            groupLen += 1;
        }
        return head;
    }
}
