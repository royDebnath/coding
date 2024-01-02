package com.java.codinground.programs.algoexpert.easy;

import com.java.codinground.support.BST;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a Binary Tree, I am asked to write a function that computes all of the branch sums of the tree
 * and returns them in an array ordered from leftmost branch sum to rightmost branch sum.
 * In a tree, a branch is a path that starts at the root node and ends at one of the leaf nodes.
 * A branch sum means the sum of all values in a branch.
 *
 * Sample Input:
 *
 * tree =   1
 *       /     \
 *      2       3
 *    /   \   /   \
 *   4     5 6     7
 *  / \
 * 8   9
 *
 * The output should be:
 *
 * [15, 16, 8, 10, 11]
 *
 */
public class BranchSums {
    public static void main(String[] args) {

        BST tree = populateBST();
        branchSums(tree).forEach(System.out::println);

    }

    private static List<Integer> branchSums(BST tree) {
        List<Integer> sums = new ArrayList<>();
        branchSums(tree, 0, sums);
        return sums;
    }

    private static void branchSums(BST node, int runningSum, List<Integer> sums) {
        if (node == null) return ;

        runningSum+=node.value;
        if (node.left==null && node.right==null){ // hits leaf node
            sums.add(runningSum);
            return; // This returns to the recursive call made from line 52,53.
        }

        branchSums(node.left, runningSum, sums);
        branchSums(node.right, runningSum,sums);

    }

    private static BST populateBST() {
        BST root = new BST(1);
        root.left = new BST(2);
        root.left.left = new BST(4);
        root.left.left.left = new BST(8);
        root.left.left.right = new BST(9);
        root.left.right = new BST(5);
        root.right = new BST(3);
        root.right.left = new BST(6);
        root.right.right = new BST(7);
        return root;
    }
}
