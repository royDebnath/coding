package com.java.codinground.programs.algoexpert.easy;

import java.util.HashMap;
import java.util.Map;

public class NthFibonacciNumber {

    public static void main(String[] args) {

        System.out.println(getFibonacciRecursive(7));
        System.out.println(getFibonacciRecursiveMemoized(7));
        System.out.println(getFibonacciIterative(7));
    }

    /**
     * Naive Recursive. Calculating same fib(n) multiple times
     */
    private static int getFibonacciRecursive(int number) {
        if (number == 1) {
            return 0;
        } else if (number == 2) {
            return 1;
        } else {
            return getFibonacciRecursive(number - 1) + getFibonacciRecursive(number - 2);
        }
    }

    /**
     * Memoized recursive where
     */
    private static int getFibonacciRecursiveMemoized(int number) {
        Map<Integer, Integer> memoize = new HashMap<>();
        memoize.put(1, 0);
        memoize.put(2, 1);
        return getFibonacciRecursiveMemoized(number, memoize);
    }

    private static int getFibonacciRecursiveMemoized(int number, Map<Integer, Integer> memoize) {
        if (!memoize.containsKey(number)) {
            memoize.put(number, getFibonacciRecursiveMemoized(number - 1, memoize) + getFibonacciRecursiveMemoized(number - 2, memoize));
        }
        return memoize.get(number);
    }

    private static int getFibonacciIterative(int number) {
        if (number == 1) {
            return 0;
        } else if (number == 2) {
            return 1;
        } else {
            int[] lastTwo = {0, 1};
            int counter = 3;
            while (counter <= number) {
                int nextFib = lastTwo[0] + lastTwo[1];
                lastTwo[0] = lastTwo[1];
                lastTwo[1] = nextFib;
                counter++;
            }
            return lastTwo[1];
        }
    }
}
