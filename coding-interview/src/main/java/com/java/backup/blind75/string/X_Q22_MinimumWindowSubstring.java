package com.java.backup.blind75.string;

/**
 * Given two strings s and t of lengths m and n respectively, return the minimum window
 * substring
 * of s such that every character in t (including duplicates) is included in the window.
 * If there is no such substring, return the empty string "".
 * <p>
 * The testcases will be generated such that the answer is unique.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 * Example 2:
 * <p>
 * Input: s = "a", t = "a"
 * Output: "a"
 * Explanation: The entire string s is the minimum window.
 * Example 3:
 * <p>
 * Input: s = "a", t = "aa"
 * Output: ""
 * Explanation: Both 'a's from t must be included in the window.
 * Since the largest window of s only has one 'a', return empty string.
 */
public class X_Q22_MinimumWindowSubstring {
    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }

    public static String minWindow(String master, String pattern) {
        if (master == null || pattern == null || master.length() == 0 || pattern.length() == 0 ||
                master.length() < pattern.length()) {
            return "";
        }
        int[] pattern_hash = new int[128];
        int count = pattern.length();
        int start = 0, end = 0, minLen = Integer.MAX_VALUE, startIndex = 0;
        for (char c : pattern.toCharArray()) {
            pattern_hash[c]++;
        }
        /**
         *         while (end < master_array.length) {
         *             char current_char_master = master.charAt(end);
         *             if(pattern_hash[current_char_master]>0){
         *                 pattern_hash[current_char_master]--;
         *                 count--;
         *                 end++;
         *             }
         */
        char[] master_array = master.toCharArray();
        while (end < master_array.length) {
            char current_char_master = master_array[end];
            if (pattern_hash[current_char_master]-- > 0) {
                count--;
            }
            end++;
            while (count == 0) {
                if (end - start < minLen) {
                    startIndex = start;
                    minLen = end - start;
                }
                if (pattern_hash[master_array[start++]]++ == 0) {
                    count++;
                }
            }
        }

        return minLen == Integer.MAX_VALUE ? new String() :
                new String(master_array, startIndex, minLen);
    }
}