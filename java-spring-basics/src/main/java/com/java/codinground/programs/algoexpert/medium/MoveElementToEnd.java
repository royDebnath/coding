package com.java.codinground.programs.algoexpert.medium;

import java.util.Arrays;

/**
 * You're given an array of integers and an integer. Write a function that
 * moves all instances of that integer in the array to the end of the array
 * and returns the array.
 * The function should perform this in place (i.e., it should mutate the input array)
 * and doesn't need to maintain the order of the other integers.
 * Sample Input
 * array = [2, 1, 2, 2, 2, 3, 4, 2]
 * toMove = 2
 * Sample Output
 * [1, 3, 4, 2, 2, 2, 2, 2] // the numbers 1, 3, and 4 could be ordered differently
 */
public class MoveElementToEnd {
    public static void main(String[] args) {
        int[] input = {2, 1, 2, 2, 2, 3, 4, 2};
        int toMove = 2;
        moveElementToEnd(input, toMove);
        Arrays.stream(input).forEach(System.out::println);
    }

    private static void moveElementToEnd(int[] input, int toMove) {
        int length = input.length;
        int j = length-1;
        for (int i = 0; i < length && i< j; i++) {
            if (input[i]==toMove){
                while (input[j]==toMove && i< j){
                    j--;
                }
                swap(input, i, j);
            }
        }
    }

    private static void swap(int[] input, int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }
}
