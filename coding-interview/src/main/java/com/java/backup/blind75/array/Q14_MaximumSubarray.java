package com.java.backup.blind75.array;

/**
 * Given an integer array nums, find the
 * subarray
 *  with the largest sum, and return its sum.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 * Example 2:
 *
 * Input: nums = [1]
 * Output: 1
 * Explanation: The subarray [1] has the largest sum 1.
 * Example 3:
 *
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 * Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 *
 * Solution :
 *
 * 1. Scan the given array from left to right.
 * 2. Keep track of local_maximum and global_maximum.
 * 3. local_maximum is the maximum of the current element and the sum of current element and previous maximum.
 * 4. If local_maximum > global_maximum, then update the global_maximum.
 * 5. Return the global_maximum.
 *
 * Pseudo code
 *
 * local_maximum = A[0]
 * global_maximum = A[0]
 *
 * for each element i in array:
 *     local_maximum = max(A[i], local_maximum + A[i])
 *     if global_maximum < local_maximum:
 *         global_maximum = local_maximum
 *
 * return global_maximum
 *
 * Dry Run :
 *
 * A = [-2, 2, 5, -11, 6]
 *
 * i = 0:
 * local_maximum = -2
 * global_maximum = -2
 *
 * i = 1:
 * local_maximum = max(2, -2 + 2) = 2
 * global_maximum = 2
 *
 * i = 2:
 * local_maximum = max(5, 2 + 5) = 7
 * global_maximum = 7
 *
 * i = 3:
 * local_maximum = max(-11, -11 + 7) = -4
 * global_maximum = 7
 *
 * i = 4:
 * local_maximum = max(6, -4 + 6) = 6
 * global_maximum = 7
 *
 * return 7
 */

class Q14_MaximumSubarray {
    public static void main(String[] args) {
        int[] input = {3, 5, -9, 1, 3, -2, 3, 9, 7, 2, -9, 6, 3, 1, -5, 4};
        System.out.println(maxSubArray(input));
    }

    public static int maxSubArray(int[] arr) {

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
}
