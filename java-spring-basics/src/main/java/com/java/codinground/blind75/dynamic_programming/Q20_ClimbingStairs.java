package com.java.codinground.blind75.dynamic_programming;

import java.util.HashMap;
import java.util.Map;

/**
 * You are climbing a staircase. It takes n steps to reach the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * Example 2:
 *
 * Input: n = 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 *
 * Solution :
 *
 * we can observe that when we are on the nth stair,
 * we have two options:
 *
 *--- either we climbed one stair from the (n-1)th stair or
 *--- we climbed two stairs from the (n-2)th stair.
 *
 * By leveraging this observation, we can break down the problem into smaller subproblems
 * and apply the concept of the Fibonacci series.
 * The base cases are when we are on the 1st stair (only one way to reach it)
 * and the 2nd stair (two ways to reach it).
 * By summing up the number of ways to reach the (n-1)th and (n-2)th stairs,
 * we can compute the total number of ways to climb the stairs
 */
public class Q20_ClimbingStairs {
    public static void main(String[] args) {
        System.out.println(climbStairs(3));
    }
    public static int climbStairs(int n) {
        Map<Integer, Integer> memo = new HashMap<>();
        memo.put(1, 1);
        memo.put(2, 2);
        return climbStairsRecursion(n, memo);
    }


    /**
     *  The recursive solution uses the concept of Fibonacci numbers to solve the problem.
     *  It calculates the number of ways to climb the stairs by recursively calling the climbStairs
     *  function for (n-1) and (n-2) steps.
     *
     *
     */
    private static int climbStairsRecursion(int n, Map<Integer, Integer> memo) {
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        memo.put(n, climbStairsRecursion(n - 1, memo) + climbStairsRecursion(n - 2, memo));
        return memo.get(n);
    }

    /**
     *
     *The tabulation solution eliminates recursion and uses a bottom-up approach to solve
     *the problem iteratively. It creates a DP table (dp) of size n+1 to store the number
     *of ways to reach each step. The base cases (0 and 1 steps) are initialized to 1 since
     *there is only one way to reach them. Then, it iterates from 2 to n, filling in the DP
     *table by summing up the values for the previous two steps. Finally, it returns the value
     *in the last cell of the DP table, which represents the total number of ways to reach the top.
     *
     */

     public int climbStairsTabulation(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        int[] dp = new int[n+1];
        dp[0] = dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    /**
     *The space-optimized solution further reduces the space complexity by using only two variables
     * (prev and curr) instead of an entire DP table. It initializes prev and curr to 1 since
     * there is only one way to reach the base cases (0 and 1 steps). Then, in each iteration,
     * it updates prev and curr by shifting their values.
     * curr becomes the sum of the previous two values, and prev stores the previous value of curr.
     */

    public int climbStairsSpaceOptimized(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        int prev = 1, curr = 1;
        for (int i = 2; i <= n; i++) {
            int temp = curr;
            curr = prev + curr;
            prev = temp;
        }
        return curr;
    }
}
