package com.java.codinground.blind75.interval;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given an array of intervals where intervals[i] = [starti, endi],
 * return the minimum number of intervals you need to remove to make
 * the rest of the intervals non-overlapping.
 *
 * Example 1:
 *
 * Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * Output: 1
 * Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
 * Example 2:
 *
 * Input: intervals = [[1,2],[1,2],[1,2]]
 * Output: 2
 * Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlap
 *
 * Intuition:
 * Minimum number of intervals to remove .
 * Which is nothing but maximum number of intervals we can keep.
 * Then it comes under Maximum Meeting we can attend.
 * In Detail
 * Removing minimum number of intervals is the same as KEEPING maximum number of intervals.
 *
 * Now, if you look at it as scheduling maximum number of meetings in a room, then you should get it.
 * For those unfamiliar with the Meeting scheduling problem, it is similar to this problem here,
 * but each interval [start,end] represents the start and end time of a meeting.
 * As there is only one room and we can have only one meeting at a time,
 * we want to find maximum number of meetings we can schedule
 * (in other words reject minimum number of meetings).
 *
 * Again we sort by end times. Why? Because regardless of when a meeting starts,
 * a meeting that ends first leaves more time for other meetings to take place.
 * We do not want a meeting that starts early and ends late, what we really care about
 * is when the meeting ends and how much time it leaves for the other meetings.
 * So, sort by endtimes, remove all overlapping meetings to get maximum meetings or
 * reject minimum meetings. Same logic applies here in this problem.
 *
 * Explanation:
 * Imagine we have a set of meetings, where each meeting is represented by
 * an interval [start_time, end_time]. The goal is to find the maximum number of
 * non-overlapping meetings we can attend.
 *
 * Sorting by end times (cmp function):
 * The function first sorts the intervals based on their end times in ascending order
 * using the custom comparator cmp. This sorting is crucial because it allows us to
 * prioritize intervals that finish early, giving us more opportunities to accommodate
 * additional meetings later on.
 *
 * Initializing variables:
 * The function initializes two variables, prev and count. The prev variable is used to
 * keep track of the index of the last processed interval, and count is used to store
 * the number of non-overlapping meetings found so far. We start count with 1 because
 * the first interval is considered non-overlapping with itself.
 *
 * Greedy approach:
 * The function uses a greedy approach to find the maximum number of non-overlapping meetings.
 * It iterates through the sorted intervals starting from the second interval (index 1) because
 * we've already counted the first interval as non-overlapping. For each interval at index i,
 * it checks if the start time of the current interval (intervals[i][0]) is greater than or
 * equal to the end time of the previous interval (intervals[prev][1]). If this condition is true,
 * it means the current interval does not overlap with the previous one, and we can safely attend
 * this meeting. In that case, we update prev to the current index i and increment count to reflect
 * that we have attended one more meeting.
 *
 * Return result:
 * Finally, the function returns the number of intervals that need to be removed to make
 * the remaining intervals non-overlapping. Since we want to maximize the number of meetings
 * we can attend, this value is calculated as n - count, where n is the total number of intervals.
 */
public class Q72_NonOverlappingIntervals {
    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1,2},{2,3},{3,4},{1,3}};
        System.out.println(eraseOverlapIntervals(intervals));
    }

    public static int eraseOverlapIntervals(int[][] intervals) {
        int length = intervals.length;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));

        int previousIndex = 0;
        int count = 1;

        for (int i = 1; i < length; i++) {
            int currentStart = intervals[i][0];
            int previousEnd = intervals[previousIndex][1];
            if (currentStart >= previousEnd) { //non-overlapping
                previousIndex = i;
                count++;
            }
        }
        return length - count;
    }
}

