package com.java.backup.blind75.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given an array of non-overlapping intervals intervals[] where
 * intervals[i] = [starti, endi] represent the start and the end of the ith interval
 * and intervals is sorted in ascending order by starti.
 * You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
 *
 * Insert newInterval into intervals such that intervals is still sorted in ascending order
 * by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
 *
 * Return intervals after the insertion.
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * Example 2:
 *
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 *
 */
public class Q18_InsertInterval {
    public static void main(String[] args) {

        int[][] intervals = {{1,2},{3,5},{6,7},{8,10},{12,16}};
        int[] newInterval = {4,8};
        int[][] intervals1 = {{1,3},{6,9}};
        int[] newInterval1 = {2,5};
        for (int[] row : insertInterval(intervals1, newInterval1)) {
            System.out.println(Arrays.toString(row));
        }
        for (int[] row : insert(intervals, newInterval)) {
            System.out.println(Arrays.toString(row));
        }
    }
    public static int[][] insert(int[][] intervals, int[] newInterval) {

        List<int[]> result = new ArrayList<>();

        // Iterate through all slots
        for(int[] slot : intervals)
        {

            /**
             * whichever is smaller, slot or newInterval will add it to the result.
             * if adding newInterval, will make the slot as newInterval
             */
            // if newInterval before slot insert newInterval & update slot as new interval
            if(newInterval[1] < slot[0])
            {
                result.add(newInterval);
                newInterval = slot;
            }

            // if slot is lesser than new Interval insert slot
            else if(slot[1] < newInterval[0])
            {
                result.add(slot);
            }

            // if above conditions fail its an overlap since possibility of new interval existing in left & right of slot is checked
            // update lowest of start & highest of end & not insert
            else {
                newInterval[0] = Math.min(newInterval[0],slot[0]);
                newInterval[1] = Math.max(newInterval[1],slot[1]);
            }
        }

        // insert the last newInterval
        result.add(newInterval);

        // convert to int[][] array
        return result.toArray(new int[result.size()][]);
    }

    public static int[][] insertInterval(int[][] intervals, int[] newInterval) {
        // Read the starting and ending time of the new interval into separate variables
        int newStart = newInterval[0];
        int newEnd = newInterval[1];

        // Initialize variables to help in iterating over the existing intervals list
        int i = 0;
        int length = intervals.length;

        // Initialize an empty list to store the output
        List<int[]> output = new ArrayList<>();

        // Append all intervals that start before the new interval to the output list
        while (i < length && intervals[i][0] < newStart) {
                output.add(intervals[i]);
            i++;
        }

        int lastIndex = output.size() - 1;
        // just append the new interval to the output list.
        if (output.size() == 0) { // no interval that started before newInterval, so newInterval is the first interval. add it.
            output.add(newInterval);
        } else {
            if (output.get(lastIndex)[1] < newStart) { // If the new interval starts after the end of the last interval appended to the output list
                output.add(newInterval);
            } else { // Otherwise, overlap, merge the two intervals
                output.get(lastIndex)[1] = Math.max(output.get(lastIndex)[1], newEnd);
            }
        }


        lastIndex = output.size() - 1; // calculate again as output size has changed
        // Copy any remaining intervals to the output list,
        // similarly merging any overlapping intervals as we go
        while (i < length) {
            int[] slot = intervals[i];
            int start = slot[0];
            int end = slot[1];
            if (output.get(lastIndex)[1] < start) {
                output.add(slot);
            } else {
                output.get(lastIndex)[1] = Math.max(output.get(lastIndex)[1], end);
            }
            i++;
        }
        return output.toArray(new int[output.size()][]);
    }
}
