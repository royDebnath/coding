package com.java.codinground.programs.leetcode;

import java.util.Arrays;

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * You want to maximize your profit by choosing a single day to buy one stock and
 * choosing a different day in the future to sell that stock.
 * Return the maximum profit you can achieve from this transaction.
 * If you cannot achieve any profit, return 0.
 * Example 1:
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transactions are done and the max profit = 0.
 */
public class BestTimeToBuySellStock {
    public static void main(String[] args) {
        int[] stockPrices = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(stockPrices));
    }

    private static int maxProfit(int[] stockPrices) {
        int maxProfit = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int[] buySell = new int[2];
        for (int i = 0; i < stockPrices.length; i++) {
            if (stockPrices[i] < min) {
                min = stockPrices[i];
                buySell[0] = i;
            } else {
                int newMax = stockPrices[i] - min;
                if (newMax > maxProfit) {
                    maxProfit = newMax;
                    buySell[1] = i;
                }
            }
        }
        System.out.println("===========Indexes==========");
        Arrays.stream(buySell).forEach(System.out::println);
        System.out.println("============================");

        return maxProfit;
    }
}
