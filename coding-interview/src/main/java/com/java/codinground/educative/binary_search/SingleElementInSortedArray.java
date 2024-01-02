package com.java.codinground.educative.binary_search;

/**
 * Statement
 * You are given a sorted array of integers, nums, where all integers appear twice except for one. Your task is to find and return the single integer that appears only once.
 *
 * Solution
 *
 * Suppose that all the elements of nums appear twice. We can say that all the elements are in pairs. In each pair, the first element of the pair is at the even index of nums, and the second element is at the odd index of nums.
 *
 * 45,45,65,65,95,95
 * 0  1  2  3  4
 *
 * The pattern of having the first elements of pairs at the even index and the second elements at the odd index breaks when we have an extra element in nums that only exists once.
 *
 * 45,45,65,65,75,95,95
 * 0  1  2  3  4  5
 *
 * since 75 occurs once 95 is at the odd 5th index instead of even 6th index.
 *
 * Keeping these observations in mind, we use binary search to find the single non-duplicate element in nums.
 *
 * The algorithm works as follows:
 *
 * Initialize left to the leftmost index (
 * 0
 * 0
 * ) and right to the rightmost index (nums.length - 1).
 *
 * Calculate mid using the formula
 * �
 * �
 * �
 * =
 * �
 * �
 * �
 * �
 * +
 * ⌊
 * (
 * �
 * �
 * �
 * ℎ
 * �
 * −
 * �
 * �
 * �
 * �
 * )
 * 2
 * ⌋
 * mid=left+⌊
 * 2
 * (right−left)
 * ​
 *  ⌋
 * . We use this formula to avoid any integer overflow during the calculation.
 *
 * If mid is odd, decrement it by
 * 1
 * 1
 *  to make it an even index.
 * Check whether nums[mid] is the same as nums[mid + 1].
 *
 * If both are the same, it means that all elements up to this point were in pairs, and the single element must appear after mid. Therefore, move the left pointer toward the right.
 *
 * If both are different, it means that the single element must have appeared before mid. Therefore, move the right pointer toward the left.
 *
 * Repeat steps 2 to 4 until left becomes equal to right.
 *
 * Eventually, all the pointers will be pointing to the same element, so return that element as the output.
 *
 *
 *
 */
public class SingleElementInSortedArray {
    public static int singleNonDuplicate(int[] nums) {

        // initilaize the left and right pointer
        int l = 0;
        int r = nums.length - 1;

        while (l < r) {

            // if mid is odd, decrement it to make it even
            int mid = l + (r - l) / 2;
            if (mid % 2 == 1) mid--;

            // if the elements at mid and mid + 1 are the same, then the single element must appear after the midpoint
            if (nums[mid] == nums[mid + 1]) {
                l = mid + 2;
            }
            // otherwise, we must search for the single element before the midpoint
            else {
                r = mid;
            }
        }
        return nums[l];
    }
}
