package com.java.codinground.programs.leetcode.top;

/**
 * Medium
 * Topics
 * Companies
 * You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].
 *
 * Each element nums[i] represents the maximum length of a forward jump from index i.
 * In other words, if you are at nums[i], you can jump to any nums[i + j] where:
 *
 * 0 <= j <= nums[i] and
 * i + j < n
 * Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that
 * you can reach nums[n - 1].
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2.
 * Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 *
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 *
 *
 * Complexity
 * Time complexity: O(N)
 * Space complexity: O(1)
 *
 * Solution Approach
 * The implementation of this solution uses the greedy algorithm approach to determine the minimum number
 * of jumps. The greedy algorithm makes the locally optimal choice at each step with the hope of finding
 * the global optimum, and in this problem, it aims to reach the farthest index possible with each jump,
 * thereby minimizing the total number of jumps.
 *
 * The following steps and variables are used to implement this strategy:
 *
 * ans is a counter to keep track of the number of jumps made.
 * mx is a variable that holds the maximum distance that can be reached at the current position.
 * last is a variable that keeps track of the farthest index reached with the last jump.
 * The algorithm works as follows:
 *
 * Initialize ans, mx, and last to 0. These will store the current number of jumps,
 * the maximum reachable index, and the last jumped-to index, respectively.
 * Iterate over the array nums except for the last element, because from the second to last element,
 * you are always one jump away from the end:
 * Update mx to be the maximum of itself and the sum of the current index i and the current element's value
 * nums[i]. This effectively sets mx to the farthest reachable index from the current index.
 * Check if the current index i is equal to last. If true, it means you've reached the maximum index
 * from the previous jump, and you have to jump again.
 * When jumping again (the if condition is satisfied):
 * Increment ans because you've made a jump.
 * Update last to mx, as now you will calculate the reachable distance from this new index.
 * By working through the array with these rules, the ans variable reflects the minimum number of jumps
 * needed when the loop ends. This greedy approach ensures that you make a jump only when necessary,
 * which coincides with reaching the maximum distance from the previous jump.
 *
 * The final result is obtained by returning the value of ans after the loop has finished executing.
 * Since the greedy algorithm here always takes the farthest reaching option, it guarantees that
 * the number of jumps is minimized.
 *
 *
 */
public class Q45_JumpGameII {
    public int jump(int[] nums) {
        if (nums.length==1) return 0;
        int steps = 0;           // Initialize a step counter to keep track of the number of jumps made
        int maxReachable = 0;        // Initialize the maximum reach from the current position
        int lastJumpMaxReach = 0;  // Initialize the maximum reach of the last jump

        // Iterate through the array except for the last element,
        // because we want to reach the last index, not jump beyond it
        for (int i = 0; i < nums.length - 1; ++i) {
            // Update the maximum reach by taking the maximum between the current maxReachable
            // and the position we could reach from the current index (i + nums[i])
            maxReachable = Math.max(maxReachable, i + nums[i]);

            // If the current index reaches the last jump's maximum reach,
            // it means we have to make another jump to proceed further
            if (lastJumpMaxReach == i) {
                steps++;               // Increment the step counter because we're making another jump

                lastJumpMaxReach = maxReachable;  // Update the last jump's max reach to the current maxReachable

                // There's no need to continue if the maximum reach is already beyond the last index,
                // as we are guaranteed to end the loop
                if (maxReachable >= nums.length - 1) {
                    break;
                }
            }
        }

        // Return the minimum number of jumps needed to reach the last index
        return steps;
    }
}
