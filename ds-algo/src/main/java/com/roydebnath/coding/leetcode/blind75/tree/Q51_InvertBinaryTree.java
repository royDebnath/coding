package com.roydebnath.coding.leetcode.blind75.tree;

import com.roydebnath.coding.leetcode.support.TreeNode;

public class Q51_InvertBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);
        TreeNode inverted = invertTree(root);
        int x =0;
    }
    public static TreeNode invertTree(TreeNode root) {
        // Base case: if the tree is empty...
        if(root == null){
            return root;
        }
        // Call the function recursively for the left subtree...
        invertTree(root.left);
        // Call the function recursively for the right subtree...
        invertTree(root.right);
        // Swapping process...
        TreeNode curr = root.left;
        root.left = root.right;
        root.right = curr;
        return root;        // Return the root...
    }
}
