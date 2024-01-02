package com.java.codinground.programs.algoexpert.easy;

/**
 * Given a non-empty string that consists of lowercase English letters and a non-negative integer key,
 * I am asked to write a function that is going to
 * shift each letter in the given string key positions in the alphabet
 * and return the new string.
 *
 * e.g : xyz becomes zab
 */
public class CesarCipherEncryptor {
    public static void main(String[] args) {
        System.out.println(caesarCypherEncryptor("xyz", 2));
    }

    private static String caesarCypherEncryptor(String input, int key) {
        int length = input.length();
        char[] newChars = new char[length];
        for (int i = 0; i < length; i++) {
            newChars[i] = getNewCharacter(input.charAt(i), key);
        }
        return new String(newChars);
    }

    private static char getNewCharacter(char input, int key) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int newCharIndex = alphabet.indexOf(input) + key;
        return alphabet.charAt(newCharIndex % 26);
    }
}
