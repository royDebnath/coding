package leetcode.all.arrays_and_hashing;

/**
 * Problem Description
 *
 * Given an integer array nums,
 * return true if there exists a triple of indices (i, j, k) such that
 *
 * i < j < k and
 * nums[i] < nums[j] < nums[k].
 *
 * If no such indices exists, return false.
 *
 * Intuition
 * To arrive at the solution efficiently, instead of looking for the whole triplet right away,
 * we can keep track of the smallest number we have encountered so far (mi) and
 * a middle number that is greater than mi but smaller than any potential third number (mid).
 *
 * As we iterate through the array, we can update these two numbers whenever possible.
 * The idea here is to maintain the lowest possible values for mi and mid as we move forward,
 * giving us the best chance to find a number that would be greater than both, thus forming our triplet.
 *
 * If the current number (num) is less than or equal to mi, it becomes the new mi
 * because we're always interested in the smallest starting point of the potential triplet.
 * If num is greater than mi but less than mid, we have found a possible middle part of our triplet,
 * so we set mid to this new number.
 *
 * If num is greater than mid, this means we have successfully found a triplet
 * (because num is greater than both mi and mid, which also implies that mi is less than mid),
 * and we can return true.
 *
 * This efficient approach uses a greedy-like method to continuously look for the most optimal
 * mi and mid with the hope of finding a third number that could fit the sequence.
 * It does so using a linear scan and constant extra space,
 * without the need for sorting or extra arrays.
 *
 * Solution Approach
 * The Reference Solution Approach begins with the creation of two variables:
 * mi and mid, both initialized to inf (infinity). Here's the reasoning for each line of code:
 *
 * mi and mid serve as placeholders to store the smallest and middle elements
 * of the potential increasing triplet subsequence.
 * By initializing them to infinity, it ensures that any number in the array will be smaller,
 * allowing for proper updating of these variables.
 * The main algorithm unfolds within a single pass through the array of numbers (nums):
 *
 * A for loop iterates through the nums array.
 * For each num in nums, there is a series of checks and updates:
 *
 * if num > mid: This is the condition that tells us we have found a valid triplet.
 * If the current num is greater than our mid value, then we already have a mi which is less than mid,
 * and hence, we have found a sequence where mi < mid < num. We return True immediately.
 *
 * if num <= mi: If the current num is smaller than or equal to the current smallest value mi,
 * it means that we can potentially start a new triplet sequence with this num as the smallest element,
 * thus we update mi with the value of num.
 *
 * else: If the current num is greater than mi and not greater than mid,
 * it fits between the mi and mid, so we update the mid to be num since it could potentially be
 * the middle element of a valid triplet.
 *
 * As the for loop continues, mi and mid are updated accordingly until
 * either a valid triplet is found—causing the function to return True—or
 * the algorithm completes the array iteration without finding a triplet,
 * resulting in a return value of False.
 *
 * It's important to note that the code uses a greedy approach to always maintain the smallest possible
 * values for mi and mid as it iterates over nums.
 * By consistently updating these with the smallest possible values at each step,
 * it optimizes the chance of finding a valid triplet later in the array.
 * No additional data structures are necessary,
 * making this solution notably efficient with O(n) time complexity
 * (due to single iteration through the array) and
 * O(1) space complexity (using only two extra variables).
 *
 * Example Walkthrough
 * Let's illustrate the solution approach using a small example
 * where our input nums array is [1, 2, 3, 4, 5].
 *
 * We initialize mi and mid to infinity. Current state: mi = inf, mid = inf.
 * We iterate over the array:
 *
 * We compare the first element, 1, with mid. Since 1 < inf, we cannot form a triplet yet,
 * but 1 becomes our new mi. Updated state: mi = 1, mid = inf.
 *
 * We move to the next element, 2. Now, 2 is greater than mi but still less than mid.
 * So, 2 becomes our new mid. Updated state: mi = 1, mid = 2.
 *
 * Next element is 3. It is greater than both mi and mid.
 * We now have a valid triplet: 1 < 2 < 3. Hence, according to our solution approach, we return True.
 * There is no need to check the remaining elements (4 and 5)
 * because we have successfully found an increasing triplet.
 * In this example, the approach quickly identifies the increasing sequence with the satisfaction of
 * the conditions outlined in the problem description.
 * The algorithm efficiently updates mi and mid and only stops when it confirms the existence of the
 * increasing triplet.
 *
 */
public class Q334_IncreasingTripletSubsequence {
    // Method to check if there exists an increasing triplet in the array.
    public boolean increasingTriplet(int[] nums) {
        // Initialize two variables to hold the smallest and the middle value found so far.
        int smallest = Integer.MAX_VALUE;
        int middle = Integer.MAX_VALUE;

        // Iterate over each number in the array.
        for (int num : nums) {
            // If the current number is greater than the middle value found,
            // an increasing triplet sequence exists.
            if (num > middle) {
                return true;
            }

            // If the current number is the smallest we've seen so far,
            // we update the smallest value.
            if (num <= smallest) {
                smallest = num;
            } else {
                // Otherwise, if it's between the smallest and the middle value,
                // we update the middle value.
                middle = num;
            }
        }

        // If we did not return true within the loop, then no increasing
        // triplet sequence was found.
        return false;
    }
}
