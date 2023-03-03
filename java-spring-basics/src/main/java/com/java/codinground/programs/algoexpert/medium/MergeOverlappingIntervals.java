package com.java.codinground.programs.algoexpert.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Write a function that takes in a non-empty array of arbitrary intervals,
 * merges any overlapping intervals, and returns the new intervals in no particular order.
 * Each interval *interval* is an array of two integers,
 * with interval[0] as the start of the interval and interval[1] as the end of the interval.
 * Note that back-to-back intervals aren't considered to be overlapping.
 * For example, [1, 5] and [6, 7] aren't overlapping; however, [1, 6] and [6, 7] are indeed overlapping.
 * Also note that the start of any particular interval will always be less than or equal to the end of that interval.
 *
 * Sample Input
 * intervals = [[1, 2], [3, 5], [4, 7], [6, 8], [9, 10]]
 *
 * Sample Output
 * [[1, 2], [3, 8], [9, 10]]
 * // Merge the intervals [3, 5], [4, 7], and [6, 8].
 * // The intervals could be ordered differently.
 */
public class MergeOverlappingIntervals {
    public static void main(String[] args) {
        int[][] intervals =
                new int[][] {
                        {1, 2},
                        {3, 5},
                        {4, 7},
                        {6, 8},
                        {9, 10}
                };
        mergeOverlappingIntervals(intervals);
    }

    private static int[][] mergeOverlappingIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0])); //sort the intervals by its starting point which is a[0]
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
