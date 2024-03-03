package com.java.codinground.programs.leetcode.top;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem Description
 * The problem at hand involves finding the total number of contiguous subarrays within a given array of integers (nums) that add up to a specified integer (k). A subarray is defined as a continuous, non-empty sequence of elements taken from the array.
 *
 * To better understand the problem, let's consider an example:
 *
 * If our input nums is [1, 2, 1, 2, 1] and k is 3, we need to find all the subarrays where the elements sum to 3. In this case, there are several such subarrays: [1, 2], [2, 1], [1, 2], [1, 2, 1] - ([2, 1] after removing the last element), hence the output would be 4.
 *
 * Intuition
 * The solution approach involves using a cumulative sum and hashmap (Counter) to keep track of the number of ways a certain sum can occur up to the current point in the array. The cumulative sum (s) represents the total sum of all elements up to the current index.
 *
 * Example Walkthrough
 * Let's walk through a small example to illustrate the solution approach.
 *
 * Consider the input nums = [3, 4, -1, 2, 1] and k = 5. We want to find all contiguous subarrays summing to k, which is 5 in this case.
 *
 * Following the solution approach step by step:
 *
 * We initialize our Counter with {0: 1} to account for the base case where a subarray can start from the beginning of the array.
 *
 * Now, we iterate over the array nums. We'll keep track of our cumulative sum (s) and initialize ans to 0.
 *
 * Start with the first element: s = 3. No previous sum of s - k = -2 exists, so don't update ans. Then, update the counter to Counter = {0: 1, 3: 1}.
 * Move to the second element: s = 3 + 4 = 7. We've seen s - k = 2 before? No, so ans remains the same. Update the counter to Counter = {0: 1, 3: 1, 7: 1}.
 * Third element: s = 7 - 1 = 6. We check for s - k = 1. It does not exist. Update the counter to Counter = {0: 1, 3: 1, 7: 1, 6: 1}.
 * Fourth element: s = 6 + 2 = 8. Look for s - k = 3. We have seen 3 before, once. Increase ans by 1 as there is one subarray ending here that sums to 5. Update the counter to Counter = {0: 1, 3: 1, 7: 1, 6: 1, 8: 1}.
 * Fifth element: s = 8 + 1 = 9. Check for s - k = 4. We have not seen 4 before, so ans remains 1. Update the counter to Counter = {0: 1, 3: 1, 7: 1, 6: 1, 8: 1, 9: 1}.
 * After iterating through all elements, our ans value, which is 1, represents the total number of contiguous subarrays that sum up to 5. The subarray in this example is [4, -1, 2].
 *
 * By using a cumulative sum and a hashmap (Counter), we accurately and efficiently found all the contiguous subarrays that add up to a given sum with a single pass through the array. This method avoids the use of nested loops for a better time complexity of O(n).
 *
 *
 */
public class Q560_SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        // Map for storing the cumulative sum and its frequency.
        Map<Integer, Integer> sumFrequencyMap = new HashMap<>();

        // Initializing with zero sum having frequency one.
        sumFrequencyMap.put(0, 1);

        int totalCount = 0; // This will hold the number of subarrays that sum to k.
        int cumulativeSum = 0; // This holds the cumulative sum of elements.

        // Loop over all elements in the array.
        for (int num : nums) {
            // Add the current element to the cumulative sum.
            cumulativeSum += num;

            // If cumulativeSum - k exists in map, then there are some subarrays ending with num that sum to k.
            totalCount += sumFrequencyMap.getOrDefault(cumulativeSum - k, 0);

            // Increment the frequency of the current cumulative sum in the map.
            // If the cumulative sum isn't already in the map, DefaultValue (0) will be used first.
            sumFrequencyMap.put(cumulativeSum, sumFrequencyMap.getOrDefault(cumulativeSum, 0) + 1);
        }

        // Return the total count of subarrays that sum to k.
        return totalCount;
    }
}
