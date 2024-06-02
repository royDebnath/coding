package com.java.codinground.leetcode.categories.trees;

import com.java.codinground.leetcode.support.TreeNode;

/**
 * Problem Description
 * The problem provided is about checking symmetry in a binary tree. Specifically, you are given a binary tree's root node, and your task is to determine if the tree is a mirror image of itself when divided down the middle. Essentially, this is asking whether the left and right sub-trees of the tree are mirror images of each other.
 *
 * Intuition
 * To solve this problem, the idea is to use a Depth-First Search (DFS) approach. The solution involves recursively comparing the left sub-tree with the right sub-tree to ensure they are mirrors of each other. This is done by checking that:
 *
 * The value of the current node in the left sub-tree is equal to the value of the current node in the right sub-tree.
 * The left child of the left sub-tree is a mirror of the right child of the right sub-tree.
 * The right child of the left sub-tree is a mirror of the left child of the right sub-tree.
 * We can start this process by comparing the root node with itself, initiating symmetry checks between its left and right child nodes. If both nodes are null, it means we are comparing leaves, and thus they are symmetric. If only one is null or the values of the two nodes are not equal, then the tree is not symmetric.
 *
 * The recursion continues this process of mirroring the checks to progressively lower levels of the tree until all mirrored nodes pass the comparisons, or a pair of nodes fails, which indicates the tree is not symmetric.
 *
 * Solution Approach
 * The solution to the given problem relies on a Depth-First Search (DFS) algorithm, which explores as far as possible along each branch before backtracking. Here, DFS is applied recursively through a helper function named dfs.
 *
 * The dfs function is designed to take two nodes as arguments—root1 and root2. Initially, these arguments are both set to the root of the whole tree since we start by comparing the tree to itself. Here's how the dfs function works:
 *
 * Base case for null nodes: If both root1 and root2 are None, this means that both branches being compared have reached the end simultaneously, indicating a mirrored structure at this branch level. So, it returns True.
 *
 * Case for asymmetry: If one of the nodes is None (while the other isn’t), or if the values of the two nodes are not equal, the function identifies a break in symmetry and returns False.
 *
 * Recursive calls: If neither of the above cases is true, the dfs function calls itself twice more: once comparing the left child of root1 with the right child of root2, and then comparing the right child of root1 with the left child of root2. This is the crux of the mirroring check, making sure that each "mirror" position across the two sub-trees holds an equivalent value.
 *
 * The dfs function uses a logical AND && to combine the results of its recursive calls. Both calls must return True for the function to return True, ensuring that all parts of the tree adhere to the symmetry condition.
 *
 * Finally, the isSymmetric function of the Solution class makes the initial call to dfs using the root as both arguments. If the entire tree is symmetric, the function will eventually return True; if any asymmetry is found at any level, it will return False.
 *
 * The overall time complexity of the algorithm is O(n), where n is the number of nodes in the tree, because each node is visited once. The space complexity is also O(n) for the call stack due to recursion, which in the worst case, could be the height of the tree. For a balanced tree, this would result in a space complexity of O(log n) due to its height.
 *
 * Example Walkthrough
 * Let's consider a simple, symmetric binary tree to illustrate the solution approach. Here is the tree structure:
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * Now let's walk through the dfs function with this tree to see how it validates symmetry:
 *
 * The isSymmetric function starts and calls dfs, passing the root node as both root1 and root2, since we're comparing the tree with itself.
 *
 * As none of the root1 and root2 are null and their values are equal (value 1), the dfs function continues to the recursive calls.
 *
 * Two dfs calls are made: one for root1's left (Node 2) and root2's right (also Node 2), the other for root1's right (Node 2) and root2's left (also Node 2). Both pairs are identical, so we proceed.
 *
 * Now, from the first recursive call of dfs, two more recursive calls are made:
 *
 * Compare root1's left (Node 3) and root2's right (Node 3).
 * Compare root1's right (Node 4) and root2's left (Node 4).
 * Simultaneously, from the second recursive call of dfs, another two recursive calls are made:
 *
 * Compare root1's left (Node 4) and root2's right (Node 4).
 * Compare root1's right (Node 3) and root2's left (Node 3).
 * All subsequent recursive calls find that the nodes are either simultaneously null or with equal values, satisfying the base case and the equality check condition. Hence, every recursive call returns True.
 *
 * Since the && operator is used to combine the results, and all recursive calls returned True, the initial call to dfs also returns True.
 *
 * The isSymmetric function concludes that the tree is symmetric.
 *
 * This tree has passed all the checks outlined in the approach; each node on the left has a corresponding node with equal value on the right, and vice versa. The recursion accurately captures this mirror image property, ensuring that a node and its "mirror" node are consistently equal in value.
 *
 *
 */
public class Q101_SymmetricTree {
    /**
     * Determines if a binary tree is symmetric around its center (mirrored).
     *
     * @param root The root of the tree.
     * @return true if the tree is symmetric, false otherwise.
     */
    public boolean isSymmetric(TreeNode root) {
        // Start DFS from the root for both subtrees for comparison.
        return isMirror(root, root);
    }

    /**
     * Helper method to perform a DFS to check for symmetry by comparing nodes.
     *
     * @param node1 The current node from the first subtree.
     * @param node2 The current node from the second subtree.
     * @return true if the two subtrees are mirrors of each other, false otherwise.
     */
    private boolean isMirror(TreeNode node1, TreeNode node2) {
        // Both nodes are null, meaning this branch is symmetric.
        if (node1 == null && node2 == null) {
            return true;
        }
        // If only one of the nodes is null, or their values differ,
        // the tree cannot be symmetric.
        if (node1 == null || node2 == null || node1.val != node2.val) {
            return false;
        }
        // Continue to compare the left subtree of node1 with the right subtree of node2
        // and the right subtree of node1 with the left subtree of node2. Both comparisons
        // must be true for the subtree to be symmetric.
        return isMirror(node1.left, node2.right) && isMirror(node1.right, node2.left);
    }
}
