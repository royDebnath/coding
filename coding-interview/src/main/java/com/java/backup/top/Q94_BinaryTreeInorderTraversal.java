package com.java.backup.top;

import com.java.codinground.leetcode.support.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Q94_BinaryTreeInorderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> nums = new ArrayList<>();
        inorder(root, nums);
        return nums;
    }

    // Inorder : Left -> Node -> Right
    public void inorder(TreeNode root, List<Integer> nums) {
        if (root == null) return;
        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }

    // Preorder: Node -> Left -> Right
    public void preorder(TreeNode root, List<Integer> nums) {
        if (root == null) return;
        nums.add(root.val);
        preorder(root.left, nums);
        preorder(root.right, nums);
    }

    // Postorder : Left -> Right -> Node
    public void postorder(TreeNode root, List<Integer> nums) {
        if (root == null) return;
        postorder(root.left, nums);
        postorder(root.right, nums);
        nums.add(root.val);
    }
}
