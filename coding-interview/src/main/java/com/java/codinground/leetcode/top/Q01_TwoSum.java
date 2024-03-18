package com.java.codinground.leetcode.top;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1. Two Sum
 * Easy
 *
 * Given an array of integers nums and an integer target,
 * return indices of the two numbers such that they add up to target.
 *
 * You may assume that each input would have exactly one solution,
 * and you may not use the same element twice.
 *
 * You can return the answer in any order.
 */
public class Q01_TwoSum {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2,7,11,15}, 9)));
    }
    public static int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];
        for (int i = 0; i < n; i++) {
            int firstNumber = nums[i];
            int secondNumber = target - firstNumber;
            if (map.containsKey(secondNumber)) {
                result[1] = i;
                result[0] = map.get(secondNumber);
                return result;
            }
            map.put(firstNumber, i);
        }
        return result;
    }
}
