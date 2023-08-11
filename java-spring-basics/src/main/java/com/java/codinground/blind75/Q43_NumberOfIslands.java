package com.java.codinground.blind75;

/**
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water),
 * return the number of islands.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 *You may assume all four edges of the grid are all surrounded by water.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * Output: 1
 * Example 2:
 *
 * Input: grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * Output: 3
 */
public class Q43_NumberOfIslands {
    public static void main(String[] args) {
        char[][] grid = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };
        System.out.println(numIslands(grid));

    }

    public static int numIslands(char[][] grid) {
        int count = 0;

        int noOfRows = grid.length;
        int noOfColumns = grid[0].length;

        for (int row = 0; row < noOfRows; row++) {
            for (int column = 0; column < noOfColumns; column++) {
                if (grid[row][column] == '1') {
                    count++;
                    clearRestOfLand(grid, row, column);
                }
            }
        }
        return count;
    }

    private static void clearRestOfLand(char[][] grid, int row, int column) {
        int noOfRows = grid.length;
        int noOfColumns = grid[0].length;

        if (row < 0 || column < 0 || row >= noOfRows || column >= noOfColumns // check row,column variables are valid
                || grid[row][column] == '0') { // means we have reached water and return from this point
            return;
        }

        grid[row][column] = '0'; //mark the land as water so that its not counted again while finding new island

        clearRestOfLand(grid, row+1, column); // check below
        clearRestOfLand(grid, row-1, column); // check above
        clearRestOfLand(grid, row, column+1); // check right
        clearRestOfLand(grid, row, column-1); // check left
    }
}
