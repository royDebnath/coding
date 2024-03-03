package com.java.codinground.programs.leetcode.top100;

/**
 * Understanding problem:
 * image
 *
 * In simple terms all the characters in s1 have to be continiously present in s2.
 *
 * Intuition and Approaches:
 * Pick first 3 char from s2 i.e xyz and then next 3 char and so on
 * xyz have abc--no
 * yzb have abc -- no
 * zba have abc --no
 * bac -- abc --yes How did we say this?? The count of each char is same i.e a == 1 and b == 1 and c==1.
 *
 * We will use the above approach using a hashMap(changed into array next)
 * We store the count of each letter of s1 in a hashMap1
 * We store the count of each letter(only 3 at a time) of s2 in hashMap2
 * We compare both the hashMaps and if they are same the ans is true
 *
 * image
 * image
 *
 * Sliding window Optimization:
 * Instead of Calculating the count in s2 every time if you look closely, only 2 char count are changing each time.
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
