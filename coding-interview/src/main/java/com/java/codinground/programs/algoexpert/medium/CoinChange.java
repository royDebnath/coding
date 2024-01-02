package com.java.codinground.programs.algoexpert.medium;

/**
 * Number Of Ways To Make Change
 * Given an array of distinct positive integers representing coin denominations
 * and a single non-negative integer n representing a target amount of money,
 * write a function that returns the number of ways to make change
 * for that target amount using the given coin denominations.
 * Note that an unlimited amount of coins is at your disposal.
 *
 * Sample Input
 * n = 6
 * denoms = [1, 5]
 * Sample Output
 * 2 // 1x1 + 1x5 and 6x1
 *
 * Solution :
 *
 * 	           Amount      0 1 2 3 4
 * 					   -------------
 * No Coin             |0| 0 0 0 0 0
 * Only Coin 1         |1| 1 1 1 1 1
 * Coin 1 and Coin 2   |2| 1 1 2 2 3
 * All 1, 2 and 3 Coin |3| 1 1 2 3 4
 */
public class CoinChange {
    public static void main(String[] args) {
        int[] input = {1, 5};
        int target = 6;
        int ways = numberOfWaysToMakeChange(target, input);
        System.out.println(ways);
    }

    public static int numberOfWaysToMakeChange(int target, int[] denoms) {
        int[] ways = new int[target + 1];
        ways[0] = 1;
        for (int denom : denoms) {
            for (int amount = 1; amount < target + 1; amount++) {
                if (denom <= amount) {
                    ways[amount] += ways[amount - denom];
                }
            }
        }
        return ways[target];
    }
}

