package com.java.codinground.programs.leetcode;

/**
 * Input: nums = [3,4,5,1,2]
 * Output: 1
 */
public class FindMinimumRotatedSortedArray {
    public static void main(String[] args) {
        int[] input = {1,2};
        System.out.println(findThroughBinarySearch(input));
    }

    private static int findThroughBinarySearch(int[] input) {
        int left = 0;
        int right = input.length-1;
        while (left<right){
            int middle = left + (right-left)/2;
            if (middle>0 && input[middle]<input[middle-1]){
                return input[middle];
            }
            else if (input[middle]>=input[left] && input[middle]>input[right]){ //means left side is sorted. discrepancy/min element is on the right side
                left = middle+1;
            }
            else {
                right = middle-1;
            }
        }
        return input[left]; //this condition is when left==right, but right is not returned as in case of left=0, right might evaluate to -1 in line 25
    }
}
