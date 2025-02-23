package leetcode.all.dynamic_programming;

/**
 *
 * Problem Description
 * In this problem, you take on the role of a professional burglar who is aiming to rob houses lined up along a street. Every house has some money, but there's a twist: the houses are equipped with connected security systems. If you rob two adjacent houses on the same night, the security system will detect the break-ins and alert the police. Your goal is to figure out the maximum amount of money you can steal tonight without triggering any alarms.
 *
 * So, you're presented with an array of integers nums, where nums[i] represents the amount of money stashed in the i-th house. The challenge is to find the optimal combination of houses to rob that will maximize your total loot, keeping in mind that you cannot rob adjacent houses.
 *
 * It's a classic optimization problem that requires you to look for the best strategy to maximize your gains without breaking the constraint (the alarm system in this case).
 *
 * Intuition
 * To get to the heart of this problem, let's discuss the intuition behind the solution.
 *
 * The main idea here is dynamic programming, which involves breaking the problem down into smaller subproblems and building up a solution from the answers to those subproblems.
 *
 * Now, consider the following choices at each house: either you rob it or you don't. If you decide to rob house i, you cannot rob house i-1, but you are free to rob house i-2 and before. If you decide not to rob house i, your best robbery amount up to house i is the same as if you were standing at house i-1.
 *
 * Let's define two variables: f and g. We'll use f to track the maximum amount we can rob up to the current house if we don't rob this house, and g to track the maximum amount if we do rob it.
 *
 * We update f like this: We take the max between the previous f (not robbing the previous house) and g (robbing the previous house) because for the current house, we are not robbing it, so we are free to choose the maximum loot collected from the previous two states.
 *
 * On the other hand, we update g by adding the current house's money to the previous f, because if we decide to rob this house, we can't rob the previous one, so we add to f (the max money robbed without including the previous house).
 *
 * Lastly, we have to return the maximum amount we can rob including or excluding the last house, which means we return the max between f and g.
 *
 * The elegance of this solution lies in its simplicity and the fact that it takes advantage of overlapping subproblems, typical in dynamic programming solutions, and calculates the result in a bottom-up manner.
 *
 * Example Walkthrough
 * To illustrate the solution approach, let's use a small example where the nums array representing money at each house is [2, 7, 9, 3, 1].
 *
 * Here's the step-by-step walkthrough:
 *
 * Initialize f and g to 0. These variables will hold the maximum money that can be robbed so far without and with robbing the current house, respectively.
 *
 * Start iterating over the array nums.
 *
 * For the first house (nums[0] = 2), we can only rob it since there's no previous house. Therefore, f remains 0 as we are not robbing this house, and g is updated to f + nums[0], which is also 2.
 * Move to the second house (nums[1] = 7):
 *
 * Update f to max(f, g), which was from the previous iteration. Here f becomes max(0, 2) so f is now 2.
 * Update g to f + nums[1], which is 2 + 7, so g becomes 9.
 * At the third house (nums[2] = 9):
 *
 * Update f to max(2, 9), so f is now 9.
 * Update g to f (previous f before updating which is 2) + nums[2], which is 2 + 9, so g is 11.
 * At the fourth house (nums[3] = 3):
 *
 * Update f to max(9, 11), so f is now 11.
 * Update g to f (previous f before updating which is 9) + nums[3], which is 9 + 3, so g is 12.
 * Finally, at the fifth house (nums[4] = 1):
 *
 * Update f to max(11, 12), so f is now 12.
 * Update g to f (previous f before updating which is 11) + nums[4], which is 11 + 1, so g becomes 12.
 * The loop has finished, and we now take the maximum of f and g. Both f and g are 12, so the maximum money that can be robbed is 12.
 *
 * If we map out the houses we chose to rob based on g's updates, we robbed the second and fourth houses - a total of 7 + 3 = 10, and not the third and fifth as the walk through suggests. Let's correct the steps for the fourth and fifth houses.
 *
 * For the fourth house, since we can't rob it (because we robbed the third house):
 *
 * f remains 11 since we're not adding the value of the current house.
 * g doesn't change since we can't rob this house directly after the third house.
 * For the fifth house:
 *
 * f remains 11, and
 * g is updated with 11 + 1 (the previous f plus the current house's value), resulting in g being 12.
 * In this case, we robbed the third and fifth houses (2nd house was not actually robbed), getting a total of 9 + 1 = 10. Hence, the final maximum amount we could rob is 12, updating our final g with the amount from the fifth house and maintaining the rule of not robbing two adjacent houses.
 *
 *
 *
 *
 */
public class Q198_HouseRobber {
    public static void main(String[] args) {
        System.out.println(rob(new int[]{1,2,3,1}));
    }
    public static int rob(int[] nums) {
        // If we get invalid input, return 0
        if (nums == null || nums.length == 0) return 0;

        // Keep track of whether we robbed the previous house
        int robbedPrevious = 0;
        int robbedBeforePrevious = 0;

        for (int i = 0; i < nums.length; i++) {

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
