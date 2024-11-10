package com.roydebnath.coding.leetcode.neetode.dfs_backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 131. Palindrome Partitioning
 * Medium
 *
 * Topics
 * Companies
 * Given a string s, partition s such that every
 * substring
 *  of the partition is a
 * palindrome
 * . Return all possible palindrome partitioning of s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aab"
 * Output: [["a","a","b"],["aa","b"]]
 * Example 2:
 *
 * Input: s = "a"
 * Output: [["a"]]
 *
 *  if the input string is "aab", then there are two ways to partition it into substrings that are all palindromes: ["aa", "b"] and ["a", "a", "b"].
 *
 * The core idea behind the solution is to use depth-first search (DFS). We want to explore all possible partitions and whenever we encounter a partition where all substrings are palindromes, we add that partition to our answer.
 *
 * Here, DFS is used to recursively build partitions. Starting from the beginning of the string, we check every possible end index for a substring that could be a palindrome. If a substring from the current start index to the current end index is a palindrome, then we add it to the current partition list and invoke DFS from the next index. If it's not, we skip the current index and move to the next.
 *
 * To optimize the identification of palindromes, we use dynamic programming. A 2D table f is maintained where f[i][j] is True if the substring from index i to j is a palindrome. The elements in this table are filled in by checking if the two ends of the substring are equal and if the inside substring (from i+1 to j-1) is also a palindrome.
 *
 * The solution uses a DFS algorithm combined with dynamic programming to efficiently find all possible palindrome partitioning of the string s. Let's walk through the key parts of the implementation.
 *
 * Firstly, the dynamic programming table f is prepared, which is a square matrix, where f[i][j] indicates whether the substring from i to j (inclusive) is a palindrome. This precomputation avoids redundant checks and optimizes the process of verifying palindromes during the DFS traversal.
 *
 * Here's how the table f is populated:
 *
 * Initialize f as an n x n matrix filled with True, as every single character is a palindrome by itself.
 * The table is then filled in a bottom-up manner. For substrings longer than one character (from n-1 to 0 for i and from i+1 to n for j), we check if the two ends are the same, s[i] == s[j], and if the inside substring f[i+1][j-1] is a palindrome.
 * Once the table is prepared, we use the DFS approach to build the partitions:
 *
 * A recursive function dfs takes an index i as a parameter, indicating the current starting point of the substring being considered.
 * The recursive function works as follows:
 * If i equals the length of the string n, it means we reached the end of the string and thus, have a valid partition we can add to the answer list ans.
 * For other cases, we check all possible end indices j (from i to n-1) and if f[i][j] is True, indicating a palindrome, we append this substring to the current partition list t.
 * We then recursively call dfs(j + 1), which will attempt to find palindrome partitions for the remaining substring.
 * Afterwards, we must backtrack by removing the last substring added to t before moving on to the next index.
 * The DFS is initiated by calling dfs(0), meaning it starts with an empty partition and it looks at the string from the very start. The result is accumulated in the list ans, which eventually contains all the possible palindrome partitionings.
 *
 * Note that ans is a list of lists, where each sublist represents a distinct palindrome partitioning of s. The variable t represents the current state of the partition list under consideration at each step of the DFS.
 *
 * Example Walkthrough
 *
 * Let's walk through an example to illustrate the solution approach using the string "aab".
 *
 * Step 1: First, we prepare the dynamic programming table f. The length of the string s is 3, so our table will be a 3x3 matrix. Initially, f[i][i] (all positions where i == j) are set to True since all characters are palindromes by themselves.
 *
 * 1f = [
 * 2  [True, False, False],
 * 3  [False, True, False],
 * 4  [False, False, True]
 * 5]
 * Step 2: Now we fill the remaining f[i][j] values where i != j. This involves checking substrings of length 2 and above.
 *
 * For i = 1 and j = 2 ("aa"), s[i] is equal to s[j] and the inside substring (which is empty) is "trivially" a palindrome. So, f[1][2] is set to True.
 * For i = 0 and j = 1 ("ab"), s[i] is not equal to s[j], so f[0][1] remains False.
 * For i = 0 and j = 2 ("aab"), s[i] is not equal to s[j], so f[0][2] remains False.
 * Our DP table now looks like this:
 *
 * 1f = [
 * 2  [True, False, False],
 * 3  [False, True, True],  // "aa" is a palindrome
 * 4  [False, False, True]
 * 5]
 * Step 3: We start a DFS traversal to build palindrome partitions.
 *
 * We call dfs(0) and look for all palindromic substrings starting at index 0.
 * For i = 0, we consider substring "a" (f[0][0] is True), and so we add ["a"] to our current partition t and call dfs(1).
 * Inside dfs(1), we again find a palindromic substring "a" (f[1][1] is True), so we add ["a"] to t and call dfs(2).
 * Finally, in dfs(2), we add "b" to t, since f[2][2] is True, and reach the end of the string. We now have ["a", "a", "b"] and add this partition to our answer list ans.
 * We backtrack to dfs(1) and continue the loop for j = 2, now "aa" is a palindrome as f[1][2] is True, so we add ["aa"] to t and call dfs(3).
 * In dfs(3), we've reached the end of the string again, so we now have ["a", "aa"] which is a valid partition that we add to ans.
 * The DFS finishes, and our answer ans contains all possible palindrome partitioning for the input string "aab":
 *
 * 1ans = [
 * 2  ["a", "a", "b"],
 * 3  ["aa", "b"]
 * 4]
 * We successfully obtained all partitions where each substring is a palindrome using a combination of DFS, dynamic programming, and backtracking.
 *
 *
 *
 */
public class Q131_PalindromePartitioning {
    private int stringLength;
    private String inputString;
    private boolean[][] palindromeTable;
    private List<String> currentPartition = new ArrayList<>();
    private List<List<String>> allPartitions = new ArrayList<>();

    public List<List<String>> partition(String s) {
        stringLength = s.length();
        inputString = s;
        palindromeTable = new boolean[stringLength][stringLength];

        // Initialize the palindrome table with true for all entries
        for (int i = 0; i < stringLength; ++i) {
            Arrays.fill(palindromeTable[i], true);
        }

        // Populate the palindrome table with actual palindrome information
        for (int i = stringLength - 1; i >= 0; --i) {
            for (int j = i + 1; j < stringLength; ++j) {
                palindromeTable[i][j] = (s.charAt(i) == s.charAt(j)) && palindromeTable[i + 1][j - 1];
            }
        }

        // Start the depth-first search from the beginning of the string
        performDfs(0);
        return allPartitions;
    }

    private void performDfs(int startIndex) {
        // If the current start index reaches the end of the string, we've found a complete partition
        if (startIndex == inputString.length()) {
            allPartitions.add(new ArrayList<>(currentPartition));
            return;
        }

        // Explore further partitions starting from the current index
        for (int endIndex = startIndex; endIndex < stringLength; ++endIndex) {
            // If the substring starting at startIndex and ending at endIndex is a palindrome
            if (palindromeTable[startIndex][endIndex]) {
                // Add the palindrome substring to the current partition
                currentPartition.add(inputString.substring(startIndex, endIndex + 1));

                // Continue searching for palindromes from the next index after the current palindrome
                performDfs(endIndex + 1);

                // Backtrack and remove the last added palindrome from the current partition
                currentPartition.remove(currentPartition.size() - 1);
            }
        }
    }
}
