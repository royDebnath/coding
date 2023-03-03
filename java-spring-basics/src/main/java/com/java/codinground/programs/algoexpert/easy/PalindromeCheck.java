package com.java.codinground.programs.algoexpert.easy;

/**
 * checks the first and last character for palindrome.
 * recursively checks the middle (excluding the first and last character from left and right)
 */
public class PalindromeCheck {
    public static void main(String[] args) {
        System.out.println(isPalindrome("racecar"));
    }

    private static boolean isPalindrome(String input) {
        return isPalindrome(input, 0);
    }

    private static boolean isPalindrome(String input, int start) {
        int left = start;
        int right = input.length() - 1 - left;
        if (left >= right) { // it means we have crossed the middle and all the comparison returned true till then
            return true;
        }
        return input.charAt(left) == input.charAt(right) && isPalindrome(input, left + 1);
    }
}
