package com.roydebnath.coding.leetcode.all.dynamic_programming;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Problem Description
 * determine if a given string s can be split into a sequence of one or more words that exist
 * in a given dictionary wordDict. To put it simply, the problem is asking whether
 * we can break the string s into chunks where each chunk matches a word in the provided list of words
 * (which we call a dictionary). A word from the dictionary can be used multiple times if needed.
 *
 * For example, if s is "leetcode" and wordDict contains "leet" and "code", then the answer is true
 * since "leetcode" can be segmented into "leet code".
 *
 * Intuition
 * The idea here is that if we can break the string s up to a given point,
 * then we can independently check the remainder of the string for other words from wordDict.
 * We can cache results to avoid redundant computations for the same substrings.
 *
 * One way to visualize this solution is by thinking of a chain.
 * If breaking the chain at a given link (character of string s) gives us two parts,
 * where the left part has been seen before and is confirmed to be composed of words in wordDict,
 * and the right part is a known dictionary word,
 * then the entire chain up to that point can be composed of dictionary words.
 *
 * Solution Approach
 * The solution utilizes dynamic programming, which involves solving smaller subproblems and
 * using their solutions to effectively solve larger problems. In this context,
 * the subproblems are answering the question:
 * "Can the string up to the i-th character be segmented into a sequence of dictionary words?"
 **
 * We start by initializing a set words from wordDict for fast lookups of words in the dictionary.
 *
 * An array wordBreakTracker is created with a size of n+1 where n is the length of the string s.
 * The array wordBreakTracker is initialized to all False except for f[0] which is True.
 * This represents that it's always possible to segment an empty string.
 *
 * We then iterate over the string s from the first character to the last,
 * checking at each point whether the string up to that point can be segmented.
 * This is achieved by using a nested loop, where the outer loop iterates from 1 to n and represents
 * the end of the current substring being checked (i), and the inner loop (j) iterates from 0 to i-1
 * and represents different start points of the substring.
 *
 * At each position i, we check all possible substrings s[j:i] where j ranges from 0 up to i-1.
 * For a substring s[j:i] to be valid, two conditions must be met:
 *
 * The substring s[j:i] must be in words.
 * The wordBreakTracker[j] entry must be True, signifying that the string can be segmented up to
 * the j-th character.
 * If both conditions are satisfied for any j, we set wordBreakTracker[i] to True because
 * we've found that the string can be segmented up to the i-th character.
 *
 * Finally, wordBreakTracker[n] indicates whether the entire string can be segmented.
 * If it's True, then the string can be split into a valid sequence of dictionary words,
 * and we return True.
 *
 *
 * Example Walkthrough
 * Let's assume s = "applepenapple" and wordDict = ["apple", "pen"].
 * We want to check if s can be split into words contained in wordDict.
 *
 * Initialize the set words from wordDict for quick lookups:
 *
 * words = {"apple", "pen"}
 * Initialize the array wordBreakTracker with False and set f[0] = True
 * (an empty string can always be segmented):
 *
 * f = [False] * (len(s) + 1)
 * f[0] = True
 * Then we iterate over the length of the string s from 1 through n, which is 15 in this case for "applepenapple".
 *
 * For each i from 1 to 15, we will check all substrings s[j:i] for j from 0 up to i-1.
 *
 * Consider the case when i = 5, we are looking at the substring "apple":
 *
 * Is "apple" (s[0:5]) in words? Yes, it is.
 * Is f[0] True? Yes, because we initialized it as such, indicating that up to the previous characters (none in this case), the string can be segmented.
 * Therefore, f[5] becomes True.
 * Move to i = 8, the substring is "pen" (s[5:8]):
 *
 * Is "pen" in words? Yes, it is.
 * Is f[5] True? Yes, because we know "apple" can be segmented till i = 5.
 * We set f[8] to True.
 * At i = 15, our substring is "apple" again (s[8:15]):
 *
 * Is "apple" in words? Yes.
 * Is f[8] True? Yes, due to previous segments.
 * f[15] is set to True.
 * Finally, we check f[n], which is f[15] in this case. It is True, which means "applepenapple" can be segmented as "apple pen apple", all words from wordDict.
 *
 * By following these steps, we have broken down the problem to show how s can be segmented using wordDict, illustrating the dynamic programming approach described in the solution.
 *
 *
 *
 */
public class Q139_WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        // Convert the list of words into a hash set for efficient look-up
        Set<String> wordSet = new HashSet<>(wordDict);

        // Get the length of the string 's'
        int strLength = s.length();

        // Initialize a boolean array to keep track of possible word breaks
        // f[i] is true if first i characters of the string can be segmented into dictionary words
        boolean[] wordBreakTracker = new boolean[strLength + 1];

        // Empty string is a valid decomposition
        wordBreakTracker[0] = true;

        // Check each substring starting from length 1 to strLength
        for (int i = 1; i <= strLength; ++i) {

            // Try different break points
            for (int j = 0; j < i; ++j) {

                // If the string up to j can be broken into valid words, and the substring from j to i is in the dictionary
                // Then, mark the position i as true in wordBreakTracker
                if (wordBreakTracker[j] && wordSet.contains(s.substring(j, i))) {
                    wordBreakTracker[i] = true;

                    // Break out of the loop since we have found a valid break point
                    break;
                }
            }
        }

        // The last entry in wordBreakTracker tells if the entire string can be segmented or not
        return wordBreakTracker[strLength];
    }
}
