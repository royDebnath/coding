package com.java.codinground.programs.leetcode.top100;

import com.java.codinground.support.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Problem Description
 * This problem asks us to determine the values of the nodes visible when we look at a binary tree from the right side. This essentially means that we want the last node's value in each level of the tree, proceeding from top to bottom.
 *
 * A binary tree is a data structure where each node has at most two children referred to as the left child and the right child.
 *
 * Intuition
 * To solve this problem, we can use a level-order traversal strategy. The level-order traversal involves traveling through the tree one level at a time, starting at the root. This is typically done using a queue data structure where we enqueue all nodes at the current level before we proceed to the next level.
 *
 * While performing a level-order traversal, we can keep track of the nodes at the current level by counting how many nodes are in the queue before we start processing the level. Then, as we enqueue their children, we can observe the rightmost node's value of each level (which will be the last one we encounter in the queue for that level) and record that value.
 *
 * This approach allows us to see the tree as if we were standing on its right side and collect the values of the nodes that would be visible from that perspective.
 *
 * Solution Approach
 * The solution uses a level-order traversal approach, utilizing a queue to track the nodes at each level. Here's a step-by-step walkthrough of the implementation:
 *
 * We initialize an empty list ans which will store the values of the rightmost nodes at each level of the binary tree.
 *
 * We check if the root is None (i.e., the tree is empty) and if so, return the empty list ans. There's nothing to traverse if the tree is empty.
 *
 * A queue q is initialized with the root node as its only element. This queue will help in the level-order traversal, keeping track of nodes that need to be processed.
 *
 * We begin a while loop which continues as long as there are nodes in the queue q. Each iteration of this loop represents traversing one level of the binary tree.
 *
 * At the beginning of each level traversal, we append the value of the last node in the queue (the rightmost node of the current level) to the ans list. We use q[-1].val to fetch this value as we are using a double-ended queue deque from Python's collections module.
 *
 * We then enter another loop to process all nodes at the current level, which are already in the queue. We find the number of nodes in the current level by the current length of the queue len(q).
 *
 * Inside this inner loop, we pop the leftmost node from the queue using q.popleft() and check if this node has a left child. If it does, we append the left child to the queue.
 *
 * We also check if the node has a right child, and if so, we append the right child to the queue.
 *
 * After this inner loop finishes, all the nodes of the next level have been added to the queue, and we proceed to the next iteration of the while loop to process the next level.
 *
 * When there are no more nodes in the queue, it indicates that we have completed traversing the binary tree. At this point, the while loop exits.
 *
 * Finally, we return the ans list, which now contains the values of the rightmost nodes of each level, as seen from the right-hand side of the tree.
 *
 * Through this algorithm, we effectively conduct a breadth-first search (BFS) while taking a snapshot of the last node at each level, resulting in the view from the right side of the binary tree.
 *
 * Example Walkthrough
 * Let's take a small example to illustrate the solution approach. Consider the following binary tree:
 *
 *     1
 *    / \
 *   2   3
 *      / \
 *     5   4
 * Now, let us walk through the solution using the presented level-order traversal approach.
 *
 * Start by initializing the answer list ans = [] which will hold the values of the rightmost nodes.
 *
 * Since the root is not None, we don't return an empty list but proceed with the next steps.
 *
 * Initialize the queue q with the root node of the binary tree, which in our case is the node with the value 1. So, q = deque([1]).
 *
 * Enter the while loop, as our queue has one element at this point.
 *
 * Queue state: [1]. The rightmost node is the last element of the queue, which is 1. Append 1 to ans, so ans = [1].
 *
 * The number of nodes at the current level (root level) is 1. We proceed to process this level.
 *
 * Pop 1 from the queue using q.popleft(). Node 1 has two children: 2 (left child) and 3 (right child). Enqueue these children into q, resulting in q = deque([2, 3]).
 *
 * Now, the queue has the next level of nodes, so we loop back to the outer while loop.
 *
 * Queue state: [2, 3]. The rightmost node now is 3. We add value 3 to ans, so ans = [1, 3].
 *
 * There are two nodes at this level. We begin the inner loop to process both.
 *
 * Pop 2 from the queue using q.popleft(). Node 2 has no children, so we do not add anything to the queue.
 *
 * Pop 3 from the queue. It has two children: 5 (left child) and 4 (right child). Enqueue these children, making q = deque([5, 4]).
 *
 * All nodes of the current level are processed, loop back to the outer loop.
 *
 * Queue state: [5, 4]. The rightmost node is 4. Append 4 to ans, so ans = [1, 3, 4].
 *
 * This level has two nodes. We enter the inner loop to process both nodes.
 *
 * Pop 5 from the queue. 5 has no children, so we move on.
 *
 * Pop 4 from the queue. 4 has no children as well.
 *
 * The queue is empty, so the outer while loop exits.
 *
 * We have traversed the entire tree and recorded the rightmost node at each level.
 *
 * The final answer list ans now contains [1, 3, 4], which are the values of the nodes visible from the right-hand side of the binary tree.
 *
 * Through the level-order traversal, we select the last node's value at each level, aligning with the view one would see if they looked at the tree from its right side.
 *
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

