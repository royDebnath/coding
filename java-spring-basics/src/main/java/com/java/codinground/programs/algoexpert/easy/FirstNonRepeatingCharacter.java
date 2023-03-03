package com.java.codinground.programs.algoexpert.easy;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class FirstNonRepeatingCharacter {
    public static void main(String[] args) {
        String input = "abcdcafb";
        System.out.println("First non-repeating character : " + firstNonRepeatingCharacter(input));

    }

    private static Character firstNonRepeatingCharacter(String input) {
        HashMap<Character, Integer> frequency = new LinkedHashMap<>();
        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);
            frequency.put(current, frequency.getOrDefault(current, 0)+1);
        }
        for (Character key : frequency.keySet()) {
            if (frequency.get(key)==1){
                return key;
            }
        }
        return null;
    }
}
