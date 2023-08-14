package com.java.codinground.blind75.array;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 *
 * You must write an algorithm that runs in O(n) time.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 * Example 2:
 *
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 */
public class Q33_LongestConsecutiveSequence {
    public static void main(String[] args) {
        int[] inputs = {0,3,7,2,5,8,4,6,0,1};
        System.out.println(longestConsecutive(inputs));
    }
    public static int longestConsecutive(int[] nums)  {
        int max = 0;

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        for (int i = 0; i < nums.length; i++) {
            int count = 1;

            // look left
            int current = nums[i];
            while (set.contains(--current)) {
                count++;
                set.remove(current);
            }

            // look right
            current = nums[i];
            while (set.contains(++current)) {
                count++;
                set.remove(current);
            }

            max = Math.max(max, count);
        }

        return max;
    }
}