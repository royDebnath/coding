package com.java.codinground.leetcode.categories.graphs;

/**
 * In this problem, we're given a graph that was a tree initially, but an additional edge was added to it. An edge in this context is represented by a pair of nodes that it connects, given as [a, b]. Since a proper tree has no cycles and is connected, the additional edge creates a single cycle in the graph. Our task is to identify and remove this redundant edge, restoring the graph to a tree. The graph is represented as an array edges of n edges, where each edge connects two different nodes from 1 to n, and n is the number of nodes in the tree. The goal is to find the redundant edge that can be removed to make the graph a tree again. If there are multiple redundant edges, we need to return the one that appears last in the edges array. This problem checks the understanding of graph properties, specifically tree structures, and graph algorithms.
 *
 * Intuition
 *
 * The intuition behind this solution is based on the Union-Find algorithm, which is a classic data structure for keeping track of disjoint sets. The idea is to iterate through the list of edges and connect the nodes using the Union operation. We start by initializing a parent array p, representing each node's parent in the disjoint set. Initially, each node is its own parent, indicating that they are each in separate sets.
 *
 * As we process the edges, we use a Find operation, implemented as the find function, to determine the set representative (parent) for each node. If the nodes connected by the current edge already have the same parent, it means that adding this edge would form a cycle, which is contrary to the tree definition. When we identify such an edge, we know it’s redundant and must be removed.
 *
 * The clever part of the algorithm is the use of path compression in the Find operation. This optimization steps speeds up subsequent queries for the set representative by making each node along the path point to the ultimate parent directly.
 *
 * Therefore, when we come across an edge connecting two nodes that are already in the same set (have the same parent), this edge is the redundant one, and we should return it as the answer. In the case that we process all edges without returning any, though highly unlikely for this problem's constraints, we return an empty list. However, since the problem states there's always one additional edge, we're guaranteed to find a redundant connection during our iteration through the edge list.
 *
 * Example Walkthrough
 *
 * Let's use a small example graph to illustrate the solution approach as described. Suppose our graph, which was originally a tree, has 4 nodes, and the edges array is as follows: [[1, 2], [2, 3], [3, 1], [4, 3]]. We can already see that there is a cycle formed by the edges [1, 2], [2, 3], and [3, 1]. According to the problem statement, we need to find the redundant edge that appears last in the edges list, which upon a preliminary look seems to be [3, 1].
 *
 * Initialization:
 *
 * We initialize our parent array p with enough space for our 4 nodes (in reality, our solution sets this up for more nodes, but for simplicity, here we'll limit it to the nodes we have). Thus, p = [0, 1, 2, 3, 4] (we include 0 just to match the node indices with their parent indices for convenience).
 *
 * Union-Find Algorithm:
 *
 * We iterate through the edges array using the find and union operations as described:
 *
 * For the first edge [1, 2], we find the parents of 1 and 2, which are themselves, so we unite them by setting p[find(1)] = find(2). Now p becomes [0, 2, 2, 3, 4].
 *
 * For the second edge [2, 3], we find the parents of 2 and 3, which are 2 and 3 respectively. Since they are different, we unite them by setting p[find(2)] = find(3). Now p becomes [0, 3, 2, 3, 4] and after path compression [0, 3, 3, 3, 4].
 *
 * For the third edge [3, 1], we find the parents of 3 and 1. Both are 3 due to the path compression that has already occurred, indicating we've encountered a cycle. As this is the redundant edge we're looking for, we immediately return [3, 1].
 *
 * The fourth edge [4, 3] does not get processed as we've already found and returned the redundant edge in the step above.
 *
 * Returning the Result:
 *
 * We successfully find that [3, 1] is the redundant edge that creates a cycle in the graph.
 *
 * The function returns [3, 1] as the answer, which is the edge that must be removed to eliminate the cycle and restore the graph to a proper tree.
 *
 * In this example, we can see that the Union-Find algorithm effectively identifies the edge causing a cycle due to the immediate parent check in the find operation, assisted by the path compression that optimizes this check as we progress through the array of edges.
 *
 *
 */
public class Q684_RedundantConnection {

        // Array representing the parents of each node in the disjoint-set (union-find).
        private int[] parent;

        // Function to find the redundant connection, taking edges of a graph.
        public int[] findRedundantConnection(int[][] edges) {

            // Initialize the parent array for a maximum of 1010 nodes.
            parent = new int[1010];
            for (int i = 0; i < parent.length; i++) {
                // Initially, each node is its own parent (self loop).
                parent[i] = i;
            }

            // Iterate through all edges.
            for (int[] edge : edges) {
                int node1 = edge[0];
                int node2 = edge[1];

                // If both nodes have the same parent, the edge is redundant.
                if (find(node1) == find(node2)) {
                    return edge;
                }

                // Union the sets of two nodes by setting the parent of one as the other.
                parent[find(node1)] = find(node2);
            }

            // If no redundant connection is found, return null (should not happen according to the problem statement).
            return null;
        }

        // Recursive function to find the parent of a given node.
        private int find(int node) {

            // Path compression: if the node is not its own parent, recursively find its parent and update the reference.
            if (parent[node] != node) {
                parent[node] = find(parent[node]);
            }
            // Return the parent of the node.
            return parent[node];
        }
}
