package leetcode.all.arrays_and_hashing;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem Description
 * In this problem, you have two integers lower and upper representing the inclusive range of numbers.
 * Also, you have an array nums which is sorted and contains unique integers that are within the given range.
 * Some numbers within the range [lower, upper] might not be present in the nums array,
 * and these numbers are considered "missing." The goal is to find the smallest sorted list of ranges,
 * where each range covers the missing numbers without overlapping with any of the numbers in nums.
 * The resulting ranges should together cover exactly all the missing numbers without any gaps or duplicates.
 *
 * For example, if the lower is 0, upper is 99, and nums is [7, 28, 63],
 * then the missing numbers are from 0 to 6, 8 to 27, and 29 to 62, and 64 to 99.
 * You need to return these missing ranges in a sorted manner which could look like
 * [[0, 6], [8, 27], [29, 62], [64, 99]].
 *
 * Intuition
 * The solution involves a direct simulation of the problem by iterating through the elements in
 * the array nums and identifying the gaps between the consecutive numbers as well as the gaps
 * between the lower, upper limits, and the first and last number in nums.
 *
 * Firstly, if the nums array is empty, the missing range is simply from lower to upper.
 * However, if the array is not empty, we check for the following:
 *
 * Is there a gap between lower and the first element of the nums? If so, this forms a range that needs to be added to the answer.
 * We then iterate over the given array, checking the difference between each pair of consecutive numbers. If the difference is more than 1, we've found a gap (missing numbers) and thus a range. These gaps are then made into ranges and added to the answer.
 * Lastly, we check for a potential gap between the last element of the nums and upper.
 * By handling both ends and the gaps in between, we're able to construct a list of ranges that covers all the missing numbers within the [lower, upper] range without including any number from nums.
 */
public class Q163_MissingRanges {
    /**
     * Finds the missing ranges between the given array elements and the specified lower and upper bounds.
     *
     * @param nums  The array of integers where missing ranges are to be found.
     * @param lower The lower bound of the range to find missing elements for.
     * @param upper The upper bound of the range to find missing elements for.
     * @return A list of lists containing the start and end of each missing range.
     */
    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
        // Initialize the length of the nums array.
        int n = nums.length;

        // Handle the edge case where the input array is empty.
        if (n == 0) {
            // The entire range from lower to upper is missing.
            return List.of(List.of(lower, upper));
        }

        // This list will store the missing ranges.
        List<List<Integer>> missingRanges = new ArrayList<>();

        // Check if there is a missing range before the first element.
        if (nums[0] > lower) {
            // Add the range from lower to the element before the first number in the array.
            missingRanges.add(List.of(lower, nums[0] - 1));
        }

        // Loop over the array to find missing ranges between the numbers.
        for (int i = 1; i < n; ++i) {
            // Check if the current element and the previous element are not consecutive.
            if (nums[i] - nums[i - 1] > 1) {
                // Add the range from the element after the previous number to the element before the current number.
                missingRanges.add(List.of(nums[i - 1] + 1, nums[i] - 1));
            }
        }

        // Check if there is a missing range after the last element.
        if (nums[n - 1] < upper) {
            // Add the range from the element after the last number in the array to the upper bound.
            missingRanges.add(List.of(nums[n - 1] + 1, upper));
        }

        // Return the list of missing ranges.
        return missingRanges;
    }
}
