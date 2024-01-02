package com.java.codinground.educative.binary_search;

import java.util.Arrays;
import java.util.List;

/**
 * Statement
 * Given a sorted integer array, nums, and an integer value, target, the array is rotated by some arbitrary number. Search and return the index of target in this array. If the target does not exist, return -
 * 1
 * 1
 * .
 *
 * Solution :
 *
 * You may notice that at least one half of the array is always sorted—if the array is rotated by less than half the length of the array, at least the second half of the array will still be sorted. Contrarily, if the array is rotated by more than half the length of the array, then at least the first half of the array will be sorted. We can use this property to our advantage and modify the binarySearch function as follows:
 *
 * If the target value lies within the sorted half of the array, our problem is a basic binary search.
 *
 * Otherwise, discard the sorted half and keep examining the unsorted half.
 *
 * Here is how we go about implementing the iterative approach for this:
 */
public class SearchInRotatedSortedArray  {

    public static int binarySearchRotated(List<Integer> nums, int target) {
        int start = 0;
        int end = nums.size() - 1;
        if (start > end)
            return -1;
        while (start <= end) {
            // Finding the mid using integer division
            int mid = start + (end - start) / 2;
            // Target value is present at the middle of the array
            if (nums.get(mid) == target)
                return mid;
            // start to mid is sorted
            if (nums.get(start) <= nums.get(mid)) {
                if (nums.get(start) <= target && target < nums.get(mid)) {
                    end = mid - 1; // target is within the sorted first half of the array
                } else {
                    start = mid + 1; // target is not within the sorted first half, so let’s examine the unsorted second half
                }
            }
            // mid to end is sorted
            else {
                if (nums.get(mid) < target && target <= nums.get(end))
                    start = mid + 1; // target is within the sorted second half of the array
                else
                    end = mid - 1; // target is not within the sorted second half, so let’s examine the unsorted first half
            }
        }
        return -1;
    }

    // Driver code
    public static void main(String args[]) {
        List<List<Integer>> numList = Arrays.asList(
                Arrays.asList(5, 6, 7, 1, 2, 3, 4),
                Arrays.asList(40, 50, 60, 10, 20, 30),
                Arrays.asList(47, 58, 69, 72, 83, 94, 12, 24, 35),
                Arrays.asList(77, 82, 99, 105, 5, 13, 28, 41, 56, 63),
                Arrays.asList(48, 52, 57, 62, 68, 72, 5, 7, 12, 17, 21, 28, 33, 37, 41)
        );

        List<Integer> targetList = Arrays.asList(1, 50, 12, 56, 5);

        for (int i = 0; i < targetList.size(); i++) {
            System.out.println((i + 1) + ".\tSorted array: " + numList.get(i) +
                    "\n\ttarget " + targetList.get(i) +
                    " found at index " + binarySearchRotated(numList.get(i), targetList.get(i)));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
