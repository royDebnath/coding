package com.java.codinground.programs.algoexpert.medium;

/**
 * Write a function that, given a string, returns its longest palindromic substring.
 * A palindrome is defined as a string that's written the same forward and backward.
 * Note that single-character strings are palindromes.
 * You can assume that there will only be one longest palindromic substring.
 * Sample Input
 * string = "abaxyzzyxf"
 * Sample Output
 * "xyzzyx"
 */
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        System.out.println(longestPalindromicSubstring("abaxyztzyxf"));
    }

    public static String longestPalindromicSubstring(String str) {
        int start = 0;
        int end = 0;
        for (int i = 1; i < str.length(); i++) {
            int odd_length = longestPalindromeLength(str, i, i); // rac e car
            int even_length = longestPalindromeLength(str, i, i + 1); // aab baa
            int currentLongest = Math.max(odd_length, even_length);
            if (currentLongest > end - start) {
                start = i - ((currentLongest-1) / 2); // in case of "aab baa" /(i, i+1) or even length need to do -1 to adjust
                end = i + (currentLongest / 2);
                // i is the middle index so start will be calculated traversing half left and end on the right half
            }
        }
        return str.substring(start, end + 1);
    }

    /**
     * this method takes input of the middle index and stretches in both side to find longest palindrome
     */
    private static int longestPalindromeLength(String str, int left, int right) {
        while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
            left--;
            right++;
        }
        return right - (left + 1); // left+1 as left index starts with 0
    }
}
