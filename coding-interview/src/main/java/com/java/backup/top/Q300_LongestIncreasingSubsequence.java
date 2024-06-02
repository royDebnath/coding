package com.java.backup.top;

import java.util.Arrays;

/**
 * Given an integer array nums, return the length of the longest strictly increasing
 * subsequence
 * .
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Example 2:
 *
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 * Example 3:
 *
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 *
 * Intuition
 * To find the length of the longest strictly increasing subsequence,
 * we can use dynamic programming to keep track of the length of the increasing subsequence
 * ending at each index.
 * Approach
 * We can initialize an array dp of the same length as the input array nums,
 * where dp[i] represents the length of the longest increasing subsequence ending at index i.
 * We can iterate through the array and update dp based on the elements before the current index.
 * The final result will be the maximum value in the dp array.
 * Complexity
 * Time complexity:
 * O(n2)O(n^2)O(n
 * 2
 *  ) - We have a nested loop where the outer loop runs for each element,
 *  and the inner loop runs for each element before the current index.
 * Space complexity:
 * O(n)O(n)O(n) - We use an additional array dp of the same length as the input array.
 *
 *
 */
public class Q300_LongestIncreasingSubsequence {
    public class Solution {
        public int lengthOfLIS(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            int[] dp = new int[n];
            Arrays.fill(dp, 1);

            for (int i = 1; i < n; ++i) {
                for (int j = 0; j < i; ++j) {
                    if (nums[i] > nums[j]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
            }

            int maxLength = Arrays.stream(dp).max().orElse(0);
            return maxLength;
        }
    }
}
