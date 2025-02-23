package leetcode.neetode.intervals;


import java.util.Arrays;

/**
 * 253. Meeting Rooms II
 * Given an array of meeting time intervals consisting of start and end times
 * [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
 *
 * Example 1:
 *
 * Input: [[0, 30],[5, 10],[15, 20]]
 * Output: 2
 * Example 2:
 *
 * Input: [[7,10],[2,4]]
 * Output: 1
 */

public class Q253_MeetingRoomsII {
    public static void main(String[] args) {
        int[][] meetings = {{0,30},{5,10},{15,20}};
        int[][] meetings2 = {{7,10},{2,4}};
        int[][] meetings3 = {{1,2},{2,3},{3,4},{1,3}};
        int[][] meetings4 = {{1,2},{1,2},{1,2}};

        System.out.println(minMeetingRooms(meetings4));
        System.out.println(minMeetingRooms1(meetings4));
    }

    /**
     * sort the intervals by end time (a,b) -> a[1] - b[1]
     * because we want meetings to end fast to make room for other meetings
     * if there is overlap of current interval with previous increment requiredMeetingRooms count.
     * overlap is decided by logic currentStart < previousEnd
     * once there is overlap update previousEnd using Math.max(currentEnd, previousEnd)
     *
     *
     * @param intervals
     * @return
     */
    public static int minMeetingRooms(int[][] intervals) {
        int length = intervals.length;
        Arrays.sort(intervals, (a,b) -> a[1]-b[1]);
        int previousEnd = intervals[0][1];
        int requiredMeetingRooms = 1;

        for (int i = 1; i < length; i++) {
            int currentStart = intervals[i][0];
            int currentEnd = intervals[i][1];
            if (currentStart < previousEnd) { //overlapping
                requiredMeetingRooms++;
                previousEnd = Math.max(previousEnd, currentEnd);
            }
        }
        return requiredMeetingRooms;
    }

    /**
     * we sort start time and end time in two different arrays
     * initialize the start and end index as 0.
     * if start time < end time, means we have a meeting in active, active++.
     * else active--.
     * We need to record the max number of the active room.
     *
     */
    public static int minMeetingRooms1(int[][] intervals) {
        int len = intervals.length;
        int[] startTimes = new int[len];
        int[] endTimes = new int[len];
        int index = 0;
        for(int i=0;i<len;i++) {
            startTimes[i] = intervals[i][0];
            endTimes[i] = intervals[i][1];
        }
        Arrays.sort(startTimes);
        Arrays.sort(endTimes);
        int startCounter = 0, endCounter = 0;
        int active = 0, maxActive = 1;
        while (startCounter < len && endCounter < len) {
            if (startTimes[startCounter] < endTimes[endCounter]) {
                active++;
                startCounter++;
            } else {
                active--;
                endCounter++;
            }
            maxActive = Math.max(maxActive, active);
        }
        return maxActive;
    }
}
