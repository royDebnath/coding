package com.java.codinground.programs.algoexpert.medium;

/**
 * Write a function that takes in an array of integers and
 * returns the length of the longest peak in the array.
 * A peak is defined as adjacent integers in the array that are
 * strictly increasing until they reach a tip (the highest value in the peak),
 * at which point they become strictly decreasing.
 * At least three integers are required to form a peak.
 * For example, the integers 1, 4, 10, 2 form a peak, but
 * the integers 4, 0, 10 don't and neither do the integers 1, 2, 2, 0.
 * Similarly, the integers 1, 2, 3 don't form a peak because
 * there aren't any strictly decreasing integers after the 3.
 * Sample Input
 * array = [1, 2, 3, 3, 4, 0, 10, 6, 5, -1, -3, 2, 3]
 * Sample Output
 * 6 // 0, 10, 6, 5, -1, -3
 */
public class LongestPeak {
    public static void main(String[] args) {
        int[] input = {1, 2, 3, 3, 4, 0, 10, 6, 5, -1, -3, 2, 3};
        System.out.println(longestPeakLength(input));
    }

    private static int longestPeakLength(int[] input) {
        int result = 0;
        int length = input.length;
        int i = 0;
        while (i < length) {
            int increment = 0;
            while (i + 1 < length && input[i + 1] > input[i]) {
                increment++;
                i++;
            }
            if (increment == 0) {
                i++;
                continue;
            }

            int decrement = 0;
            while (i + 1 < length && input[i + 1] < input[i]) {
                decrement++;
                i++;
            }
            if (decrement!=0){
                System.out.println("Current Peak Index : " + (i-decrement));
                System.out.println("Start index : " + (i-decrement-increment));
                System.out.println("End index : " + (i));
            }
            if (decrement == 0) {
                continue;
            }

            result = Math.max(result, increment + decrement + 1);
        }
        return result;
    }
}
