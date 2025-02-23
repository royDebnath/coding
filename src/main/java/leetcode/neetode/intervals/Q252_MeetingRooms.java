package leetcode.neetode.intervals;

import java.util.Arrays;

/**
 *252. Meeting Rooms
 * Question
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * determine if a person could attend all meetings.
 *
 * Example 1:
 *
 * Input: [[0,30],[5,10],[15,20]]
 * Output: false
 * Example 2:
 *
 * Input: [[7,10],[2,4]]
 * Output: true
 * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */
public class Q252_MeetingRooms {
    public static void main(String[] args) {
        int[][] meetings = {{0, 3}, {5, 10}, {15, 20}};
        int[][] meetings1 = {{0, 30}, {5, 10}, {15, 20}};
        int[][] meetings2 = {{7, 10}, {2, 4}};

        System.out.println("true " + canAttend(meetings));
        System.out.println("false " + canAttend(meetings1));
        System.out.println("true " + canAttend(meetings2));
    }

    public static boolean canAttend(int[][] intervals) {
        //Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0])); //sort the intervals by its starting point which is a[0]
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) {
                return false;
            }
        }
        return true;
    }
}
