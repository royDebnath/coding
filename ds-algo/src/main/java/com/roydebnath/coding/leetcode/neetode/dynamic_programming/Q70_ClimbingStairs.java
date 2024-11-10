package com.roydebnath.coding.leetcode.neetode.dynamic_programming;

/**
 * Problem Description
 * The problem presents us with a scenario where we are required to climb a staircase that consists of
 * n individual steps. The objective is to determine the total number of distinct ways to reach the
 * top of the staircase.
 *
 * To solve this problem, we need to think about it in terms of finding the total combinations of
 * 1-step and 2-step moves that would lead us to the top.
 *
 * Intuition
 * To understand the solution's intuition, we can notice that the way to reach a particular step
 * is a combination of the ways to reach the previous two steps
 * (since we can take a step from either of them).
 * This is a classic example of a dynamic programming problem that exhibits the property of
 * overlapping subproblems.
 *
 * If we look at the smaller subproblems, we can see that:
 *
 * To reach the first step, there is exactly 1 way (climb 1 step).
 * To reach the second step, there are 2 ways (climb 1 step + 1 step or climb 2 steps directly).
 * For any higher step i, the ways to reach it is the sum of the ways to reach step (i-1) and step (i-2).
 * This is because we can arrive at step i by taking a single step from (i-1) or a double step from (i-2).
 *
 */
public class Q70_ClimbingStairs {
    // This method calculates the number of distinct ways to climb to the top.
    public static int climbStairs(int n) {
        // base cases
        if(n <= 0) return 0;
        if(n == 1) return 1;
        if(n == 2) return 2;


        /**
         * base case for two steps
         * ways_to_first_step = 1;
         * ways_to_second_step = 2;
         */




        //Going to start the loop from step 3
        int ways_to_previous_step = 2; // ways to second step
        int ways_to_second_previous = 1; // ways to first step
        int ways_to_nth_step = 0;

        for(int i=3; i<=n; i++){ // the loop will terminate at n-1, giving ways to next step which is n
            ways_to_nth_step = ways_to_previous_step + ways_to_second_previous;

            //prepare variables for next iteration
            ways_to_second_previous = ways_to_previous_step;
            ways_to_previous_step = ways_to_nth_step;
        }
        return ways_to_nth_step;
    }

    /**Reference for easy understanding*/
    public static int climbStairsDpArray(int n) {
        if (n==1) return 1;
        // dp[i] := the number of ways to climb to the i-th stair
        int[] dp = new int[n + 1];

        //base cases
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public static void main(String[] args) {
        //System.out.println(climbStairs(5));
        System.out.println(climbStairsDpArray(1));
    }
}
