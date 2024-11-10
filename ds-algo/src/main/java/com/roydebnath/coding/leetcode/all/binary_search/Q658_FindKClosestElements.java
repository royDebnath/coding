package com.roydebnath.coding.leetcode.all.binary_search;

import java.util.ArrayList;
import java.util.List;

/**
 * 658. Find K Closest Elements
 * Medium
 * 7.4K
 * 591
 * Companies
 * Given a sorted integer array arr, two integers k and x,
 * return the k closest integers to x in the array.
 * The result should also be sorted in ascending order.
 */
class Q658_FindKClosestElements {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {

        int left = 0, right = arr.length - k;
        while (left < right) {
            int midpoint = left + (right - left) / 2; // same as (left + right) / 2
            if (x - arr[midpoint] > arr[midpoint + k] - x) {
                left = midpoint + 1;
            }
            else {
                right = midpoint;
            }
        }

        List<Integer> result = new ArrayList<>(k);
        for (int i = left; i < left + k; i++) {
            result.add(arr[i]);
        }
        return result;
    }
}