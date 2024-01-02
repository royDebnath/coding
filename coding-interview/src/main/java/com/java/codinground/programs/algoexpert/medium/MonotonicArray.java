package com.java.codinground.programs.algoexpert.medium;

/**
 * Write a function that takes in an array of integers and
 * returns a boolean representing whether the array is monotonic.
 * An array is said to be monotonic if its elements, from left to right,
 * are entirely non-increasing or entirely non-decreasing.
 * Non-increasing elements aren't necessarily exclusively decreasing; they simply don't increase. Similarly, non-decreasing elements aren't necessarily exclusively increasing; they simply don't decrease.
 * Note that empty arrays and arrays of one element are monotonic.
 * Sample Input
 * array = [-1, -5, -10, -1100, -1100, -1101, -1102, -9001]
 * Sample Output
 * true
 */
public class MonotonicArray {
    public static void main(String[] args) {
        int[] input = {-1, -5, -10, -1100, -1100, -1101, -1102, -9001};
        System.out.println("Monotonic : " + isMonotonic(input));
    }

    private static boolean isMonotonic(int[] input) {
        boolean isMonotonic=true;
        boolean increasing;
        if (input[1]>input[0]){
            increasing = true;
        }
        else {
            increasing =false;
        }
        for (int i = 1; i < input.length-1; i++) {
            if (increasing && input[i] > input[i + 1]) {
                isMonotonic = false;
                break;
            }
            if (increasing == false && input[i] < input[i + 1]) {
                isMonotonic = false;
                break;
            }
        }
        return isMonotonic;
    }
}
