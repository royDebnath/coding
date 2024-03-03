package com.java.codinground.programs.leetcode.top100;

/**
 * Problem Description
 * The given problem asks us to determine if we can split an array of integers, nums, into two subsets such that the sum of the elements in both subsets is the same. This is essentially asking if nums can be partitioned into two subsets of equal sum. If such a partition is possible, we should return true, otherwise, we return false.
 *
 * To understand this problem better, imagine you have a set of blocks with different weights, and you want to see if you can divide them into two groups that weigh the same. If it can be done, then each group represents a subset with an equal sum.
 *
 * Intuition
 * The solution to this problem is based on the concept of dynamic programming, particularly the 0/1 Knapsack problem, where we aim to find a subset of numbers that can sum up to a specific target, in this case, half the sum of all elements in nums.
 *
 * The intuition behind this solution is:
 *
 * First, we calculate the sum of all elements in the array. If the sum is an odd number, it's impossible to partition the array into two subsets with an equal sum, so we immediately return false.
 *
 * If the sum is even, our target becomes half of the total sum, and we set up an array f of boolean values that represents if this sum can be reached using a combination of the numbers we’ve seen so far. f is initialized with a size equal to the target plus one (m + 1), with the first value True (since we can always reach a sum of 0) and the rest False.
 *
 * We iterate over each number in our array nums. For each number, we update our f array from right to left, starting at our target m and going down to the value of the number x. We do this backward to ensure that each number is only considered once. At each position j, we update f[j] by checking if f[j] was previously true or if f[j-x] was true. The latter means that if we already could sum up to j-x, then by adding x, we can now also sum up to j.
 *
 * At the end of this process, f[m] tells us whether we've found a subset of elements that sum up to m, which would be half the sum of the entire array. If f[m] is true, we have our partition and return true, otherwise, we return false.
 *
 * Example Walkthrough
 * Let's walk through an example to illustrate the solution approach. Consider an array nums with the following elements: [1, 5, 11, 5].
 *
 * Calculate the Sum and Determine Feasibility:
 *
 * Compute the sum of the elements: 1 + 5 + 11 + 5 = 22.
 * Use divmod to check if the sum is even or odd: divmod(22, 2) gives us (11, 0).
 * Since the remainder is 0, the sum is even, and proceeding is feasible.
 * Dynamic Programming Array Setup:
 *
 * Our target sum m is 22 / 2 = 11.
 * Initialize f with dimensions [12] (m + 1) and set f[0] to True.
 * Iterate and Update the DP Array:
 *
 * Start iterating over the array nums: [1, 5, 11, 5].
 * Update the DP Array:
 *
 * For x = 1 (first element), update f from 11 down to 1. Since f[0] is True, set f[1] to True.
 * For x = 5 (second element), update f from 11 down to 5. Now f[5], f[6], f[7], f[8], f[9], and f[11] become True.
 * For x = 11 (third element), since f[0] is True, set f[11] to True. However, f[11] is already True from the previous step.
 * Lastly, for x = 5 (fourth element), update f again similarly to when x was 5 before.
 * Return the Result:
 *
 * After the final iteration, we check the value of f[11], which is True.
 * This indicates that there is a subset with a sum of 11, which is half of the total sum.
 * Therefore, the array [1, 5, 11, 5] can be partitioned into two subsets with equal sum, and we return true.
 *
 *
 */
public class Q416_PartitionEqualSubsetSum {
}
