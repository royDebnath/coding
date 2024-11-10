package com.roydebnath.coding.leetcode.all.binary_search;

/**
 *
 * 81. Search in Rotated Sorted Array II
 * Medium
 * 7.9K
 * 979
 * Companies
 * There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).
 *
 * Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].
 *
 * Given the array nums after the rotation and an integer target, return true if target is in nums, or false if it is not in nums.
 *
 * You must decrease the overall operation steps as much as possible.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,5,6,0,0,1,2], target = 0
 * Output: true
 * Example 2:
 *
 * Input: nums = [2,5,6,0,0,1,2], target = 3
 * Output: false
 *
 *Approach
 * To tackle this challenge, we employ a binary search strategy, taking into account the rotation and potential duplicates. The primary goal is to identify the sorted part of the array in each step and narrow down our search based on the target.
 *
 * Binary Search with Rotation Handling
 * Our approach doesn't involve searching for the rotation point. Instead, we modify the binary search to work directly with the rotated array:
 *
 * We calculate the middle index, mid, of our current search interval.
 * If the value at mid is our target, we've found it.
 * If the value at low is the same as the value at mid, we might be dealing with duplicates. In this case, we increment low to skip potential duplicates.
 * If the left part of our interval (from low to mid) is sorted (i.e., nums[low] <= nums[mid]), we check if our target lies within this sorted interval. If it does, we search in the left half; otherwise, we search in the right half.
 * If the left part isn't sorted, then the right part must be. We apply a similar logic to decide which half to search in next.
 * Code Breakdown
 * Initialize pointers low and high at the start and end of nums, respectively.
 * While low is less than or equal to high, compute mid.
 * Check if nums[mid] is the target. If yes, return True.
 * If nums[low] is equal to nums[mid], increment low.
 * If the left half is sorted, check if target lies within it. If yes, update high; otherwise, update low.
 * If the right half is sorted, use a similar logic to update low or high.
 * If the loop completes without finding the target, return False.
 * Rationale
 * The beauty of this solution lies in its adaptability. While binary search is a straightforward algorithm on sorted arrays, this problem added the twist of a rotated array and duplicates. By understanding the structure of the rotated array and handling duplicates wisely, we can still achieve efficient search performance.
 *
 * Complexity
 * Time Complexity: O(log⁡n)O(\log n)O(logn) for the best case (unique elements). However, in the worst-case scenario (many duplicates), the complexity can degrade to O(n)O(n)O(n).
 *
 * Space Complexity: O(1)O(1)O(1) as we only use a constant amount of space.
 *
 * */
public class Q81_SearchInRotatedSortedArrayII {
    public boolean search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {

            int midIndex = left + (right - left) / 2;
            int midElement = nums[midIndex];

            if (midElement == target) {
                return true;
            }

            if (midElement == nums[left]) {
                left++;
                continue;
            }

            if (midElement == nums[right]) {
                right--;
                continue;
            }

            if (midElement > nums[left]) { //left side is sorted
                if (target >= nums[left] && target <= midElement) {
                    right = midIndex - 1;
                } else {
                    left = midIndex + 1;
                }
            } else { // right side is sorted,
                if (target >= midElement && target <= nums[right]) {
                    left = midIndex + 1;
                } else {
                    right = midIndex - 1;
                }
            }
        }
        return false;
    }
}
