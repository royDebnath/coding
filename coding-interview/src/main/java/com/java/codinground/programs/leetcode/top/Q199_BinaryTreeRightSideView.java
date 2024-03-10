package com.java.codinground.programs.leetcode.top;

import com.java.codinground.support.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * This problem asks us to determine the values of the nodes visible when we look at a binary tree
 * from the right side. This essentially means that we want the last node's value in each level of the tree,
 * proceeding from top to bottom.
 *
 * Will use level order traversal and put each row in a queue.
 * whill traverse the queue from left to right to fill the next row in the queue.
 * Everytime, a level of nodes is filled in the queue, will take the last item of the queue
 * and add it to answer.
 *
 * Since we want last item of queue, will use a Deque heere, instead of a simple Queue LinkedList.
 *
 */
public class Q199_BinaryTreeRightSideView  {

    // Function to get a list of integers representing the right side view of the binary tree
    public List<Integer> rightSideView(TreeNode root) {
        // Initialize an answer list to store the right side view
        List<Integer> answer = new ArrayList<>();

        // Return empty list if the root is null
        if (root == null) {
            return answer;
        }

        // Initialize a dequeue to perform level order traversal
        Deque<TreeNode> queue = new ArrayDeque<>();

        // Add the root to the queue as the start of traversal
        queue.offer(root);

        // Perform a level order traversal to capture the rightmost element at each level
        while (!queue.isEmpty()) {
            // Get the rightmost element of the current level and add to the answer list
            answer.add(queue.peekLast().val);

            // Iterate through nodes at current level
            for (int n = queue.size(); n > 0; --n) {
                // Poll the node from the front of the queue
                TreeNode node = queue.poll();

                // If left child exists, add it to the queue
                if (node.left != null) {
                    queue.offer(node.left);
                }

                // If right child exists, add it to the queue
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        // Return the list containing the right side view of the tree
        return answer;
    }
}

