package com.java.codinground.leetcode.categories.trees;

import com.java.codinground.leetcode.support.TreeNode;

/**
 * 110. Balanced Binary Tree
 * Easy
 *
 * Topics
 * Companies
 * Given a binary tree, determine if it is
 * height-balanced
 * .
 *Problem Description
 *
 * The problem presents a scenario where one is given a binary tree and is tasked with determining if the tree is height-balanced. In the context of this problem, a height-balanced binary tree is defined as a binary tree where the heights of the two child subtrees of any node differ by no more than one. Additionally, the subtrees of any node must also be height-balanced.
 *
 * Intuition
 *
 * The solution to this problem involves a recursive approach, where we traverse the tree to check the height of both left and right subtrees for each node and verify the balance condition (that the height difference is no more than one).
 *
 * The intuition behind the solution is to perform a post-order traversal of the tree. A post-order traversal means we check the subtrees before dealing with the current node, which naturally allows us to check whether the subtrees are balanced and also to calculate their heights.
 *
 * If at any point we find that the subtree is not balanced (i.e., the height difference between the left and right subtree is greater than 1), we can immediately conclude that the tree is not height-balanced.
 *
 * The function height calculates the height of a tree rooted at a given node. If the left or right subtree is unbalanced (represented by a height of -1), or if the current node's subtrees' heights differ by more than 1, it returns -1, indicating that the tree is not balanced starting from this node. If the subtrees are balanced, the function returns the actual height, which is 1 plus the maximum of the heights of the left and right subtrees.
 *
 * The isBalanced function calls the height function with the root node and checks if the returned value is non-negative. A non-negative return value indicates that the tree is balanced, whereas a value of -1 indicates that it is not.
 *
 * By using this approach, we only need to traverse each node once, giving us an efficient algorithm with a time complexity of O(n) where n is the number of nodes in the tree.
 *
 * Learn more about Tree, Depth-First Search and Binary Tree patterns.
 *
 * Solution Approach
 *
 * The solution implements a recursive function to determine the height of a given subtree and whether the subtree is balanced.
 *
 * Within this approach, a few key concepts and algorithms are used:
 *
 * Post-order Traversal: The function height uses post-order traversal of the tree. At each node, it first checks the height of its left and right subtrees before performing any action on the current node. Only after we have the heights of the subtrees do we check if they satisfy the balance criterion.
 *
 * Recursion: Recursion is a fundamental part of the solution. The height function calls itself to find the height of the left subtree and the right subtree.
 *
 * Termination Checks: The function includes condition checks to terminate early if an imbalance is found. When the left or right subtree has an imbalance (height of -1), or their height difference is more than 1, the function returns -1 immediately, avoiding further unnecessary checks or recursive calls.
 *
 * Height Calculation: To calculate a subtree's height, the function considers the larger height of its left or right subtree and adds 1 to account for the current node.
 *
 * Example Walkthrough
 *
 * Let's use a small example to illustrate the solution approach. Consider the following binary tree:
 *
 * 1    3
 * 2   / \
 * 3  9  20
 * 4    /  \
 * 5   15   7
 * In this example, we have a binary tree where the root node has a value of 3, the left child is a leaf node with a value of 9, and the right child has a value of 20. The right child has its own children (15 on the left and 7 on the right), both leaf nodes.
 *
 * Now, let's walk through the solution algorithm step-by-step:
 *
 * The isBalanced function will begin by calling the height function on the root node (with value 3).
 * The height function checks if the root node is None. Since it's not, it proceeds to find the height of the left and right subtrees.
 * The height function is recursively called on the left child (value 9) which is a leaf node. As it does not have any children, the function returns 1 because the height of a leaf node is 1.
 * Similarly, the height function is recursively called on the right child (value 20).
 * For node 20, the function calls itself on node 15 (left child) and node 7 (right child), both of which are leaf nodes and so return 1.
 * Now, with l (the height of the left subtree of node 20) and r (the height of the right subtree of node 20) both equal to 1, the function proceeds to check if the subtree rooted at node 20 is balanced. Since abs(l - r) = 0 which does not exceed 1, it is balanced and the function returns 1 + max(l, r) = 2.
 * Back at the root node (value 3), we now have l (the height of the left subtree) as 1 and r (the height of the right subtree) as 2. The function checks if the subtree rooted at node 3 is balanced. abs(1 - 2) equals 1, which is within the allowed difference, so it is balanced, and the function returns 1 + max(1, 2) = 3.
 * Since there was no step where the height function returned -1, indicating an imbalance, the overall result of the height function when called on the root is the height of the tree 3, which is a non-negative number.
 * The isBalanced function finally checks the returned value from the height function. Since the value is not -1, isBalanced returns True, indicating that the binary tree is indeed height-balanced.
 * Thus, using the given solution approach, the binary tree in our example has been determined to be height-balanced efficiently in O(n) time complexity, where n is the number of nodes in the tree.
 *
 *
 */
public class Q110_BalancedBinaryTree {
    /**
     * Determines if a binary tree is balanced.
     * In a balanced tree, the height of the two subtrees of any node never differ by more than one.
     *
     * @param root The root of the binary tree.
     * @return true if the tree is balanced, false otherwise.
     */
    public boolean isBalanced(TreeNode root) {
        // A non-negative height indicates that the tree is balanced,
        // while -1 represents an imbalance.
        return calculateHeight(root) >= 0;
    }

    /**
     * Recursively calculates the height of a binary tree.
     * Returns -1 if the subtree is unbalanced.
     *
     * @param node The node to calculate height of.
     * @return The height of the tree if balanced, otherwise -1.
     */
    private int calculateHeight(TreeNode node) {
        // Tree with no nodes has height 0.
        if (node == null) {
            return 0;
        }

        // Recursively find the height of the left and right subtrees.
        int leftHeight = calculateHeight(node.left);
        int rightHeight = calculateHeight(node.right);

        // Check if left or right subtree is unbalanced or if they differ in height by more than 1.
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1; // Tree is not balanced.
        }

        // Current node height is max of left and right subtree heights plus 1 (for the current node).
        return 1 + Math.max(leftHeight, rightHeight);
    }
}
