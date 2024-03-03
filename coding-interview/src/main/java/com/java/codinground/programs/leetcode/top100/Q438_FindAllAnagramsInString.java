package com.java.codinground.programs.leetcode.top100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "cbaebabacd", p = "abc"
 * Output: [0,6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 *
 * Input: s = "abab", p = "ab"
 * Output: [0,1,2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 *
 * Solution :
 * Build an HashMap containing the count of each char in the p string;
 * Decrement the counter of each char for each char you insert in the sliding window;
 * Increment the counter of each char for each char you remove from the sliding window;
 * Store the number of char with count == 0 in the hash map, so you don't have to check every time.
 * If all the char have count==0, that means that the sliding window contains all the chars from the p string.
 *
 *
 */
public class Q438_FindAllAnagramsInString  {
    public List<Integer> findAnagrams(String s, String p) {

        List<Integer> indexes = new ArrayList<>();
        if(s.isEmpty() || p.length()>s.length()) return indexes;
        Map<Character,Integer> pHash = new HashMap<>();
        for(char c: p.toCharArray()) {
            if(pHash.containsKey(c)) {
                pHash.put(c, pHash.get(c)+1);
            } else {
                pHash.put(c, 1);
            }
        }

        int numberOfCharsToBeZero = pHash.keySet().size();

        for(int i=0;i<p.length();i++) {
            char c = s.charAt(i);
            if(pHash.containsKey(c)) {
                int value = pHash.get(c)-1;
                pHash.put(c, value);
                if(value==0) numberOfCharsToBeZero--;
            }
        }
        if(numberOfCharsToBeZero==0) indexes.add(0);
        int start=0;
        int end=p.length()-1;
        while(end<s.length()-1) {
            char startChar = s.charAt(start++);
            char endChar = s.charAt(++end);
            if(pHash.containsKey(startChar)) {
                if(pHash.get(startChar)==0) numberOfCharsToBeZero++;
                pHash.put(startChar, pHash.get(startChar)+1);
            }
            if(pHash.containsKey(endChar)) {
                pHash.put(endChar, pHash.get(endChar)-1);
                if(pHash.get(endChar)==0) numberOfCharsToBeZero--;
            }
            if(numberOfCharsToBeZero==0) indexes.add(start);
        }
        return indexes;
    }
}
