package com.java.codinground.educative.binary_search;

import java.util.*;

public class BinarySearch {

    public static int binarySearch(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            // Finding the mid using integer division
            int mid = low + (high - low) / 2;

            // Target value is present at the middle of the array
            if (nums[mid] == target) {
                return mid;
            }
            // Target value is present in the low subarray
            else if (target < nums[mid]) {
                high = mid - 1;
            }
            // Target value is present in the high subarray
            else if (target > nums[mid]) {
                low = mid + 1;
            }
        }

        // Target value is not present in the array
        return -1;
    }

    public static void main(String[] args) {
        int[][] numsLists = {
                {},
                {0, 1},
                {1, 2, 3},
                {-1, 0, 3, 5, 9, 12},
                {-100, -67, -55, -50, -49, -40, -33, -22, -10, -5}
        };

        int[] targetList = {12, 1, 3, 9, -22};

        for (int i = 0; i < numsLists.length; i++) {
            int[] nums = numsLists[i];
            int target = targetList[i];
            int index = binarySearch(nums, target);
            System.out.println((i + 1) + ".\tArray to search: " + Arrays.toString(nums));
            System.out.println("\tTarget: " + target);
            if (index != -1) {
                System.out.println("\t" + target + " exists in the array at index " + index);
            } else {
                System.out.println("\t" + target + " does not exist in the array, so the return value is " + index);
            }
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}