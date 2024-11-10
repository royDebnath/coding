package com.roydebnath.coding.leetcode.neetode.binary_search;

/**
 * Given a sorted array of distinct integers and a target value,
 * return the index if the target is found. If not, return the index where it would be if
 * it were inserted in order.
 * <p>
 * You must write an algorithm with O(log n) runtime complexity.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,3,5,6], target = 5
 * Output: 2
 * Example 2:
 * <p>
 * Input: nums = [1,3,5,6], target = 2
 * Output: 1
 * Example 3:
 * <p>
 * Input: nums = [1,3,5,6], target = 7
 * Output: 4
 */
public class Q35_SearchInsertPosition {
    public static void main(String[] args) {
        int[] input = {1, 3};
        System.out.println(searchInsertPosition(input, 2));
    }

    private static int searchInsertPosition(int[] input, int key) {
        int length = input.length;
        int left = 0;
        int right = length - 1;
        int diff = Integer.MAX_VALUE;
        int insertPosition = -1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            int currentDiff = Math.abs(input[middle] - key);
            if (key < input[middle]) {
                right = middle - 1;
                if (currentDiff < diff) {
                    diff = currentDiff;
                    /**
                     *  If the diff is less than the least seen so far and the target is less than the current number
                     *  then the possible position for the number would be the next position => so "mid"
                     */
                    insertPosition = middle;
                }
            } else if (key > input[middle]) {
                left = middle + 1;
                if (currentDiff < diff) {
                    diff = currentDiff;
                    /**
                     * If the diff is less than the least seen so far and the target is greater than the current number
                     * then the possible position for the number would be the next position => so "mid+1"
                     */
                    insertPosition = middle + 1;
                }
            } else {
                return middle;
            }
        }
        return insertPosition;
    }
}
