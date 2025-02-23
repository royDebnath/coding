package leetcode.all.trees;

import leetcode.support.TreeNode;

/**
 * Problem Description
 * The problem asks to verify whether a given binary tree is a valid binary search tree (BST). By definition, a valid BST is characterized by the following properties:
 *
 * Every node's left subtree contains only nodes with keys that are less than the node's own key.
 * Every node's right subtree contains only nodes with keys that are greater than the node's own key.
 * Both the left and right subtrees must also themselves be valid BSTs.
 * The objective is to use these criteria to check every node in the tree and ensure that the structure adheres to the rules of a BST.
 *
 * Intuition
 * The solution uses the concept of in-order traversal. In a BST, an in-order traversal yields the nodes' values in ascending order. The approach involves a Depth-First Search (DFS) where we traverse the tree in an in-order manner (left, node, right), keeping track of the value of the previously visited node (prev). During the traversal, we ensure that each subsequent node has a greater value than the previous node.
 *
 * A recursive function dfs is defined, which will do an in-order traversal of the tree.
 * If we encounter a None (indicative of reaching the end of a path), we return True, because an empty tree is technically a valid BST.
 * As we traverse the left subtree, we check for violations of the BST properties.
 * Before visiting the current node, we ensure that we've finished validating the left subtree. If the left subtree is invalid, the function returns False.
 * We then check the current node's value against prev (the previous node's value, initialized to negative infinity). The value must be strictly greater to satisfy BST conditions.
 * Update prev to the current node's value.
 * Proceed to validate the right subtree.
 * If every recursive call returns True, the entire tree is a valid BST, and thus the function returns True.
 * This approach ensures that we confirm the BST property where each node's value must be greater than all values in its left subtree and less than all values in its right subtree.
 *
 * Solution Approach
 * The provided Python code implements the DFS in-order traversal to check the validity of the BST, and it uses the TreeNode data structure, which is a standard representation of a node in a binary tree, consisting of a value val and pointers to left and right child nodes.
 *
 * Here is the step-by-step breakdown of the solution approach:
 *
 * In-order traversal (DFS): We recursively traverse the binary tree using in-order traversal, where we first visit the left subtree, then the node, and finally the right subtree.
 *
 * Global variable: A global variable prev (initialized to negative infinity) is used to keep track of the value of the last visited node in the traversal.
 *
 * Checking BST properties:
 *
 * When visiting a node, we first call the dfs function on its left child. If this function call returns False, it means the left subtree contains a violation of the BST rules, and thus, we return False immediately to stop checking further.
 * After checking the left subtree, we examine the current node by comparing its value with prev. If the current node's value is less than or equal to prev, this means the in-order traversal sequence is broken, and hence, the tree is not a valid BST. We return False in this case.
 * The prev variable is then updated to the current node's value, indicating that this node has been processed, and we move to the right subtree.
 * Recursive base case: For a None node, which signifies the end of a branch, the dfs function returns True, as an empty tree or subtree is valid by definition.
 *
 * Final validation: After the entire tree has been traversed, if no violations were found, the initial call to dfs(root) will ultimately return True, confirming the tree is a valid BST.
 *
 * The key algorithmic pattern used here is recursion, combined with the properties of in-order traversal for a BST. The recursive function dfs combines the logic for traversal and validation, effectively checking the BST properties as it moves through the tree.
 *
 * Example Walkthrough
 * Let's consider a small binary tree with the following structure to illustrate the solution approach:
 *
 *     2
 *    / \
 *   1   3
 * Our target is to walk through the solution approach provided above and confirm whether this tree is a valid BST.
 *
 * Step 1 - In-order traversal (DFS): We begin the depth-first search (DFS) with an in-order traversal from the root node which is 2. The in-order traversal dictates that we visit the left subtree first, then the root node, and finally the right subtree.
 *
 * Step 2 - Global variable: Initially, the prev variable is set to negative infinity. It will help us to keep track of the last visited node's value.
 *
 * Step 3 - Checking BST properties:
 *
 * We start with the left child (node 1). Since node 1 has no children, we compare it to prev, which is negative infinity. Node 1 is greater, so we update prev to 1, and then we return back to node 2.
 * Now, we are at node 2 and compare its value with prev which is now 1. The value of node 2 is greater; therefore, it satisfies the BST condition. We update prev to 2 and proceed to the right subtree.
 * In the right subtree, we have node 3. We call the dfs function on node 3. As it has no children, we compare it to prev (which is now 2). Node 3 is greater than 2, so we update prev to 3.
 * Step 4 - Recursive base case: Since we reached the leaf nodes without encountering any None nodes, we confirm that all subtrees are valid BSTs as well.
 *
 * Step 5 - Final validation: After visiting all the nodes following the in-order traversal, and none of the recursive dfs calls returned False, the whole tree is thus confirmed to be a valid BST. The final call to dfs(root) returns True.
 *
 * By following the in-order traversal method and checking each node against the previous node's value, we have verified that the given tree meets all the properties of a valid BST:
 *
 * Each node's left subtree contains only nodes with values less than the node's own value.
 * Each node's right subtree contains only nodes with values greater than the node's own value.
 * All the subtrees are valid BSTs on their own.
 * In summary, the example binary tree with nodes 1, 2, and 3 is indeed a valid binary search tree.
 *
 *
 */
public class Q98_ValidateBinarySearchTree {
    private Integer previousValue; // variable to store the previously visited node's value

    /**
     * Validates if the given binary tree is a valid binary search tree (BST).
     *
     * @param root The root of the binary tree to check.
     * @return true if the given tree is a BST, false otherwise.
     */
    public boolean isValidBST(TreeNode root) {
        previousValue = null; // Initialize previousValue as null before starting traversal
        return inOrderTraversal(root);
    }

    /**
     * Performs an in-order depth-first traversal on the binary tree to validate BST property.
     * It ensures that every node's value is greater than the values of all nodes in its left subtree
     * and less than the values of all nodes in its right subtree.
     *
     * @param node The current node being visited in the traversal.
     * @return true if the subtree rooted at 'node' satisfies BST properties, false otherwise.
     */
    private boolean inOrderTraversal(TreeNode node) {
        if (node == null) {
            return true; // Base case: An empty tree is a valid BST.
        }
        // Traverse the left subtree. If it's not a valid BST, return false immediately.
        if (!inOrderTraversal(node.left)) {
            return false;
        }
        // Check the current node value with the previous node's value.
        // As it's an in-order traversal, previousValue should be less than the current node's value.
        if (previousValue != null && previousValue >= node.val) {
            return false; // The BST property is violated.
        }
        previousValue = node.val; // Update previousValue with the current node's value.
        // Traverse the right subtree. If it's not a valid BST, return false immediately.
        if (!inOrderTraversal(node.right)) {
            return false;
        }
        return true; // All checks passed, it's a valid BST.
    }
}

