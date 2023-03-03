package com.java.codinground.programs.algoexpert.easy;

import java.util.HashMap;
import java.util.Map;

/***
 * You’re given a string of available characters and a string representing a document that you need to generate.
 * Write a function that determines if you can generate the document using the available characters.
 * If you can generate the document, your function should return true; otherwise, it should return false.
 *
 * You’re only able to generate the document if the frequency of unique characters in the characters string is
 * greater than or equal to the frequency of unique characters in the document string. For example, if you’re given
 * characters = “abcabc” and document = “aabbccc” you cannot generate the document because you’re missing one c.
 */
public class GenerateDocument {
    public static void main(String[] args) {
        String input = "Bste!hetsi ogEAxpelrt x ";
        String document = "AlgoExpert is the Best!";
        System.out.println("Document can be generated : " + generateDocument(input, document));
    }

    private static boolean generateDocument(String input, String document) {
        Map<Character, Integer> inputMap = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            inputMap.put(currentChar, inputMap.getOrDefault(currentChar, 0) + 1);
        }

        for (int i = 0; i < document.length(); i++) {
            char currentChar = document.charAt(i);
            if (!inputMap.containsKey(currentChar) || inputMap.get(currentChar) == 0) {
                return false;
            }
            inputMap.put(currentChar, inputMap.get(currentChar) - 1);
        }
        return true;
    }
}

