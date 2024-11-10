package com.roydebnath.coding.leetcode.blind75.dynamic_programming;

/**
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
 *
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * To decode an encoded message, all the digits must be grouped then mapped back into letters
 * using the reverse of the mapping above (there may be multiple ways).
 * For example, "11106" can be mapped into:
 *
 * "AAJF" with the grouping (1 1 10 6)
 * "KJF" with the grouping (11 10 6)
 * Note that the grouping (1 11 06) is invalid because
 * "06" cannot be mapped into 'F' since "6" is different from "06".
 *
 * Given a string s containing only digits, return the number of ways to decode it.
 *
 * The test cases are generated so that the answer fits in a 32-bit integer.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "12"
 * Output: 2
 * Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
 * Example 2:
 *
 * Input: s = "226"
 * Output: 3
 * Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 * Example 3:
 *
 * Input: s = "06"
 * Output: 0
 * Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").
 *
 **                 /**
 *                  * 2120121
 *                  *
 *                  * 2=2, dp[0] = 1
 *                  * 21 = [2,1] [21] dp[1] = 2
 *                  * 212 = [2,1,2], [21,2], [2,12] = dp[2]=3
 *                  * 2120 = [2,1,20], [21,20] = dp[3]=2
 *                  * 21201= [2,1,20,1], [21,20,1] = dp[4]=2
 *                  * 212012 = [2,1,20,1,7], [21,20,1,7], [2,1,20,17], [21,20,17] = dp[4] = 4
 *                  *
 *                  * dp[i] = dp[i] + dp[i-2] (where dp[i] will be pre populated dp[i]=dp[i-1])
 *
 *                  */
public class X_Q24_DecodeWays {
    public static void main(String[] args) {
        System.out.println(numDecodings("2120121"));
    }

    public static int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        char[] chars = s.toCharArray();
        int length = chars.length;
        int[] dp = new int[length];
        dp[0] = 1;
        for (int i = 1; i < length; i++) {
            char current = chars[i];
            char prev = chars[i - 1];
            if (current >= '1' && current <= '9') {
                dp[i] = dp[i - 1]; // this will always be set unless current is 0
            }
            if ((prev == '1' && current >= '0' && current <= '9') || //c1 -> 10-19
                    (prev == '2' && current >= '0' && current <= '6')) {// c2 -> 20-26
                if (i >= 2) {
                    dp[i] = dp[i] + dp[i - 2];
                } else {
                    dp[i] = dp[i] + 1; // only when i=1
                }
            }
        }
        return dp[length - 1];
    }
}
