package leetcode.neetode.fast_slow_pointers;

import java.util.Arrays;

/**
 *
 * ou are playing a game involving a circular array of non-zero integers nums. Each nums[i] denotes the number of indices forward/backward you must move if you are located at index i:
 *
 * If nums[i] is positive, move nums[i] steps forward, and
 * If nums[i] is negative, move nums[i] steps backward.
 * Since the array is circular, you may assume that moving forward from the last element puts you on the first element, and moving backwards from the first element puts you on the last element.
 *
 * A cycle in the array consists of a sequence of indices seq of length k where:
 *
 * Following the movement rules above results in the repeating index sequence seq[0] -> seq[1] -> ... -> seq[k - 1] -> seq[0] -> ...
 * Every nums[seq[j]] is either all positive or all negative.
 * k > 1
 * Return true if there is a cycle in nums, or false otherwise.
 *
 * Input: nums = [2,-1,1,2,2]
 * Output: true
 * Explanation: The graph shows how the indices are connected. White nodes are jumping forward, while red is jumping backward.
 * We can see the cycle 0 --> 2 --> 3 --> 0 --> ..., and all of its nodes are white (jumping in the same direction).
 *
 *
 * Input: nums = [-1,-2,-3,-4,-5,6]
 * Output: false
 * Explanation: The graph shows how the indices are connected. White nodes are jumping forward, while red is jumping backward.
 * The only cycle is of size 1, so we return false.
 *
 * Input: nums = [1,-1,5,1,4]
 * Output: true
 * Explanation: The graph shows how the indices are connected. White nodes are jumping forward, while red is jumping backward.
 * We can see the cycle 0 --> 1 --> 0 --> ..., and while it is of size > 1, it has a node jumping forward and a node jumping backward, so it is not a cycle.
 * We can see the cycle 3 --> 4 --> 3 --> ..., and all of its nodes are white (jumping in the same direction).
 *
 *
 * Statement
 * An input array, nums containing non-zero integers, is given, where the value at each index represents
 * the number of places to skip forward (if the value is positive) or backward (if the value is negative).
 * When skipping forward or backward, wrap around if you reach either end of the array.
 * For this reason, we are calling it a circular array. Determine if this circular array has a cycle.
 * A cycle is a sequence of indices in the circular array characterized by the following:
 *
 * The same set of indices is repeated when the sequence is traversed in accordance with the aforementioned rules.
 *
 * 1. The length of the sequence is at least two.
 * 2. The loop must be in a single direction, forward or backward.
 * 3. It should be noted that a cycle in the array does not have to originate at the beginning.
 *    A cycle can begin from any point in the array.
 *
 *Solution :
 *
 * The circular array loop can be found efficiently using no extra memory with the fast and slow pointers technique.
 * The algorithm uses fast and slow pointers that move through the array following the values at the array indexes.
 * We move the fast pointer twice as fast as the slow pointer. If the pointers reach the same index, a cycle is detected.
 * This is because the fast pointer covers twice the distance as the slow pointer does in each iteration,
 * so the fast pointer guarantees to meet the slow pointer if the cycle exists.
 * If the loop is found, we’ll return TRUE.
 *
 * If the values at the array indexes of the slow and fast pointers have different signs, i.e.,
 * one pointer is pointing to a positive value, and the other is pointing to a negative value,
 * the loop cannot exist. Additionally, if moving a pointer takes it to the current position again,
 * it forms a loop with one element. Since we are not considering it, we will skip it.
 * If any of these conditions are TRUE, we’ll move to the next element.
 *
 */
public class Q457_CircularArrayLoop {

    public static boolean circularArrayLoop(int[] nums) {
        int size = nums.length;

        // Iterate through each index of the array 'nums'.
        for (int i = 0; i < size; i++) {
            // Set slow and fast pointer at current index value.
            int slow = i, fast = i;

            // Set true in 'forward' if element is positive, set false otherwise.
            boolean currentDirection = nums[i] > 0;

            while (true) {
                // Move slow pointer to one step.
                slow = nextStep(slow, nums[slow], size);

                // If cycle is not possible, break the loop and start from next element.
                if (isNotCycle(nums, currentDirection, slow))
                    break;

                // First move of fast pointer.
                fast = nextStep(fast, nums[fast], size);
                // If cycle is not possible, break the loop and start from next element.
                if (isNotCycle(nums, currentDirection, fast))
                    break;

                // Second move of fast pointer.
                fast = nextStep(fast, nums[fast], size);
                // If cycle is not possible, break the loop and start from next element.
                if (isNotCycle(nums, currentDirection, fast))
                    break;

                // At any point, if fast and slow pointers meet each other,
                // it indicates that loop has been found, return true.
                if (slow == fast)
                    return true;
            }
        }

        return false;
    }

    // A function to calculate the next step
    public static int nextStep(int pointer, int value, int size) {
        int result = (pointer + value) % size;
        if (result < 0)
            result += size;
        return result;
    }

    // A function to detect a cycle doesn't exist
    public static boolean isNotCycle(int[] nums, boolean prevDirection, int pointer) {
        // Set current direction to true if current element is positive, set false otherwise.
        boolean currDirection = nums[pointer] >= 0;

        // If current direction and previous direction are different or moving a pointer takes back to the same value,
        // then the cycle is not possible, we return true, otherwise return false.
        if (prevDirection != currDirection || // rule no 2
                Math.abs(nums[pointer] % nums.length) == 0) { // rule no 1
            return true;
        }

        return false;
    }
    // Driver code
    public static void main(String[] args) {
        int[][] input = {
                {-2, -3, -9},
                {-5, -4, -3, -2, -1},
                {-1, -2, -3, -4, -5},
                {2, 1, -1, -2},
                {-1, -2, -3, -4, -5, 6},
                {1, 2, -3, 3, 4, 7, 1},
                {2, 2, 2, 7, 2, -1, 2, -1, -1}
        };

        for (int i = 0; i < input.length; i++) {
            System.out.println((i + 1) + ".\tCircular array = " + Arrays.toString(input[i]) + "\n");
            boolean result = circularArrayLoop(input[i]);
            System.out.println("\tFound Loop = " + result);
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}