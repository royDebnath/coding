package com.java.codinground.leetcode.categories.two_pointers;

import java.util.*;

/**
 * 15. 3Sum
 * Medium
 *
 * Given an integer array nums, return all the triplets
 * [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and
 * nums[i] + nums[j] + nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 *
 * Example 1:
 *
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Explanation:
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
 * The distinct triplets are [-1,0,1] and [-1,-1,2].
 * Notice that the order of the output and the order of the triplets does not matter.
 * Example 2:
 *
 * Input: nums = [0,1,1]
 * Output: []
 * Explanation: The only possible triplet does not sum up to 0.
 * Example 3:
 *
 * Input: nums = [0,0,0]
 * Output: [[0,0,0]]
 * Explanation: The only possible triplet sums up to 0.
 *
 *
 * Solution :
 *
 * Sort the array.
 * We will follow two pointer approach after sorting.
 * loop through array with pointer i : the current number is nums[i]
 * left number is mums[i+1]
 * right number is nums[length-1]
 *
 * if sum of these three > than target, move the right pointer to left (Array sorted so sum will decrease)
 * else if sum < target, move left pointer to right one step (sorted array)
 * else sum==target, add combination to result. move to next number and repeat same thing
 *
 * the loop runs till length-2, because we need current, current+1 and last element for the last iteration.
 *
 */
public class Q15_3Sum {
    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-1,0,1,2,-1,-4}));
    }
    public static List<List<Integer>> threeSum(int[] nums) {
        int target = 0;
        Set<List<Integer>> result = new HashSet<>();
        if (nums.length == 0) {
            return new ArrayList<>(result);
        }
        Arrays.sort(nums);
        for (int current = 0; current < nums.length - 2; current++) {
            int left = current + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[current] + nums[left] + nums[right];
                if (sum > target) {
                    right--;
                } else if (sum < target) {
                    left++;
                } else { // sum = target
                    result.add(Arrays.asList(nums[current], nums[left], nums[right]));
                    left++;
                    right--;
                }
            }
        }
        return new ArrayList<>(result);
    }
}
