package com.roydebnath.coding.leetcode.blind75.dynamic_programming;

import java.util.*;
import java.util.stream.Collectors;

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
public class Q35_WordBreak {
    public static void main(String[] args) {
        System.out.println(wordBreak("", List.of("leet", "code")));
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        // DFS
        Set<Integer> set = new HashSet<>();
        Set<String> dict = wordDict.stream().collect(Collectors.toSet());
        return dfs(s, 0, dict, set);
    }

    private static boolean dfs(String s, int index, Set<String> dict, Set<Integer> set) {
        // base case
        if (index == s.length()) return true;
        // check memory
        if (set.contains(index)) return false;
        // recursion
        for (int i = index + 1; i <= s.length(); i++) {
            String t = s.substring(index, i);
            if (dict.contains(t) && dfs(s, i, dict, set)) {
                return true;
            }
        }
        set.add(index);
        return false;
    }
}

