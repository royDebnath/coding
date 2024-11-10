package com.roydebnath.coding.leetcode.blind75.graph;

import java.util.*;

/**
 * There is a new alien language which uses the latin alphabet.
 * However, the order among letters are unknown to you.
 * You receive a list of non-empty words from the dictionary,
 * where words are sorted lexicographically by the rules of this new language.
 * Derive the order of letters in this language.
 *
 * Example 1:
 *
 * Input：["wrt","wrf","er","ett","rftt"]
 * Output："wertf"
 * Explanation：
 * from "wrt"and"wrf" ,we can get 't'<'f'
 * from "wrt"and"er" ,we can get 'w'<'e'
 * from "er"and"ett" ,we can get 'r'<'t'
 * from "ett"and"rftt" ,we can get 'e'<'r'
 * So return "wertf"
 * Example 2:
 *
 * Input：["z","x"]
 * Output："zx"
 * Explanation：
 * from "z" and "x"，we can get 'z' < 'x'
 * So return "zx"
 *
 * Solution :
 *
 * Approach: Here, if we consider ["wrt", “wrf”, ….]
 * the first two of the words of the alien dictionary then
 * by looking at the first mismatch in the characters tells us vital information on the order they occur!
 *
 *
 *
 * That means, in from the above two words,
 * we can say ‘t’ comes before ‘f’! We can denote this relation by, ‘t’ → ‘f’.
 *
 * We can represent this relation using a directed graph!
 *
 * Hence, Iterate over all the words and make the graph representing the above relation.
 * All the distinct characters will be the vertices of the graph whereas,
 * the relation, mapping which character comes before another character will be the directed edge.
 * Do a Topological Sort over the built graph and return one of the possible orders for the same.
 *
 * Time Complexity
 * O(N + K), Where N is the total number of words and K is the total number of unique characters
 * present in the list of N words.
 *
 *
 *
 * The time complexity of Topological Sort is O(V + E)
 * where V is the number of vertices and E is the number of edges, which is O(K + N).
 *
 * Space Complexity
 * O(K), where K represents the total number of unique characters.
 *
 *
 *
 * Since we are storing K distinct characters
 *
 *
 */
public class Q61_AlienDictionary {
    public static void main(String[] args) {
        String[] input1 = {"wrt", "wrf", "er", "ett", "rftt"};
        System.out.println(alienOrder(input1));
    }

    public static String alienOrder(String[] words) {
        Map<Character, Set<Character>> adj = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();
        String result = "";
        if (words == null || words.length == 0) {
            return result;
        }
        // Degree char = 0
        for (String s : words) {
            for (char c : s.toCharArray()) {
                indegree.put(c, 0);
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            String curr = words[i];
            String next = words[i + 1];
            int min = Math.min(curr.length(), next.length());
            for (int j = 0; j < min; j++) {
                char c1 = curr.charAt(j);
                char c2 = next.charAt(j);
                if (c1 != c2) {
                    Set<Character> set = adj.getOrDefault(c1, new HashSet<>());
                    if (!set.contains(c2)) {
                        set.add(c2);
                        adj.put(c1, set);
                        indegree.put(c2, indegree.get(c2) + 1); // update c2, c1 < c2
                    }
                    break;
                }
            }
        }

        LinkedList<Character> q = new LinkedList<>();
        for (char c : indegree.keySet()) {
            if (indegree.get(c) == 0) {
                q.add(c);
            }
        }

        while (!q.isEmpty()) {
            char c = q.poll();
            result += c;
            if (adj.containsKey(c)) {
                for (char next : adj.get(c)) {
                    indegree.put(next, indegree.get(next) - 1);
                    if (indegree.get(next) == 0) {
                        q.offer(next);
                    }
                }
            }
        }

        return result.length() == indegree.size() ? result : "";
    }
}

class Graph {

    ArrayList<Integer>[] neighbours;
    int numVertices;

    public Graph(int vertexCount) {
        numVertices = vertexCount;
        neighbours = new ArrayList[vertexCount];
        for (int vertexIndex = 0; vertexIndex < vertexCount; vertexIndex++) {
            neighbours[vertexIndex] = new ArrayList<>();
        }
    }

    public void addEdge(int src, int dest) {
        neighbours[src].add(dest);
    }

    public char[] topologicalSort() {
        boolean[] visited = new boolean[numVertices];
        Stack<Integer> completedVertices = new Stack<>();

        // Getting the topological sort.
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i]) {
                dfs(i, visited, completedVertices);
            }
        }

        // Storing the topological sort in a character array.
        char[] arr = new char[completedVertices.size()];
        int curr = 0;

        while (!completedVertices.isEmpty()) {
            arr[curr++] = ((char) ('a' + completedVertices.pop()));
        }

        return arr;
    }

    private void dfs(int curr, boolean[] visited, Stack<Integer> completedVertices) {
        // Mark the current node as visited.
        visited[curr] = true;

        // Make a recursive call to all the neighbours.
        for (int neighnour : neighbours[curr]) {
            if (!visited[neighnour]) {
                dfs(neighnour, visited, completedVertices);
            }
        }
        // All neighbours completed.
        completedVertices.push(curr);
    }
}