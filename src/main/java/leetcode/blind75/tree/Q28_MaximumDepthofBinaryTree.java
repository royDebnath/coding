package leetcode.blind75.tree;

import leetcode.support.TreeNode;

/**
 * Given the root of a binary tree, return its maximum depth.
 *
 * A binary tree's maximum depth is the number of nodes along the longest path
 * from the root node down to the farthest leaf node.
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 3
 *
 * Solution :
 *
 * Lets have faith in recursion and assume that we are already given the maximum depth
 * of root's left and right subtrees by recursion.
 * So to fnd the maximum depth of this binary tree,
 * we will have to take out the maximum of the 2 depths given to us by recursion,
 * and add 1 to that to consider the current level i.e. root's level into our depth.
 *
 *
 */
public class Q28_MaximumDepthofBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left= new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(maxDepth(root));
    }

    public static int maxDepth(TreeNode root) {
        return maxDepth(root, 0);
    }
    private static int maxDepth(TreeNode root, int depth){
        if(root == null) return depth;
        int leftDepth  = maxDepth(root.left,  depth + 1);
        int rightDepth = maxDepth(root.right, depth + 1);
        return Math.max(leftDepth, rightDepth);
    }
}
