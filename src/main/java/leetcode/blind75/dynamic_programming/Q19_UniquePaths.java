package leetcode.blind75.dynamic_programming;

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
 * 000
 * 000
 * 000
 *
 *
 * Example 1:
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Down -> Down
 * 2. Down -> Down -> Right
 * 3. Down -> Right -> Down
 *
 *
 *Solution :
 *
 * This question solved by Dynamic Programming.
 *
 * Find the base case:
 *
 * There is only one way to reach the left most edge and there is only one way to reach the bottom most edge.
 * So we init the first row and first column of 2D list with 1.
 *
 * 1,1,1,1,1,1,1
 * 1,x,x,x,x,x,x
 * 1,x,x,x,x,x,x
 * 1,x,x,x,x,x,x
 *
 * Find the pattern:
 *
 * The robot can only move either down or right. For each target cell that the robot want to reach,
 * it is either from left cell of the tagret or from up cell of the target.
 * Therefor we need sum up target’s left cell and up cell to get the total path to reach the target cell.
 *
 * 1,1,1,1, 1 , 1 , 1
 * 1,2,3,4, 5 , 6 , 7
 * 1,3,6,10,15,21,28
 * 1,x,x,x,x,x,x
 *
 * Answer:
 *
 * When reach to the bottom-right corner, then number in the bottom-right corner will be the answer.
 *
 *
 *
 */
public class Q19_UniquePaths {
    public static void main(String[] args) {
        System.out.println(uniquePaths(3, 3));
    }

    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n ; j++){
                if(i == 0 || j == 0) // populating the first row/column
                    dp[i][j] = 1;
                else
                    dp[i][j] = dp[i][j-1] + dp[i-1][j]; // adding the top and left
            }
        }
        return dp[m-1][n-1];
    }
}