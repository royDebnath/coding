package com.java.codinground.leetcode.top;

/**
 * Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
 *
 * In other words, return true if one of s1's permutations is the substring of s2.
 *
 *
 *
 * Example 1:
 *
 * Input: s1 = "ab", s2 = "eidbaooo"
 * Output: true
 * Explanation: s2 contains one permutation of s1 ("ba").
 * Example 2:
 *
 * Input: s1 = "ab", s2 = "eidboaoo"
 * Output: false
 *
 *
 *
 * Sliding window Optimization:
 * Instead of Calculating the count in s2 every time if you look closely,
 * only 2 char count are changing each time.
 * i.e xyz -- yzb b count increase by 1 and x count decrease by 1. So instead of recalculating every char just change what is added and what is deleted.
 * If at any point hashMap1 == hasMap2 (counts) return true
 *
 * Further optimization using array:
 * Instead of using hashMap use an array of size 26, since its only lower case to store the count.(HashMap takes some time to insert and compare)
 * image
 *
 * Approach:
 * 1. Initialize s1count array and s2 count array of size 26 or a hashMap if you prefer.
 * 2. k = s1.length();
 * 3. for 0 to k change the count of char of both s1count and s2count.
 * 4. Check if both matches. if yes return true.
 * 5. for char from i = k increase count of char at i by 1 and decrease i-k by 1
 * 6. Check if both matches. if yes return true. at each step
 *
 * If matches: Checks all 26 char count to be same.
 *
 *
 */
public class Q567_PermutationInString {
    class Solution {
        public boolean checkInclusion(String s1, String s2) {
            if (s1.length() > s2.length())
                return false;
            int[] s1count = new int[26];
            int[] s2count = new int[26];
            int k = s1.length();
            for(int i = 0;i<k;i++){
                s1count[s1.charAt(i) - 'a']++;
                s2count[s2.charAt(i) - 'a']++;
            }
            if(matches(s1count,s2count)) return true;
            for(int i = k;i<s2.length();i++){
                s2count[s2.charAt(i)- 'a']++;
                s2count[s2.charAt(i-k) - 'a']--;
                if(matches(s1count,s2count)) return true;
            }
            return matches(s1count, s2count);
        }

        public boolean matches(int[] s1count, int[] s2count) {
            for (int i = 0; i < 26; i++) {
                if (s1count[i] != s2count[i])
                    return false;
            }
            return true;
        }
    }
}
