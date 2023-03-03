package com.java.codinground.programs.algoexpert.medium;


import java.util.Arrays;

/**
 * Write a function that takes in two non-empty arrays of integers,
 * finds the pair of numbers (one from each array) whose absolute difference is closest to zero,
 * and returns an array containing these two numbers,
 * with the number from the first array in the first position.
 * Note that the absolute difference of two integers is the distance between them on the real number line.
 * For example, the absolute difference of -5 and 5 is 10, and the absolute difference of -5 and -4 is 1.
 * You can assume that there will only be one pair of numbers with the smallest difference.
 * Sample Input
 * arrayOne = [-1, 5, 10, 20, 28, 3]
 * arrayTwo = [26, 134, 135, 15, 17]
 * Sample Output
 * [28, 26]
 */
public class SmallestDifference {
    public static void main(String[] args) {

        int[] input1 = {-1, 5, 10, 20, 28, 3};
        int[] input2 = {26, 134, 135, 15, 17};
        int[] output = smallestDifference(input1, input2);
        Arrays.stream(output).forEach(System.out::println);
    }

    private static int[] smallestDifference(int[] input1, int[] input2) {
        int[] result = new int[2];
        Arrays.sort(input1);
        Arrays.sort(input2);
        int i =0,j=0;
        int smallestDiff = Integer.MAX_VALUE;

        while (i<input1.length && j < input2.length){
            int currentDiff = Math.abs(input1[i]-input2[j]);
            if (currentDiff<smallestDiff){
                smallestDiff =currentDiff;
                result[0]=input1[i];
                result[1]=input2[j];
            }
            if (input1[i]<input2[j]){
                i++;
            }
            else {
                j++;
            }
        }
        return result;
    }
}
