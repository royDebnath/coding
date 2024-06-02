package com.java.backup.top;

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
        System.out.println(search(input, 0));
    }

    /**
     * Another approach
     *
     *  * Solution :
     *  *
     *  * The approach is simple if we are able to find the index from where the given array is rotated.
     *  * We can follow below steps to solve this problem —
     *  *
     *  * 1. Find the index where the array is rotated. Notice if a sorted array is rotated then
     *  *    the rightmost element will not be the biggest element in the array.
     *  * 2. Using the information in step #1, we can perform binary search to find the index where
     *       the array is rotated.
     *  * 3. Once we have found that index, then we can easily determine in which half
     *  *    (array will be divided into two halves by the pivot index) of the array the target lies.
     *  * 4. Notice, the two halves are themselves will be sorted.
     *  * 5. We can then perform binary search once again in the determined half to find the index
     *       of the target element.
     *
     *  6 7 0 1 2 3 4 5 -- its always two sorted array side by side, lowest is the pivot
     *
     *  21  20  19 11 12 13 -- not possible, the array cannot start decreasing first then increasing
     *
     */

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

    /**
     * Another approach
     * @param array
     * @param key
     * @return
     */

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
