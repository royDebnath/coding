package com.java.codinground.leetcode.top;

public class Q704_BinarySearch {
    public int search(int[] nums, int target) {
        // Initialize the starting index of the search range.
        int left = 0;
        // Initialize the ending index of the search range.
        int right = nums.length - 1;

        // Continue searching while the range has more than one element.
        while (left < right) {
            // Calculate the middle index of the current range.
            int mid = left + (right - left) / 2;

            // If the middle element is greater than or equal to the target,
            // narrow the search range to the left half (including the middle element).
            if (nums[mid] >= target) {
                right = mid;
            } else {
                // If the middle element is less than the target,
                // narrow the search range to the right half (excluding the middle element).
                left = mid + 1;
            }
        }

        // At this point, left is the index where the target may be if it exists.
        // Check if the element at the 'left' index is the target.
        // If it is, return the index. Otherwise, return -1 indicating the target is not found.
        return nums[left] == target ? left : -1;
    }
}
