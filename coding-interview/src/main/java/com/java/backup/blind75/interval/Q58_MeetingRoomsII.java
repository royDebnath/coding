package com.java.backup.blind75.interval;

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
public class Q58_MeetingRoomsII {
    public static void main(String[] args) {
        int[][] meetings = {{0,30},{5,10},{15,20}};
        System.out.println(minMeetingRooms(meetings));
    }

    /**
     * we sort start time and end time in two different arrays
     * initialize the start and end index as 0.
     * if start time < end time, means we have a meeting in active, active++.
     * else active--.
     * We need to record the max number of the active room.
     *
     */
    public static int minMeetingRooms(int[][] intervals) {
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
