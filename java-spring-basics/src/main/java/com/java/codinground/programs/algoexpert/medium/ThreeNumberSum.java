package com.java.codinground.programs.algoexpert.medium;

import java.util.*;

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
            System.out.print(triplets[0] + ", ");
            System.out.print(triplets[1] + ", ");
            System.out.println(triplets[2]);
            System.out.println("===================");
        }
    }

    private static List<Integer[]> threeNumberSum(int[] input, int target) {
        List<Integer[]> result = new ArrayList<>();
        int length = input.length;
        for (int i = 0; i < length; i++) {
            int first = input[i];
            int currentTarget = target - first;
            Set<Integer> existing = new HashSet<>();
            for (int j = i + 1; j < length; j++) {
                int second = input[j];
                if (existing.contains(currentTarget - second)) {
                    int third = currentTarget - second;
                    result.add(new Integer[]{first, second, third});
                } else {
                    existing.add(second);
                }
            }
        }
        return result;
    }

    /**
     *
     Sort the input array
     Initialize a set to store the unique triplets and an output vector to store the final result
     Iterate through the array with a variable i, starting from index 0.
     Initialize two pointers, j and k, with j starting at i+1 and k starting at the end of the array.
     In the while loop, check if the sum of nums[i], nums[j], and nums[k] is equal to 0. If it is, insert the triplet into the set and increment j and decrement k to move the pointers.
     If the sum is less than 0, increment j. If the sum is greater than 0, decrement k.
     After the while loop, iterate through the set and add each triplet to the output vector.
     Return the output vector
     *
     */
    private static List<List<Integer>> threeNumberSumWithSorting(int[] nums, int target) {
        Arrays.sort(nums);
        Set<List<Integer>> s = new HashSet<>();
        List<List<Integer>> output = new ArrayList<>();
        for (int i = 0; i < nums.length; i++){
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) {
                    s.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                } else if (sum < target) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        output.addAll(s);
        return output;
    }

}
