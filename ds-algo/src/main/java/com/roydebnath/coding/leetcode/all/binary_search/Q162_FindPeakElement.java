package com.roydebnath.coding.leetcode.all.binary_search;

/**
 * Problem Description
 * The problem presents an integer array called nums which is 0-indexed.
 * We are tasked with finding an index of a peak element. A peak element is defined as an element
 * that is strictly greater than its neighboring elements.
 * Here the term "strictly" means that the peak element must be greater than its neighbors,
 * not equal to them.
 *
 * So in our modified binary search, instead of looking for a specific value,
 * we look for any peak as follows:
 *
 * We take the middle element of the current interval and compare it with its right neighbor.
 *
 * If the middle element is greater than its right neighbor, we've found a descending slope,
 * and there must be a peak to the left.
 * Hence, we restrict our search to the left half of the current interval.
 *
 * If the middle element is less than its right neighbor, we've found an ascending slope,
 * and a peak exists to the right. We then do our next search on the right half.
 *
 * We continue this process of narrowing down our search interval by half
 * until we've isolated a peak element.
 * This binary search on a wave-like array ensures we find a peak in O(log n) time,
 * satisfying the problem's constraints.
 *
 *
 */
public class Q162_FindPeakElement {
    public int findPeakElement(int[] nums) {
        int left = 0; // Initialize the left boundary of the search space
        int right = nums.length - 1; // Initialize the right boundary of the search space

        // Continue the loop until the search space is reduced to one element
        while (left < right) {
            // Calculate the middle index of the current search space
            int mid = left + (right - left) / 2;

            // If the middle element is greater than its next element, then a peak must be to the left (including mid)
            if (nums[mid] > nums[mid + 1]) {
                // Narrow the search space to the left half
                right = mid;
            } else {
                // Otherwise, the peak exists in the right half (excluding mid)
                // Narrow the search space to the right half
                left = mid + 1;
            }
        }

        // When left == right, we have found the peak element's index, return it
        return left;
    }
}
