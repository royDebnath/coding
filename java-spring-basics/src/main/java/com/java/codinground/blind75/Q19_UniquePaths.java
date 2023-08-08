package com.java.codinground.blind75;

import java.util.HashMap;
import java.util.Map;

/**
 * There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]).
 * The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
 * The robot can only move either down or right at any point in time.
 *
 * Given the two integers m and n, return the number of possible unique paths that the robot can take
 * to reach the bottom-right corner.
 *
 * The test cases are generated so that the answer will be less than or equal to 2 * 109.
 *
 *
 *
 * Example 1:
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Down -> Down
 * 2. Down -> Down -> Right
 * 3. Down -> Right -> Down
 */
public class Q19_UniquePaths {
    public static void main(String[] args) {
        System.out.println(uniquePaths(3, 7));
    }

    public static int uniquePaths(int m, int n) {
        Map<String, Integer> memo = new HashMap<>();
        return uniquePaths(m, n, memo);
    }

    public static int uniquePaths(int m, int n, Map<String, Integer> memo) {
        // base case
        if (m == 1 || n == 1) return 1;

        // check if we have already calculated unique paths for cell(m, n)
        String cell = m + "," + n;
        // if yes, then get its value from our memoization table
        if (memo.containsKey(cell))
            return memo.get(cell);

        // else, explore the down move
        int downMove = uniquePaths(m - 1, n);
        // explore the right move
        int rightMove = uniquePaths(m, n - 1);

        // put the value obtained for unique paths from cell(m, n)
        memo.put(cell, downMove + rightMove);

        return downMove + rightMove;
    }
}