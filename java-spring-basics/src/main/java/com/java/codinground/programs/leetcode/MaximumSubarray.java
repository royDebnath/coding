package com.java.codinground.programs.leetcode;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * <p>
 * A subarray is a contiguous part of an array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * Example 2:
 * <p>
 * Input: nums = [1]
 * Output: 1
 * Example 3:
 * <p>
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 */
public class MaximumSubarray {
    public static void main(String[] args) {
        int[] input1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4}; //6
        int[] input2 = {5, 4, -1, 7, 8}; //23

        System.out.println(maxSumSubarray(input1));
        System.out.println(maxSumSubarray(input2));
    }

    private static int maxSumSubarray(int[] input) {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < input.length; i++) {
            sum = sum + input[i];
            max = Math.max(sum, max);

            if (sum < 0) sum = 0;
            /**
             by initializing the sum back to zero when -ve is faced,
             we are starting fresh from the next element because -ve will only make the sum less.
             In case of -2, 1, -3 ....
             when 1 is encountered if we did not reset sum to 0 new sum = sum + input[i] will evaluate to -2+1=-1,
             where as we can start from index 1 and max sum will be 1
             **/
        }
        return max;
    }
}
