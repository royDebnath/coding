package com.roydebnath.coding.leetcode.neetode.binary_search;

/**
 *

 Code

 Testcase

 Test Result
 Test Result
 540. Single Element in a Sorted Array
 Medium

 Topics
 Companies
 You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.

 Return the single element that appears only once.

 Your solution must run in O(log n) time and O(1) space.



 Example 1:

 Input: nums = [1,1,2,3,3,4,4,8,8]
 Output: 2
 Example 2:

 Input: nums = [3,3,7,7,10,11,11]
 Output: 10


 Solution:

 To solve this problem, we leverage the sorted nature of the array and the requirements for time and space complexity to determine that binary search is the right approach. Binary search helps us cut down the problem space in half with each iteration, making our time complexity O(log n).

 The key insight here is to understand how pairs of the same numbers are arranged in the array. Since the array is sorted, identical numbers are adjacent. If we take the first occurrence of a pair, it should be at an even index, and its pair should be immediately next, at an odd index, and this pattern continues until we hit the single element. After the single element, this pattern will flip because of the missing pair.

 By examining the middle index and its adjacent numbers, we can decide whether the single number lies on the left or right of our current middle point.

 Initialization:

 int low = 0;
 int high = nums.length - 1;
 low and high are initialized to the start and end of the array, respectively.
 Binary Search Loop:

 while (low < high) {
 This loop continues until low is equal to high.
 Finding the Middle Element:

 int mid = low + (high - low) / 2;
 if (mid % 2 == 1) {
 mid--;
 }
 mid is calculated to be the middle index between low and high.
 If mid is odd, it is decremented by 1 to make it even. This ensures that we are always comparing pairs starting with an even index.
 Comparison:

 if (nums[mid] == nums[mid + 1]) {
 low = mid + 2;
 } else {
 high = mid;
 }
 If the element at mid is equal to the element at mid + 1, it means that the single non-duplicate element is in the right half of the array. So, low is updated to mid + 2.
 If nums[mid] is not equal to nums[mid + 1], it means the single non-duplicate element is in the left half of the array, including the current mid. Hence, high is updated to mid.
 Return Statement:

 return nums[low];
 When low equals high, the loop exits and the single non-duplicate element is at the index low.

 */
public class Q540_SingleElementInSortedArray {
    public static int singleNonDuplicate(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (mid % 2 == 1) {
                mid--;
            }

            if (nums[mid] == nums[mid + 1]) {
                low = mid + 2;
            } else {
                high = mid;
            }
        }
        return nums[low];
    }

    public static void main(String[] args) {
        int[] input = {1,1,3,3,4,4,2,8,8};
        System.out.println(singleNonDuplicate(input));
    }
}
