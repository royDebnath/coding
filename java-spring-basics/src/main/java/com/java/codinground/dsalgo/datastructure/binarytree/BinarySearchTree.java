package com.java.codinground.dsalgo.datastructure.binarytree;

import com.java.codinground.support.TreeNode;

class Node{
	int data;
	Node left;
	Node right;	
	public Node(int data){
		this.data = data;
		left = null;
		right = null;
	}
}

public class BinarySearchTree {


	public static  Node root;
	public BinarySearchTree(){
		this.root = null;
	}
	
	
	
	/*
	 * Inorder Traversal (left -> root -> right)
	 * 
	 * In this traversal the left sub tree of the given node is visited first,
	 * then the value at the given node is printed and then the right sub tree
	 * of the given node is visited. This process is applied recursively all the
	 * node in the tree until either the left sub tree is empty or the right sub
	 * tree is empty.
	 */
	public void displayInOrder(Node root){
		if(root!=null){
			displayInOrder(root.left);
			System.out.print(" " + root.data);
			displayInOrder(root.right);
		}
	}

		/*
	 * Preorder Traversal (root -> left -> right)
	 * 
	 * In this traversal the value at the given node is printed first and then
	 * the left sub tree of the given node is visited and then the right sub
	 * tree of the given node is visited. This process is applied recursively
	 * all the node in the tree until either the left sub tree is empty or the
	 * right sub tree is empty.
	 */
	private void displayPreOrder(Node root) {
		if (root != null) {
			System.out.print(root.data + ", ");
			displayPreOrder(root.left);
			displayPreOrder(root.right);
		}
	}

		/*
	 * Postorder Traversal (left -> right -> root)
	 * 
	 * In this traversal the left sub tree of the given node is traversed first,
	 * then the right sub tree of the given node is traversed and then the value
	 * at the given node is printed. This process is applied recursively all the
	 * node in the tree until either the left sub tree is empty or the right sub
	 * tree is empty.
	 */
	private void displayPostOrder(Node root) {
		if (root != null) {
			displayPostOrder(root.left);
			displayPostOrder(root.right);
			System.out.print(root.data + ", ");
		}
	}
	
    /*
	start from the root and compare root.data with n
	if n < root.data  that means we need to go to the left of the root.
	if n > root.data that means we need to go to the right of the root.
	if any point of time root.data is equal to the n then we have found the node, return true.
	if we reach to the leaves (end of the tree) return false, we didn’t find the element
	*/
	public boolean find(int key){
		Node current = root;
		while(current!=null){
			if(key==current.data){
				return true;
			}else if(key<current.data){
				current = current.left;
			}else{
				current = current.right;
			}
		}
		return false;
	}
	
	/*	Very much similar to find() operation.
	To insert a node our first task is to find the place to insert the node.
	Take current = root .
	start from the current and compare root.data with n
	if current.data is greater than n that means we need to go to the left of the root.
	if current.data is smaller than n that means we need to go to the right of the root.
	if any point of time current is null that means we have reached to the leaf node,
	insert your node here with the help of parent node. (See code)
	*/
	public void insert(int data){
		Node newNode = new Node(data);
		if(root==null){
			root = newNode;
			return;
		}
		Node current = root;
		Node parent;
		while(true){
			parent = current; // storing in parent because current becomes null
			if(data<current.data){
				current = current.left;
				if(current==null){
					parent.left = newNode;
					return;
				}
			}else{
				current = current.right;
				if(current==null){
					parent.right = newNode;
					return;
				}
			}
		}
	}


	/*
	 * Intution: Use the BST property to search the node and then delete if found the required node.
	 *
	 * So if the traget node has value less than root then we will surely get it in the left subtree...so just call ur recursive function for the left subtree.
	 * If the traget node has value greater than root then we will surely get it in the right subtree...so just call ur recursive function for the right subtree.
	 * And now comes the case when u have to do your work that is root itself is the required node to be deleted. Here again comes three cases:
	 * If left of root is null and u also have to delete the root node...then just simply return the right subtree.
	 *
	 * If right of root is null and u also have to delete the root node...then just simply return the left subtree.
	 *
	 * Both are not null then you have to not just delete the node but also maintain the BST structure.
	 * So now you have to think if you delete the root node then which node can optimally replace it so that all the nodes on left are still small and on right are larger.
	 * So that node will be the node just greater than the largest node in the left subtree which is the smallest node in the right subtree.
	 *
	 * So point your pointer on the right subtree and then move it to the left most node of this subtree that will be your required node and so now replace the value of your root with this node value which will ensure that the key which u wanted to delete is deleted and the value there is the right value.
Now you have to delete that node whose value is already present in the root...so now that work will be done by the recursion so now just pass that right subtree in which the value is present with that nodes value which will be now the target
	 */
	public Node deleteNode(Node root, int key){
		if(root==null) return null;

		if(key<root.data){
			root.left = deleteNode(root.left,key);
			return root;
		}

		else if(key>root.data){
			root.right = deleteNode(root.right,key);
			return root;
		}

		else{
			if(root.left==null){
				return root.right;
			}
			else if(root.right==null){
				return root.left;
			}
			else{
				Node min = root.right;
				while(min.left!=null){
					min = min.left;
				}

				root.data = min.data;
				root.right = deleteNode(root.right,min.data);
				return root;
			}
		}
	}

	/**
	 * Create Mirror of a given tree
	 */
	Node mirror(Node node) {
		if (node == null)
			return node;

		/* do the subtrees */
		Node left = mirror(node.left);
		Node right = mirror(node.right);

		/* when the recursion reaches this point,
		we are at the end/base of the tree where left and right are leaf nodes
		swap the left and right pointers */
		node.left = right;
		node.right = left;

		return node;
	}

	/**
	 * Check if two trees are mirror
	 */
	boolean isMirror(Node a, Node b) {
		/* Base case : Both empty */
		if (a == null && b == null)
			return true;

		// If only one is empty
		if (a == null || b == null)
			return false;

		/*
		 * Both non-empty, compare them recursively Note that in recursive
		 * calls, we pass left of one tree and right of other tree
		 */
		return a.data == b.data && isMirror(a.left, b.right) && isMirror(a.right, b.left);
	}

	/**
	 * Checks if a tree is symmetric
	 */
	boolean isSymmetric(Node node) {
		// check if tree is mirror of itself
		return isMirror(root, root);
	}

	/**
	 * Height of binary tree
	 */
	int findHeight(Node node) {
		if (node == null)
			return 0;
		return 1 + Math.max(findHeight(node.left), findHeight(node.right));
	}

	/**
	 * Diameter of a tree
	 * 
	 */
	int getDiameter(Node root) {
		if (root == null)
			return 0;
		int rootDiameter = findHeight(root.left) + findHeight(root.right) + 1;
		int leftDiameter = getDiameter(root.left);
		int rightDiameter = getDiameter(root.right);
		return Math.max(rootDiameter, Math.max(leftDiameter, rightDiameter));
	}


	/**
	 *
	 *                          3
	 *                         / \
	 *                        1   \--8
	 *                         \    /  \
	 *                          2  4    \--10
	 *                              \      / \
	 *                               6    9   20
	 *                                        / \
	 *                                       15  25
	 *                                        \
	 *                                        16
	 *
	 */
	public static void main(String arg[]){

		BinarySearchTree b = new BinarySearchTree();
		b.insert(3);b.insert(8);
		b.insert(1);b.insert(4);b.insert(6);b.insert(2);b.insert(10);b.insert(9);
		b.insert(20);b.insert(25);b.insert(15);b.insert(16);
		System.out.println("Original Tree Inorder : ");
		b.displayInOrder(b.root);
		System.out.println("\n");
		System.out.println("Original Tree Preorder : ");
		b.displayPreOrder(b.root);
		System.out.println("\n");
		System.out.println("Original Tree Postorder : ");
		b.displayPostOrder(b.root);
		System.out.println("\n");
		System.out.println("");
		System.out.println("Check whether Node with value 4 exists : " + b.find(4));
		System.out.println("Delete Node with no children (2) : " + b.deleteNode(root, 2));
		b.displayInOrder(root);
		System.out.println("\n Delete Node with one child (4) : " + b.deleteNode(root,4));
		b.displayInOrder(root);
		System.out.println("\n Delete Node with Two children (10) : " + b.deleteNode(root,10));
		b.displayInOrder(root);

		BinarySearchTree b1 = new BinarySearchTree();
		b1.insert(4);
		b1.insert(1);
		b1.insert(2);
		b1.insert(3);
		b1.displayInOrder(root);


	}
}

