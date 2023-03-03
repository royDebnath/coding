package com.java.codinground.programs.leetcode;

/**
 * Given an m x n 2D binary grid grid which represents a map of
 * '1's (land) and '0's (water), return the number of islands.
 * <p>
 * An island is surrounded by water and is formed by connecting
 * adjacent lands horizontally or vertically. You may assume
 * all four edges of the grid are all surrounded by water.
 * <p>
 * Example 1:
 * <p>
 * Input: grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * Output: 1
 * Example 2:
 * <p>
 * Input: grid = [
 * ["1","1","0","0","0"],
 * ["1","1","0","0","0"],
 * ["0","0","1","0","0"],
 * ["0","0","0","1","1"]
 * ]
 * Output: 3
 */
public class NumberOfIslands {
    public static void main(String[] args) {
        String[][] grid = {
                {"1", "1", "0", "0", "0"},
                {"1", "1", "0", "0", "0"},
                {"0", "0", "1", "0", "0"},
                {"0", "0", "0", "1", "1"}
        };
        System.out.println(numOfIslands(grid));
    }

    private static int numOfIslands(String[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].equals("1")) {
                    count += 1;
                    callDfs(grid, i, j);
                }
            }
        }
        return 0;
    }

    private static void callDfs(String[][] grid, int i, int j) {
        if (i < 0 || i > grid.length || j < 0 || j > grid[i].length || grid[i][j].equals("0")) {
            return;
        }
        grid[i][j] = "0";
        callDfs(grid, i - 1, j); //up
        callDfs(grid, i + 1, j); //down
        callDfs(grid, i, j - 1); //left
        callDfs(grid, i, j + 1); //right
    }
}
