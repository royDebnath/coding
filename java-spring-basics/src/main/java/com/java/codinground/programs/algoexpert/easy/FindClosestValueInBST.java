package com.java.codinground.programs.algoexpert.easy;

import com.java.codinground.programs.support.BST;

/***
 * Given a Binary Search Tree and a target integer value, I am asked to write a function that returns
 * the closest value to that target value that's contained in the BST. There will be only one closest value.
 *
 * For example,
 *
 * bst =   10
 *       /     \
 *      5      15
 *    /   \   /   \
 *   2     5 13   22
 *  /          \
 * 1           14
 * target = 12
 * The closest value to the target value in the BST is 13.
 */
public class FindClosestValueInBST {
    public static void main(String[] args) {
        BST root = populateBST();
        System.out.println("Closest value in tree : " + findClosestValueInBst(root, 12));
    }

    private static int findClosestValueInBst(BST tree, int target) {
        return findClosestValueInBst(tree, target, tree.value);
    }

    private static int findClosestValueInBst(BST tree, int target, int closest) {
        BST currentNode = tree;

        while (currentNode!=null){
            int currentValue = currentNode.value;
            int currentDifference = Math.abs(target - currentValue);
            int minDifference = Math.abs(target-closest);

            if (currentDifference < minDifference){
                closest = currentNode.value;
            }

            if (target < currentValue){
                currentNode = currentNode.left;
            }
            else if(target > currentValue){
                currentNode = currentNode.right;
            }
            else {
                break;
            }
        }

        return closest;
    }

    private static BST populateBST() {
        BST root = new BST(10);
        root.left = new BST(5);
        root.left.left = new BST(2);
        root.left.left.left = new BST(1);
        root.left.right = new BST(5);
        root.right = new BST(15);
        root.right.left = new BST(13);
        root.right.left.right = new BST(14);
        root.right.right = new BST(22);
        return root;
    }
}

