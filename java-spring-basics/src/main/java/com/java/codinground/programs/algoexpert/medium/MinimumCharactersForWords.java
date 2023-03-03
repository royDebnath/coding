package com.java.codinground.programs.algoexpert.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Write a function that takes in an array of words and returns the
 * smallest array of characters needed to form all of the words.
 * The characters don't need to be in any particular order.
 * For example, the characters ["y", "r", "o", "u"] are needed to form
 * the words ["your", "you", "or", "yo"].
 * Note: the input words won't contain any spaces; however,
 * they might contain punctuation and/or special characters.
 * Sample Input
 * words = ["this", "that", "did", "deed", "them!", "a"]
 * Sample Output
 * ["t", "t", "h", "i", "s", "a", "d", "d", "e", "e", "m", "!"]
 * // The characters could be ordered differently.
 */
public class MinimumCharactersForWords {
    public static void main(String[] args) {
        String[] words = new String[]{"this", "that", "did", "deed", "them!", "a"};
        System.out.println(minimumCharactersForWords(words));
    }

    private static char[] minimumCharactersForWords(String[] words) {
        Map<Character, Integer> masterMap = new HashMap<>();
        Map<Character, Integer> currentMap = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                currentMap.put(c, currentMap.getOrDefault(c, 0) + 1);
            }
            for (Map.Entry<Character, Integer> currentMapEntry : currentMap.entrySet()) {
                Optional<Integer> value = Optional.ofNullable(masterMap.get(currentMapEntry.getKey()));
                if (value.isPresent() && value.get() > currentMapEntry.getValue()) continue;
                masterMap.put(currentMapEntry.getKey(), currentMapEntry.getValue());
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : masterMap.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                sb.append(entry.getKey());
            }
        }
        return sb.toString().toCharArray();
    }
}

