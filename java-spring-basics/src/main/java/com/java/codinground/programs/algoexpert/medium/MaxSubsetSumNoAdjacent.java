package com.java.codinground.programs.algoexpert.medium;

/**
 * Max Subset Sum No Adjacent / House Robber Problem
 * Write a function that takes in an array of positive integers and
 * returns the maximum sum of non-adjacent elements in the array.
 * If the input array is empty, the function should return 0.
 * Sample Input
 * array = [75, 105, 120, 75, 90, 135]
 * Sample Output
 * 330 // 75 + 120 + 135
 */
public class MaxSubsetSumNoAdjacent {
    public static void main(String[] args) {
        int[] input = {75, 105, 120, 75, 90, 135};
        System.out.println(maxSubsetSumNoAdjacent(input));
    }

    private static int maxSubsetSumNoAdjacent(int[] input) {
        int length = input.length;
        int[] maxSumArray = new int[length];
        maxSumArray[0] = input[0];
        maxSumArray[1] = Math.max(input[0], input[1]);

        for (int i = 2; i < input.length; i++) {
            maxSumArray[i] = Math.max(input[i]+maxSumArray[i-2], maxSumArray[i-1]);
        }
        return maxSumArray[length-1];
    }
}
