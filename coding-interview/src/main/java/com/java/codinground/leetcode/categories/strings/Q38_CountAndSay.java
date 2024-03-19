package com.java.codinground.leetcode.categories.strings;

/**
 * Problem Description
 *
 * if we have a digital string "3322251":
 *
 * There are two '3's, so we say "23".
 * Then we have three '2's, so we say "32".
 * Next is one '5', so we say "15".
 * Lastly, there's one '1', so we say "11".
 * Putting it all together, we "say" "23321511", which becomes our "count and say" string
 * for the next number in the sequence.
 *
 * The challenge asks us to find the nth term of this sequence given a positive integer n.
 *
 * Example 2:
 *
 * Input: n = 4
 * Output: "1211"
 * Explanation:
 * countAndSay(1) = "1"
 * countAndSay(2) = say "1" = one 1 = "11"
 * countAndSay(3) = say "11" = two 1's = "21"
 * countAndSay(4) = say "21" = one 2 + one 1 = "12" + "11" = "1211"
 *
 * Intuition
 * The intuition for solving this problem lies in understanding the "count and say" mechanism—
 * specifically, how each term in the sequence is derived from the previous one.
 * Here's how we can approach the solution:
 *
 * Initialize the sequence with the base case—"1" for countAndSay(1).
 *
 * To generate each subsequent term, iterate over the current string while
 * keeping track of the count of identical consecutive digits encountered.
 *
 * We move along the string and, for each unique digit (or when the next digit is different from the current),
 * we append the count followed by the digit to the result for this iteration.
 * The result of this iteration becomes the input for the next,
 * allowing us to build up the sequence term by term.
 * This intuition is reflected in the solution, which uses a loop to construct
 * each term using the process described above, iterating n-1 times to reach the nth term.
 *
 *
 */
public class Q38_CountAndSay {
    public static void main(String[] args) {
        System.out.println(countAndSay(4));
    }
    public static String countAndSay(int n) {
        String s = "1";
        for(int i = 1; i < n; i++){
            s = countIdx(s);
        }
        return s;
    }

    public static String countIdx(String s){
        StringBuilder result = new StringBuilder();
        char previous = s.charAt(0);
        int count = 1;
        for(int i = 1; i < s.length(); i++){
            char current = s.charAt(i);
            if(current == previous){
                count++;
            }
            else
            {
                result.append(count);
                result.append(previous);

                //initialize for the next loop
                previous = current;
                count = 1;
            }
        }

        // add the last character
        result.append(count);
        result.append(previous);

        return result.toString();
    }
}