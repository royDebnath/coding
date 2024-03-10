package com.java.codinground.programs.leetcode.top;

import java.util.*;

/**

 /**
 * 207. Course Schedule
 * Medium
 *
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates
 * that you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 *
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you should also have
 * finished course 1. So it is impossible.
 *
 * Solution :
 *
 * The code aims to solve the problem of determining whether it is possible to finish all the given courses
 * without any cyclic dependencies. It uses the topological sort algorithm, specifically Kahn's algorithm.
 *
 * Initialization:
 * Create an empty adjacency list to represent the directed graph. Each node in the graph
 * represents a course, and the edges represent the prerequisites.
 *
 * Create an array called indegree of size n (number of courses) and initialize all its elements to 0.
 * The indegree array will keep track of the number of incoming edges to each course.
 *
 * Create an empty ans list to store the topological order of the courses.
 *
 *
 * 2.Building the Graph:
 *
 * Iterate over the prerequisites list, which contains pairs of courses indicating the prerequisites.
 * For each pair [a, b], add an edge in the adjacency list from b to a.
 * This indicates that course b must be completed before course a.
 * Increment the indegree of course a by 1, as it has one more prerequisite.
 *
 * Performing Topological Sort using Kahn's Algorithm:
 *
 * Create an empty queue called q to store the nodes to visit.
 *
 * Iterate over all the courses (0 to n-1) and enqueue the courses with an indegree of 0 into the queue.
 * These courses have no prerequisites and can be started immediately.
 *
 * While the queue is not empty, do the following:
 *
 * Dequeue the front element from the queue and store it in a variable t.
 * Add t to the ans list to keep track of the topological order.
 * For each neighbor x of t in the adjacency list:
 * Decrement the indegree of x by 1 since we are removing the prerequisite t.
 * If the indegree of x becomes 0, enqueue x into the queue.
 * This means that all the prerequisites of course x have been completed.
 *
 * Checking the Result:
 *
 * After the topological sort is complete, check if the size of the ans list is equal
 * to the total number of courses (n).
 * If they are equal, it means that all the courses can be finished without any cyclic dependencies.
 * Return true.
 * If the sizes are different, it implies that there is a cycle in the graph,
 * and it is not possible to complete all the courses. Return false.
 *
 * Intuition:
 * The intuition behind this approach is that if there is a cycle in the graph,
 * there will be at least one node that cannot be visited
 * since it will always have a nonzero indegree. On the other hand, if there are no cycles,
 * all the nodes can be visited by starting from the nodes with no incoming edges and removing their
 * outgoing edges one by one.
 * If all the nodes are visited in the end, it means that it is possible to finish all the courses.
 */
public class Q207_CourseSchedule {

    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {{1,0},{0,1}};
        System.out.println(canFinish(numCourses, prerequisites));
    }
    public static boolean canFinish(int n, int[][] prerequisites) {
        List<Integer>[] adj = new List[n];
        int[] indegree = new int[n];
        List<Integer> ans = new ArrayList<>();

        /**
         * populate the adjacency list.
         * Array's nth location  is the prereq course i.e n is the prereq. and the contents of the list
         * in the nth location of the array are the courses for that prereq.
         */
        for (int[] pair : prerequisites) {
            int course = pair[0];
            int prerequisite = pair[1];
            if (adj[prerequisite] == null) {
                adj[prerequisite] = new ArrayList<>();
            }
            adj[prerequisite].add(course);
            indegree[course]++; //if course has prereq indegree increases
        }
        Queue<Integer> queue = new LinkedList<>();
        //build the queue with indegree=0 elements
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) { // have to start with courses with indegree 0, i.e courses with no prereq.
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();
            ans.add(current); // queue only contains courses with no prereq so they are added to the answer.

            if (adj[current] != null) {
                for (int next : adj[current]) { // for each course of the adjList of the prereq with zero indegeree(current) reduce the indegree by 1 or remove the edge
                    indegree[next]--;
                    if (indegree[next] == 0) { // course indegree zero means it can be done without prereq, so add it to the queue
                        queue.offer(next);
                    }
                }
            }
        }



        return ans.size() == n;
    }
}
