package com.roydebnath.coding.leetcode.blind75.tree;

import com.roydebnath.coding.leetcode.helper.TreeNode;

/**
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes
 * in the sequence has an edge connecting them. A node can only appear in the sequence
 * at most once. Note that the path does not need to pass through the root.
 *
 * The path sum of a path is the sum of the node's values in the path.
 *
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 *
 *Input: root = [1,2,3]
 * Output: 6
 * Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
 *
 * Input: root = [-10,9,20,null,null,15,7]
 * Output: 42
 * Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 *
 *
 */
public class Q31_BinaryTreeMaximumPathSum {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(maxPathSum(root));

    }
    static int max = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        max= root.val;
        dfs(root);
        return max;
    }


    /**
     * this function goes from the bottom of the tree to the top,
     * it's in post-order(left-right-root) manner.
     *
     * At every node, we need to make a decision,
     * if the sum comes from the left path larger than the right path,
     * we pick the left path/right path and plus the current node's value,
     * this recursion goes all the way up to the root node.
     */
    static int dfs(TreeNode root) {
        if (root == null) return 0;

        int leftMax = dfs(root.left);
        int rightMax = dfs(root.right);

        //Handling negetives
        leftMax = Math.max(leftMax,0);
        rightMax = Math.max(rightMax,0);

        max = Math.max(max, root.val + leftMax + rightMax);

        return root.val + Math.max(leftMax, rightMax); // we can choose either left path or right path which is larger
    }
}
