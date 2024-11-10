package com.roydebnath.coding.leetcode.neetode.linkedlist;

import com.roydebnath.coding.leetcode.helper.ListNode;

/**
 * Problem Description
 * The problem presents us with a singly linked list and requires us to sort it in ascending order.
 *
 * Intuition
 * The given solution uses a divide and conquer strategy.
 * Specifically, it uses the merge sort algorithm adapted for linked lists.
 *
 * Here's the intuition behind this approach:
 *
 * Divide phase:
 * First, we split the linked list into two halves.
 * This is done by using a fast and slow pointer approach to find the middle of the linked list.
 * The slow pointer moves one step at a time, while the fast pointer moves two steps.
 * When the fast pointer reaches the end of the list, the slow pointer will be at the middle.
 * We then break the list into two parts from the middle.
 *
 * Conquer phase:
 * We recursively call the sortList function on these two halves.
 * Each recursive call will further split the lists into smaller sublists until
 * we are dealing with sublists that either have a single node or are empty.
 *
 * Merge phase:
 * After the sublists are sorted, we need to merge them back together.
 * We use a helper pointer to attach nodes in the correct order (the smaller value first).
 * This is similar to the merge operation in the traditional merge sort algorithm on arrays.
 *
 * Base case:
 * The recursion stops when we reach a sublist with either no node or just one node,
 * as a list with a single node is already sorted.
 *
 * By following these steps, the sortList function continues to split and merge until
 * the entire list is sorted. The dummy node (dummy in the code) is used to simplify the merge phase,
 * so we don't have to handle special cases when attaching the first node to the sorted part of the list.
 * At the end, dummy.next will point to the head of our sorted list, which is what we return.
 *
 * Solution Approach
 * The implementation follows the intuition outlined previously and can be broken down as follows:
 *
 * Base Case Handling:
 * The function first checks whether the linked list is empty or contains only a single node
 * by looking for head is None or head.next is None. If either condition holds true,
 * it returns head as it stands for an already sorted list or no list at all.
 *
 * Dividing the List:
 * The list is split into two halves by employing two pointers, slow and fast.
 * These pointers start at head with fast one step ahead (pointing to head.next).
 * They then loop through the list, with slow moving one node at a time (slow.next) and
 * fast moving two nodes at a time (fast.next.next).
 * This continues until fast reaches the end of the list. At this point,
 * slow points to the node just before the midpoint of the list.
 *
 * The split is performed by setting t to slow.next (which is the start of the second half of the list)
 * and then severing the list by setting slow.next to None.
 * This leaves us with two separate lists: one starting from head and ending at slow, and
 * the other starting from t.
 *
 * Recursive Sorting: The function is recursively called on the two halves of the list,
 * self.sortList(head) for the first half and self.sortList(t) for the second half.
 * The recursive calls continue to split the sublists until they can no longer be split (i.e., the base case).
 *
 * Merging: Once the base cases return, the merge phase begins.
 *
 * A dummy node is created with dummy = ListNode(), which serves as the starting node of the sorted list.
 *
 * A cur pointer references it and is used to keep track of the last node in the sorted list
 * as we merge nodes from l1 and l2.
 *
 * In a loop, we compare the values of the nodes at the heads of l1 and l2.
 * We attach the smaller node to cur.next and advance the corresponding pointer (l1 or l2) as well as cur.
 *
 * This loop continues until either l1 or l2 is exhausted. Once that happens,
 * the remaining nodes of the non-exhausted list are appended to the end of the merged list because
 * they are already sorted. This is done by the line cur.next = l1 or l2.
 *
 * Returning the Sorted List:
 * The head of the dummy node (dummy) does not contain any value and was just a placeholder to
 * ease the merge process. Therefore, dummy.next refers to the first node of the merged, sorted list,
 * which is the output of the function sortList.
 *
 * In summary, this solution utilizes the merge sort algorithm adapted for linked lists
 * and employs a recursive divide and conquer approach to sorting. It's efficient and effective
 * for sorting linked lists as it doesn't rely on random access memory and works well with the
 * sequential nature of a linked list.
 *
 * Example Walkthrough
 * Let's illustrate the solution approach using a small example. Imagine we have a linked list:
 *
 * 4 -> 2 -> 1 -> 3
 * We want to sort this list in ascending order using the merge sort algorithm designed for linked lists.
 *
 * Base Case Handling: Check if the list is empty or has one node. Our list has multiple nodes, so we move to dividing the list.
 *
 * Dividing the List: We create two pointers, slow and fast. They start off with fast being one node ahead. As we step through the list, slow ends up on node 2 and fast ends up past node 3 (signifying the end of the list).
 *
 * After the while loop, slow is on 2, so we split the list into head (pointing at 4) to slow (pointing at 2) and t (pointing at 1) to the end.
 * Recursive Sorting: We now call sortList(head) which sorts the sublist 4 -> 2, and sortList(t) which sorts the sublist 1 -> 3.
 *
 * In the sublist 4 -> 2, we would further divide it into 4 and 2, and since these are single nodes, they serve as our base cases and are already sorted.
 *
 * Similarly, the sublist 1 -> 3 is divided into 1 and 3. Again, these are single nodes and are sorted by definition.
 *
 * Merging: We have our sublists sorted: 4, 2, 1, and 3. We now need to merge them. This is done using the dummy node approach and comparing one by one.
 *
 * First, 2 and 4 are merged into 2 -> 4.
 * Then 1 and 3 are merged into 1 -> 3.
 * Finally, we merge 2 -> 4 and 1 -> 3 into one sorted list. Our pointers would compare 2 and 1 and choose 1, moving one step. Compare 2 and 3 and choose 2, and so forth until the list is fully merged into 1 -> 2 -> 3 -> 4.
 * Returning the Sorted List: The dummy.next will point to 1 which is the start of our sorted linked list, and that's what we return. Thus, we have successfully sorted the original linked list using the merge sort algorithm: 1 -> 2 -> 3 -> 4.
 *
 *
 */
public class Q148_SortList {

    /**
     * Sorts a linked list using the merge sort algorithm.
     *
     * @param head The head node of the linked list.
     * @return The sorted linked list.
     */
    public ListNode sortList(ListNode head) {
        // Base cases: if the list is empty or has just one element, it is already sorted.
        if (head == null || head.next == null) {
            return head;
        }

        // Find the midpoint of the list using the slow and fast pointer approach.
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next; // moves one step at a time
            fast = fast.next.next; // moves two steps at a time
        }

        // Split the list into two halves.
        ListNode mid = slow.next;
        slow.next = null;

        // Recursively sort each half.
        ListNode leftHalf = sortList(head);
        ListNode rightHalf = sortList(mid);

        // Merge the two halves and return the merged sorted list.
        return merge(leftHalf, rightHalf);
    }

    /**
     * Merges two sorted linked lists into one sorted linked list.
     *
     * @param list1  The head node of the first sorted linked list.
     * @param list2 The head node of the second sorted linked list.
     * @return The head node of the merged sorted linked list.
     */
    private ListNode merge(ListNode list1, ListNode list2) {
        // Create a dummy node to serve as the starting point for the merged list.
        ListNode dummyHead = new ListNode();

        // Use a pointer to build the new sorted linked list.
        ListNode current = dummyHead;
        while (list1 != null && list2 != null) {
            // Choose the node with the smaller value from either left or right,
            // and append it to the current node of the merged list.
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        // If any nodes remain in either list, append them to the end of the merged list.
        current.next = (list1 == null) ? list2 : list1;

        // Return the head of the merged sorted list, which is the next node of the dummy node.
        return dummyHead.next;
    }
}