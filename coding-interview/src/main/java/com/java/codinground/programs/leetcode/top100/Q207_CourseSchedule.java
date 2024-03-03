package com.java.codinground.programs.leetcode.top100;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Problem Description
 * The problem gives us numCourses total number of courses, each labeled uniquely from 0 to numCourses - 1. We are also given an array of course pairs for prerequisites, where each pair [a, b] signifies that course a requires course b as a prerequisite. The goal is to determine if it is possible to complete all the courses fulfilling their respective prerequisites.
 *
 * To simplify: imagine you have a list of courses, each with dependencies on other courses. You can only take a course if all of its prerequisite courses have been completed. You need to figure out if you can schedule these courses in such a way that all can be finished.
 *
 * Intuition
 * The intuition behind the solution is based on detecting a possible cyclic dependency among the courses which would make it impossible to complete all of them. If there's a cycle, you would be in a situation where to take course X you need to have completed course Y, but course Y similarly requires course X. This deadlock prevents completion of the courses.
 *
 * To solve this, we use topoogical sorting based on Kahn's algorithm, which is a strategy for sorting nodes in a directed graph. This approach works well here because prerequisites create a directed graph where courses are nodes and dependencies are edges.
 *
 * Breaking down the intuition step by step:
 *
 * We construct a directed graph where each node is a course, and edges represent prerequisites (a directed edge from course b to course a means b is a prerequisite for a).
 * We count incoming edges (indegrees) for each node. If a node/course has no incoming edges, it means there are no prerequisites, and it can be taken immediately.
 * We repeatedly pick and remove nodes with no incoming edges (representing courses with all prerequisites completed or no prerequisites at all) and remove all edges from these nodes to other nodes (simulating taking the course and fulfilling those prerequisites).
 * If we manage to remove all nodes (take all courses), then it's possible to finish all courses.
 * In more technical terms:
 *
 * We are using a graph (specifically a directed graph) to represent the dependencies between courses.
 * A defaultdict is used to keep track of what courses depend on a given course.
 * An array indeg maintains the count of prerequisites (incoming edges) for each course.
 * A queue (deque) helps in processing the courses with zero incoming edges (prerequisites).
 * We traverse the graph starting from nodes with zero indegrees, repeatedly reducing the indegrees of the dependent nodes and adding them to the queue when their indegre becomes zero.
 * If we process (cnt) the same number of courses as are given by numCourses, we return true; else, a cycle must exist, and we return false.
 * Solution Approach
 * The solution approach is genuinely inspired by the topological sorting algorithm. It uses several data structures and algorithms to implement the logic described in the Intuition section.
 *
 * Here are the main points of the implementation, outlined step by step:
 *
 * Graph Representation:
 *
 * A defaultdict for adjacency list representation of our directed graph: g = defaultdict(list).
 * Each course is a node, and each element in the list represents the adjacent nodes. For example, if course b is a prerequisite for course a, then a will be in the adjacency list of b.
 * In-Degree Array:
 *
 * An array indeg that represents the in-degree of each node (number of prerequisites for each course): indeg = [0] * numCourses.
 * Whenever a prerequisite relation (edge) is added, we increment the in-degree count for course a.
 * Building the Graph and In-Degree Array:
 *
 * We iterate through the prerequisites list, for each pair [a, b] we:
 * Add a to the adjacency list of b: g[b].append(a)
 * Increase the in-degree of a: indeg[a] += 1
 * Processing Nodes with Zero In-Degrees:
 *
 * A queue q using deque is initialized with all courses that have zero in-degree.
 * cnt variable is initialized to count the number of courses that we've been able to schedule (process).
 * Topological Sort:
 *
 * While our queue q is not empty, we process the nodes (courses) inside our queue:
 * i = q.popleft(): we get a course i from the queue.
 * cnt += 1: increment our counter as we are able to take course i.
 * For each adjacent course j in our graph (g[i]):
 * Decrease the in-degree of j: indeg[j] -= 1
 * If the in-degree of j becomes zero, it means all prerequisites of j are satisfied, and we can add j to our queue: q.append(j)
 * Return Value:
 *
 * After the whole process, if cnt matches numCourses, it means we were able to find a way to take all courses, and we return true.
 * If cnt does not match numCourses, it implies there are courses with prerequisites that form a cycle so we could not process them, and we return false.
 * This algorithm efficiently determines whether all courses can be taken by making sure all nodes are processed, ensuring that there are no directed cycles in our graph which would indicate an impossible situation with mutual prerequisites.
 *
 * Example Walkthrough
 * Let's use a small example to illustrate the solution approach.
 *
 * Suppose we have numCourses = 4 and prerequisites = [[1,0],[2,0],[3,1],[3,2]]. This means we have 4 courses labeled 0 to 3. The prerequisites are that course 1 requires course 0, course 2 requires course 0, and course 3 requires both courses 1 and 2.
 *
 * Following the steps of the solution approach:
 *
 * Graph Representation:
 *
 * Initialize the graph g and have courses 0 to 3 without any edges.
 * It will look like this: g = {0:[], 1:[], 2:[], 3:[]} before adding prerequisites.
 * In-Degree Array:
 *
 * Initialize the in-degree array indeg to have all zeros since we haven't accounted for any prerequisites yet: indeg = [0, 0, 0, 0].
 * Building the Graph and In-Degree Array:
 *
 * Add prerequisites to the graph. After iterating through the prerequisites, g will look like this:
 * g = {0:[1,2], 1:[3], 2:[3], 3:[]}
 * The in-degree array indeg will be [0, 1, 1, 2] indicating the number of courses each course is waiting on.
 * Processing Nodes with Zero In-Degrees:
 *
 * Initialize the queue q with courses that have an in-degree of 0: q = deque([0]).
 * The cnt variable is set to 0.
 * Topological Sort:
 *
 * Process the queue:
 * Pop course 0. We can take the course 0 because it has no prerequisites.
 * Increment cnt to 1.
 * Reduce the in-degree of courses dependent on course 0 (1 and 2), so indeg becomes [0, 0, 0, 2].
 * Since courses 1 and 2 now have an in-degree of 0, add them to q: q = deque([1,2]).
 * Continue processing:
 * Pop course 1. Increment cnt to 2.
 * Reduce the in-degree of course 3 (dependent on course 1), now indeg is [0, 0, 0, 1].
 * Do not add anything to queue as no new courses have an in-degree of 0.
 * Pop course 2. Increment cnt to 3.
 * Again, reduce the in-degree of course 3, indeg now becomes [0, 0, 0, 0].
 * Add course 3 to q since it now has an in-degree of 0: q = deque([3]).
 * Finally:
 * Pop course 3. Increment cnt to 4.
 * Return Value:
 *
 * Now, cnt is 4 which is equal to numCourses indicating we have been able to find an order to take all courses without getting stuck in a cycle.
 * Return true.
 * Thus, we can confirm that it is indeed possible to complete all the courses fulfilling their respective prerequisites with this particular example.
 *
 *
 */
public class Q207_CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Create a graph represented by an adjacency list where each course points to its prerequisites
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // Create an array to store the in-degree (number of prerequisites) for each course
        int[] inDegrees = new int[numCourses];

        // Populate the graph and update the in-degrees based on the prerequisites
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int prerequisiteCourse = prerequisite[1];
            graph.get(prerequisiteCourse).add(course);
            inDegrees[course]++; // Increment the in-degree of the course
        }

        // Queue to hold courses with in-degree of 0 (no prerequisites)
        Deque<Integer> queue = new ArrayDeque<>();

        // Enqueue all courses which have no prerequisites
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }

        // Counter for number of courses that have been processed
        int processedCourses = 0;

        // Process the courses in the queue
        while (!queue.isEmpty()) {
            int course = queue.poll();
            processedCourses++; // Increment count of processed courses

            // Iterate over the neighbors of the current course
            for (int neighbor : graph.get(course)) {
                // Decrement the in-degree of each neighbor, since we have processed one of their prerequisites
                inDegrees[neighbor]--;
                if (inDegrees[neighbor] == 0) {
                    // If in-degree becomes 0, it means all prerequisites are met, so enqueue the course
                    queue.offer(neighbor);
                }
            }
        }

        // If the number of processed courses equals the total number of courses, all can be finished
        return processedCourses == numCourses;
    }
}
