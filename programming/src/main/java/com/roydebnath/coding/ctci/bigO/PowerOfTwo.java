package com.roydebnath.coding.ctci.bigO;

/**
 * The following function prints the powers of 2 from 1 through n (inclusive). For example, if n is 4, it would
 * print 1, 2, and 4.
 * a call like powers0f2 ( 50).
 *
 * powers0f2(50) ->
 * powers0f2(25) ->
 * powers0f2(12) ->
 * powers0f2(6)  ->
 * powers0f2(3)  ->
 * powersOf2(1)  ->
 * --> Basecase hit, powersOf2(1) executed with the print statement at 31
 * print & return 1 -- from line no : 31, 32 -- returns control to calling function powers0f2(3) with prev=1
 * print & return 2 -- from line no : 36, 37 -- returns control to calling function powers0f2(6) with prev=2
 * print & return 4 -- from line no : 36, 37 -- returns control to calling function powers0f2(6) with prev=4
 * print & return 8 -- from line no : 36, 37 -- returns control to calling function powers0f2(6) with prev=8
 * print & return 16 --from line no : 36, 37 -- returns control to calling function powers0f2(6) with prev=16
 * print & return 32 --from line no : 36, 37 -- returns control to calling function powers0f2(6) with prev=32
 *
 * The runtime, then, is the number of times we can divide 50 (or n) by 2 until we get down to the base case (1 ).
 * The number of times we can halve n until we get 1 is O ( log n).
 *
 */
public class PowerOfTwo {
    public static int powersOf2(int n) {
        if (n < 1) {
            return 0;
        } else if (n == 1) {
            System.out.println(1);
            return 1;
        } else {
            int prev = powersOf2(n / 2);
            int curr = prev * 2;
            System.out.println(curr);
            return curr;
        }
    }

    public static void main(String[] args) {
        powersOf2(50);
    }

}