package com.java.codinground.programs.algoexpert.medium;

import java.util.Arrays;

/**
 * Write a function that takes in a non-empty array of integers and
 * returns an array of the same length, where each element in the output array
 * is equal to the product of every other number in the input array.
 *
 * In other words, the value at output[i] is equal to the product
 * of every number in the input array other than input[i].
 * Note that you're expected to solve this problem without using division.
 * Sample Input
 * array = [5, 1, 4, 2]
 * Sample Output
 * [8, 40, 10, 20]
 * // 8 is equal to 1 x 4 x 2
 * // 40 is equal to 5 x 4 x 2
 * // 10 is equal to 5 x 1 x 2
 * // 20 is equal to 5 x 1 x 4
 */
public class ArrayOfProducts {
    public static void main(String[] args) {
        int[] input = {5, 1, 4, 2};
        int[] output = arrayOfProducts(input);
        Arrays.stream(output).forEach(System.out::println);
    }

    private static int[] arrayOfProducts(int[] input) {
        int length = input.length;
        int[] products = new int[length];
        int[] leftProducts = new int[length];
        int[] rightProducts = new int[length];
        leftProducts[0] = 1;
        rightProducts[length-1] = 1;

        for (int i = 1; i < length; i++) {
            leftProducts[i] = input[i-1]*leftProducts[i-1];
        }

        for (int i = length - 2; i >= 0; i--) {
            rightProducts[i] = input[i+1]*rightProducts[i+1];
        }

        for (int i = 0; i < products.length; i++) {
            products[i] = leftProducts[i]*rightProducts[i];
        }

        return products;
    }
}
