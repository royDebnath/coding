package com.java.codinground.programs.leetcode;

/**
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix.
 * This matrix has the following properties:
 * <p>
 * 1. Integers in each row are sorted from left to right.
 * 2. The first integer of each row is greater than the last integer of the previous row.
 * <p>
 * Input: matrix =  [[1,3,5,7],
 * [10,11,16,20],
 * [23,30,34,60]] target = 3
 * Output: true
 */
public class BinarySearch2DMatrix {
    public static void main(String[] args) {
        int[][] matrix = {{1, 5, 9},
                {14, 20, 21},
                {30, 34, 43}};
        System.out.println(searchMatrix(matrix, 7));
    }

    private static boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        int left = 0;
        int right = row * col - 1;

        while (left <= right) {
            int midIndex = left + (right - left) / 2;
            int midElement = matrix[midIndex / col][midIndex % col];
            if (target == midElement) {
                return true;
            } else if (target > midElement) {
                left = midIndex + 1;
            } else if (target < midElement) {
                right = midIndex - 1;
            }
        }
        return false;
    }
}
