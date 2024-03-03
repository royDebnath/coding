package com.java.codinground.programs.leetcode.top100;

import com.java.codinground.support.ListNode;

/**
 * Intuition
 * To reverse the linked list, we iterate over the original list and rearrange the next pointers without creating a new list. The intuition behind this solution is to take each node and move it to the beginning of the new reversed list as we traverse through the original list. We maintain a temporary node, often referred to as a 'dummy' node, which initially points to null, as it will eventually become the tail of the reversed list once all nodes are reversed.
 *
 * We iterate from the head towards the end of the list, and with each iteration, we do the following:
 *
 * Temporarily store the next node (since we are going to disrupt the next reference of the current node).
 * Set the next reference of the current node to point to what is currently the first node of the reversed list (initially, this is null or dummy.next).
 * Move the dummy's next reference to the current node, effectively placing the current node at the beginning of the reversed list.
 * Move to the next node in the original list using the reference we stored earlier.
 * This process ensures that we do not lose track of the remaining parts of the original list while building the reversed list. After we have iterated through the entire original list, the dummy.next will point to the new head of the reversed list, which we then return as the result.
 *
 * Solution Approach
 * The provided solution employs an iterative approach to go through each node in the linked list and reverse the links. Here's a step-by-step walk-through of the algorithm used:
 *
 * A new ListNode called dummy is created, which acts as the placeholder before the new reversed list's head.
 *
 * A pointer called curr is initialized to point to the head of the original list. This pointer is used to iterate over the list.
 *
 * The iteration starts with a while loop which continues as long as curr is not null. This ensures we process all nodes in the list.
 *
 * Inside the loop, next temporarily stores curr.next, which is the pointer to the next node in the original list. This is crucial since we are going to change curr.next to point to the new list and we don't want to lose the reference to the rest of the original list.
 *
 * We then set curr.next to point to dummy.next. Since dummy.next represents the start of the new list, or null in the first iteration, the current node now points to the head of the reversed list.
 *
 * dummy.next is updated to curr to move the starting point of the reversed list to the current node. At this point, curr is effectively inserted at the beginning of the new reversed list.
 *
 * curr is updated to next to move to the next node in the original list, using the pointer we saved earlier.
 *
 * Once all nodes have been processed and the loop exits, dummy.next will be the head of the new reversed list.
 *
 * The new reversed list referenced by dummy.next is returned.
 *
 * By updating the next pointers of each node, the solution reverses the direction of the list without allocating any additional nodes, which makes it an in-place reversal with a space complexity of O(1). Each node is visited once, resulting in a time complexity of O(n), where n is the number of nodes in the list.
 *
 * Example Walkthrough
 * Let's illustrate the solution approach with a small example. Suppose we have the following linked list:
 *
 * 1 -> 2 -> 3 -> null
 * We want to reverse it to become:
 *
 * 3 -> 2 -> 1 -> null
 * Here's the step-by-step process to achieve that using the provided algorithm:
 *
 * We create a ListNode called dummy that will initially serve as a placeholder for the reversed list. At the beginning, dummy.next is set to null.
 *
 * We initialize a pointer curr to point to the head of the original list which is the node with the value 1.
 *
 * dummy -> null
 * curr -> 1 -> 2 -> 3 -> null
 * Starting the iteration, we enter the while loop since curr is not null.
 *
 * We store curr.next in next, so next points to 2. next will help us move forward in the list after we've altered curr.next.
 *
 * next -> 2 -> 3 -> null
 * We update curr.next to point to dummy.next, which is currently null. Now the first node (1) points to null, the start of our new reversed list.
 * dummy -> null <- 1    2 -> 3 -> null
 * curr ---------^      next ----^
 * We move the start of the reversed list to curr by setting dummy.next to curr. The reversed list now starts with 1.
 * dummy -> 1 -> null
 *          ^
 * curr ----|
 * We update curr to next, moving forward in the original list. curr now points to 2.
 * dummy -> 1 -> null
 * curr -> 2 -> 3 -> null
 * The loop continues. Again, we save curr.next to next, and update curr.next to point to dummy.next. Then we shift the start of the reversed list by setting dummy.next to the current node and update curr to next. After this iteration, dummy points to the new head 2, and our reversed list grows:
 * dummy -> 2 -> 1 -> null
 *          ^
 * curr ----|    3 -> null
 *          next --^
 * In the final iteration, we perform similar steps. We save curr.next to next, set curr.next to dummy.next, and move dummy.next to curr. curr is then updated to the null we saved in next:
 * dummy -> 3 -> 2 -> 1 -> null
 *          ^
 * curr ----|
 * Once curr is null, the while loop terminates, and we find that dummy.next points to 3, which is the new head of the reversed list.
 *
 * Lastly, we return the reversed list starting from dummy.next, which is 3 -> 2 -> 1 -> null.
 *
 * And that completes the reversal of our linked list using the iterative approach described in the solution.
 *
 *
 * */
public class Q206_ReverseLinkedList {

    /**
     * Reverses the given linked list.
     *
     * @param head The head of the original singly-linked list.
     * @return The head of the reversed singly-linked list.
     */
    public ListNode reverseList(ListNode head) {
        // Dummy node that will help in reversing the list.
        ListNode dummy = new ListNode();

        // Pointer to traverse the original list.
        ListNode current = head;

        // Iterating through each node in the list.
        while (current != null) {
            // Temporary store the next node.
            ListNode nextTemp = current.next;

            // Reversing the link so that current.next points to the new head (dummy.next).
            current.next = dummy.next;

            // Move the dummy's next to the current node making it the new head of the reversed list.
            dummy.next = current;

            // Move to the next node in the original list.
            current = nextTemp;
        }

        // Return the reversed linked list which is pointed by dummy's next.
        return dummy.next;
    }
}
