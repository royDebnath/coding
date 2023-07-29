package com.java.codinground.blind75;

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
    public int rob(int[] nums) {
        int n = nums.length;
        if( n==1 ){
            return nums[0];
        }else if(n == 0){
            return 0;
        }else if(n == 2){
            return Math.max(nums[0],nums[1]);
        }

        int dp[] = new int[n];
        dp[0] = nums[0];
        dp[1] = nums[1];
        dp[2] = nums[2] + nums[0];
        for(int i=3;i<n;i++){
            dp[i] = Math.max(dp[i-2],dp[i-3]) + nums[i];
        }

        return Math.max(dp[n-1],dp[n-2]);

    }
}
