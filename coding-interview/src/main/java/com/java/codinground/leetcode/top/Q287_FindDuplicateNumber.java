package com.java.codinground.leetcode.top;

/**
 * Problem Description
 * In this problem, we're given an array called nums which contains n + 1 integers,
 * where every integer is within the range of 1 to n, both inclusive.
 * There's an important constraint in the problem: there is exactly one number in the array nums
 * that appears more than once, and our task is to find that one repeated number.
 * The challenge is to solve this problem under the following two conditions:
 * we are not permitted to alter the original array,
 * and we have to solve it using only a constant amount of space,
 * which eliminates the possibility of using additional data structures
 * that can grow with the size of the input.
 *
 * Intuition
 * We can approach this problem with a binary search technique despite the fact that the array is not sorted.
 * This might seem counterintuitive at first because binary search usually requires a sorted array.
 * However, the key insight here is to use binary search not on the elements of the array itself,
 * but rather on the range of numbers between 1 to n.
 *
 * The intuition is based on the Pigeonhole Principle which states that if you have more pigeons
 * than pigeonholes, at least one pigeonhole must contain more than one pigeon.
 * In this context, if there are more numbers in the array than the range it covers
 * (n numbers in the range 1 to n), one of the numbers must be a duplicate.
 *
 * We start by considering the entire range of numbers from 1 to n.
 * Then, we use binary search to split this range into two halves:
 * the first half (from 1 to mid) and
 * the second half (from mid + 1 to n).
 * The helper function, f, calculates how many numbers in the array
 * are less than or equal to a given middle value, x.
 * If the count is greater than x, we know the duplicate number must be in the first half;
 * otherwise, it's in the second half.
 *
 *
 */
public class Q287_FindDuplicateNumber {
    public int findDuplicate(int[] nums) {
        // Initializing the low and high pointers for binary search.
        int low = 0;
        int high = nums.length - 1;

        // Binary search to find the duplicate number.
        while (low < high) {
            // Calculating the middle index.
            int middle = (low + high) / 2;
            int count = 0; // Counter for the number of elements less than or equal to middle.

            // Iterate over the array and count elements less than or equal to middle.
            for (int value : nums) {
                if (value <= middle) {
                    count++;
                }
            }

            // Determine if the duplicate is in the lower half or upper half.
            // If the count is greater than middle, the duplicate is in the lower half.
            if (count > middle) {
                high = middle; // Narrow the search to the lower half.
            } else {
                low = middle + 1; // Narrow the search to the upper half.
            }
        }

        // When low == high, we have found the duplicate number.
        return low;
    }
}
