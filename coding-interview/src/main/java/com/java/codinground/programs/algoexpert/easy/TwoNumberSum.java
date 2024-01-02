package com.java.codinground.programs.algoexpert.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of integers, no number in this array is repeated,
 * and an integer representing the target sum,
 * implement a function that find whether there's a pair of numbers in the array
 * that adds up to the target sum.
 * Return the pair in an array.
 * If such pair does not exist, return an empty array.
 */
public class TwoNumberSum {

    public static void main(String[] args) {
        int[] input = {1,4,3,11,0,2,-1};
        int target = 10;
        int[] output = twoNumberSum(input, target);

        for (int i : output) {
            System.out.println(i);
        }

    }

    private static int[] twoNumberSum(int[] inputArray, int target){
        Set<Integer> check = new HashSet<>();
        for (int currentNumber : inputArray) {
            int difference = target - currentNumber;
            if (check.contains(difference)){
                return new int[]{currentNumber, difference};
            }
            else {
                check.add(currentNumber);
            }
        }
        return new int[0];
    }
}
