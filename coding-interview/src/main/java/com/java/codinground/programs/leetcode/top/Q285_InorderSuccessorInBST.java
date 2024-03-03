package com.java.codinground.programs.leetcode.top;

import com.java.codinground.support.TreeNode;

/**
 *
 * Problem Description
 * The problem is set within the context of a binary search tree (BST), a type of binary tree where each node has the following properties:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * Given such a tree and a particular node p in it, you need to find the "in-order successor" of that node. The in-order successor for a given node p in a BST is defined as the node with the smallest key that is greater than p.val. If such a node doesn't exist, in other words, if p is the node with the highest value in the tree, the function should return null.
 *
 * The in-order traversal of a BST produces the node values in an ascending order. So the task could also be seen as finding the next node in the in-order traversal sequence.
 *
 * Example Walkthrough
 * Let's illustrate the solution approach using a simple binary search tree and following the steps to find the in-order successor of a given node p. Consider the following BST:
 *
 *       4
 *      / \
 *     2   6
 *    / \ / \
 *   1  3 5  7
 * Let's find the in-order successor of the node p with a value of 3.
 *
 * We start at the root of the tree, which is the node with the value 4.
 * We compare p.val (which is 3) with root.val (which is 4).
 * Since 4 is greater than 3, the node with 4 could be the in-order successor. We save this node and move to the left to look for an even closer value greater than 3.
 * Now, root is at the node with the value 2.
 * We compare p.val with the new root.val (2).
 * Since 2 is less than 3, we discard the left subtree including the current node and move right to find a larger value.
 * However, the right child of 2 is the node p itself. So, we move to the right subtree of p.
 * Because p doesn't have a right subtree, our loop terminates, and we return the stored ans, which is 4.
 * In this example, the in-order successor of node p with value 3 is the node with value 4.
 *
 *
 */
public class Q285_InorderSuccessorInBST {

    /**
     * Finds the inorder successor of a given node in a BST.
     * The inorder successor of a node is the node with the smallest key greater than the current node's key.
     *
     * @param root the root of the BST
     * @param p the target node for which we need to find the inorder successor
     * @return the inorder successor node if exists, otherwise null
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor = null; // This will hold the successor as we traverse the tree

        while (root != null) {
            if (root.val > p.val) {
                // If current node's value is greater than p's value,
                // go left to find a smaller value, but closer to p's value than the current value.
                successor = root; // The potential successor is updated
                root = root.left;
            } else {
                // If current node's value is less than or equal to p's value,
                // successor must be in the right subtree.
                root = root.right;
            }
        }

        return successor; // Return the successor found during traversal
    }
}
