package com.java.codinground.blind75.dynamic_programming;

/**
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed, the only constraint stopping you from
 * robbing each of them is that adjacent houses have security systems connected and it will
 * automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house,
 * return the maximum amount of money you can rob tonight without alerting the police.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * Example 2:
 *
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 *
 *
 * Solution :
 *
 * this is a modification of Fibonacci number problem.
 * The only variation I can see is I can’t pick a number which are next to each other.
 * However I can pick something prior to it. Let’s try to solve this problem with tabular variation
 * of dynamic programming.
 *
 *
 */
public class Q42_HouseRobber {
    public static void main(String[] args) {
        System.out.println(rob(new int[]{1,2,3,1}));
    }
    public static int rob(int[] nums) {
        // If we get invalid input, return 0
        if (nums == null || nums.length == 0) return 0;

        // Keep track of whether we robbed the previous house
        int robbedPrevious = 0;
        int didNotRobPrevious = 0;

        for (int i = 0; i < nums.length; i++) {

            // If we don't rob the current house,
            // take the max of robbing and not robbing the previous house
            int currentNotRobbed = Math.max(robbedPrevious, didNotRobPrevious);

            // If we rob the current house,
            // add the current money robbed to what we got from not robbing previous
            int currentIsRobbed = didNotRobPrevious + nums[i];

            // Update our values for the next iteration
            didNotRobPrevious = currentNotRobbed;
            robbedPrevious = currentIsRobbed;
        }

        // Return the maximum we could have robbed provided we looked at both options
        return Math.max(robbedPrevious, didNotRobPrevious);
    }
}
