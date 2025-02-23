package leetcode.all.sliding_window;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given a string s and an integer k.
 * You can choose any character of the string and change it to any other uppercase English character.
 * You can perform this operation at most k times.
 *
 * Return the length of the longest substring containing the same letter you can get after performing
 * the above operations.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "ABAB", k = 2
 * Output: 4
 * Explanation: Replace the two 'A's with two 'B's or vice versa.
 * Example 2:
 *
 * Input: s = "AABABBA", k = 1
 * Output: 4
 * Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 * There may exists other ways to achive this answer too.
 *
 * Solution :
 *
 * Key Idea: Adding characters to the window and use the map to track
 * the number of dominant char(meaning the character that counts the most in the window).
 * Expanding the window as wide as it can be until
 * window size - number of dominant character > k which means
 * there are at least k characters are not same as the dominant character,
 * so we need shrink the window from the left side and also update the character count in the map.
 *
 * One key point that causes confusion is when we remove the left side character in the map,
 * the maxRepeat becomes inaccurate. But in this particular case, we do not care about
 * the maxRepeat gets smaller because it won't affect the max window size,
 * we only care about when maxRepeat gets greater.
 *
 */
public class Q424_LongestRepeatingCharacterReplacement {
    public static void main(String[] args) {
        System.out.println(characterReplacement("AABABBA", 1));
    }

    public static int characterReplacement(String s, int k) {
        //holds all repeated characters
        Map<Character, Integer> letterCountMap = new HashMap<>();
        //left pointer, max repeated char count, max window size
        int left = 0, mostFreqLetterFrequency = 0, maxWindow = 0;

        for (int right = 0; right < s.length(); right++) {

            //increase current char count
            char currentChar = s.charAt(right);
            //put character count in map
            letterCountMap.put(currentChar, letterCountMap.getOrDefault(currentChar, 0) + 1);

            // max repeated char count
            mostFreqLetterFrequency = Math.max(mostFreqLetterFrequency, letterCountMap.get(currentChar));

            int currentSizeOfWindow = right - left + 1;

            int lettersTochange = currentSizeOfWindow - mostFreqLetterFrequency;

            //if current size of the window - max repeated count greater than k
            if (lettersTochange > k) { //slide window from left
                char charToDecrease = s.charAt(left);
                //decrease char count
                letterCountMap.put(charToDecrease, letterCountMap.get(charToDecrease) - 1);
                left++;

                //update current size of window
                currentSizeOfWindow--;
            }

            maxWindow = Math.max(maxWindow, currentSizeOfWindow);
        }
        return maxWindow;
    }
}
