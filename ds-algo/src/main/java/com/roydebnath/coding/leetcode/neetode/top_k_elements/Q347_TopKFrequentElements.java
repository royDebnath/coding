package com.roydebnath.coding.leetcode.neetode.top_k_elements;

import java.util.*;

/**
 * 347. Top K Frequent Elements
 * Medium
 * 15.9K
 * 565
 * Companies
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 *
 */
public class Q347_TopKFrequentElements {
        public int[] topKFrequent(int[] nums, int k) {

            Map<Integer, Integer> frequency = new HashMap<>();
            for (int num : nums) {
                frequency.put(num, frequency.getOrDefault(num, 0) + 1);
            }

            Queue<Integer> maxHeap = new PriorityQueue<>((a, b) -> frequency.get(b) - frequency.get(a));
            for (int key : frequency.keySet()) {
                maxHeap.add(key);
            }

            int[] ans = new int[k];
            for (int i = 0; i < ans.length; i++) {
                ans[i] = maxHeap.poll();
            }
            return ans;
        }
    }

