package leetcode.all.sliding_window;

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
public class Q438_FindAllAnagramsInString {
    public static void main(String[] args) {
        System.out.println(findAnagrams("cbaebabacd", "abc"));
    }
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        if (p.length() > s.length()) {  // p annot be longer than s
            return ans;
        }
        // create hashmaps to store the frequencies of characters
        // we would check every window of size len(p)
        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> pMap = new HashMap<>();

        // store the frequency for the starting window
        for (int i = 0; i < p.length(); i++) {
            pMap.put(p.charAt(i), pMap.getOrDefault(p.charAt(i), 0) + 1);
            sMap.put(s.charAt(i), sMap.getOrDefault(s.charAt(i), 0) + 1);
        }

        //After the above loop, pMap contains the frequency of characters of pattern
        // sMap contains frequency of characters in the first p.length characters in s.

        // if frequencies are same means we got an anagram
        if (sMap.equals(pMap)) {
            ans.add(0);     // add the first index, because we moved from
                            // 0 to p.length() in both s and p
        }

        // start from index 1 with a window of p.length,
        int left = 1, right = p.length();

        // Now we will start sliding the window
        while (right < s.length()) {

            // get rid the char we moved from, from left
            char discard = s.charAt(left-1);
            sMap.put(discard, sMap.get(discard) - 1);
            // if frequency has become 0, remove the mapping itself
            //removing the mapping will help to compare the map
            if (sMap.get(discard) == 0) {
                sMap.remove(discard);
            }


            // Acquire the char at right
            char acquire = s.charAt(right);   // r moves ahead
            // put it on the map/update its frequency
            sMap.put(acquire, sMap.getOrDefault(acquire, 0) + 1);

            //check if the new window matches with pattern
            if (sMap.equals(pMap)) {
                ans.add(left);  // add the left bound of the window
            }

            //move to next window
            left++;
            right++;
        }

        return ans;
    }
}
