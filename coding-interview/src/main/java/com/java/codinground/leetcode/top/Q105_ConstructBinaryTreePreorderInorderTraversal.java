package com.java.codinground.leetcode.top;

// Preorder: Node -> Left -> Right
// Inorder : Left -> Node -> Right


import com.java.codinground.support.TreeNode;

import java.util.Arrays;

/**
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal
 * of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree
 *
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 *
 *
 * The basic idea is here:
 * Say we have 2 arrays, PRE and IN.
 * Preorder traversing implies that PRE[0] is the root node.
 * Then we can find this PRE[0] in IN, say it's IN[5].
 * Now we know that IN[5] is root, so we know that IN[0] - IN[4] is on the left side, IN[6] to the end is on the right side.
 * Recursively doing this on subarrays, we can build a tree out of it :)
 *
 * 1. The Root of the tree is the first element in Preorder Array.
 * 2. Find the position of the Root in Inorder Array.
 * 3. Elements to the left of Root element in Inorder Array are the left subtree.
 * 4. Elements to the right of Root element in Inorder Array are the right subtree.
 * 5. Call recursively buildTree on the subarray composed by the elements
 *    in the left subtree. Attach returned left subtree root as left child of Root node.
 * 6. Call recursively buildTree on the subarray composed by the elements
 * in the right subtree. Attach returned right subtree root as right child of Root node.
 * 7. return Root.
 */

class Q105_ConstructBinaryTreefromPreorderandInorderTraversal {
    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int [] inorder = {9,3,15,20,7};
        buildTree(preorder, inorder);
    }
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || inorder.length == 0 || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        if (preorder.length == 1) {
            return root;
        }
        int rootIndex = -1;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == preorder[0]) {
                rootIndex = i;
                break;
            }
        }
        int[] subleftin = Arrays.copyOfRange(inorder, 0, rootIndex);
        int[] subrightin = Arrays.copyOfRange(inorder, rootIndex + 1, inorder.length);
        int[] subleftpre = Arrays.copyOfRange(preorder, 1, rootIndex + 1);
        int[] subrightpre = Arrays.copyOfRange(preorder, rootIndex + 1, preorder.length);


        root.left = buildTree(subleftpre, subleftin);
        root.right = buildTree(subrightpre, subrightin);
        return root;
    }
}