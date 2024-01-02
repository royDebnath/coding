package com.java.codinground.practice;

import java.util.*;


public class Practice {
    public static void main(String[] args) {
        int[][] intervals =
                new int[][] {
                        {1, 2},
                        {3, 5},
                        {6, 7},
                        {6, 8},
                        {9, 10}
                };
        for (int[] row : mergeOverlappingIntervals(intervals)) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static int[][] mergeOverlappingIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));
        List<int[]> result = new ArrayList<>();
        result.add(intervals[0]);
        int previousIndex=0;
        for (int i = 1; i < intervals.length; i++) {
            int[] currentInterval = intervals[i];
            int currentStart = currentInterval[0];
            int currentEnd = currentInterval[1];


            int[] previousInterval = result.get(previousIndex);
            int previousStart= previousInterval[0];
            int previousEnd= previousInterval[1];

            if (currentStart<=previousEnd){
                previousEnd=Math.max(previousEnd, currentEnd);
                previousInterval[1]=previousEnd;
            }
            else {
                result.add(currentInterval);
                previousIndex++;
            }

        }

        return result.toArray(new int[result.size()][2]);
    }


}