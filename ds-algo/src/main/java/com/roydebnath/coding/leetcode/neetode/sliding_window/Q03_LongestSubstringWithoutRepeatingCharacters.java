package com.roydebnath.coding.leetcode.neetode.sliding_window;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s, find the length of the longest substring without repeating characters.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 * <p>
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 * <p>
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class Q03_LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {

        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }

    private static int lengthOfLongestSubstring(String s) {
        int window_start = 0; //starting a sliding window
        int window_end = 0;
        int maxLength = 0;
        int length = s.length();
        Set<Character> seen = new HashSet<>();

        while (window_end < length) {
            char endingChar = s.charAt(window_end);
            if (!seen.contains(endingChar)) {
                seen.add(endingChar);
                window_end++;
                maxLength = Math.max(maxLength, seen.size());
            } else {
                seen.remove(s.charAt(window_start));
                window_start++;
            }
        }
        return maxLength;
    }
}
