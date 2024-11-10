package com.roydebnath.coding.leetcode.neetode.intervals;

import java.util.ArrayList;
import java.util.List;

/**
 * 986. Interval List Intersections
 * Medium
 * 5.3K
 * 102
 * Companies
 * You are given two lists of closed intervals, firstList and secondList, where firstList[i] = [starti, endi] and secondList[j] = [startj, endj]. Each list of intervals is pairwise disjoint and in sorted order.
 *
 * Return the intersection of these two interval lists.
 *
 * A closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
 *
 * The intersection of two closed intervals is a set of real numbers that are either empty or represented as a closed interval. For example, the intersection of [1, 3] and [2, 4] is [2, 3].
 *
 * Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
 * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 * Example 2:
 *
 * Input: firstList = [[1,3],[5,9]], secondList = []
 * Output: []
 *
 * Solution :
 *
 * The algorithm to solve this problem is as follows:
 *
 * We’ll use two indices, i and j, to iterate through the intervals in both lists, that is, intervalLista and intervalListb respectively.
 *
 * To check whether there’s any intersecting point among the given intervals:
 *
 * Take the starting times of the first pair of interval from both lists and check which occurs later, storing it in a variable, say start.
 *
 * Also compare the ending times of the same pair of intervals from both lists and store the minimum end time in another variable, say end.
 *
 * Next, we will check if intervalLista[i] and intervalListb[j] overlap by comparing the start and end times.
 *
 * If the times overlap, then the intersecting time interval will be added to the resultant list, that is, intersections.
 *
 * After the comparison, we need to move forward in one of the two input lists. The decision is taken based on which of the two intervals being compared ends earlier. If the interval that ends first is in intervalLista, we move forward in that list, else, we move forward in intervalListb.
 *
 * Solution summary
 * Let’s briefly discuss the approach that we have used to solve the above mentioned problem:
 *
 * Compare the starting and ending times of a given interval from A and B.
 *
 * If the start time of the current interval in A is less than or equal to the end time of the current interval in B, or vice versa, we have found an intersection. Add it to a resultant list.
 *
 * Move forward in the list whose current interval ends earlier and repeat comparison and moving forward steps to find all intersecting intervals.
 *
 * Return the resultant list of intersecting intervals.
 *
 * Time complexity
 * The time complexity is O(n+m)
 * , where n and m  are the number of meetings in intervalLista and intervalListb, respectively.
 */
class Q986_IntervalListIntersections {

	public static void main(String[] args) {
		int[][] firstList = {{0,2},{5,10},{13,23},{24,25}};
	    int [][] secondList = {{1,5},{8,12},{15,24},{25,26}};
		int[][] output = intervalIntersection(firstList, secondList);
		for (int[] interval : output) {
			System.out.print("["+interval[0]+","+interval[1]+"]" + " ");
		}
	}
		public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
			int firstListCounter=0;
			int secondListCounter=0;

			List<int[] > output = new ArrayList<>();

			while(firstListCounter<firstList.length && secondListCounter<secondList.length){

				// 1. start - the potential start point of the intersection
				// 2. end - the potential end point of the intersection

				int start=Math.max(firstList[firstListCounter][0],secondList[secondListCounter][0]);
				int end=Math.min(firstList[firstListCounter][1],secondList[secondListCounter][1]);

				if(start<=end){
					output.add(new int[]{start,end});
				}

				// Move forward in the list whose interval ends earlier
				if(firstList[firstListCounter][1]<secondList[secondListCounter][1]){
					firstListCounter++;
				}else{
					secondListCounter++;
				}
			}
			return output.toArray(new int[output.size()][]);
		}
	}