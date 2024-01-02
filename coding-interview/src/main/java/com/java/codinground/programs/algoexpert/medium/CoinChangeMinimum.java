package com.java.codinground.programs.algoexpert.medium;

import java.util.Arrays;

/**
 * Min Number Of Coins For Change
 * Given an array of positive integers representing coin denominations
 * and a single non-negative integer n representing a target amount of money,
 * write a function that returns the smallest number of coins needed to make change
 * for (to sum up to) that target amount using the given coin denominations.
 * Note that you have access to an unlimited amount of coins. In other words,
 * if the denominations are [1, 5, 10], you have access to an unlimited amount of 1s, 5s, and 10s.
 * If it's impossible to make change for the target amount, return -1.
 * Sample Input
 * n = 7
 * denoms = [1, 5, 10]
 * Sample Output
 * 3 // 2x1 + 1x5
 */
public class CoinChangeMinimum {
    public static void main(String[] args) {

    }

    public static int minNumberOfCoinsForChange(int n, int[] denoms) {
        int[] numOfCoins = new int[n + 1];
        Arrays.fill(numOfCoins, Integer.MAX_VALUE);
        numOfCoins[0] = 0;
        int toCompare = 0;
        for (int denom : denoms) {
            for (int amount = 0; amount < numOfCoins.length; amount++) {
                if (denom <= amount) {
                    if (numOfCoins[amount - denom] == Integer.MAX_VALUE) {
                        toCompare = numOfCoins[amount - denom];
                    } else {
                        toCompare = numOfCoins[amount - denom] + 1;
                    }
                    numOfCoins[amount] = Math.min(numOfCoins[amount], toCompare);
                }
            }
        }
        return numOfCoins[n] != Integer.MAX_VALUE ? numOfCoins[n] : -1;
    }
}
