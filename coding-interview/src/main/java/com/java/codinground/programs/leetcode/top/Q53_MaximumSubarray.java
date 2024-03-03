package com.java.codinground.programs.leetcode.top;

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
public class Q53_MaximumSubarray {
    public static void main(String[] args) {
        int[] input1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4}; //6
        int[] input2 = {5, 4, -1, 7, 8}; //23

        System.out.println(maxSumSubarray(input1));
        System.out.println(maxSumSubarray(input2));

        System.out.println(maxSubArray(input1));
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

    private static int maxSubArray(int[] arr){

        int max = arr[0];
        int start = 0;
        int end = 0;

        int currentMax = arr[0];
        int tempStart = 0;
        int tempEnd = 0;

        for (int i = 1; i < arr.length; i++) {
            tempEnd = i;
            currentMax = currentMax + arr[i];

            if (currentMax < arr[i] ) {
                currentMax = arr[i];
                tempStart = i; // start is only changed when the previous max is bringing down the current max
            }

            if (currentMax > max) {
                max = currentMax;
                start = tempStart;
                end = tempEnd;
            }
        }
        System.out.println("start : " + start + " end : " + end);
        return max;
    }

    /**
     * Problem Description
     * The problem gives us an array of integers called nums. Our task is to find a contiguous subarray within this array that adds up to the maximum sum possible and then return this maximum sum. The term "subarray" here refers to a sequence of consecutive elements from the original array. It's important to note that the array may contain both positive and negative numbers, and the subarray with the largest sum must contain at least one element.
     *
     * Intuition
     * To solve this problem, we use a well-known algorithm called Kadane's algorithm. The intuition behind this approach is to iterate through each element in the array while keeping track of two important variables: f and ans. Here, f tracks the maximum sum of the subarray ending at the current position, and ans keeps the overall maximum sum found so far.
     *
     * At each step of the iteration, we decide whether to "start fresh" with the current element (if the sum up to this point is negative, since it would only reduce the sum of the subarray) or to add it to the current running sum f. We do this by comparing f with 0 (essentially dropping the subarray if f is negative) and then add the current element to f.
     *
     * Then, we update ans to be the maximum of ans or the new sum f. By the end of the iteration, we would have examined every subarray and ans holds the value of the largest subarray sum.
     *
     * The key idea is to keep adding elements to the current sum if it contributes positively and start a new subarray sum whenever the running sum becomes negative.
     *
     * Solution Approach
     * The implementation of the solution is straightforward once the intuition behind the problem is clear. The solution uses no additional data structures other than simple variables for tracking the current sum and the maximum sum.
     *
     * The algorithm initializes ans and f with the first element of the array. It assumes that the best subarray could at least be the first element itself. Then it begins to iterate from the second element of the array all the way to the last element.
     *
     * For each element x in the array:
     *
     * We update f to be the maximum of f + x or 0 + x. The reason we compare with 0 is to decide whether to start a new subarray from the current element (in case the previous f was negative and thus, doesn't help in increasing the sum).
     *
     * This is implemented as:
     *
     * f = max(f, 0) + x
     * We update ans to be the maximum of ans or the new f. This way, we ensure ans always holds the maximum sum found so far.
     *
     * This is implemented as:
     *
     * ans = max(ans, f)
     * After the loop terminates, ans will hold the maximum subarray sum that we are looking for, which gets returned as the result.
     *
     * This approach only requires O(1) extra space for the tracking variables and O(n) time complexity, as it passes through the array only once. It's a prime example of an efficient algorithm that combines simple ideas to solve a problem that might seem complex at first glance.
     *
     * Example Walkthrough
     * Let's walk through an example to illustrate the solution approach. Consider the following array of integers:
     *
     * nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
     * We want to find a contiguous subarray that has the maximum sum. According to Kadane's algorithm, we initialize our tracking variables:
     *
     * f = nums[0] = -2
     * ans = nums[0] = -2
     * Now, let's iterate through the array starting from the second element.
     *
     * At index 1, nums[1] = 1
     *
     * We calculate f = max(f + nums[1], nums[1]) = max(-2 + 1, 1) = 1
     * We then update ans = max(ans, f) = max(-2, 1) = 1
     * At index 2, nums[2] = -3
     *
     * Update f = max(f + nums[2], nums[2]) = max(1 + (-3), -3) = max(-2, -3) = -2
     * Since f is less than ans, we don't update ans. ans remains 1.
     * At index 3, nums[3] = 4
     *
     * Update f = max(f + nums[3], nums[3]) = max(-2 + 4, 4) = 4
     * Update ans = max(ans, f) = max(1, 4) = 4
     * At index 4, nums[4] = -1
     *
     * Update f = max(f + nums[4], nums[4]) = max(4 + (-1), -1) = 3
     * ans remains the same as f is less than ans.
     * At index 5, nums[5] = 2
     *
     * Update f = max(f + nums[5], nums[5]) = max(3 + 2, 2) = 5
     * Update ans = max(ans, f) = max(4, 5) = 5
     * At index 6, nums[6] = 1
     *
     * Update f = max(f + nums[6], nums[6]) = max(5 + 1, 1) = 6
     * Update ans = max(ans, f) = max(5, 6) = 6
     * At index 7, nums[7] = -5
     *
     * Update f = max(f + nums[7], nums[7]) = max(6 + (-5), -5) = 1
     * ans remains 6.
     * Finally, at index 8, nums[8] = 4
     *
     * Update f = max(f + nums[8], nums[8]) = max(1 + 4, 4) = 5
     * Update ans = max(ans, f) = max(6, 5) = 6
     * After iterating through all elements, we find that the maximum sum of a contiguous subarray in nums is 6, and the contiguous subarray that gives this sum is [4, -1, 2, 1]. Thus our function would return 6 as the final result.
     *
     *
     */

    public int maxSubArrayKadane(int[] nums) {
        // `maxSoFar` holds the maximum subarray sum found so far
        int maxSoFar = nums[0];
        // `currentMax` holds the maximum sum of the subarray ending at the current position
        int currentMax = nums[0];

        // Loop through the array starting from the second element
        for (int i = 1; i < nums.length; ++i) {
            // Update `currentMax` to be the maximum of `currentMax` + current element or 0 + current element
            // This is the essence of the Kadane's algorithm which decides whether to start a new subarray or continue with the current one
            currentMax = Math.max(currentMax, 0) + nums[i];

            // If the current computed `currentMax` is greater than `maxSoFar`, update `maxSoFar`
            maxSoFar = Math.max(maxSoFar, currentMax);
        }
        // Return the largest sum
        return maxSoFar;
    }

    /**
     * Time Complexity
     * The given code snippet consists of a single loop that iterates through the list nums. The loop starts from the second element and goes till the last element, performing constant time operations in each iteration. The max function is also O(1). Therefore, the time complexity is O(n), where n is the number of elements in the input list nums.
     *
     */
}
