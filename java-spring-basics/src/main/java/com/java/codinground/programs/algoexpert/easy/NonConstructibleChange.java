package com.java.codinground.programs.algoexpert.easy;

import java.util.Arrays;

/**
 * We will be given an array of positive integers, which represent the values of coins that we have in our possession.
 * The array could have duplicates. We are asked to write a function that returns the minimum amount of change
 * that we cannot create with our coins.
 * For instance, if the input array is [1, 2, 5],
 * the minimum amount of change that we cannot create is 4,
 * since we can create 1, 2, 3 (1 + 2) and 5.
 *
 * Approach :
 *
 * Imagine we have a set of coin called U. In this set we have now one coin and its value is 1: U = {1}.
 * Imagine we have another value called C, representing the change we can create with our coins: C = 1.
 * We have also V which represents the new coin we add to our set.
 * If V > C + 1, we cannot make C + 1 change; if V <= C + 1, we can make from C to C + V change,
 * and the minimum amount of change we cannot create is C + V + 1.
 *
 * So to solve the problem, what we need to do is:
 *
 * Sort the input array in ascending order,
 * Use a variable to keep track of the current change we create. Initiate it to 0.
 * Iterate through every integer in the sorted array.
 * For every integer, compare it to the current change, if it is greater than the current change,
 * we found the minimum amount of change we cannot make, which is current change we make plus 1, return the result;
 * otherwise, add the integer to the current change.
 * If we get out of the loop without returning the result, return current change we make plus 1.
 */
public class NonConstructibleChange {
    public static void main(String[] args) {
        int[] input = new int[]{5, 7, 1, 1, 2, 3, 22};
        System.out.println("Non-Constructible change is : " + nonConstructibleChange(input));
    }

    private static int nonConstructibleChange(int[] coins) {
        Arrays.sort(coins);
        int currentChange = 0;
        for (int coin : coins) {
            if (coin > currentChange + 1) {
                return currentChange + 1;
            } else {
                currentChange += coin;
            }
        }
        return currentChange + 1;
    }
}
