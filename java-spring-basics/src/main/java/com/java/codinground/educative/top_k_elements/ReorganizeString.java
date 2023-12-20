package com.java.codinground.educative.top_k_elements;

import java.util.PriorityQueue;

/**
 * 767. Reorganize String
 * Medium
 * 8K
 * 232
 * Companies
 * Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
 *
 * Return any possible rearrangement of s or return "" if not possible.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aab"
 * Output: "aba"
 * Example 2:
 *
 * Input: s = "aaab"
 * Output: ""
 *
 * Approach
 * Count the frequency of each character in the input string.
 * Check if the frequency of any character exceeds half the length of the string. If it does, it's not possible to reorganize the string, so return an empty string.
 * Build a priority queue (max heap) of character frequencies and characters themselves.
 * While the priority queue has at least two elements:
 * Pop the two characters with the highest frequencies from the priority queue.
 * Append these characters to the result string.
 * Decrease their frequencies by 1 and push them back to the priority queue if their frequency is still greater than 1.
 * If there's one character left in the priority queue, append it to the result string.
 * Return the result string as the reorganized string.
 * Explanation
 * The algorithm ensures that characters with the highest frequencies are placed as far apart as possible in the reorganized string. This prevents adjacent characters from being the same, satisfying the problem's condition.
 *
 * By using a priority queue, we always have access to the character with the highest frequency. We repeatedly select the two characters with the highest frequencies and alternate between them to construct the reorganized string.
 *
 * The check for a character's frequency exceeding half the length of the string is crucial. If a character appears more frequently than that, it would be impossible to avoid having adjacent characters, making the reorganization impossible.
 *
 * Complexity
 * Time complexity:O(nlogn)O(nlogn)O(nlogn)
 * Space complexity:O(n)O(n)O(n)
 *
 *
 */
public class ReorganizeString {
        public String reorganizeString(String s) {
            int[] f = new int[26];
            int n = s.length();

            for (int i = 0; i < n; i++) {
                f[s.charAt(i) - 'a']++;
                if (f[s.charAt(i) - 'a'] > (n + 1) / 2) {
                    return "";
                }
            }

            PriorityQueue<Pair> p = new PriorityQueue<>((a, b) -> b.freq - a.freq);
            for (int i = 0; i < 26; i++) {
                if (f[i] != 0) {
                    p.offer(new Pair(f[i], (char) (i + 'a')));
                }
            }

            StringBuilder ans = new StringBuilder();
            while (p.size() >= 2) {
                Pair p1 = p.poll();
                Pair p2 = p.poll();
                ans.append(p1.ch);
                ans.append(p2.ch);
                if (p1.freq > 1) {
                    p.offer(new Pair(p1.freq - 1, p1.ch));
                }
                if (p2.freq > 1) {
                    p.offer(new Pair(p2.freq - 1, p2.ch));
                }
            }

            if (!p.isEmpty()) {
                ans.append(p.poll().ch);
            }

            return ans.toString();
        }

    class Pair {
        int freq;
        char ch;

        Pair(int freq, char ch) {
            this.freq = freq;
            this.ch = ch;
        }
    }
}
