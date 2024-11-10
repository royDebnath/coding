package com.roydebnath.coding.leetcode.blind75.tree;

import com.roydebnath.coding.leetcode.support.TreeNode;

import java.util.LinkedList;
import java.util.List;

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
 *
 * Solution :
 *
 * Going to use inorder traversal for this solution.
 * The inorder traversal traverses a tree in the following way : left, root, right
 * this sequence will always be sorted sequence for a valid binary tree as left < root < right
 *
 * Instead of printing inOrder Traversal, will store the inorder sequence in a linked list.
 * then will verify if the list is sorted.
 *
 */
public class Q25_ValidateBinarySearchTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(3);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.right.right = new TreeNode(9);

        System.out.println(isValidBST(root));
    }

    private static boolean isValidBST(TreeNode root) {
        List<Integer> inOrderList = new LinkedList<>();
        inOrderStore(root, inOrderList);
        for (int i = 0; i < inOrderList.size()-1; i++) {
            if (inOrderList.get(i+1)<=inOrderList.get(i)){
                return false;
            }
        }
        return true;
    }

    private static void inOrderStore(TreeNode root, List<Integer> inOrderList) {
        if (root!=null){
            inOrderStore(root.left, inOrderList);
            inOrderList.add(root.val);
            inOrderStore(root.right, inOrderList);
        }
    }


}


