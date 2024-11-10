package com.roydebnath.coding.leetcode.neetode.greedy;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
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
public class Q53_MaximumSubarray {
    public static void main(String[] args) {
        int[] input1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4}; //6
        int[] input2 = {5, 4, -1, 7, 8}; //23
        System.out.println(maxSubArray(input1));
    }

    /**
     *
     * Intuition
     * To solve this problem, we use a well-known algorithm called Kadane's algorithm.
     *
     * The intuition behind this approach is to iterate through each element in the array while
     * keeping track of two important variables: currentSum and max.
     * Here, currentSum tracks the maximum sum of the subarray ending at the current position,
     * and max keeps the overall maximum sum found so far.
     *
     * At each step of the iteration, we decide whether
     * to "start fresh" with the current element (if the sum up to this point is negative,
     * since it would only reduce the sum of the subarray)
     * or
     * to add it to the current running sum currentSum.
     *
     * We do this by comparing currentSum with 0
     * (essentially dropping the subarray if currentSum is negative) and
     * then add the current element to f.
     *
     * Then, we update max to be the maximum of max or the new sum currentSum.
     *
     * By the end of the iteration, we would have examined every subarray and ans holds the value
     * of the largest subarray sum.
     *
     * The key idea is to keep adding elements to the current sum if it contributes positively and
     * start a new subarray sum whenever the running sum becomes negative.
     *
     * */

    // additional
    private static int maxSubArray(int[] input) {
        int currentSum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < input.length; i++) {
            currentSum = currentSum + input[i];
            max = Math.max(currentSum, max);

            if (currentSum < 0) currentSum = 0; // read explanation below
        }
        return max;
    }

    /**
     by initializing the sum back to zero when -ve is faced,
     we are starting fresh from the next element because -ve will only make the sum less.
     In case of -2, 1, -3 ....
     when 1 is encountered if we did not reset sum to 0 new sum = sum + input[i] will evaluate to -2+1=-1,
     where as we can start from index 1 and max sum will be 1
     **/
}
