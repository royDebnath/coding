package com.java.codinground.educative.sliding_window;

import java.util.*;

/**
 * 187. Repeated DNA Sequences
 * Medium
 * 3.1K
 * 498
 * Companies
 * The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.
 *
 * For example, "ACGAATTCCG" is a DNA sequence.
 * When studying DNA, it is useful to identify repeated sequences within the DNA.
 *
 * Given a string s that represents a DNA sequence, return all the 10-letter-long sequences (substrings)
 * that occur more than once in a DNA molecule. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * Output: ["AAAAACCCCC","CCCCCAAAAA"]
 * Example 2:
 *
 * Input: s = "AAAAAAAAAAAAA"
 * Output: ["AAAAAAAAAA"]
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s[i] is either 'A', 'C', 'G', or 'T'.
 */
public class RepeatedDNASequences {
    public static void main(String[] args) {
        System.out.println(findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
    }
    public static List<String> findRepeatedDnaSequences(String input) {
        if(input == null || input.length() < 10 || input.length() > 10000) {
            return Collections.emptyList();
        }
        int start = 0;
        int end = 10;
        Set<String> visited = new HashSet<>();
        Set<String> result = new HashSet<>();
        while(end <= input.length()) {
            String current = input.substring(start,end);
            if(visited.contains(current)) {
                result.add(current);
            }
            visited.add(current);
            start++;
            end++;
        }
        return new ArrayList<>(result);
    }
}