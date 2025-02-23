package leetcode.blind75.array;

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

    /**
     *
     * For each index i keep updating the max and min.
     * We are also keeping min because on multiplying with any negative number your min will become max
     * and max will become min. So for every index i we will take
     * max of (i-th element, prevMax * i-th element) -- ith element is +ve
     *                      or
     *        (i-th element, prevMin * i-th element). --ith element is negetive
     */
    public static int maxProduct(int[] nums) {

        int max, currMax, currMin;
        max = currMin = currMax = nums[0];

        for (int i=1; i<nums.length; i++) {

            int current = nums[i];

            // If num is positive or zero
            if (current >= 0) {
                // Just keep on calculating as it is
                currMax = Math.max(current * currMax, current);
                currMin = Math.min(current * currMin, current);

            }
            // If num is negative
            else {
                // Do the same as if the nums[i] were positive, but swap min and max while calculating
                // In this case, new max has to be calculated with num * currMin
                //             & new min has to be calculated with num * currMax
                // because -ve becomes +ve and +ve becomes -ve i.e min becomes max and max becomes min

                int prevMin = currMin;
                int prevMax = currMax;

                currMax = Math.max(current * prevMin, current);
                currMin = Math.min(current * prevMax, current);
            }

            max = Math.max(max, currMax);

        }

        return max;
    }
}
