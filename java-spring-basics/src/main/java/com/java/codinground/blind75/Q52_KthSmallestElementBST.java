package com.java.codinground.blind75;

import com.java.codinground.support.TreeNode;

/**
 * The inorder traversal of BST gives the sorted order of nodes.
 * Will use this concept to find the kth smallest.
 */
public class Q52_KthSmallestElementBST {

    public static void main(String[] args) {

    }
    int count = 0;
    int result = Integer.MIN_VALUE;

    public int kthSmallest(TreeNode root, int k) {
        inOrder(root, k);
        return result;
    }

    public void inOrder(TreeNode root, int k) {
        if(root != null) {
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
