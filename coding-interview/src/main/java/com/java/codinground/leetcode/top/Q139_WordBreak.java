package com.java.codinground.leetcode.top;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Problem Description
 * The LeetCode problem asks us to determine if a given string s can be split into a sequence of one or more words that exist in a given dictionary wordDict. To put it simply, the problem is asking whether we can break the string s into chunks where each chunk matches a word in the provided list of words (which we call a dictionary). A word from the dictionary can be used multiple times if needed.
 *
 * For example, if s is "leetcode" and wordDict contains "leet" and "code", then the answer is true since "leetcode" can be segmented into "leet code".
 *
 * Intuition
 * The intuition behind the solution is to use Dynamic Programming (DP), which is a method for solving complex problems by breaking them down into simpler subproblems. The idea here is that if we can break the string s up to a given point, then we can independently check the remainder of the string for other words from wordDict. We can cache results to avoid redundant computations for the same substrings.
 *
 * One way to visualize this solution is by thinking of a chain. If breaking the chain at a given link (character of string s) gives us two parts, where the left part has been seen before and is confirmed to be composed of words in wordDict, and the right part is a known dictionary word, then the entire chain up to that point can be composed of dictionary words.
 *
 * In the solution provided, the list f is used to store the results of the subproblems. Here's what it represents:
 *
 * f[0] is True because a string with no characters can trivially be segmented (it's an empty sequence, which is considered a valid segmentation).
 *
 * f[i] for i>0 is True if and only if the string up to the i-th character can be segmented into one or more of the dictionary words. So f[i] is set to True if, for any j where 0 <= j < i, f[j] is True and the substring s[j:i] is in wordDict.
 *
 * The algorithm loops over the length of the string, checking at each character if there is a valid word ending at this character and, simultaneously, if the string before this word can be split into valid words. If both these conditions are ever fulfilled at some index i, we set f[i] to True.
 *
 * By the time we reach the end of the string if f[n] (where n is the length of the string) is True, we know the entire string can be split up according to the wordDict. Otherwise, f[n] will be False, indicating that the string s cannot be segmented into a sequence of one or more dictionary words.
 *
 * Solution Approach
 * The solution utilizes dynamic programming, which involves solving smaller subproblems and using their solutions to effectively solve larger problems. In this context, the subproblems are answering the question: "Can the string up to the i-th character be segmented into a sequence of dictionary words?"
 *
 * Let's delve into the algorithm and the data structures used:
 *
 * We start by initializing a set words from wordDict for fast lookups of words in the dictionary.
 *
 * An array f is created with a size of n+1 where n is the length of the string s. The array f is initialized to all False except for f[0] which is True. This represents that it's always possible to segment an empty string.
 *
 * We then iterate over the string s from the first character to the last, checking at each point whether the string up to that point can be segmented. This is achieved by using a nested loop, where the outer loop iterates from 1 to n and represents the end of the current substring being checked (i), and the inner loop (j) iterates from 0 to i-1 and represents different start points of the substring.
 *
 * At each position i, we check all possible substrings s[j:i] where j ranges from 0 up to i-1. For a substring s[j:i] to be valid, two conditions must be met:
 *
 * The substring s[j:i] must be in words.
 * The f[j] entry must be True, signifying that the string can be segmented up to the j-th character.
 * If both conditions are satisfied for any j, we set f[i] to True because we've found that the string can be segmented up to the i-th character.
 *
 * Finally, f[n] indicates whether the entire string can be segmented. If it's True, then the string can be split into a valid sequence of dictionary words, and we return True. If it's False, then there is no way to split the entire string into valid dictionary words, and we return False.
 *
 * In summary, this problem is efficiently solved by breaking it down into smaller parts, solving each part individually, and then combining those solutions to find the answer to the original problem. The any() function in Python provides a succinct way to check whether any substring s[j:i] fulfills our two conditions, making the code more concise and readable.
 *
 * Example Walkthrough
 * Let's assume s = "applepenapple" and wordDict = ["apple", "pen"]. We want to check if s can be split into words contained in wordDict.
 *
 * Initialize the set words from wordDict for quick lookups:
 *
 * words = {"apple", "pen"}
 * Initialize the array f with False and set f[0] = True (an empty string can always be segmented):
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
