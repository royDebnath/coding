package com.java.codinground.programs.algoexpert.easy;

import java.util.Arrays;

/**
 * Given an array of integers that are sorted in increasing order,
 * write a function that squares all the integers in the array and returns them in a new sorted array.
 * <p>
 * Approach :
 * <p>
 * Consider we have the input array [-4, -2, 0, 1, 3], after being squared, we got [16, 4, 0, 1, 9],
 * we can realize the smallest possible squared value which could be end up in the output array is 0.
 * The farther the value is away from 0, regardless of whether it is positive or negative,
 * the larger the squared value will become. And the closer the value is to 0,
 * the smaller the squared value. The number line below illustrates this.
 * <p>
 * <--|---|---|---|---|---|---|---|---|-->
 * -5  -4  -3  -2  -1   0   1   2   3
 * 25  16   9   4   1   0   1   4   9
 * What the number line also tells us is the largest value in the output array is
 * either going to come from the smallest element in the input array or the largest element in the input array.
 * Since we know where to find the largest squared value in the input array,
 * we can build the sorted squared values array from largest square value to smallest squared value.
 * We can use two pointers to keep track of the smallest integer and the largest integer in the input array;
 * compare the absolute value of the two integers to find out which integer is larger once squared;
 * square the larger value and put it into the resulting array;
 * move the pointer that points to the larger value either to left if the largest squared value comes from the largest integer or
 * to right if it comes from the smallest integer, so we can find the next largest squared value.
 * Continue until the resulting array is filled up.
 */
public class SortedSquareArray {

    public static void main(String[] args) {
        int[] input = {-5,-4,-3,-2 ,-1,0,1,2,3};
        Arrays.stream(sortedSquared(input)).forEach(System.out::println);

    }

    private static int[] sortedSquared(int[] input) {
        int length = input.length;
        int[] squaredOutput = new int[length];

        int left = 0;
        int right = length - 1;

        int outputIndex = length - 1;

        while (outputIndex >= 0) {
            int leftValue = input[left];
            int rightValue = input[right];
            if (Math.abs(leftValue) > Math.abs(rightValue)) {
                squaredOutput[outputIndex] = leftValue * leftValue;
                left++;
            } else {
                squaredOutput[outputIndex] = rightValue * rightValue;
                right--;
            }
            outputIndex--;
        }
        return squaredOutput;
    }
}
