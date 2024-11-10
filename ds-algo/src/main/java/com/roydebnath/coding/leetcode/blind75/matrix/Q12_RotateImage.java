package com.roydebnath.coding.leetcode.blind75.matrix;

import java.util.Arrays;

/**
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 *
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
 * DO NOT allocate another 2D matrix and do the rotation.
 *
 * Input: [[1,2,3],
 *         [4,5,6],
 *         [7,8,9]]
 *
 * Output: [[7,4,1],
 *          [8,5,2],
 *          [9,6,3]]
 *
 * Input: [[5,1,9,11],
 *         [2,4,8,10],
 *         [13,3,6,7],
 *         [15,14,12,16]]
 *
 * Output: [[15,13,2,5],
 *          [14,3,4,1],
 *          [12,6,8,9],
 *          [16,7,10,11]]
 *
 *
 */
public class Q12_RotateImage {
    public static void main(String[] args) {
        int[][] input = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        rotate(input);
    }
    public static void rotate(int[][] matrix) {
        /**
         * Input Format
         * {
         * {1,2,3},
         * {4,5,6},
         * {7,8,9}
         * }
         */
        /**
         * Output Format
         * {
         * {7,4,1},
         * {8,5,2},
         * {9,6,3}
         * }
         */
        int length = matrix.length;
        System.out.println("Input Array" + "\n");
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("\n");
        /**Step 1 : Make the rows into columns and vice versa by swapping matrix[i][j] with matrix [j][i]
         * {1,2,3},
         * {4,5,6},
         * {7,8,9}
         *
         * will become
         *
         * {1,4,7},
         * {2,5,8},
         * {3,6,9}
         *
         * */
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) { // initiate j with i not 0, else swap will happen twice making it the same
                swap2d(matrix, i, j);
            }
        }
        System.out.println("Intermediate Array" + "\n");
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("\n");
        /**
         * Now reverse each row to get the final result:
         * {1,4,7},
         * {2,5,8},
         * {3,6,9}
         *
         * will become
         *
         * {7,4,1},
         * {8,5,2},
         * {9,6,3}
         */
        for (int[] row : matrix) {
            int row_length = row.length;
            for (int i = 0; i < row_length / 2; i++) {
                swap(row, i, length - i - 1);
            }
        }
        /** Print the final Output**/
        System.out.println("Output Array" + "\n");
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static void swap2d(int[][] matrix, int i, int j) {
        if (i == j) {
            return;
        }
        int temp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = temp;
    }

    private static void swap(int[] nums, int source, int target) {
        int temp = nums[source];
        nums[source] = nums[target];
        nums[target] = temp;
    }
}