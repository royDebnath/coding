package com.java.codinground.programs.leetcode.top100;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must be unique and you may return the result in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2]
 * Example 2:
 *
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [9,4]
 * Explanation: [4,9] is also accepted.
 */
public class IntersectionOfTwoArrays {
    public static void main(String[] args) {


    }
    private static int[] intersection(int[] input1, int[] input2){
            boolean[] map1 = new boolean[1001];
            List<Integer> list = new ArrayList<>();
            for (int num: input1) {
                map1[num] = true;
            }
            for (int num: input2) {
                if(map1[num]) {
                    map1[num] = false; //setting to false to not include duplicate in intersection
                    list.add(num);
                }
            }
            int[] result = new int[list.size()];
            for(int i = 0; i < result.length; i++) {
                result[i] = list.get(i);
            }
            return result;
    }
}
