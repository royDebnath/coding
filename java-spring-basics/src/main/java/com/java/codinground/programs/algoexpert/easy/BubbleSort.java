package com.java.codinground.programs.algoexpert.easy;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] input = {8, 5, 2, 9, 5, 6, 3};
        bubbleSort(input);
        Arrays.stream(input).forEach(System.out::print);
    }

    private static void bubbleSort(int[] input) {
        int length = input.length;
        for (int i = 0; i < length; i++) {
            boolean didSwap = false;
            for (int j = 0; j < length - i - 1; j++) {
                if (input[j] > input[j + 1]) {
                    swap(input, j);
                    didSwap = true; // if this does not happen even once in the loop that means
                                    // [if (input[j] > input[j + 1])] never occured and that means the array is sorted.
                }
            }
            if (!didSwap) {
                break; //break from loop if the array is sorted
            }
        }
    }

    private static void swap(int[] input, int i) {
        int temp = input[i];
        input[i] = input[i + 1];
        input[i + 1] = temp;
    }
}

