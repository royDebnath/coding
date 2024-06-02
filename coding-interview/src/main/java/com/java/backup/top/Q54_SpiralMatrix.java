package com.java.backup.top;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a function that takes in an n x m two-dimensional array
 * (that can be square-shaped when n == m) and
 * returns a one-dimensional array of all the array's elements in spiral order.
 * <p>
 * Spiral order starts at the top left corner of the two-dimensional array,
 * goes to the right, and proceeds in a spiral pattern all the way until
 * every element has been visited.
 * <p>
 * Sample Input
 * array = [
 * [1,   2,  3, 4],
 * [12, 13, 14, 5],
 * [11, 16, 15, 6],
 * [10,  9,  8, 7],
 * ]
 * Sample Output
 * [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]
 */
public class Q54_SpiralMatrix {
    public static void main(String[] args) {
        int[][] matrix =
                new int[][]{
                        {1, 2, 3, 4},
                        {12, 13, 14, 5},
                        {11, 16, 15, 6},
                        {10, 9, 8, 7},
                };
        spiralTraverse(matrix).forEach(System.out::println);
    }

    private static List<Integer> spiralTraverse(int[][] matrix) {

        List<Integer> result = new ArrayList<>();
        if (matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }

        int top = 0;
        int right = matrix[0].length - 1;
        int bottom = matrix.length - 1;
        int left = 0;

        while (true) {

            /** Print the top row, traverse from left to right*/
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]); // top, i
            }
            top++;
            if (top > bottom) break;

            /** Print the right row, traverse from top to bottom*/
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]); // i, right
            }
            right--;
            if (right < left) break;

            /** Print the bottom row, traverse from right to left*/
            for (int i = right; i >= left; i--) {
                result.add(matrix[bottom][i]); // bottom, i
            }
            bottom--;
            if (bottom < top) break;

            /** Print the left row, traverse from bottom to top*/
            for (int i = bottom; i >= top; i--) {
                result.add(matrix[i][left]); // i, left
            }
            left++;
            if (left > right) break;

        }
        return result;
    }
}
