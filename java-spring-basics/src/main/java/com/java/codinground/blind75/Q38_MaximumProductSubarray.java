package com.java.codinground.blind75;

/**
 * Given an integer array nums, find a
 * subarray
 *  that has the largest product, and return the product.
 */
public class Q38_MaximumProductSubarray {
    public static void main(String[] args) {
        int[] nums = {2,3,-2,4};
        System.out.println(maxProduct(nums));
    }
    public static int maxProduct(int[] nums) {

        int max, currMax, currMin;
        max = currMin = currMax = nums[0];

        for (int i=1; i<nums.length; i++) {

            // If num is positive or zero
            if (nums[i] >= 0) {
                // Just keep on calculating as it is
                currMax = Math.max(nums[i] * currMax, nums[i]);
                currMin = Math.min(nums[i] * currMin, nums[i]);

            }
            // If num is negative
            else {
                // Do the same as if the nums[i] were positive, but swap min and max while calculating
                // In this case, new max has to be calculated with num * currMin
                //             & new min has to be calculated with num * currMax
                // because -ve becomes +ve and +ve becomes -ve i.e min becomes max and max becomes min

                int tempMin = currMin;
                int tempMax = currMax;

                currMax = Math.max(nums[i] * tempMin, nums[i]);
                currMin = Math.min(nums[i] * tempMax, nums[i]);
            }

            max = Math.max(max, currMax);

        }

        return max;
    }
}
