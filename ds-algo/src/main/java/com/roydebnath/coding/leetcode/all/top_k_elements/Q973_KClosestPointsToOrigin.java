package com.roydebnath.coding.leetcode.all.top_k_elements;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 973. K Closest Points to Origin
 * Medium
 * 7.9K
 * 274
 * Companies
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).
 *
 * The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
 * since, we are calculating distance from (0,0), formula will be √(x2 + y2)
 *
 * You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).
 *
 *
 *
 * Example 1:
 * Input: points = [[1,3],[-2,2]], k = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].
 *
 *
 * Example 2:
 *
 * Input: points = [[3,3],[5,-1],[-2,4]], k = 2
 * Output: [[3,3],[-2,4]]
 * Explanation: The answer [[-2,4],[3,3]] would also be accepted.
 *
 * Solution :
 *
 * storing these points in a max-heap that is sorted on the distance from the origin, we get points in a max-heap that is sorted on the distance from the origin, we get
 * �
 * (
 * 1
 * )
 * O(1)
 *  access to the point, among these
 * �
 * k
 *  points, that is farthest from the origin.
 *
 * Now, instead of comparing all
 * �
 * k
 *  points with the next point from the list, we simply compare the point in the max-heap that is farthest from the origin with the next point from the list. If the next point is closer to the origin, it wins inclusion in the max-heap and ejects the point it was compared with. If not, nothing changes.
 *
 * In this way, at every step of the scan through the list, the max-heap acts like a sieve, picking out the top
 * �
 * k
 *  points in terms of their distance from the origin.
 *
 * And as we’ll see, the time complexity is much better than
 * �
 * (
 * �
 * .
 * �
 * )
 * O(n.k)
 * .
 *
 * We’ll will compare the distances of the two points relative to the origin. The point closer to the origin will be considered less than the other point. We’ll iterate through the given list of points, and if we find one that is closer to the origin than the point at the root of the max-heap, we do the following two things:
 *
 * Pop from the max-heap—that is, remove the point in the heap farthest from the origin.
 * Push the point that is closer to the origin onto the max-heap.
 * As we move through the given list of points, this will ensure that we always have the
 * �
 * k
 *  points in our heap that are the closest to the origin.
 *
 */
public class Q973_KClosestPointsToOrigin {
    public int[][] kClosest(int[][] points, int k) {
        Queue<int[]> maxHeap = new PriorityQueue<>(k, (p1, p2) -> distanceFromOrigin(p2) - distanceFromOrigin(p1));
        // put first 'k' points in the max heap
        for (int i = 0; i < k; i++)
            maxHeap.add(points[i]);

        // go through the remaining points of the input array,
        // if a Location is closer to the origin than the top Location
        // of the max-heap, remove the top Location from heap and add the Location from the input array
        for (int i = k; i < points.length; i++) {
            if (distanceFromOrigin(points[i]) < distanceFromOrigin(maxHeap.peek())) {
                maxHeap.poll();
                maxHeap.add(points[i]);
            }
        }

        return new ArrayList<>(maxHeap).toArray(new int[k][2]);
    }

    private int distanceFromOrigin(int[] point) {
        return (point[0] * point[0]) + (point[1] * point[1]);
    }
}

/**
 * Solution summary
 * To recap, the solution to this problem can be divided into the following parts:
 *
 * Push the first
 * �
 * k
 *  points to the max-heap.
 *
 * Calculate and compare the distances of the points list with the distance of the top of the max-heap.
 *
 * If the point distance is smaller than the max-heap top’s distance, pop the top from the max heap and push the point.
 *
 * Time complexity
 * The algorithm’s time complexity is
 * �
 * (
 * �
 *
 * �
 * �
 * �
 * �
 * )
 * O(n logk)
 * , where
 * �
 * n
 *  is the total number of points and
 * �
 * k
 *  is the number of points closest to the origin. This is because we need to iterate over all the
 * �
 * n
 *  points and perform operations on a heap of size
 * �
 * k
 * , which takes
 * �
 * (
 * �
 *
 * �
 * �
 * �
 * �
 * )
 * O(n logk)
 *  time in the worst case.
 *
 * Space complexity
 * The memory complexity will be
 * �
 * (
 * �
 * )
 * O(k)
 *  because we need to store
 * �
 * k
 *  points in the heap.
 */
