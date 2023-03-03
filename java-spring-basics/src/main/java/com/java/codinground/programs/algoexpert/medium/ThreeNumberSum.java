package com.java.codinground.programs.algoexpert.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Write a function that takes in a non-empty array of distinct integers and
 * an integer representing a target sum. The function should find
 * all triplets in the array that sum up to the target sum and
 * return a two-dimensional array of all these triplets.
 * The numbers in each triplet should be ordered in ascending order, and
 * the triplets themselves should be ordered in ascending order with respect to the numbers they hold.
 * If no three numbers sum up to the target sum, the function should return an empty array.
 * Sample Input
 * array
 *  = [12, 3, 1, 2, -6, 5, -8, 6]
 * targetSum
 *  = 0
 * Sample Output
 * [[-8, 2, 6], [-8, 3, 5], [-6, 1, 5]]
 */
public class ThreeNumberSum {
    public static void main(String[] args) {

        int[] input = {12, 3, 1, 2, -6, 5, -8, 6};
        List<Integer[]> output = threeNumberSum(input, 0);
        for (Integer[] triplets : output) {
            System.out.print(triplets[0]+", ");
            System.out.print(triplets[1]+", ");
            System.out.println(triplets[2]);
            System.out.println("===================");
        }
    }

    private static List<Integer[]> threeNumberSum(int[] input, int target) {
        List<Integer[]> result = new ArrayList<>();
        int length = input.length;
        for (int i = 0; i < length; i++) {
            int first = input[i];
            int currentTarget = target- first;
            Set<Integer> existing = new HashSet<>();
            for (int j = i+1; j < length; j++) {
                int second = input[j];
                if (existing.contains(currentTarget- second)){
                    int third = currentTarget-second;
                    result.add(new Integer[]{first,second,third});
                }
                else {
                    existing.add(second);
                }
            }
        }
        return result;
    }
}
