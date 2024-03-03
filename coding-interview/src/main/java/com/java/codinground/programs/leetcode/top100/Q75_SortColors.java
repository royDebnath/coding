package com.java.codinground.programs.leetcode.top100;

/**
 * Problem Description
 * In this problem, you are given an array nums which contains n elements. Each element represents a color coded as an integer: 0 for red, 1 for white, and 2 for blue. Your task is to sort this array in a way that the colors are grouped together and in the order of red, white, and blue. The sorting has to be done in-place, without using any extra space for another array, and you cannot use the library's sort function.
 * <p>
 * Intuition
 * To solve this problem, the solution approach uses a variant of the famous Dutch National Flag algorithm proposed by Edsger Dijkstra. The crux of this algorithm is a three-way partitioning technique that segments an array into three parts to sort the elements of three different types.
 * <p>
 * In this case, we will maintain three pointers:
 * <p>
 * i - All elements before index i are 0s (reds).
 * j - All elements from index j onwards are 2s (blues).
 * k - Current element that is being inspected.
 * Initially, i is set to -1, indicating there are no 0s in the beginning, and j is set to the length of nums, indicating there are no 2s at the end. The k pointer will start from 0 and move towards j.
 * <p>
 * We iterate through the array with k, and when we find a 0, we increment i and swap the values at i and k. If we find a 2, we decrement j and swap the values at k and j but we don't move the pointer k because the new element we swapped from position j might be 0, so it needs to be rechecked. If the element is 1, it's already in its correct place since we are ensuring all 0s and 2s are moved to their correct places. So, for 1, we just move k forward.
 * <p>
 * We continue this process until k meets j, at which point all elements to the left of i are 0s, elements between i and j are 1s, and all elements from j onwards are 2s, resulting in a sorted array.
 * <p>
 * Solution Approach
 * The solution implements the Dutch National Flag algorithm, which is a partitioning strategy.
 * <p>
 * Here's a step-by-step explanation using the Reference Solution Approach:
 * <p>
 * Initialize three pointers (i, j, and k):
 * <p>
 * i starts just before the array at -1. This will eventually track the position up to which 0s have been sorted.
 * j starts after the end of the array at len(nums). This will eventually track the position from which 2s have been sorted.
 * k starts at 0 and is used to iterate through the array.
 * Perform iterations while k < j:
 * <p>
 * If nums[k] == 0, this element needs to be moved to the front.
 * Increment i to move it to the next unsorted position.
 * Swap the elements at i and k (nums[i], nums[k] = nums[k], nums[i]), effectively moving the 0 to its correct place.
 * Increment k to move on to the next element.
 * Else, if nums[k] == 2, this element needs to be moved to the end.
 * Decrement j to move it towards the first unsorted position from the end.
 * Swap the elements at k and j (nums[j], nums[k] = nums[k], nums[j]), moving the 2 closer to its correct place. Here we don't increment k because the newly swapped element could be 0 or 1 and it has not been evaluated yet.
 * If nums[k] == 1, no action is needed as 1s are automatically sorted when 0s and 2s are moved to their correct places.
 * Simply increment k to continue to the next element.
 * By following this approach, we continue to partition the array into three parts: 0s before i, 1s between i and j, and 2s after j. The loop continues until k becomes equal to j, meaning all elements have been examined and placed in their correct position. Therefore, the array is now sorted in-place with red (0), white (1), and blue (2) colors in the correct order without using any additional space or the library sort function.
 */
public class Q75_SortColors {
    // Method to sort the array containing 0s, 1s, and 2s
    public void sortColors(int[] nums) {
        // 0 - red, 1 - white, 2 - blue
        // Initialize pointers for the current element (currIndex),
        // the last position of 0 (redIndex) and the first position of 2 (blueIndex)
        int redIndex = -1;
        int blueIndex = nums.length;
        int currIndex = 0;

        // Process elements until currIndex reaches blueIndex
        while (currIndex < blueIndex) {
            if (nums[currIndex] == 0) {
                // If the current element is 0, swap it to the position after the last 0 we found
                redIndex++;
                swap(nums, redIndex, currIndex);
                currIndex++;
            } else if (nums[currIndex] == 2) {
                // If the current element is 2, swap it with the element at the position
                // just before the first 2 we found
                blueIndex--;
                swap(nums, blueIndex, currIndex);
            } else {
                // If the current element is 1, just move to the next element
                currIndex++;
            }
        }
    }

    // Helper method to swap two elements in an array
    private void swap(int[] nums, int i, int j) {
        if (nums[i]==nums[j]){
            return;
        }
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}