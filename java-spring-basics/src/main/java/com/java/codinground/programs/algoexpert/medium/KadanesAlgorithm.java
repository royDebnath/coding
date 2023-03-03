package com.java.codinground.programs.algoexpert.medium;

/**
 * Kadane's Algorithm
 * Write a function that takes in a non-empty array of integers and
 * returns the maximum sum that can be obtained by summing up
 * all of the integers in a non-empty subarray of the input array.
 * A subarray must only contain adjacent numbers (numbers next to each other in the input array).
 * Sample Input
 * array = [3, 5, -9, 1, 3, -2, 3, 4, 7, 2, -9, 6, 3, 1, -5, 4]
 * Sample Output
 * 19 // [1, 3, -2, 3, 4, 7, 2, -9, 6, 3, 1]
 */
public class KadanesAlgorithm {
    public static void main(String[] args) {
        int[] input = {3, 5, -9, 1, 3, -2, 3, 9, 7, 2, -9, 6, 3, 1, -5, 4};
        System.out.println(kadanesAlgorithm(input));
    }

    private static int kadanesAlgorithm(int[] input) {
        int maxEndingHere =0;
        int maxSoFar = 0;
        for (int i = 0; i < input.length; i++) {
            int currentValue = input[i];
            maxEndingHere = Math.max(currentValue, maxEndingHere+ currentValue);
            maxSoFar = Math.max(maxEndingHere, maxSoFar);
        }
        return maxSoFar;
    }

}
