package com.java.codinground.programs.leetcode.top100;

import java.util.Arrays;
import java.util.Stack;

/**
 * Design an algorithm that collects daily price quotes for some stock and
 * returns the span of that stock's price for the current day.
 * <p>
 * The span of the stock's price today is defined as the maximum number of
 * consecutive days (starting from today and going backward) for which the
 * stock price was less than or equal to today's price.
 * <p>
 * For example, if the price of a stock over the next 7 days
 * were [100,80,60,70,60,75,85], then the stock spans would be
 * [1,1,1,2,1,4,6].
 */
public class StockSpan {
    public static void main(String[] args) {
        int[] prices = {100, 80, 60, 70, 60, 75, 85};
        Arrays.stream(stockSpan(prices)).forEach(System.out::println);
    }

    private static int[] stockSpan(int[] prices) {
        int length = prices.length;
        int[] spans = new int[length];
        Stack<Integer> indexes = new Stack<>();
        spans[0] = 1;
        indexes.push(0);
        for (int i = 1; i < length; i++) {
            while (indexes.size() > 0 && prices[i] > prices[indexes.peek()]) {
                indexes.pop();
            }
            if (indexes.size() == 0) {
                spans[i] = i + 1;
            } else {
                spans[i] = i - indexes.peek();
            }
            indexes.push(i);
        }
        return spans;
    }
}
