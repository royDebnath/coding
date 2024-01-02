package com.java.codinground.programs.algoexpert.easy;

import com.java.codinground.support.BST;

/**
 * Given a Binary Tree, find the depth of each node in the tree,
 * sum up all of these depths and return the sum.
 * tree =   1
 *       /     \
 *      2       3
 *    /   \   /   \
 *   4     5 6     7
 *
 *
 */
public class NodeDepths {

    public static void main(String[] args) {
        BST tree = populateBST();
        System.out.println("Node depths : " + nodeDepths(tree));
    }

    private static int nodeDepths(BST tree) {
        return nodeDepths(tree, 0);
    }

    private static int nodeDepths(BST tree, int depth) {
        if (tree==null) return 0;
        return depth + nodeDepths(tree.left, depth+1) + nodeDepths(tree.right, depth+1);
    }


    private static BST populateBST() {
        BST root = new BST(1);
        root.left = new BST(2);
        root.left.left = new BST(4);
        root.left.right = new BST(5);
        root.right = new BST(3);
        root.right.left = new BST(6);
        root.right.right = new BST(7);
        return root;
    }
}

