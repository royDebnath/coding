package com.java.codinground.leetcode.categories.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
 * <p>
 * A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 * <p>
 * <p>
 * * 2: abc
 * * 3: def
 * * 4: ghi
 * * 5: jkl
 * * 6: mno
 * * 7: pqrs
 * * 8: tuv
 * * 9: wxyz
 * <p>
 * Example 1:
 * <p>
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * Example 2:
 * <p>
 * Input: digits = ""
 * Output: []
 * Example 3:
 * <p>
 * Input: digits = "2"
 * Output: ["a","b","c"]
 * <p>
 * Phone Keypad :
 * <p>
 * 2: abc
 * 3: def
 * 4: ghi
 * 5: jkl
 * 6: mno
 * 7: pqrs
 * 8: tuv
 * 9: wxyz
 */
public class Q17_LetterCombinationsOfPhoneNumber {
    private Map<Character, String> digitToLetters = new HashMap<>();
    private List<String> resultList = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return resultList;
        }

        digitToLetters.put('2', "abc");
        digitToLetters.put('3', "def");
        digitToLetters.put('4', "ghi");
        digitToLetters.put('5', "jkl");
        digitToLetters.put('6', "mno");
        digitToLetters.put('7', "pqrs");
        digitToLetters.put('8', "tuv");
        digitToLetters.put('9', "wxyz");

        generateCombinations(digits, 0, new StringBuilder());

        return resultList;


    }


    private void generateCombinations(String digits, int currentIndex, StringBuilder currentCombination) {
        if (currentIndex == digits.length()) {
            resultList.add(currentCombination.toString());
            return;
        }

        char currentDigit = digits.charAt(currentIndex);
        String letterOptions = digitToLetters.get(currentDigit);

        if (letterOptions != null) { // for 0, 1 its null
            for (int i = 0; i < letterOptions.length(); i++) {
                char letter = letterOptions.charAt(i);
                currentCombination.append(letter);
                generateCombinations(digits, currentIndex + 1, currentCombination);
                currentCombination.deleteCharAt(currentCombination.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Q17_LetterCombinationsOfPhoneNumber app = new Q17_LetterCombinationsOfPhoneNumber();
        System.out.println(app.letterCombinations("23").toString());
    }
}
