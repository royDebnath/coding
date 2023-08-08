package com.java.codinground.blind75;

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 *
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 *
 *
 *
 * Example 1:
 *
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 * Example 2:
 *
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transactions are done and the max profit = 0.
 *
 *
 */
public class Q30_BestTimetoBuyandSellStock {
    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit(prices));
    }
    public static int maxProfit(int[] prices) {
        int least_so_far = Integer.MAX_VALUE;
        int overall_profit = 0; // overall profit
        int profit_if_sold_today = 0; // profit if sold today

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < least_so_far) { // if we found new buy value which is smaller than previous one
                least_so_far = prices[i]; // update our least so far
            }
            profit_if_sold_today = prices[i] - least_so_far; // calculating profit if sold today by, Buy - sell
            if (overall_profit < profit_if_sold_today) { // if profit_if_sold_today is more then our previous overall profit
                overall_profit = profit_if_sold_today; // update overall profit
            }
        }
        return overall_profit; // return overall_profit
    }
}
