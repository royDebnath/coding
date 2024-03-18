package com.java.codinground.leetcode.top;

import com.java.codinground.support.TreeNode;

import java.util.*;

/**
 * Given the root of a binary tree, return the level order traversal of its nodes' values.
 * (i.e., from left to right, level by level).
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[9,20],[15,7]]
 *
 * A binary tree level order traversal generally recommends a breadth first search (BFS) approach
 * with the use of a queue data structure. When we process a node (curr),
 * we'll push the node's children onto the end of the queue in the order in which
 * we want to traverse (in this case, left to right).
 * In this way, we'll have finished putting the next row in the queue at the same time
 * we finish iterating through this row.
 *
 * To help us keep track of the rows, we just nest the main loop inside another loop.
 * At the beginning of the outer loop, we capture the queue length,
 * which will tell us how long the row is. Then we can iterate through that many nodes,
 * popping them off the queue's front one at a time, then process any end-of-row instructions.
 * In the case of this problem, that will mean
 * pushing the current row array (row) onto our answer array (ans).
 *
 * We'll continue this process until the queue is empty, at which point we will have reached the end
 * of the binary tree, and can return ans.
 *
 * Time Complexity: O(N) where N is the number of nodes in the binary tree
 * Space Complexity: O(N) for our answer array
 */
public class Q102_BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left= new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println(rightSideView(root).toString());
    }
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        Deque<TreeNode> deque = new ArrayDeque<>(); // have to take Deque here to get last element
        deque.offer(root);
        while (!deque.isEmpty()) {
            ans.add(deque.peekLast().val);

            //Add the next level of nodes in the queue
            int qlen = deque.size(); // nodes in the current level
            for (int i = 0; i < qlen; i++) { //using qlen instead of deque.size because adding the next level in the deque inside this loop
                TreeNode curr = deque.poll();
                if (curr.left != null) deque.add(curr.left); //populating the next level in deque
                if (curr.right != null) deque.add(curr.right);//populating the next level in deque
            }
        }
        return ans;
    }
}
