package com.java.codinground.programs.leetcode.top;

/**
 * Problem Description
 * The "count-and-say" problem requires us to generate a sequence of digit strings where each term is derived from its predecessor by a process that mimics "counting and saying" the numbers of consecutive digits. Specifically, it follows these rules:
 *
 * countAndSay(1) is defined to be the string "1".
 * For countAndSay(n) where n > 1, we consider the digit string of countAndSay(n-1), then "count and say" the digits to form a new string.
 * To "count and say" the string, we go through the string from left to right, identify consecutive runs of the same digit, and for each run, we first say the quantity of digits, followed by the digit itself. This is then concatenated to give us the next number in the sequence.
 *
 * For example, if we have a digital string "3322251":
 *
 * There are two '3's, so we say "23".
 * Then we have three '2's, so we say "32".
 * Next is one '5', so we say "15".
 * Lastly, there's one '1', so we say "11".
 * Putting it all together, we "say" "23321511", which becomes our "count and say" string for the next number in the sequence.
 *
 * The challenge asks us to find the nth term of this sequence given a positive integer n.
 *
 * Intuition
 * The intuition for solving this problem lies in understanding the "count and say" mechanism—specifically, how each term in the sequence is derived from the previous one. Here's how we can approach the solution:
 *
 * Initialize the sequence with the base case—"1" for countAndSay(1).
 * To generate each subsequent term, iterate over the current string while keeping track of the count of identical consecutive digits encountered.
 * We move along the string and, for each unique digit (or when the next digit is different from the current), we append the count followed by the digit to the result for this iteration.
 * The result of this iteration becomes the input for the next, allowing us to build up the sequence term by term.
 * This intuition is reflected in the solution, which uses a loop to construct each term using the process described above, iterating n-1 times to reach the nth term.
 *
 *
 */
public class Q38_CountAndSay {
    public String countAndSay(int n) {
        String s = "1";
        for(int i = 1; i < n; i++){
            s = countIdx(s);
        }
        return s;
    }

    public String countIdx(String s){
        StringBuilder sb = new StringBuilder();
        char c = s.charAt(0);
        int count = 1;
        for(int i = 1; i < s.length(); i++){
            if(s.charAt(i) == c){
                count++;
            }
            else
            {
                sb.append(count);
                sb.append(c);
                c = s.charAt(i);
                count = 1;
            }
        }
        sb.append(count);
        sb.append(c);
        return sb.toString();
    }
}