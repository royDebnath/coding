package com.java.codinground.programs.algoexpert.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * I am given two non-empty arrays of positive integers.
 * The first array is going to represent the heights of students wearing red shirts and
 * the second array is going to represent the heights of students wearing blue shirts.
 * The two arrays will always have the same length. I am asked to write a function
 * that is going to find out if we can take a photo of these students
 * that adheres to the following constraints:
 *
 * All the students that are wearing red shirts must be in the same row;
 * All of the students that are wearing blue shirts must be in the same row;
 * The photo must have exactly two rows and the two rows must have the same number of students in them.
 * Every student in the front row must be shorter than the student directly behind them in the back row.
 * The function is going to arrange the students and return true if we can take a photo that follows these constraints;
 * otherwise return false.
 */
public class ClassPhotos {
    public static void main(String[] args) {
        List<Integer> redShirtHeights = new ArrayList<Integer>(Arrays.asList(5, 8, 1, 3, 4));
        List<Integer> blueShirtHeights = new ArrayList<Integer>(Arrays.asList(6, 9, 2, 4, 5));
        System.out.println(classPhotos(redShirtHeights, blueShirtHeights));
    }

    private static boolean classPhotos(List<Integer> redShirtHeights, List<Integer> blueShirtHeights) {
        int rowLength = redShirtHeights.size();
        Collections.sort(redShirtHeights);
        Collections.sort(blueShirtHeights);
        Collections.reverse(redShirtHeights);
        Collections.reverse(blueShirtHeights);

        List<Integer> backRow;
        List<Integer> frontRow;
        if (redShirtHeights.get(0).equals(blueShirtHeights.get(0))) {
            return false;
        }
        if (redShirtHeights.get(0) > blueShirtHeights.get(0)) {
            backRow = redShirtHeights;
            frontRow = blueShirtHeights;
        } else {
            backRow = blueShirtHeights;
            frontRow = redShirtHeights;
        }
        for (int i = 0; i < rowLength; i++) {
            if (backRow.get(i) <= frontRow.get(i)) {
                return false;
            }
        }
        return true;
    }
}
