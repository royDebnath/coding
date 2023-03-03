package com.java.codinground.programs.algoexpert.easy;

/**
 * Implement a function that takes two arrays of integers as input and
 * finds whether all the numbers in the sequence array
 * appear in the first array and they appear in the same order. I
 * n other words, the function need to find out if we can get the sequence array,
 * when we delete some or no elements in the first array without changing the order of the remaining elements.
 *
 * Approach
 * Use a pointer to keep track of the position we are at in the sequence array.
 * Iterate through every integer in the first array.
 * At each iteration, compare the integer in the first array with the value in the sequence array that the pointer currently points to,
 * if they are equal, then we found the value in the first array, move the pointer forward by 1.
 * If the pointer is equal to the length of the sequence array,
 * then it means all the numbers in the sequence array are found in the first array and they are in the same order, return true.
 * After the loop finishes, if the pointer is not equal to the length of the sequence array, return false.
 */
public class ValidateSubSequence {
    public static void main(String[] args) {
        int[] main = {3, 1, 7, 5, 10, 2};
        int[] sequence = {1, 5, 2};
        System.out.println(isValidSubsequence(main, sequence));
    }

    private static boolean isValidSubsequence(int[] main, int[] sequence) {

        int sequenceLength = sequence.length;
        int mainLength = main.length;

        int pointer = 0;
        for (int i = 0; i < mainLength; i++) {
            if (main[i] == sequence[pointer]) {
                pointer++;
            }
        }
        return pointer == sequenceLength;

/*      int mainIndex = 0;
        int sequenceIndex = 0;
        while (mainIndex < mainLength && sequenceIndex < sequenceLength) {
            if (main[mainIndex] == sequence[sequenceIndex]) {
                sequenceIndex++;
            }
            mainIndex++;
        }
        return sequenceIndex == sequenceLength;*/
    }
}
