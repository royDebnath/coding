package com.roydebnath.coding.leetcode.blind75.tree;

import com.roydebnath.coding.leetcode.helper.TreeNode;

/**
 *
 * 236. Lowest Common Ancestor of a Binary Tree
 * Medium
 *
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * The lowest common ancestor is defined between two nodes p and q as the lowest node in T that
 * has both p and q as descendants
 * (where we allow a node to be a descendant of itself).
 *
 *
 */
public class Q53_LowestCommonAncestorBST {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode a = root.left = new TreeNode(2);
        TreeNode b = root.right = new TreeNode(7);
        TreeNode c = root.left.left = new TreeNode(1);
        TreeNode d = root.left.right = new TreeNode(3);
        TreeNode e = root.right.left = new TreeNode(6);
        TreeNode f = root.right.right = new TreeNode(9);

        /**
         *           4 -root
         * 	        / \
         * 	  a->  2   7 <-b
         * 	     / \   /\
         *  c-> 1   3|6  9 <-f
         *          | |
         *          d e
         */
        System.out.println(lowestCommonAncestor(root, b,c).val);
    }
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q)
            // found p or q or touch the ground
            return root;

        // search p and q from left and right
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null)
            // from root's left & right we found both p and q, so root is the LCA
            return root;
        else
            // left is not null means from left's left & right we found both q and q
            // so left is the LCA, otherwise, right is the answer
            return left != null ? left : right;
    }
}
