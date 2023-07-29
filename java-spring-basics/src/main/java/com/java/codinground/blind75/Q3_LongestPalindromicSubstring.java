package com.java.codinground.blind75;

/**
 * Given a string s, return the longest palindromic substring in s.
 *
 * Example 1:
 *
 * Input: s = "babad"
 * Output: "bab"
 * Explanation: "aba" is also a valid answer.
 * Example 2:
 *
 * Input: s = "cbbd"
 * Output: "bb"
 *
 */
public class Q3_LongestPalindromicSubstring {
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
                start = i - ((currentLongest - 1) / 2); // in case of "aab baa" /(i, i+1) or even length need to do -1 to adjust
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
