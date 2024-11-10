package com.roydebnath.coding.leetcode.all.trees;

import com.roydebnath.coding.leetcode.support.TreeNode;

/**
 * Given a binary tree root, a node X in the tree is named good if in the path
 * from root to X there are no nodes with a value greater than X.
 *
 * Return the number of good nodes in the binary tree.
 */
public class Q1448_CountGoodNodesInBinaryTree {
    private int numGoodNodes = 0; // Variable to keep count of good nodes

    // Public method that starts the depth-first search and returns the number of good nodes
    public int goodNodes(TreeNode root) {
        dfsHelper(root, Integer.MIN_VALUE);
        return numGoodNodes;
    }

    // Helper method that performs a depth-first search on the tree
    private void dfsHelper(TreeNode node, int maxSoFar) {
        if (node == null) {
            return; // Base case: if the node is null, return
        }
        if (maxSoFar <= node.val) {
            // If the current value is greater than or equal to the maximum value so far,
            // it is a good node, so increment the counter and update the maximum value
            numGoodNodes++;
            maxSoFar = node.val;
        }
        dfsHelper(node.left, maxSoFar); // Recursively call helper for left subtree
        dfsHelper(node.right, maxSoFar); // Recursively call helper for right subtree
    }
}
