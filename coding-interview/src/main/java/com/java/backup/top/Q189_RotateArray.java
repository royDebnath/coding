package com.java.backup.top;

/**
 * Problem Description
 * In this problem, we are given an array of integers named nums.
 *
 * The goal is to rotate the elements of the array to the right by k steps.
 * When we rotate the array, every element moves k places to the right,
 * and the elements at the end of the array wrap around to the beginning.
 * For example, if the array is [1, 2, 3, 4, 5]
 * and k is 2,
 * the rotated array will be [4, 5, 1, 2, 3]. It is important to note that k is non-negative,
 * which means rotating by k steps always moves elements to the right, and may be larger than
 * the length of the array.
 *
 * Intuition
 * The key to approaching this problem is to understand that rotating an array by k steps
 * to the right is equivalent to taking the last k elements and moving them to the front,
 * while shifting the remaining elements to make space.
 * However, if k is larger than the length of the array, rotating the array k times
 * would effectively be the same as rotating it k % len(nums) times since every len(nums) rotations,
 * the array returns to its original configuration.
 *
 * To optimize the array rotation, we can follow a three-step reversal strategy which essentially achieves the rotation without additional storage for the displaced elements:
 *
 * Reverse the entire array. This step puts the last k elements at the front, but in reversed order.
 * Reverse the first k elements. This step puts these elements into the correct order at the start of the array.
 * Reverse the last n - k elements, where n is the length of the array. This will fix the order of the rest of the array.
 * This method capitalizes on the idea that reversing specific parts of the array can be used to manipulate the positions of the elements with respect to each other, achieving the same result required by the rotation.
 *
 * Example Walkthrough
 * Let's walk through an example to illustrate the solution approach given in the content above. Suppose we have an array nums with the elements [6, 7, 8, 1, 2, 3] and k equals 2.
 *
 * We would like to rotate this array k steps to the right. Here's how we do it step by step:
 *
 * First, we need to take care of cases where k may be greater than the length of nums. In our case, k is less than the length of nums, so k %= len(nums) does not change the value of k. If the nums array was shorter or k larger, this step would be critical to prevent unnecessary rotations.
 *
 * Next, we take the last k elements of the array with the slice nums[-k:]. Since k is 2, this gives us [2, 3].
 *
 * Now, we take the rest of the array without the last k elements using nums[:-k]. This gives us [6, 7, 8, 1].
 *
 * We then concatenate the slices obtained in steps 2 and 3. So, nums[-k:] + nums[:-k] equals [2, 3] + [6, 7, 8, 1], which results in the array [2, 3, 6, 7, 8, 1].
 *
 * The final step is to update the original array nums with the rotated version by using the slice assignment nums[:]. So, nums[:] becomes [2, 3, 6, 7, 8, 1].
 *
 * The original array nums is now rotated k steps to the right, and our final result is [2, 3, 6, 7, 8, 1]. No extra space was used; we've simply manipulated nums to its rotated form in place by using array slicing and concatenation.
 *
 *
 */
public class Q189_RotateArray {
    // Class-level variable to store the input array
    private int[] nums;

    /**
     * Rotates the given array to the right by k steps.
     * @param nums Array to be rotated.
     * @param k Number of steps to rotate the array to the right.
     */
    public void rotate(int[] nums, int k) {
        // Assign the input array to the class-level variable
        this.nums = nums;

        // Number of elements in the array
        int n = nums.length;

        // Normalize the number of steps k to avoid extra rotations
        k %= n;

        // Reverse the entire array
        reverse(0, n - 1);

        // Reverse the first part (up to k elements)
        reverse(0, k - 1);

        // Reverse the second part (from k to the end of the array)
        reverse(k, n - 1);
    }

    /**
     * Reverses elements in the array between indices i and j.
     * @param left Starting index for reversal.
     * @param right Ending index for reversal.
     */
    private void reverse(int left, int right) {
        // Using two pointers approach, swap elements until pointers meet or cross
        while (left < right) {
            // Temporary variable to hold a value during the swap
            int temp = nums[left];

            // Perform swap
            nums[left] = nums[right];
            nums[right] = temp;

            // Move pointers towards each other
            ++left;
            --right;
        }
    }
}
