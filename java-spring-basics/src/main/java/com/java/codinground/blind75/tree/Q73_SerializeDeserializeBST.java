package com.java.codinground.blind75.tree;

import com.java.codinground.support.TreeNode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 449. Serialize and Deserialize BST
 * Medium
 *
 * Serialization is converting a data structure or object into a
 * sequence of bits so that it can be stored in a file or memory buffer,
 * or transmitted across a network connection link to be reconstructed later
 * in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary search tree.
 * There is no restriction on how your serialization/deserialization algorithm should work.
 * You need to ensure that a binary search tree can be serialized to a string,
 * and this string can be deserialized to the original tree structure.
 *
 * The encoded string should be as compact as possible.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [2,1,3]
 * Output: [2,1,3]
 * Example 2:
 *
 * Input: root = []
 * Output: []
 *
 * Approach
 * Perform DFS and store the result in a string seperated by ',' and null is represented by 'x'

 * Complexity
 * Time complexity:
 * O(N)
 *
 * Space complexity:
 * O(N)
 *
 *
 */
public class Q73_SerializeDeserializeBST {

}

class Codec {
    public String serialize(TreeNode root) {
        StringBuilder res = new StringBuilder();
        serialize(root,res);
        return res.toString();
    }

    private void serialize(TreeNode root, StringBuilder res){
        if(root == null){
            res.append("x,");
            return ;
        }

        res.append(root.val);
        res.append(',');

        serialize(root.left, res);
        serialize(root.right, res);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Deque<String> q = new LinkedList<>();
        q.addAll(Arrays.asList(data.split(",")));
        return deserialize(q);
    }

    private TreeNode deserialize(Deque<String> q){
        String res = q.remove();
        if(res.equals("x")){
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(res));

        root.left = deserialize(q);
        root.right = deserialize(q);

        return root;
    }
}