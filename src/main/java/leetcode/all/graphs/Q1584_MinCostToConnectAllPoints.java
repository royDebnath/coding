package leetcode.all.graphs;

import java.util.Arrays;

/**
 * You are given an array points representing integer coordinates of some points on a 2D-plane,
 * where points[i] = [xi, yi].
 *
 * The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them:
 * |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.
 *
 * Return the minimum cost to make all points connected. All points are connected if there is exactly
 * one simple path between any two points.
 *
 *  The end result is that every point must be connected to all other points with exactly one simple path between any two points, meaning that there are no cycles and each pair of points is connected directly or indirectly.
 *
 *  The implementation of the solution follows a series of systematic steps which adhere to Prim's Algorithm for finding the Minimum Spanning Tree (MST) in a weighted graph. Here's a closer look at how the code achieves this:
 *
 * Creating the graph: We initialize a 2D list g (short for graph) with size n x n, where n is the number of points. Here, g[i][j] will store the Manhattan distance between the i-th and j-th point.
 *
 * Initialization: We introduce an array dist and set all initial distances to inf (infinity), implying that initially, all points are infinitely far away from the tree we're building. A vis (visited) array is also used to keep track of the points that have been added to the MST.
 *
 * Priming the first point: We set the distance of the starting point (arbitrarily chosen to be the first point in our list) to 0, because the cost of including the first point in our MST is nothing.
 *
 * Building the MST: We then enter a loop that will iterate n times, once for each point. Inside the loop, we implement the core logic of Prim's algorithm:
 *
 * Finding the next point to add: We search through all points to find the one that is not yet visited and has the smallest distance in dist. We can add this point to the MST at the least cost.
 * Adding the point to MST and updating the cost: Mark this point as visited and add the distance to the total cost (ans), which accumulates the cost of connecting to the MST.
 * Updating distances: Lastly, we iterate over all other points to recalculate the minimum distance needed to connect them to the MST, updating our dist array. If the Manhattan distance from the newly added point to any other point is less than the currently known distance, we update the distance accordingly.
 *
 * By the end of the loop, ans will contain the sum of the distances of all the edges in the MST, which is the minimum cost to connect all the points.
 *
 * Throughout the code, efficiency comes from the fact that we only need to consider each potential edge once when we add a new vertex to the MST, rather than needing to traverse the entire graph each time.
 *
 * Example Walkthrough
 *
 * Let's apply the aforementioned solution approach to a small example. Consider the array of points points = [[0,0], [2,2], [3,10], [5,2], [7,0]]. The goal is to connect these points so that the total cost, defined by the Manhattan distance, is minimized.
 *
 * Step 1: Creating the graph
 *
 * First, we create our graph g with the Manhattan distances between each pair of points:
 *
 * Distance between points [0,0] and [2,2] is |0-2| + |0-2| = 4.
 * Distance between points [0,0] and [3,10] is |0-3| + |0-10| = 13.
 * (Similarly, we calculate distances between all pairs of points).
 * Thus, the graph g will look like a matrix where g[i][j] represents the distance between i-th and j-th points.
 *
 * Step 2: Initialization
 *
 * We initialize the dist array to [inf, inf, inf, inf, inf] where each entry corresponds to a point, signifying the initial cost of connecting it is infinity.
 *
 * Step 3: Priming the first point
 *
 * We choose the first point [0,0] to start with and set dist[0] to 0.
 *
 * Step 4: Building the MST
 *
 * First iteration: We find the point with the smallest distance in dist that hasn't been visited. This is [0,0]. We mark it as visited.
 * We update dist based on the distances from [0,0] to other points, resulting in dist = [0, 4, 13, 7, 7].
 * Second iteration: The next unvisited point with the smallest distance is [2,2], with distance 4.
 * We add this distance to our total cost and mark [2,2] as visited.
 * Update dist to reflect the closest distances using [2,2]. New dist values will be dist = [0, 4, 9, 3, 5] (changed from [3,10] and [5,2]).
 * Third iteration: Now [5,2] has the smallest dist of 3.
 * We add 3 to the total cost, mark [5,2] as visited.
 * Update dist to dist = [0, 4, 9, 3, 5] (no changes this time since no other point offers a shorter distance).
 * Fourth iteration: Point [7,0] has the smallest dist of 5.
 * We add 5 to the total cost, mark [7,0] as visited.
 * Update dist to dist = [0, 4, 8, 3, 5] (changed from [3,10]).
 * Fifth iteration: The only remaining point is [3,10] with dist of 8.
 * We add 8 to our total cost and mark [3,10] as visited.
 * Total cost to connect all points is now 0 + 4 + 3 + 5 + 8 = 20.
 *
 * At each step, we chose the edge with the minimum cost to add a new point to our growing MST, and we ensured this by updating distances for the remaining points whenever a new point was added to the MST.
 *
 * By following these steps iteratively, and using updated distances to determine the next closest point, we were able to build a connection between all the points at a minimum cost using Prim's algorithm.
 *
 *
 *
 */
public class Q1584_MinCostToConnectAllPoints {

        public int minCostConnectPoints(int[][] points) {
            // Define a very large number for the initial distances
            final int INFINITY = 1 << 30;

            // Get the number of points
            int numberOfPoints = points.length;

            // Create a graph represented by an adjacency matrix
            int[][] graph = new int[numberOfPoints][numberOfPoints];

            // Calculate all the distances between each pair of points
            for (int i = 0; i < numberOfPoints; ++i) {
                int x1 = points[i][0], y1 = points[i][1];
                for (int j = i + 1; j < numberOfPoints; ++j) {
                    int x2 = points[j][0], y2 = points[j][1];
                    int distance = Math.abs(x1 - x2) + Math.abs(y1 - y2);
                    graph[i][j] = distance;
                    graph[j][i] = distance;
                }
            }

            // Create an array to store the minimum distance from the selected point to the MST
            int[] minDistances = new int[numberOfPoints];

            // Keep track of visited points
            boolean[] visited = new boolean[numberOfPoints];

            // Initialize distances with a very large number
            Arrays.fill(minDistances, INFINITY);

            // The distance of the first point from itself is zero
            minDistances[0] = 0;

            // Accumulate the minimum cost of connecting all points
            int totalCost = 0;

            // Build the Minimum Spanning Tree (MST) using Prim's algorithm
            for (int i = 0; i < numberOfPoints; ++i) {
                int nearestPoint = -1; // Index of the closest unvisited point

                // Find the unvisited point with the minimum distance to the MST
                for (int k = 0; k < numberOfPoints; ++k) {
                    if (!visited[k] && (nearestPoint == -1 || minDistances[k] < minDistances[nearestPoint])) {
                        nearestPoint = k;
                    }
                }

                // Include this nearest unvisited point to the MST
                visited[nearestPoint] = true;

                // Add its distance to the total cost
                totalCost += minDistances[nearestPoint];

                // Update the minDistances array with the new possible shorter distances
                for (int k = 0; k < numberOfPoints; ++k) {
                    if (!visited[k]) {
                        minDistances[k] = Math.min(minDistances[k], graph[nearestPoint][k]);
                    }
                }
            }

            // Return the total cost of connecting all the points
            return totalCost;
        }
}
