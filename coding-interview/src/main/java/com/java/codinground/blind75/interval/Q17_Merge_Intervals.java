package com.java.codinground.blind75.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Given an array of intervals where intervals[i] = [starti, endi],
 * merge all overlapping intervals, and return an array of the non-overlapping intervals that cover
 * all the intervals in the input.
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
 *
 */
public class Q17_Merge_Intervals {
    public static void main(String[] args) {
        int[][] intervals =
                new int[][] {
                        {1, 2},
                        {3, 5},
                        {4, 7},
                        {6, 8},
                        {9, 10}
                };
        for (int[] row : mergeOverlappingIntervals(intervals)) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static int[][] mergeOverlappingIntervals(int[][] intervals) {
        //sort the intervals by its starting point which is a[0]
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));

        List<int[]> result = new ArrayList<>();
        result.add(intervals[0]); //adding the first interval in the result, because it has the smallest start
        int resultEndIndex = 0;
        for (int i = 1; i < intervals.length; i++) {

            //Get the last interval detail from the result list
            int[] previousInterval = result.get(resultEndIndex);
            int previousStart = previousInterval[0];
            int previousEnd = previousInterval[1];

            //Get the current interval detail from the running loop counter i
            int[] currentInterval = intervals[i];
            int currentStart = currentInterval[0];
            int currentEnd = currentInterval[1];

            //Condition for the overlap
            if (currentStart <= previousEnd){
                previousEnd = Math.max(previousEnd, currentEnd); // handle the overlap by calculating the interval end
                previousInterval[1] =previousEnd; //extending the previous interval by setting the previous end which is previousInterval[1]
            }
            else { //if the overlapping condition does not satisfy just add the interval in the list
                result.add(currentInterval);
                resultEndIndex++;
            }
        }
        return result.toArray(new int[result.size()][2]);
    }
}
