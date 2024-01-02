package com.java.codinground.programs.algoexpert.easy;

import java.util.Arrays;

/**
 * Given an array that contains at least three integers,
 * I am asked to write a function that is going to find the three largest numbers
 * in the input array without sorting and return them in an array sorted in ascending order.
 */
public class FindThreeLargestNumbers {

    public static void main(String[] args) {
        int[] threeLargestNumbers = findThreeLargestNumbersBySorting(new int[]{141, 1, 17, -7, -17, -27, 18, 541, 8, 7, 7});
        int[] threeLargestNumbers1 = findThreeLargestNumbers(new int[]{141, 1, 17, -7, -17, -27, 18, 541, 8, 7, 7});

        for (int threeLargestNumber : threeLargestNumbers) {
            System.out.println(threeLargestNumber);
        }

        for (int threeLargestNumber : threeLargestNumbers1) {
            System.out.println(threeLargestNumber);
        }
    }

    private static int[] findThreeLargestNumbers(int[] ints) {
        int[] result = {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
        for (int element : ints) {
            if (element>result[2]){
                shiftAndUpdate(result, element, 2);
            }
            else if (element>result[1]){
                shiftAndUpdate(result, element, 1);
            }
            else if (element>result[0]){
                shiftAndUpdate(result,element,0);
            }
        }
        return result;
    }

    private static void shiftAndUpdate(int[] result, int element, int index) {
        for (int i = 0; i <=index; i++) {
            if (i==index){
                result[i]=element;
            }
            else {
                result[i]=result[i+1];
            }
        }
    }


    private static int[] findThreeLargestNumbersBySorting(int[] array) {
        int[] result = {array[0], array[1], array[2]};
        Arrays.sort(result); // for first three members of input array the result is ready
        for (int i = 3; i < array.length; i++) {
            if (array[i] > result[0]) {
                result[0] = array[i];
                Arrays.sort(result); // Since we are sorting the array in each iteration, the result at any iteration is good.
                                        // Because of sorting the lowest is always at result[0], so replacing only result[0]
            }
        }
        return result;
    }
}
