package com.java.backup.blind75.string;

import java.util.*;

/**
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 *
 * Example 1:
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * Example 2:
 * Input: strs = [""]
 * Output: [[""]]
 *
 * Example 3:
 * Input: strs = ["a"]
 * Output: [["a"]]
 *
 *
 */
public class Q13_GroupAnagrams {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("yo", "act", "flop", "tac", "foo", "cat", "oy", "olfp");
        List<List<String>> anagrams = groupAnagrams(words);
        anagrams.forEach(anagram -> {
            System.out.println(anagram);
        });
    }

    public static List<List<String>> groupAnagrams(List<String> words) {
        Map<String, List<String>> anagrams = new HashMap();
        for (String word : words) {
            char[] charArray = word.toCharArray();
            Arrays.sort(charArray);
            String sortedWord = new String(charArray);
            if (anagrams.containsKey(sortedWord)) {
                anagrams.get(sortedWord).add(word);
            } else {
                anagrams.put(sortedWord, new ArrayList<>(Arrays.asList(word)));
            }
        }
        return new ArrayList<>(anagrams.values());
    }
}
