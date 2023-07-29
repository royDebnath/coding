package com.java.codinground.blind75;

import com.java.codinground.support.TreeNode;

/**
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 *
 * A valid BST is defined as follows:
 *
 * The left
 * subtree
 *  of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 */
public class Q25_ValidateBinarySearchTree {
    public TreeNode prev = null;

    public boolean isValidBST(TreeNode root) {
        if (root != null) {
            if (!isValidBST(root.left)) {
                return false;
            }
            if (prev != null && prev.val >= root.val)
                return false;
            prev = root;
            return isValidBST(root.right);
        }
        return true;
    }
}


