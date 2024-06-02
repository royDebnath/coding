package com.java.backup.blind75.tree;

import com.java.codinground.support.TreeNode;

/**
 * The inorder traversal of BST gives the sorted order of nodes.
 * Will use this concept to find the kth smallest.
 */
public class Q52_KthSmallestElementBST {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);
        KthSmallestElementBST soln = new KthSmallestElementBST();
        /**
         *           4
         * 	        / \
         * 	       2   7
         * 	     / \   /\
         * 	   1    3|6  9
         *
         */
        System.out.println(soln.kthSmallest(root, 3));

    }
}

class KthSmallestElementBST {
    int count = 0;
    int result = Integer.MIN_VALUE;

    public int kthSmallest(TreeNode root, int k) {
        inOrder(root, k);
        return result;
    }

    public void inOrder(TreeNode root, int k) {
        if (root != null) {
            inOrder(root.left, k);
            count++;
            if (count == k) {
                result = root.val;
                return;
            }
            inOrder(root.right, k);
        }
    }
}

