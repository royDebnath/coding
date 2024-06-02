package com.java.backup.blind75.tree;

import com.java.codinground.leetcode.support.TreeNode;

/**
 * 572. Subtree of Another Tree
 * Easy
 *
 * Given the roots of two binary trees root and subRoot, return true if
 * there is a subtree of root with the same structure and node values of subRoot and false otherwise.
 *
 * A subtree of a binary tree  is a tree that consists of a node in tree and
 * all of this node's descendants. The tree could also be considered as a subtree of itself.
 *
 *
 */
public class Q74_SubtreeOfAnotherTree {
    public boolean isSubtree(TreeNode s, TreeNode t) { // takes O(m x n)
        if (s == null) {
            return t == null;
        }
        return isSame(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private boolean isSame(TreeNode t1, TreeNode t2) { // takes O(n)
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;

        if (t1.val != t2.val) return false;
        return isSame(t1.left, t2.left) && isSame(t1.right, t2.right);
    }
}
