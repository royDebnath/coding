package com.roydebnath.coding.leetcode.neetode.top_k_elements;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 703. Kth Largest Element in a Stream
 * Easy
 * 5K
 * 3.1K
 * Companies
 * Design a class to find the kth largest element in a stream.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Implement KthLargest class:
 *
 * KthLargest(int k, int[] nums) Initializes the object with the integer k and the stream of integers nums.
 * int add(int val) Appends the integer val to the stream and returns the element representing the kth largest element in the stream.
 *
 *
 * Example 1:
 *
 * Input
 * ["KthLargest", "add", "add", "add", "add", "add"]
 * [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
 * Output
 * [null, 4, 5, 5, 8, 8]
 *
 * Explanation
 * KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
 * kthLargest.add(3);   // return 4
 * kthLargest.add(5);   // return 5
 * kthLargest.add(10);  // return 5
 * kthLargest.add(9);   // return 8
 * kthLargest.add(4);   // return 8
 *
 */
public class Q703_KthLargestElementStream {

    public static void main(String args[]) {
        int[] nums = { 3, 6, 9, 10 };
        System.out.println("Initial stream: " + Arrays.toString(nums));
        KthLargest KLargest = new KthLargest(3, nums);
    }
}

class KthLargest {
    PriorityQueue<Integer> pq;
    int k=0;
    public KthLargest(int k, int[] nums) {
        this.k = k;
        pq = new PriorityQueue<>();
        for(int i: nums) {
            this.add(i); // call class's own function, instead of repeating yourself
        }
    }

    public int add(int val) {
        pq.add(val);
        if(pq.size()>k) { // remove elements ONLY when heap size is too big
            pq.remove();
        }
        return pq.peek();
    }
}