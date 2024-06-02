package com.java.backup.top;

/**
 * Given an array nums, write a function to move all 0's to the end of it while
 * maintaining the relative order of the non-zero elements.
 *
 * Example:
 *
 * Input: [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * Note:
 *
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 *
 * Problem Description
 * The problem presents us with a challenge to modify an array nums such that all of the zeros are moved to the end, while the relative order of the non-zero elements is preserved. The important constraints for this task are that the changes must be made to the original array nums directly, without using an additional array or making a copy of the original array. This requirement ensures an in-place solution with no extra space overhead, which tests one's ability to manipulate array elements efficiently.
 *
 * Intuition
 * The intuition behind the solution is based on the two-pointer technique. The goal is to iterate through the array and process elements one by one, swapping non-zero elements towards the front while effectively moving zero elements towards the end.
 *
 * We initialize a pointer i at the position just before the start of the array (i.e., i = -1). This pointer will keep track of the position to place the next non-zero element.
 *
 * As we iterate over the elements of the array with another pointer j, we look for non-zero elements. When we encounter a non-zero element (x), we increment the pointer i, indicating that we have found another non-zero element. Then we swap the elements at indices i and j. This action has two consequences:
 *
 * It moves the non-zero element x to the front of the array at the next available position.
 * It moves the zero (if i and j are not the same) to the position j.
 * The process ensures that all non-zero elements are shifted to the front, maintaining their relative order because we swap non-zero elements with zeroes (or with themselves) without affecting previously positioned non-zero elements. Since i increases only when we find a non-zero, it always points to the first zero in the sequen`ce of elements processed so far, ensuring the relative order is maintained.
 *
 * When the loop is complete, the array is effectively partitioned into two segments: the non-zero elements from the start to index i, followed by zeros from index i + 1 to the end.
 *
 *
 *
 */
class Q283_MoveZeroesToRight {
    /**
     * Method to move all zeros in the array to the end while maintaining the relative order of
     * the non-zero elements.
     */
    public void moveZeroes(int[] nums) {
        // Initialize a pointer to keep track of the position of the last non-zero element found.
        int lastNonZeroFoundAt = -1;
        // Variable to store the array's length to avoid recalculating it.
        int arrayLength = nums.length;

        // Iterate over the array.
        for (int currentIndex = 0; currentIndex < arrayLength; ++currentIndex) {
            // If the current element is not zero,
            if (nums[currentIndex] != 0) {
                // Increment the lastNonZeroFoundAt.
                lastNonZeroFoundAt++;
                // Swap the current element with the element at the lastNonZeroFoundAt position.
                swap(nums, lastNonZeroFoundAt, currentIndex);
            }
        }
    }
    
    private void swap(int[] nums, int source, int target){
        int temp = nums[source];
        nums[source] = nums[target];
        nums[target] = temp;
    }
}