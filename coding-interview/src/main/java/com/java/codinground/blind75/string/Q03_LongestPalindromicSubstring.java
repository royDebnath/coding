package com.java.codinground.blind75.string;

/**
 * 5. Longest Palindromic Substring
 * Medium
 *
 * Given a string s, return the longest palindromic substring in s.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "babad"
 * Output: "bab"
 * Explanation: "aba" is also a valid answer.
 * Example 2:
 * <p>
 * Input: s = "cbbd"
 * Output: "bb"
 */
public class Q03_LongestPalindromicSubstring {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("aa"));
    }

    public static String longestPalindrome(String s) {
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {

            char current = s.charAt(i);
            int left = i;
            int right = i;

            while (left >= 0 && s.charAt(left) == current) {
                left--;
            }

            while (right < s.length() && s.charAt(right) == current) {
                right++;
            }

            while (left >= 0 && right < s.length()) {
                if (s.charAt(left) != s.charAt(right)) {
                    break;
                }
                left--;
                right++;
            }


            // left + 1 and right - 1 are actually the start and end index of the Palindromic string
            // we don't set "right-1" because String.substring function required end index exclusively
            left = left + 1;
            if (end - start < right - left) {
                start = left;
                end = right;
            }
        }

        return s.substring(start, end);
    }
}
