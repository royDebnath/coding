package com.roydebnath.coding.leetcode.neetode.trees;

import com.roydebnath.coding.leetcode.helper.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * This problem asks us to determine the values of the nodes visible when we look at a binary tree
 * from the right side. This essentially means that we want the last node's value in each level of the tree,
 * proceeding from top to bottom.
 *
 * Will use level order traversal and put each row in a queue.
 * whill traverse the queue from left to right to fill the next row in the queue.
 * Everytime, a level of nodes is filled in the queue, will take the last item of the queue
 * and add it to answer.
 *
 * Since we want last item of queue, will use a Deque heere, instead of a simple Queue LinkedList.
 *
 */
public class Q199_BinaryTreeRightSideView {

    // Function to get a list of integers representing the right side view of the binary tree
    public List<Integer> rightSideView(TreeNode root) {
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

