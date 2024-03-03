package com.java.codinground.programs.leetcode.top;

/**
 * There is an integer array nums sorted in ascending order (with distinct values).
 * <p>
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 * <p>
 * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
 * <p>
 * You must write an algorithm with O(log n) runtime complexity.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * Example 2:
 * <p>
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 */
public class Q33_SearchRotatedSortedArray {
    public static void main(String[] args) {
        int[] input = {6, 7, 0, 1, 2, 3,4,5};
        System.out.println(pivotedBinarySearch(input, 0));
    }

    static int pivotedBinarySearch(int array[], int key) {
        int length = array.length;
        int left = 0, right = length - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (key == array[middle]) {
                return middle;
            }
            else if (array[left] <= array[middle]) { // if left-to-middle part is sorted
                if (key < array[middle] && key >= array[left]){
                    right = middle - 1;
                }
                else left = middle + 1;
            } else { // pivot/lowest is in between left and middle -- not sorted
                if (key > array[middle] && key <= array[right]){
                    left = middle + 1;
                }
                else right = middle - 1;
            }
        }
        return -1;
    }

    /** Only for reference*/
    static int binarySearch(int[] array, int key) {
        int length = array.length;
        int left = 0, right = length - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (key == array[middle]) {
                return middle;
            } else if (key < array[middle]) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return -1;
    }
}
