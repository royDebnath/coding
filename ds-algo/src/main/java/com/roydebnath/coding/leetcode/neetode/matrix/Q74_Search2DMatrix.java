package com.roydebnath.coding.leetcode.neetode.matrix;

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
public class Q74_Search2DMatrix {
    public static void main(String[] args) {
        int[][] matrix = {{1, 5, 9},
                {14, 20, 21},
                {30, 34, 43}};
        System.out.println(searchMatrix(matrix, 1));
    }

    // Method to search for a target value in a matrix.
    public static boolean searchMatrix(int[][] matrix, int target) {
        // Get the number of rows and columns from the matrix.
        int rows = matrix.length, cols = matrix[0].length;

        // Initialize the left and right pointers for the binary search.
        int left = 0, right = rows * cols - 1;

        // Loop until the search space is reduced to one element.
        while (left <= right) {
            // Calculate the middle point of the current search space.
            int mid = (left + right) / 2;

            // Map the middle index to a 2D position in the matrix.
            int midRow = mid / cols;
            int midCol = mid % cols;

            int midElement = matrix[midRow][midCol];
            // Compare the middle element with the target.
            if (target < midElement) {
                right = mid - 1;
            } else if (target > midElement) {
                left = mid + 1;
            } else {
                System.out.println("Element found -- rowIndex : " + midRow + " colIndex : " + midCol);
                return true;
            }
        }
        // After exiting the loop, left should point to the target element, if it exists.
        // Check if the element at the 'left' position equals the target.
        return false;
    }
}
