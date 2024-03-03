package com.java.codinground.programs.leetcode.top;

/**
 * Medium
 * Topics
 * Companies
 * Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
 * <p>
 * If target is not found in the array, return [-1, -1].
 * <p>
 * You must write an algorithm with O(log n) runtime complexity.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 * <p>
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 * Example 3:
 * <p>
 * Input: nums = [], target = 0
 * Output: [-1,-1]
 */
public class Q34_FindFirstAndLastPositionOfElementInSortedArray {
    public int[] searchRange(int[] nums, int target) {

        int[] result = new int[2];
        result[0] = findPosition(nums, target, "first");
        result[1] = findPosition(nums, target, "last");
        return result;

    }

    public int findPosition(int[] nums, int target, String position) {

        int result = -1;
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + ((high - low) / 2);

            if (nums[mid] < target) {
                low = mid + 1;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else { // nums[mid] == target
                result = mid;

                if ("first".equals(position)) {
                    high = mid - 1;
                    // because nothing after mid
                    // can be the first occurance of target.
                    //maybe mid is the first occurance , maybe not
                    //so let's narrow the target for [0...mid-1] and find out
                }
                if ("last".equals(position)) {
                    low = mid + 1;
                    // because nothing before mid
                    // can be the last occurance of target.
                    //maybe mid is the last occurance , maybe not
                    //so let's narrow the target for [mid+1...high] and find                   // out
                }
            }
        }
        return result;
    }
}