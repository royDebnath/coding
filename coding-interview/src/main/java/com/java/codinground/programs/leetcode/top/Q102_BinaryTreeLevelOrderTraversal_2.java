package com.java.codinground.programs.leetcode.top;

import com.java.codinground.support.TreeNode;

import java.util.*;

/**
 * Problem Description
 * The given problem is about performing a level order traversal on a binary tree. In a level order traversal, we visit all the nodes of a tree level by level, starting from the root. We start at the root level, then move to the nodes on the next level, and continue this process until all levels are covered. The goal is to return the values of the nodes in an array of arrays, where each inner array represents a level in the tree and contains the values of the nodes at that level, ordered from left to right.
 *
 * Intuition
 * To solve this problem, we use an algorithm known as Breadth-First Search (BFS). BFS is a traversal technique that explores the neighbor nodes before moving to the next level. This characteristic of BFS makes it perfectly suited for level order traversal.
 *
 * To implement BFS, we use a queue data structure. The queue helps us process nodes in the order they were added, ensuring that we visit nodes level by level. Here's the step-by-step intuition behind the solution:
 *
 * If the root is None, the binary tree is empty, and we have nothing to traverse. Therefore, we return an empty list.
 *
 * Initialize an empty list ans to store the level order traversal result, and a queue q (implemented as a deque to allow for efficient pop and append operations) with the root node as the starting point.
 *
 * We want to process each level of the tree one at a time. We do this by iterating while there are still nodes in our queue q.
 *
 * For each level, we initialize a temporary list t to store the values of the nodes at this level.
 *
 * Before we start processing the nodes in the current level, we note how many nodes there are in this level which is the current size of our queue q.
 *
 * We then dequeue each node in this level from q, one by one, and add their values to our temporary list t. For each node we process, we check if they have a left or right child. If they do, we enqueue those children to q. This prepares our queue for the next level.
 *
 * Once a level is processed, we append our temporary list t to ans.
 *
 * Continue the process until there are no more nodes left in the queue, meaning we have visited all levels.
 *
 * Return ans, which contains the level order traversal result. Each inner list within ans represents node values at a respective level of the tree.
 *
 * The breadth-first nature of the queue ensures that we visit all nodes at a given depth before moving on to nodes at the next depth, fitting the needs of a level order traversal perfectly.
 *
 * Solution Approach
 * The implementation of the solution primarily involves utilizing the Breadth-First Search (BFS) algorithm with the help of a queue data structure to traverse the tree. The code involves the following steps:
 *
 * Initialization: We first define a class Solution with the method levelOrder, which takes the root of the binary tree as an input. We initialize an empty list ans to hold our results. If the root is None, we immediately return the empty list as there are no nodes to traverse.
 *
 * Queue Setup: We initialize a queue q (typically implemented as a double-ended queue deque in Python for efficient addition and removal of elements). We add the root to the queue to serve as our starting point for the level order traversal.
 *
 * Traversal Loop: We then enter a while loop, which will run until the queue is empty. This loop is responsible for processing all levels in the tree.
 *
 * Level Processing: Inside the loop, we start by creating a temporary list t to store node values for the current level. Since queue q currently holds all nodes at the current level, we use a for loop to process the number of nodes equal to the current size of the queue. For each node:
 *
 * We dequeue the node from q using popleft.
 * We add the node's value to the temporary list t.
 * If the node has a left child, we add it to the queue.
 * Similarly, if the node has a right child, we add it to the queue as well.
 * After we process all nodes at the current level, their children are in the queue, ready to be processed for the next level.
 *
 * Appending to Result: Once we finish processing all nodes at one level, we append temporary list t, which contains the values of these nodes, to our main result list ans.
 *
 * Completion: After the while loop exits (the queue is now empty since all nodes have been visited), we return ans. The list ans now holds the node values for each level of the binary tree, arranged from top to bottom and left to right, as required by the problem.
 *
 * The BFS approach ensures we process all nodes at one level before moving onto the next, aligning with the level order traversal definition. By taking advantage of the queue to keep track of nodes to visit and maintaining a list for each level's node values, the solution effectively combines data structure utilization with traversal strategy to solve the problem efficiently.
 *
 * Example Walkthrough
 * Let's illustrate the solution approach with a small binary tree example.
 *
 * Consider the following binary tree:
 *
 *     1
 *    / \
 *   2   3
 *  /   / \
 * 4   5   6
 * Here's how the level order traversal would process this tree:
 *
 * Initialization: We create an instance of Solution and call the method levelOrder with the root of the tree (node with value 1). We also initialize an empty list ans to store the result of the traversal.
 *
 * Queue Setup: We initialize a queue q and add the root node with value 1 to it.
 *
 * Traversal Loop: The queue is not empty (it contains the root), so we start the while loop.
 *
 * Level Processing - First Iteration
 *
 * Create a temporary list t to store node values for this level.
 * The current queue size is 1, so the loop runs for one iteration.
 * We dequeue the root node (value 1) from the queue and add it to list t (which is now [1]).
 * The root node has two children, nodes with values 2 and 3, which we add to the queue.
 * Appending to Result: We've finished processing the first level and append t to ans (which is now [[1]]).
 *
 * Level Processing - Second Iteration
 *
 * Since the queue has two elements (values 2 and 3), we process two nodes this round.
 * Dequeue node with value 2, add it to temporary list t, and enqueue its child (value 4 to queue).
 * Dequeue node with value 3, add it to t, and enqueue its children (values 5 and 6 to queue).
 * At the end of this iteration, t has [2, 3], and the queue has nodes 4, 5, and 6.
 * Appending to Result: We've finished processing the second level and append t to ans (which is now [[1], [2, 3]]).
 *
 * Level Processing - Third Iteration
 *
 * This time queue size is 3, and we process nodes 4, 5, and 6 similarly.
 * We end up with t having [4, 5, 6], and the queue is now empty.
 * Appending to Result: The third level is processed, and we append t to ans (which is now [[1], [2, 3], [4, 5, 6]]).
 *
 * Completion: The queue is empty, so the loop ends and we return ans. The final result is [[1], [2, 3], [4, 5, 6]], which correctly represents the level order traversal of the tree.
 *
 * The traversal has visited all nodes level by level and has constructed a list of node values for each level, fitting the problem requirements perfectly.
 *
 *
 */
public class Q102_BinaryTreeLevelOrderTraversal_2 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left= new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        for (List<Integer> integers : levelOrder(root)) {
            System.out.print(integers.toString());
        }
    }
    public static List<List<Integer>> levelOrder(TreeNode root) {
        // Create a list to hold the result.
        List<List<Integer>> result = new ArrayList<>();

        // Return an empty list if the tree is empty.
        if (root == null) {
            return result;
        }

        // Create a queue to hold nodes at each level.
        Deque<TreeNode> queue = new ArrayDeque<>();

        // Start the level order traversal from the root.
        queue.offer(root);

        // While there are nodes to process
        while (!queue.isEmpty()) {
            // Temporary list to store the values of nodes at the current level.
            List<Integer> level = new ArrayList<>();

            // Process all nodes at the current level.
            int levelLength = queue.size();
            for (int i = 0; i < levelLength; ++i) {
                // Retrieve and remove the head of the queue.
                TreeNode currentNode = queue.poll();

                // Add the node's value to the temporary list.
                level.add(currentNode.val);

                // If the left child exists, add it to the queue for the next level.
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }

                // If the right child exists, add it to the queue for the next level.
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }

            // Add the temporary list to the result list.
            result.add(level);
        }

        // Return the list of levels.
        return result;
    }
}
