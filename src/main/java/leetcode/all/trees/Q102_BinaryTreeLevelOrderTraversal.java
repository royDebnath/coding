package leetcode.all.trees;


import leetcode.support.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Problem Description
 * The given problem is about performing a level order traversal on a binary tree.
 * In a level order traversal, we visit all the nodes of a tree level by level, starting from the root.
 * We start at the root level, then move to the nodes on the next level,
 * and continue this process until all levels are covered.
 * The goal is to return the values of the nodes in an array of arrays,
 * where each inner array represents a level in the tree and contains the values of the nodes at that level,
 * ordered from left to right.
 *
 * Intuition
 *  A binary tree level order traversal generally recommends a breadth first search (BFS) approach
 *  with the use of a queue data structure. When we process a node (curr),
 *  we'll push the node's children onto the end of the queue in the order in which
 *  we want to traverse (in this case, left to right).
 *  In this way, we'll have finished putting the next row in the queue at the same time
 *  we finish iterating through this row.
 *
 *  queue.poll() -- returns the first element from the front of the queue.
 *  queue.offer(element) -- places the element at the end of the queue.
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
 * Initialization: We create an instance of Solution and call the method levelOrder with the root
 * of the tree (node with value 1). We also initialize an empty list ans to store the result of the traversal.
 *
 * Queue Setup: We initialize a queue q and add the root node with value 1 to it.
 *
 * Traversal Loop: The queue is not empty (it contains the root), so we start the while loop.
 *
 * Level Processing - First Iteration
 *
 * Create a temporary list t to store node values for this level.
 * The current queue size is 1, so the loop runs for one iteration.
 * We poll the root node (value 1) from the queue and add it to list t (which is now [1]).
 * The root node has two children, nodes with values 2 and 3, which we add to the end of the queue.
 * Appending to Result: We've finished processing the first level and append t to ans (which is now [[1]]).
 *
 * Level Processing - Second Iteration
 *
 * Since the queue has two elements (values 2 and 3), we process two nodes this round.
 * poll node with value 2, add it to temporary list t, and enqueue its child (value 4 to the end of queue).
 * poll node with value 3, add it to t, and enqueue its children (values 5 and 6 to end of queue).
 * At the end of this iteration, t has [2, 3], and the queue has nodes 4, 5, and 6.
 *
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
public class Q102_BinaryTreeLevelOrderTraversal {
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
        Queue<TreeNode> queue = new LinkedList<>();


        // Start the level order traversal from the root.
        queue.offer(root);

        // While there are nodes to process
        while (!queue.isEmpty()) {
            // Temporary list to store the values of nodes at the current level.
            List<Integer> level = new ArrayList<>();

            // Process all nodes at the current level.
            int levelLength = queue.size();
            for (int i = 0; i < levelLength; ++i) { // adds the current level to ans list level, fills the queue for next level
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
