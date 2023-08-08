package com.java.codinground.blind75;

/**
 * 33. Search in Rotated Sorted Array
 * Medium
 *
 * There is an integer array nums sorted in ascending order (with distinct values).
 * Prior to being passed to your function, nums is possibly rotated at an
 * unknown pivot index k (1 <= k < nums.length) such that the resulting array is
 * [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
 *
 * For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 * Given the array nums after the possible rotation and an integer target,
 * return the index of target if it is in nums, or -1 if it is not in nums.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 * *
 * Example 1:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * Example 2:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 * Example 3:
 *
 * Input: nums = [1], target = 0
 * Output: -1
 *
 * Solution :
 *
 * The approach is simple if we are able to find the index from where the given array is rotated.
 * We can follow below steps to solve this problem —
 *
 * 1. Find the index where the array is rotated. Notice if a sorted array is rotated then the rightmost element will not be the biggest element in the array.
 * 2. Using the information in step #1, we can perform binary search to find the index where the array is rotated.
 * 3. Once we have found that index, then we can easily determine in which half (array will be divided into two halves by the pivot index) of the array the target lies.
 * 4. Notice, the two halves are themselves will be sorted (this is pretty intuitive, right 😄?).
 * 5. We can then perform binary search once again in the determined half to find the index of the target element.
 */
public class Q10_SearchInRotatedSortedArray {

    public static void main(String[] args) {
        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 6));
    }

    public static int search(int[] nums, int target) {
        // Special case
        if (nums == null || nums.length == 0) {
            return -1;
        }
        // Left and right pointers in the array
        int left = 0;
        int right = nums.length - 1;
        // First step is to find the pivot where the
        // array is rotated
        while (left < right) {
            // Middle pointer
            int middle = left + (right - left) / 2;
            // If the element at the mid is greater than
            // the element at the right then we can say that
            // the array is rotated after middle index
            if (nums[middle] > nums[right]) {
                left = middle + 1;
            }
            // Else, the pivot is in the left part
            else {
                right = middle;
            }
        }
        // After the above loop is completed, then all left,right,middle will point to the same index
        // which is the pivot. middle is not available outside the loop so can assign either left/right to pivot.
        int pivot = left;

        //search in one of the half
        left = 0;
        right = nums.length - 1;
        // Now we will find in which half of the array,
        // our target is present
        if (target >= nums[pivot] && target <= nums[right]) {
            left = pivot;
        } else {
            right = pivot;
        }

        // Now perform normal binary search
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] == target) {
                return middle;
            } else if (target < nums[middle]) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return -1;
    }
}
