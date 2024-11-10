package com.roydebnath.coding.leetcode.neetode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Problem Description
 * The problem provides an array named temperatures, which represents the daily temperatures recorded. The goal is to find out how many days one would have to wait for a warmer temperature than the current day. To solve this problem, we need to construct an array called answer where answer[i] represents the number of days you would have to wait after the i-th day to experience a warmer temperature. If there is no such day in the future where the temperature is warmer, then answer[i] should be set to 0.
 *
 * For example, given temperatures = [73, 74, 75, 71, 69, 72, 76, 73], the output should be [1, 1, 4, 2, 1, 1, 0, 0]. This means that on day 0 with a temperature of 73, you have to wait 1 day to get a temperature higher than 73, which occurs on day 1 with a temperature of 74. By the end of the array, the temperatures are not followed by any warmer temperatures, hence the 0s.
 *
 * Intuition
 * The intuition behind the solution is to use a stack that helps to track the temperatures and indexes. We traverse the temperatures from left to right, and for each day's temperature, we check whether it is higher than the temperature at the indexes recorded on the stack. If so, this means we have found a day with a warmer temperature for the days corresponding to those indexes. Therefore, for each such index, j, we can update answer[j] to the current day's index minus j, indicating the number of days that had to be waited.
 *
 * The stack keeps track of indexes with temperatures that we haven't yet found a warmer day for. This is an effective approach because the temperatures are processed in order and the stack ensures we only compare temperatures where the future warmer temperature hasn't been found yet. When a warmer temperature is encountered, it is the immediate next warmer temperature for all temperatures currently in the stack. Once updated, we no longer need to consider those days because their next warmer temperature has been determined.
 *
 * In cases where there are no warmer temperatures in the future, the answer will remain 0 by default, as established at the start of the solution.
 *
 * Overall, the idea is to push the index of the current temperature to the stack if it cannot find a higher temperature immediately. Subsequently, with each new day's temperature, we compare it against the peak temperatures on the stack until we find one that is lower or until the stack is empty. This process efficiently calculates the number of days to wait for each day, leading to the final answer array when we have processed all temperatures.
 *
 * Solution Approach
 * The implementation of the solution uses a stack data structure to keep track of the indices of days that have not yet found a warmer future temperature. The stack will help us maintain the order in which we need to find the next warmer temperature while iterating through the temperatures only once, which achieves a time complexity of O(n), where n is the number of days.
 *
 * Here is a step-wise breakdown of the algorithm used in the solution:
 *
 * Initialize an answer array ans with the same length as the temperatures array, filled with zeros. This array will hold the final number of days one has to wait for a warmer temperature.
 *
 * Create an empty stack stk that will store indices of the temperatures array.
 *
 * Iterate through the temperatures array using an index i and temperature t.
 *
 * While there are indices on the stack and the current temperature t is greater than the temperature at the top index of the stack (i.e., temperatures[stk[-1]] < t), pop the index j from the top of the stack. This indicates that we have found a warmer day for the day at index j.
 *
 * Calculate the number of days waited for index j by subtracting j from the current index i (i.e., ans[j] = i - j). This gives us the number of days that had to pass to reach a warmer temperature.
 *
 * Continue popping from the stack and updating the ans array until the stack is empty or a day with a lower temperature is found.
 *
 * If the current day's temperature isn't higher than the temperature at the top index of the stack, or if the stack is empty, push the current index i onto the stack. This signifies that we are still looking for a future warmer temperature for this day.
 *
 * Once we exit the loop, we have filled out the ans array with the number of days to wait for a warmer temperature after each day. In cases where we do not find a warmer temperature, the default value of 0 remains.
 *
 * This approach utilizes a monotonic stack in which, instead of storing the values, we store indices and ensure that the temperatures related to these indices are in ascending order. A monotonic stack is helpful when dealing with problems where we need to know the next greater or smaller element in an array.
 *
 * The beauty of this algorithm lies in its efficient use of the stack to find the next greater element and its ability to accomplish this in a single pass through the temperatures array, hence having a linear time complexity relative to the input size.
 *
 * Example Walkthrough
 * Let's follow the solution approach using a small example with the temperatures array [71, 72, 70, 76, 69].
 *
 * We start by initializing an answer array, ans, of the same length as temperatures, filled with zeros: [0, 0, 0, 0, 0].
 *
 * We also create an empty stack stk to keep track of indices where we haven't found a warmer temperature yet.
 *
 * Now let's iterate through the temperatures array:
 *
 * Day 0: t = 71
 *
 * The stack stk is empty, so we push the index 0 onto stk.
 * Day 1: t = 72
 *
 * The top index on stk is 0 and temperatures[0] < 72, so we pop 0 from stk and update ans[0] = 1 - 0 = 1. We then push the index 1 onto stk.
 * Day 2: t = 70
 *
 * The top index on stk is 1 and temperatures[1] > 70, so we don't pop anything from stk. Push the index 2 onto stk.
 * Day 3: t = 76
 *
 * The top index on stk is 2 and temperatures[2] < 76, so we pop 2 from stk and update ans[2] = 3 - 2 = 1.
 * Next, we check the new top index which is 1. Since temperatures[1] < 76, we pop 1 from stk and update ans[1] = 3 - 1 = 2. There are no more indices on stk, so we push the current index 3 onto stk.
 * Day 4: t = 69
 *
 * The top index on stk is 3 and temperatures[3] > 69, so we don't pop anything from stk. Push the index 4 onto stk.
 * After the iteration, we have the ans array updated as [1, 2, 1, 0, 0], which means:
 *
 * On Day 0, you have to wait 1 day to get a higher temperature on Day 1 (71 to 72).
 * On Day 1, you have to wait 2 days to get a higher temperature on Day 3 (72 to 76).
 * On Day 2, you have to wait 1 day to get a higher temperature on Day 3 (70 to 76).
 * Day 3 and Day 4 are followed by no warmer temperatures, so their values remain 0.
 * This walkthrough has provided a step-by-step execution of the solution approach, showcasing how we use a stack to efficiently calculate the number of days one would have to wait for a warmer temperature.
 *
 */
public class Q739_DailyTemperatures {
    // This method returns an array that contains the number of days you would
    // have to wait until a warmer temperature for each day represented in 'temperatures'.
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length; // Total number of days
        int[] result = new int[n]; // Initialize the result array with the same length as temperatures
        Deque<Integer> stack = new ArrayDeque<>(); // Use a stack to keep track of indices

        // Iterate through each day in temperatures
        for (int currentIndex = 0; currentIndex < n; ++currentIndex) {
            // While the stack is not empty and the current temperature is greater
            // than the temperature at the top index of the stack
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[currentIndex]) {
                int prevIndex = stack.pop(); // Get the index from the top of the stack
                result[prevIndex] = currentIndex - prevIndex; // Calculate the number of days and update result
            }
            // Push current index onto the stack
            stack.push(currentIndex);
        }
        // At the end, result array contains the answer
        return result;
    }
}
