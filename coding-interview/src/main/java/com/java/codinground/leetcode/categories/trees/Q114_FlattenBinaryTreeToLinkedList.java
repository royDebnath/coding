package com.java.codinground.leetcode.categories.trees;

import com.java.codinground.support.TreeNode;

/**
 * Given the root of a binary tree, flatten the tree into a "linked list":
 *
 * The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
 * The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 *
 * The idea is very simple:
 *
 * flatten left subtree
 *
 * flatten right subtree
 *
 * concatenate root -> left flatten subtree -> right flatten subtree
 *
 */
public class Q114_FlattenBinaryTreeToLinkedList {
    public void flatten(TreeNode root) {
        if(root == null)
            return;

        flatten(root.left);
        flatten(root.right);

        // save current right for concatination
        TreeNode right = root.right;

        if(root.left != null) {

            // step 1: concatinate root with left flatten subtree
            root.right = root.left;
            root.left = null; // set left to null

            // step 2: move to the end of new added flatten subtree
            while(root.right != null)
                root = root.right;

            // step 3: contatinate left flatten subtree with flatten right subtree
            root.right = right;
        }
    }
}
