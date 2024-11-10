package com.roydebnath.coding.leetcode.all.dynamic_programming;

/**
 * Problem Description
 * The given problem asks us to determine if we can split an array of integers, nums,
 * into two subsets such that the sum of the elements in both subsets is the same.
 * This is essentially asking if nums can be partitioned into two subsets of equal sum.
 * If such a partition is possible, we should return true, otherwise, we return false.
 *
 *
 * Intuition
 * The solution to this problem is based on the concept of dynamic programming,
 * particularly the 0/1 Knapsack problem, where we aim to find a subset of numbers
 * that can sum up to a specific target, in this case, half the sum of all elements in nums.
 *
 * The intuition behind this solution is:
 *
 * First, we calculate the sum of all elements in the array.
 * If the sum is an odd number, it's impossible to partition the array into two subsets with an equal sum,
 * so we immediately return false.
 *
 * If the sum is even, our target becomes half of the total sum,
 * and we set up an array f of boolean values that represents if this sum can be reached
 * using a combination of the numbers we’ve seen so far.
 * f is initialized with a size equal to the target plus one (m + 1),
 * with the first value True (since we can always reach a sum of 0) and the rest False.
 *
 * We iterate over each number in our array nums.
 * For each number, we update our f array from right to left,
 * starting at our target m and going down to the value of the number x.
 * We do this backward to ensure that each number is only considered once.
 * At each position j, we update f[j] by checking if f[j] was previously true or if f[j-x] was true.
 * The latter means that if we already could sum up to j-x, then by adding x, we can now also sum up to j.
 *
 * At the end of this process, f[m] tells us whether we've found a subset of elements that sum up to m,
 * which would be half the sum of the entire array.
 * If f[m] is true, we have our partition and return true, otherwise, we return false.
 *
 */
public class Q416_PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        // Calculate the sum of all array elements
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // If the sum is odd, it's not possible to partition the array into two subsets of equal sum
        if (sum % 2 != 0) {
            return false;
        }

        // Target sum for each subset is half of the total sum
        int targetSum = sum / 2;

        // Create a boolean array to store the subset sums achievable up to the targetSum
        boolean[] subsetSums = new boolean[targetSum + 1];

        // There's always one subset with sum 0, the empty set
        subsetSums[0] = true;

        // Check each number in the given array
        for (int num : nums) {
            // Traverse the subsetSums array in reverse to avoid using an element multiple times
            for (int j = targetSum; j >= num; j--) {
                // Update the subset sums that are achievable
                // If j-num is achievable, set j as achievable (because we're adding num to the subset)
                subsetSums[j] = subsetSums[j] || subsetSums[j - num];
                if (subsetSums[targetSum]){
                    break; // target is reached
                }
            }
        }

        // The result is whether the targetSum is achievable as a subset sum
        return subsetSums[targetSum];
    }
}
