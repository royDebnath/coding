package com.java.codinground.blind75;

/**
 * Given two strings s and t of lengths m and n respectively, return the minimum window
 * substring
 * of s such that every character in t (including duplicates) is included in the window.
 * If there is no such substring, return the empty string "".
 * <p>
 * The testcases will be generated such that the answer is unique.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 * Example 2:
 * <p>
 * Input: s = "a", t = "a"
 * Output: "a"
 * Explanation: The entire string s is the minimum window.
 * Example 3:
 * <p>
 * Input: s = "a", t = "aa"
 * Output: ""
 * Explanation: Both 'a's from t must be included in the window.
 * Since the largest window of s only has one 'a', return empty string.
 */
public class X_Q22_MinimumWindowSubstring {
    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }

    public static String minWindow(String master, String pattern) {
        int no_of_chars = 256;
        int masterLength = master.length();
        int patternLength = pattern.length();

        if (masterLength < patternLength) {
            System.out.println("No such window exists");
            return "";
        }

        int patternHash[] = new int[no_of_chars];
        int masterHash[] = new int[no_of_chars];

        // Store occurrence of characters of pattern
        for (int i = 0; i < patternLength; i++)
            patternHash[pattern.charAt(i)]++;

        int start = 0, start_index = -1,
                min_len = Integer.MAX_VALUE;

        int count = 0; // count of character in master matched with pattern
        // Start traversing the master string
        for (int j = 0; j < masterLength; j++) {

            // Count occurrence of characters of string
            masterHash[master.charAt(j)]++;

            // If string's char matches
            // with pattern's char
            // then increment count
            if (masterHash[master.charAt(j)] <= patternHash[master.charAt(j)]) // if char is not in pattern hash for pat would be 0, master hash would be always 1 or more
                count++;

            // In case the pattern is found the hash values in both and master and pattern would be same in mathcing positions and count will be = patternLength
            // If all the characters are matched
            if (count == patternLength) {

                // Try to minimize the window
                while (masterHash[master.charAt(start)] > patternHash[master.charAt(start)]
                        || patternHash[master.charAt(start)] == 0 ) {

                    if (masterHash[master.charAt(start)] > patternHash[master.charAt(start)]){
                        masterHash[master.charAt(start)]--;
                    }

                    start++;
                }

                // update window size
                int len_window = j - start + 1;
                if (min_len > len_window) {
                    min_len = len_window;
                    start_index = start;
                }
            }
        }

        // If no window found
        if (start_index == -1) {
            System.out.println("No such window exists");
            return "";
        }

        // Return substring starting
        // from start_index
        // and length min_len
        return master.substring(start_index,
                start_index + min_len);
    }
}