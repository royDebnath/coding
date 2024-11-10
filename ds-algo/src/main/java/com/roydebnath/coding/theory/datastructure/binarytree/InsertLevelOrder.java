package com.roydebnath.coding.theory.datastructure.binarytree;


public class InsertLevelOrder {
    public static void main(String[] args) {
        int[] input = {1, 2, 3, 4, 5, 6, 6, 6, 6, 6};
        Node root = insertLevelOrder(input, 0);
        inOrder(root);
    }

    private static Node insertLevelOrder(int[] arr, int i) {
        Node root = null;
        // Base case for recursion
        if (i < arr.length) {
            root = new Node(arr[i]);

            // insert left child
            root.left = insertLevelOrder(arr, 2 * i + 1);

            // insert right child
            root.right = insertLevelOrder(arr, 2 * i + 2);
        }
        return root;
    }

    private static void inOrder(Node root)
    {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }
    }
}