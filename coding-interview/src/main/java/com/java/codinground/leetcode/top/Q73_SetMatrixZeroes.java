package com.java.codinground.leetcode.top;

/**
 * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
 *
 * You must do it in place.
 *
 * Input: [[1,1,1],
 *         [1,0,1],
 *         [1,1,1]]
 * Output: [[1,0,1],
 *          [0,0,0],
 *          [1,0,1]]
 *
 * Solution Steps
 *
 * 1. Define two variables firstRow and firstColumn to store the status of the first row and first column.
 *    Set them to false.
 * 2. Use these row and column as hash that stores the status of that row and column.
 * 3. Iterate over the matrix and for every A[i][j] == 0, set A[i][0] = 0 and col[0][j] = 0.
 * 4. Update the values of the matrix except the first row and the first column to 0
 *    if A[i][0] = true or A[0][i] = true for A[i][j].
 * 5. At last, update the values of the first row and the first column.
 */
public class Q73_SetMatrixZeroes {
    public static void main(String args[]) {
        //defining an array
        int[][] matrix = new int[][]{{1, 6, 3, 0},
                                     {1, 8, 9, 3},
                                     {6, 2, 0, 7}};
        //finds the length of the matrix
        int n = matrix.length;
        int m = matrix[0].length;
        //function calling
        setZeros(matrix);
    }

    public static void setZeros(int[][] matrix) {
        System.out.println("Print matrix before setZero");
        printMatrix(matrix);
//initially set first row and column to false i.e. 1
        boolean firstRowZero = false;
        boolean firstColumnZero = false;
//set first row and column zero or not
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
//if the above condition returns true, sets the first column to true i.e. 0
                firstColumnZero = true;
                break;
            }
        } //ends of for loop
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
//if the above condition returns true, sets the first rowto true i.e. 0
                firstRowZero = true;
                break;
            }
        }//ends of for loop
//loop iterates over row and column
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
//if the above condition returns true, mark zeros on first row and column
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
//use mark to set elements
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
//if true, sets an elements to 0
                    matrix[i][j] = 0;
                }
            }
        }
//set first column and row
        if (firstColumnZero) {
            for (int i = 0; i < matrix.length; i++)
                matrix[i][0] = 0;
        }
        if (firstRowZero) {
            for (int i = 0; i < matrix[0].length; i++)
                matrix[0][i] = 0;
        }
        System.out.println("Print matrix before setZero");
        printMatrix(matrix);
    }

    private static void printMatrix(int[][] matrix) {
        //loop for printing rows
        for (int i = 0; i < matrix.length; i++) {
//loop for printing columns
            for (int j = 0; j < matrix[i].length; j++) {
//prints matrix elements
                System.out.print(matrix[i][j] + "\t");
                if (j == matrix[i].length - 1)
//throws cursor to the next line
                    System.out.println();
            }
        }
    }
}
