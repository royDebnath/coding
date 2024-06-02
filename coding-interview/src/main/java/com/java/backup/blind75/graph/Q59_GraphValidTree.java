package com.java.backup.blind75.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Problem: Given n nodes labeled from 0 to n — 1 and a list of undirected edges (each edge is a pair of nodes),
 * write a function to check whether these edges make up a valid tree.
 * <p>
 * Example(s):
 * <p>
 * Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
 * Show Hint Note: you can assume that no duplicate edges will appear in edges.
 * Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 * <p>
 * Solution:
 * <p>
 * A valid Tree should not have a loop/cycle. Every node should be connected.
 * <p>
 * In other words, a tree is an undirected graph which is connected and has no cycles.
 *
 *Code :
 *
 * Create a array of sets of n length, where n is the number of nodes.
 * the index of the array denotes the node of the array
 * the elements of the sets in that index of array denotes the other nodes that are connected to that index node.
 * The above array of sets is the adjacency list representation of the graph
 *
 * create an array of booleans of length n. index of the array denotes the node.
 * name it visited. This keeps record of which nodes are visited.
 *
 * Start a dfs from the 0th node of the graph. recursively keep calling the dfs (hasCycle) to mark the
 * nodes visited to true in the visited[] array by index. also while doing the recursion check if we are
 * encountering a node which is already visited, that means the graph has a cycle. While checking if a node is
 * already visited or not we need to exclude the parent node, as it will obviously be visited by then.
 *
 * If while doing the dfs we find out the graph has a cycle then its not a tree.
 *
 * If at the end of the dfs, check the visited[] array. if any node is not visited then its not a tree. *
 *
 */
public class Q59_GraphValidTree {
    public static void main(String[] args) {

    }

    public boolean validTree(int n, int[][] edges) {
        List<Set<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adjList.add(new HashSet<>());
        }

        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];

        // Check if graph has cycle
        if (hasCycle(0, visited, adjList, -1)) {
            return false;
        }

        // Check if graph is connected
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean hasCycle(int node, boolean[] visited, List<Set<Integer>> adjList, int parent) {
        visited[node] = true;

        for (int nextNode : adjList.get(node)) {
            // (1) If nextNode is visited but it is not the parent of the curNode, then there is cycle
            // (2) If nextNode is not visited but we still find the cycle later on, return true;
            if ((visited[nextNode] && parent != nextNode) ||  //
                    (!visited[nextNode] && hasCycle(nextNode, visited, adjList, node))) { //dfs for consecutive next nodes
                return true;
            }
        }
        return false;
    }

    public boolean hasCycle(int node, boolean[] visited, int parent, List<Integer>[] adj) {
        visited[node] = true;
        for (int nextNode : adj[node]) {
            if ((visited[nextNode] && nextNode!=parent) ||
                    (!visited[nextNode] && hasCycle(nextNode, visited, node, adj))){
                return true;
            }
        }
        return false;
    }
}


