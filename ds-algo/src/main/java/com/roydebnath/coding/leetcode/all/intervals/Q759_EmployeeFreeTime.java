package com.roydebnath.coding.leetcode.all.intervals;

/**
 *
 * We are given a list schedule of employees, which represents the working time for each employee.
 *
 * Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
 *
 * Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.
 *
 * Example 1:
 *
 * Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
 * Output: [[3,4]]
 * Explanation:
 * There are a total of three employees, and all common
 * free time intervals would be [-inf, 1], [3, 4], [10, inf].
 * We discard any intervals that contain inf as they aren't finite.
 *
 * Example 2:
 *
 * Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
 * Output: [[5,6],[7,9]]
 *
 * Problem Explanation:
 *
 * Given a list of employee work schedules with each employee having a list of non-overlapping intervals representing their working hours, we are tasked with finding the common free time for all employees, or in other words, the times when all employees are not working.
 *
 * The input is a nested list of intervals, each interval as [start, end], with start < end. The intervals are non-overlapping and are already sorted in ascending order. The output should also be a list of sorted intervals.
 *
 * For example, consider schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]. Here, Employee 1 works from 1 to 3 and 6 to 7. Employee 2 works from 2 to 4 and Employee 3 works from 2 to 5 and 9 to 12. The common free time for all employees is [5,6] and [7,9] as these are the intervals when all employees are free.
 *
 * Solution Approach:
 *
 * The proposed solution concatenates all work schedules into a single list, sorts this list and then identifies the gaps between work intervals, which represent the common free time for all employees.
 *
 * First, the solution creates an empty array to store the result. Then, it goes through the work shifts of each employee and combines all the intervals to a single list.
 *
 * Next, this combined list of work intervals is sorted using the start of the work intervals. This will ensure that the work schedules are in chronological order.
 *
 * After that, the solution stores the end time of the first shift in a variable called prevEnd, and then iterates through each work interval.
 *
 * If the start of a work interval is greater than prevEnd, this means there's a gap between the end of the previous work shift and the start of the current work shift. This gap represents a common free time, which is then added to the result list.
 *
 * Finally, prevEnd is updated with the maximum end time of the current work interval or the prevEnd.
 *
 * Through this approach, the algorithm successfully identifies the common free time of all employees.
 *
 *
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 4 * Definition for an interval.
 5 * public class Interval {
 6 *     int start;
 7 *     int end;
 8 *     Interval() { start = 0; end = 0; }
 9 *     Interval(int s, int e) { start = s; end = e; }
 10 * }
 11 */


public class Q759_EmployeeFreeTime {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
                List<Interval> res = new ArrayList<>();
                List<Interval> intervals = new ArrayList<>();
                // Flattening the schedule
                for (List<Interval> employee : schedule)
                     for (Interval interval : employee)
                         intervals.add(interval);
             // Sorting by start of each Interval
             Collections.sort(intervals, (a, b) -> a.start - b.start);
             int end = intervals.get(0).end;
             // Checking for free time between intervals
             for (Interval interval : intervals) {
                     if (interval.start > end)
                           res.add(new Interval(end, interval.start));
                     end = Math.max(end, interval.end);
                 }
                return res;
            }
}
 class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
 }