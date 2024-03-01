package com.java.codinground.programs.leetcode.top100;

/**
 * Problem Description
 * You are given an array nums, where each element represents the maximum number of steps you can jump forward from that position. Your goal is to determine if you can reach the last element of the array starting from the first element. You start at the first index of the array, and at each step, you can jump forward to any position within your current element's range. If you can make it to any index that has enough range to reach the end, or if the end is within your reach from where you are, the answer is true. Otherwise, it's false.
 *
 * Intuition
 * The key intuition behind the solution is to take a greedy approach. This means we want to make the locally optimal choice at each step in the hope that this will lead to the globally optimal solution. In this case, as we traverse the array, we continuously keep track of the furthest we can reach (mx).
 *
 * We start at the first index and iterate through the array. For each index, we update mx to be the maximum of its current value or the furthest we can get from this index (which is the current index i plus the jump length nums[i]). As we do this, if we find mx is ever less than i, we cannot reach position i or beyond, which means we cannot reach the last index, and return false. If we can always reach the position we're iterating over, by the end of the iteration, we can reach the end, and we return true.
 *
 * Solution Approach
 * The solution uses a simple but effective greedy algorithm. The implementation of this solution does not require any complex data structures, relying only on a single integer variable mx which keeps track of the maximum index that can be reached at each step.
 *
 * The following steps summarize the implementation of the solution:
 *
 * We initialize a variable mx to 0. mx represents the maximum index that can be reached so far.
 * We iterate through each index i of the nums array using a for loop. The enumerate function is handy here as it gives us both the index i and the value x at that index.
 * During each iteration, we first check if mx is less than i. If this is the case, we return false because it indicates that we can't reach the current index (or any index beyond it).
 * If mx is not less than i, it means we can reach this position, and therefore we update mx to be the maximum of its current value and i + x, where x is the maximum jump length from the current position. The expression max(mx, i + x) efficiently accomplishes this.
 * After the loop, if we never encountered a situation where mx < i, we have been able to reach or surpass each index, including the last index. Thus, we return true.
 * The code or mathematical formulas are encapsulated using backticks, for example, mx = max(mx, i + nums[i]).
 *
 * By always keeping track of the furthest reachable index and bailing out early if we find an unreachable index (mx < i), we ensure an efficient solution with a time complexity that is linear with respect to the length of the nums array.
 *
 * Example Walkthrough
 * Let's walk through a small example using the greedy solution approach outlined above. Suppose we are given the following array nums:
 *
 * nums = [2, 3, 1, 1, 4]
 * Our task is to determine if we can reach the last element, which in this case is 4. We will use the steps of the solution to illustrate how we can arrive at the answer:
 *
 * Initialize mx to 0. This means before we start, the furthest index we can reach is 0.
 *
 * Start iterating:
 *
 * At index 0, the value is 2. We can jump to index 1 or 2. Update mx to max(0, 0 + 2) which is 2.
 * Move to index 1, with a value of 3. Now we can reach as far as 1 + 3 = 4, which is the end of the array. Update mx to max(2, 1 + 3) which is 4.
 * At this point, we can already reach the last element, so we could stop and return true. Nonetheless, for completeness:
 * At index 2, the value is 1. From here, max(4, 2 + 1) is still 4.
 * At index 3, the value is 1. From here, max(4, 3 + 1) is still 4.
 * Throughout the iteration, mx was always greater than or equal to i, so we never returned false.
 *
 * After the loop, since there were no indices that we could not reach, and we were always able to update mx to reach or go beyond the next index, we can return true.
 *
 * So, in our example, it is indeed possible to reach the last element starting from the first element following the greedy approach.
 *
 *
 */
public class Q55_JumpGame {
    public boolean canJump(int[] nums) {
        int maxReachable = 0; // Initialize the maximum reachable index to 0

        // Iterate over each index in the array
        for (int i = 0; i < nums.length; ++i) {
            // If the current index is greater than the maximum reachable index,
            // it means we cannot proceed further, so return false.
            if (maxReachable < i) {
                return false;
            }

            // Update the maximum reachable index if the reachable index
            // from the current position is greater than the previous max.
            maxReachable = Math.max(maxReachable, i + nums[i]);
        }

        // If the loop completes without returning false, it means we can
        // reach the last index, so return true.
        return true;
    }
}
