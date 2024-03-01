package com.java.codinground.programs.leetcode.top100;

/**
 * Given an array of positive integers nums and a positive integer target,
 * return the minimal length of a contiguous subarray [numsl, numsl+1, ..., numsr-1, numsr] of which
 * the sum is greater than or equal to target. If there is no such subarray, return 0 instead.
 * <p>
 * Example 1:
 * <p>
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 * Example 2:
 * <p>
 * Input: target = 4, nums = [1,4,4]
 * Output: 1
 * Example 3:
 * <p>
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output: 0
 */
public class MinimumSizeSubarraySum {
    public static void main(String[] args) {
        int[] input = {2, 3, 1, 2, 4, 3};
        System.out.println(minimumLengthSubarray(input, 7));

    }

    private static int minimumLengthSubarray(int[] input, int target) {
        int minLength = Integer.MAX_VALUE;
        int left = 0;
        int targetSum = 0;
        for (int i = 0; i < input.length; i++) {
            targetSum += input[i];
            while (targetSum >= target) {
                minLength = Math.min(minLength, i + 1 - left);
                targetSum = targetSum - input[left];
                left++;
            }
        }
        return minLength != Integer.MAX_VALUE ? minLength : 0;
    }
}
