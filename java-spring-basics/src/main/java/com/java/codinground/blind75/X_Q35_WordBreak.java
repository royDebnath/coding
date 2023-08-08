package com.java.codinground.blind75;

import java.util.*;

/**
 * 139. Word Break
 * Medium
 *
 * Given a string s and a dictionary of strings wordDict, return true if s can be
 * segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * Example 2:
 *
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 *
 */
public class X_Q35_WordBreak {
    public static void main(String[] args) {
        System.out.println(wordBreak("", List.of("leet", "code")));
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> visited = new HashSet<>();
        Set<String> dict = new HashSet<>(wordDict);
        return dfs(s, dict, visited);
    }

    private static boolean dfs(String s, Set<String> dict, Set<String> visited) {

        if (s.isEmpty()) {
            return true;
        }

        if (visited.contains(s)) {
            return false;
        }
        visited.add(s);

        for (int i = 1; i <= s.length(); i++) {
            String left = s.substring(0, i);
            String right = s.substring(i);
            if (dict.contains(left) && dfs(right, dict, visited)) {
                return true;
            }
        }
        return false;
    }
}

