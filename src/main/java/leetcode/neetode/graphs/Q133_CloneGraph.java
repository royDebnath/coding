package leetcode.neetode.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The problem is about creating a deep copy of a connected undirected graph. A deep copy means a new graph where each node and edge is a replica of the original graph but is an entirely new instance, with no shared references with the original graph.
 *
 * In a graph, nodes are interconnected through edges. To represent this in code, each node has an integer value and a list of its neighboring nodes. The goal is to copy all the nodes and their connections in such a way that if you manipulate the copied graph, the original graph remains unaffected.
 *
 * The representation of the graph is done using an adjacency list, which is an array of lists where each list represents a node and contains all of its neighbors. For the purposes of this problem, every node's value is assumed to be unique and equal to its 1-based index in the adjacency list, simplifying the graph's representation and the copying process.
 *
 * The challenge here is to ensure that while copying the nodes, their neighbors are also copied correctly, and any neighbor connections in the new graph mirror those in the original graph, preserving the structure.
 *
 * To solve the problem, the idea is to traverse the graph, starting from the given node, and create new nodes as we go. Since the original graph could have cycles or shared neighbors, it is crucial to keep track of the nodes that have already been copied to avoid infinite loops and ensure that neighbors are not duplicated in the new graph.
 *
 * The algorithm involves a depth-first search (DFS) traversal with the following steps:
 *
 * Initialize a visited dictionary that will map original nodes to their corresponding cloned nodes. This will help check if a node has been visited, and also provide a reference to its cloned counterpart.
 *
 * Define a recursive function clone(node) that takes a node from the original graph.
 *
 * If the node is None, return None (this handles empty graph cases).
 * If the node has already been visited, return its cloned counterpart from the visited dictionary.
 * If the node is new, create a clone with the same value.
 * Store this cloned node in the visited dictionary with the original node as the key.
 * Iteratively clone all the neighbor nodes using the same clone function and append them to the neighbors list of the cloned node.
 * Start the cloning process by calling clone(node) on the given node and return its result, which is a reference to the cloned graph's starting node.
 *
 * By following these steps, we ensure that each node is visited once, and a deep copy of the graph is created, with all the original connections between the nodes preserved in the new graph.
 *
 * Example Walkthrough
 *
 * Let's say we have a small undirected graph represented by the adjacency list [[2,4],[1,3],[2,4],[1,3]], which corresponds to the following graph:
 *
 * 1Node 1 -- Node 2
 * 2 |         |
 * 3Node 4 -- Node 3
 * Initialization:
 *
 * We initialize an empty dictionary called visited to keep track of the cloned nodes.
 * Start Cloning:
 *
 * We begin by calling clone(node) on the node with value 1 (Node 1).
 * Node 1:
 *
 * Since Node 1 isn't in visited, we create a cloned node C1 and add it to visited with visited[1] = C1.
 * We proceed to clone Node 1's neighbors (Node 2 and Node 4).
 * Node 2:
 *
 * We call clone(node) on Node 1's first neighbor, Node 2.
 * Node 2 isn't in visited, so we create a clone C2 and add it to visited.
 * We then clone Node 2's neighbors (Node 1 and Node 3). Since Node 1 is already visited, we link C1 (Node 1's clone) as a neighbor to C2.
 * Node 3:
 *
 * When cloning Node 2's neighbors, we call clone(node) on Node 3.
 * Node 3 isn't in visited, so we create a clone C3 and add it to visited.
 * We clone Node 3's neighbors (Node 2 and Node 4). Node 2's clone, C2, is already in visited, so we link C2 as a neighbor to C3.
 * Node 4:
 *
 * Now, from Node 1, we clone the second neighbor, Node 4, by calling clone(node) on it.
 * We create C4 since Node 4 isn't in visited and add it to visited.
 * We clone Node 4's neighbors (Node 1 and Node 3). Clones for both these nodes (C1 and C3) are found in visited, so we link both as neighbors to C4.
 * Completing the Graph:
 *
 * With all nodes visited, the DFS cloning is complete. Each node in the graph is cloned exactly once, and their neighbors are interconnected following their original structure.
 * Return:
 *
 * We return C1, which now has C2 and C4 as its neighbors, representing the start of the cloned graph.
 * The cloned graph is now a deep copy of the original and can be manipulated without affecting the original graph. The connections are:
 *
 * 1C1 -- C2
 * 2 |     |
 * 3C4 -- C3
 * Each Ci denotes the cloned node corresponding to the original node of the same index. The deep copy process ensures that even if the initial graph had a more complex structure with cycles, the algorithm would have correctly cloned it without entering an infinite loop, since each node is visited only once.
 *
 *
 */
public class Q133_CloneGraph {
        // A HashMap to keep track of all the nodes which have already been copied.
        private Map<Node, Node> visited = new HashMap<>();

        // This function returns the clone of the graph.
        public Node cloneGraph(Node node) {
            // If the input node is null, then return null.
            if (node == null) {
                return null;
            }

            // If the node has already been visited i.e., already cloned,
            // return the cloned node from the map.
            if (visited.containsKey(node)) {
                return visited.get(node);
            }

            // Create a new node with the value of the input node (clone it).
            Node cloneNode = new Node(node.val);
            // Mark this node as visited by putting into the visited map.
            visited.put(node, cloneNode);

            // Iterate over all the neighbors of the input node.
            for (Node neighbor : node.neighbors) {
                // Perform a depth-first search (DFS) for each neighbor,
                // and add the clone of the neighbor to the neighbors list
                // of the clone node.
                cloneNode.neighbors.add(cloneGraph(neighbor));
            }

            // Return the cloned graph node.
            return cloneNode;
    }
}



// Definition for a graph node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
