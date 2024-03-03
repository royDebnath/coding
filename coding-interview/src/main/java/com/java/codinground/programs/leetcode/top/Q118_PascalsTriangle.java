package com.java.codinground.programs.leetcode.top;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem Description
 * Given an integer numRows, the task is to return the first numRows of Pascal's triangle. In Pascal's triangle, each number is the sum of the two numbers directly above it except for the boundaries, which are always 1. The triangle starts with a 1 at the top. Then, each subsequent row contains one more number than the previous row, and these numbers are positioned such that they form a triangle. The challenge here is to compute these numbers using the given property of Pascal's triangle and to represent the triangle in the form of a list of lists, where each inner list corresponds to a row in the triangle.
 *
 * For example, the first 3 rows of Pascal's triangle look like this:
 *
 * 1               //First row (only one element)
 * 1 1             //Second row (two 1s at the boundaries)
 * 1 2 1           //Third row (1s at the boundaries, middle is sum of the two numbers above)
 * Intuition
 * The solution to generating Pascal's triangle is a direct application of its properties. The key idea is to start with the first row [1], and then generate each following row based on the previous row. For any new row, aside from the first and last elements which are 1, each element is obtained by adding the two numbers directly above it in the triangle. With this approach, we can iteratively construct each row and add it to the final result.
 *
 *
 */
public class Q118_PascalsTriangle {
    public List<List<Integer>> generate(int numRows) {
        // Create an array list to store the output result...
        List<List<Integer>> output = new ArrayList<List<Integer>>();
        // Base cases...
        if (numRows <= 0)  return output;
        // Create an array list to store the prev triangle value for further addition...
        ArrayList<Integer> prev = new ArrayList<Integer>();
        // Inserting for the first row & store the prev array to the output array...
        prev.add(1);
        output.add(prev);
        // For rest of the rows, Traverse all elements through a for loop...
        for (int i = 2; i <= numRows; i++) {
            // Create another array to store the current triangle value...
            ArrayList<Integer> curr = new ArrayList<Integer>();
            curr.add(1);    //first
            // Calculate for each of the next values...
            for (int j = 0; j < prev.size() - 1; j++) {
                // Inserting the addition of the prev arry two values...
                curr.add(prev.get(j) + prev.get(j + 1));    //middle
            }
            // Store the number 1...
            curr.add(1);    //last
            // Store the value in the Output array...
            output.add(curr);
            // Set prev is equal to curr...
            prev = curr;
        }
        return output;      // Return the output list of pascal values...
    }
}
