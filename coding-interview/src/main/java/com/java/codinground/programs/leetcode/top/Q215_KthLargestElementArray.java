package com.java.codinground.programs.leetcode.top;

import java.util.Arrays;

/**
 * Problem Description
 * The given problem presents us with a scenario where we need to find the k(^{th}) largest element in a provided integer array nums. Unlike finding the maximum or minimum element, this task requires us to identify the value that would be placed at the k(^{th}) largest position if the array were sorted. However, the twist in this problem is that duplicates are allowed, and each instance counts for the position. For example, if nums = [3,2,3,1,2,4,5,5,6] and k = 4, the fourth largest element is 4.
 *
 * Moreover, the problem poses an additional challenge: it hints at the possibility of finding the solution without sorting the entire array, which might suggest that there are more optimal ways to solve this especially when we consider time complexity.
 *
 *
 */
public class Q215_KthLargestElementArray {

    public static void main(String[] args) {
        int[] intList = { 5, 2, 4, 6, 1, 3 };
        findKthLargest(intList, 2);
        Arrays.stream(intList).forEach(System.out::println);
    }

    static int findKthLargest(int[] nums, int k) {
        // the kth largest element's final position would be `nums.length-k`
        return quickSelect(nums, 0, nums.length-1, nums.length-k);
    }

    //after partition only the pivot element moves into it's correct position
    private static int quickSelect(int[] nums, int lo, int hi, int k /* index we're looking for */) {
        int i=lo, j = hi, pivot = nums[hi];

        //partition numbers into either side of pivot
        while(i < j) {
            if (nums[i++] > pivot){
                swap(nums, --i, --j);
            }
        }

        swap(nums, i, hi);

        //how many elements were greater than pivot
        if(i == k)
            return nums[i];
        else if(i > k)
            return quickSelect(nums, lo, i-1, k);
        else
            return quickSelect(nums, i+1, hi, k);
    }

   static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
