package leetcode.blind75.dynamic_programming;

/**
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed.
 * All houses at this place are arranged in a circle.
 * That means the first house is the neighbor of the last one.
 * Meanwhile, adjacent houses have a security system connected,
 * and it will automatically contact the police if two adjacent houses
 * were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house,
 * return the maximum amount of money you can rob tonight without alerting the police.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
 * Example 2:
 *
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * Example 3:
 *
 * Input: nums = [1,2,3]
 * Output: 3
 *
 *
 */
public class Q49_HouseRobberII {
    public static void main(String[] args) {
        System.out.println(rob(new int[]{1,2,3,1}));
    }

    /**
     * Will re-use the linear house robber solution here.
     *
     * This circular can be divided into two linear problems :
     *
     *1. rob the first house and go on till the last but one house
     *2. rob the second house and go on till the last house
     *
     * Take the max of it.
     *
     */
    public static int rob(int[] nums) {

        // Handle the edge cases
        if (nums == null) return 0;
        int length = nums.length;
        if (length == 1) return nums[0];
        if (length == 2) return Math.max(nums[0],nums[1]);

        //calculate two cases return the max.
        int startWithFirst = robLinear(nums, 0, length - 1);
        int startWithSecond = robLinear(nums, 1, length);
        return Math.max(startWithFirst, startWithSecond);
    }

    private static int robLinear(int[] nums, int start, int end) {

        // Keep track of whether we robbed the previous house
        int robbedPrevious = 0;
        int robbedBeforePrevious = 0;

        for (int i = start; i < end; i++) {

            // If we don't rob the current house,
            // take the max of robbing and not robbing the previous house
            int currentNotRobbed = Math.max(robbedPrevious, robbedBeforePrevious);

            // If we rob the current house,
            // add the current money robbed to what we got from not robbing previous
            int currentIsRobbed = robbedBeforePrevious + nums[i];

            // Update our values for the next iteration
            robbedBeforePrevious = currentNotRobbed;
            robbedPrevious = currentIsRobbed;
        }

        // Return the maximum we could have robbed provided we looked at both options
        return Math.max(robbedPrevious, robbedBeforePrevious);
    }
}
