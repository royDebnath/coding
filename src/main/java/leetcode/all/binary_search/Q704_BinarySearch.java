package leetcode.all.binary_search;

import java.util.Arrays;

public class Q704_BinarySearch {
    public static int search(int[] nums, int target) {
        // Initialize the starting index of the search range.
        int left = 0;
        // Initialize the ending index of the search range.
        int right = nums.length - 1;

        // Continue searching while the range has more than one element.
        while (left < right) {
            // Calculate the middle index of the current range.
            int mid = left + (right - left) / 2;

            // If the middle element is greater than or equal to the target,
            // narrow the search range to the left half (including the middle element).
            if (nums[mid] >= target) {
                right = mid;
            } else {
                // If the middle element is less than the target,
                // narrow the search range to the right half (excluding the middle element).
                left = mid + 1;
            }
        }

        // At this point, left is the index where the target may be if it exists.
        // Check if the element at the 'left' index is the target.
        // If it is, return the index. Otherwise, return -1 indicating the target is not found.
        return nums[left] == target ? left : -1;
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
            int index = search(nums, target);
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
