package com.java.codinground.leetcode.categories.binary_search;

/**
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
 * For example, the array nums = [0,1,2,4,5,6,7] might become:
 *
 * [4,5,6,7,0,1,2] if it was rotated 4 times.
 * [0,1,2,4,5,6,7] if it was rotated 7 times.
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array
 * [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 *
 * Given the sorted rotated array nums of unique elements, return the minimum element of this array.
 *
 * You must write an algorithm that runs in O(log n) time.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,4,5,1,2]
 * Output: 1
 * Explanation: The original array was [1,2,3,4,5] rotated 3 times.
 * Example 2:
 *
 * Input: nums = [4,5,6,7,0,1,2]
 * Output: 0
 * Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
 * Example 3:
 *
 * Input: nums = [11,13,15,17]
 * Output: 11
 * Explanation: The original array was [11,13,15,17] and it was rotated 4 times.
 */
// Java program to find minimum element in a sorted and
// rotated array


class Q153_FindMinimumInRotatedSortedArray {

    // Driver Program
    public static void main(String[] args) {
        int nums[] = {5, 6, 1, 2, 3, 4};
        int N = nums.length;
        findMin(nums);
        System.out.println("The minimum element is " + findMin(nums));
    }

    private static int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        //set left and right bounds
        int left = 0;
        int right = nums.length - 1;
        //DO NOT use left <= right because that would loop forever
        while (left < right) {
            // Middle pointer
            /**
             * the main idea for our checks is to converge the left and right bounds on the start
             * or the pivot, and never disqualify the index for a possible minimum value.
             */
            int middle = left + (right - left) / 2;


            if (nums[middle] > nums[right]) {
                left = middle + 1;
                /**
                 *  example:  [3,4,5,6,7,8,9,1,2]
                 *  in the first iteration, when we start with mid index = 4, right index = 9.
                 *  if nums[mid] > nums[right], we know that at some point to the right of mid,
                 *  the pivot must have occurred, which is why the values wrapped around
                 *  so that nums[right] is less then nums[mid]
                 *
                 *  we know that the number at mid is greater than at least
                 *  one number to the right, so we can use mid + 1 and
                 *  never consider mid again; we know there is at least
                 *  one value smaller than it on the right
                 */
            } else {
                right = middle;
                /**
                 * example: [8,9,1,2,3,4,5,6,7]
                 * in the first iteration, when we start with mid index = 4, right index = 9.
                 * if nums[mid] <= nums[right], we know the numbers continued increasing to
                 * the right of mid, so they never reached the pivot and wrapped around.
                 * therefore, we know the pivot must be at index <= mid.
                 *
                 * we know that nums[mid] <= nums[right].
                 * therefore, we know it is possible for the mid index to store a smaller
                 * value than at least one other index in the list (at right), so we do
                 * not discard it by doing right = mid - 1. it still might have the minimum value.
                 */
            }
        }
        return nums[left];
        /**
         * at this point, left and right converge to a single index (for minimum value) since
         * our if/else forces the bounds of left/right to shrink each iteration:
         *
         * when left bound increases, it does not disqualify a value
         * that could be smaller than something else (we know nums[mid] > nums[right],
         * so nums[right] wins and we ignore mid and everything to the left of mid).
         *
         * when right bound decreases, it also does not disqualify a
         * value that could be smaller than something else (we know nums[mid] <= nums[right],
         * so nums[mid] wins and we keep it for now).
         *
         * so we shrink the left/right bounds to one value,
         * without ever disqualifying a possible minimum
         */
    }
}

