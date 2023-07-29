package com.java.codinground.blind75;

import com.java.codinground.support.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

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
 * In the case of this problem, that will mean pushing the current row array (row) onto our answer array (ans).
 *
 * We'll continue this process until the queue is empty, at which point we will have reached the end
 * of the binary tree, and can return ans.
 *
 * Time Complexity: O(N) where N is the number of nodes in the binary tree
 * Space Complexity: O(N) for our answer array
 */
public class Q27_BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int qlen = queue.size();
            List<Integer> row = new ArrayList<>();
            for (int i = 0; i < qlen; i++) {
                TreeNode curr = queue.poll();
                row.add(curr.val);
                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
            ans.add(row);
        }
        return ans;
    }
}
