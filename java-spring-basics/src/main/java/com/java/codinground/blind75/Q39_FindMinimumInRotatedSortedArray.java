package com.java.codinground.blind75;

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

class Q39_FindMinimumInRotatedSortedArray {

    // Driver Program
    public static void main(String[] args) {
        int nums[] = {5, 6, 1, 2, 3, 4};
        int N = nums.length;
        findMin(nums);
        System.out.println("The minimum element is " + findMin(nums));
    }

    private static int findMin(int[] nums) {
        return findMinBinarySearch(nums, 0, nums.length - 1);
    }

    static int findMinBinarySearch(int nums[], int left, int right) {
        // This condition is needed to handle the case when
        // array is not rotated at all
        if (right < left)
            return nums[0];

        // If there is only one element left
        if (right == left)
            return nums[left];

        // Find mid
        int mid = left + (right - left) / 2; /*(left + right)/2;*/

        // Check if element (mid+1) is minimum element.
        // Consider the cases like {3, 4, 5, 1, 2}
        if (mid < right && nums[mid] > nums[mid + 1]) // have to check mid<right first as [mid+1] can go out of index bound
            return nums[mid + 1];

        // Check if mid itself is minimum element
        if (mid > left && nums[mid] < nums[mid - 1]) // have to check mid>left first as [mid-1] can go out of index bound
            return nums[mid];

        // Decide whether we need to go to left half or
        // right half
        if (nums[mid] > nums[right]){ // array is decreasing on the right side after mid, so min will be on right side
            return findMinBinarySearch(nums, mid + 1, right);
        }else {
            return findMinBinarySearch(nums, left, mid - 1); // min will be on left
        }
    }
}

