package com.java.codinground.leetcode.categories.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 * Example 2:
 *
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 */
public class Q56_MergeIntervals {
    // Method to merge overlapping intervals.
    public int[][] merge(int[][] intervals) {
        // Sort the intervals by their starting times.
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // List that holds the merged intervals.
        List<int[]> mergedIntervals = new ArrayList<>();
        // Add the first interval to the list as starting interval for merging.
        mergedIntervals.add(intervals[0]);

        // Loop through all the intervals starting from the second one.
        for (int i = 1; i < intervals.length; ++i) {
            // Get the start and end times of the current interval.
            int start = intervals[i][0];
            int end = intervals[i][1];

            // Get the last interval in the merged list.
            int[] lastMergedInterval = mergedIntervals.get(mergedIntervals.size() - 1);

            // Check if there is an overlap with the last interval in the merged list.
            if (lastMergedInterval[1] < start) {
                // No overlap, so we can add the current interval as it is.
                mergedIntervals.add(intervals[i]);
            } else {
                // Overlap exists, so we extend the last interval's end time.
                lastMergedInterval[1] = Math.max(lastMergedInterval[1], end);
            }
        }

        // Convert the merged intervals list to a 2D array and return it.
        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }
}
