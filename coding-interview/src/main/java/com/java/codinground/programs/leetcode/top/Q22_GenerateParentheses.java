package com.java.codinground.programs.leetcode.top;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. Generate Parentheses
 *
 * Problem Description
 * The problem requires us to generate all possible combinations of well-formed parentheses
 * given n pairs. A well-formed combination of parentheses means that
 * each opening bracket "(" has a corresponding closing bracket ")", and they are correctly nested.
 * To better understand, for n=3, one such correct combination would be "((()))",
 * whereas "(()" or "())(" would be incorrect formations.
 *
 * Intuition
 * To arrive at the solution, we need to think about how we can ensure we create well-formed parentheses.
 * For that, we use Depth First Search (DFS), which is a recursive method to explore
 * all possible combinations of the parentheses.
 *
 * We start with an empty string and at each level of the recursion we have two choices:
 * add an opening parenthesis "(" or a closing parenthesis ")".
 * However, we have to maintain the correctness of the parentheses.
 * This means we cannot add a closing parenthesis if there are not enough opening ones that need closing.
 * We keep track of the number of opening and closing parentheses used so far.
 * We are allowed to add an opening parenthesis if we have not used all n available opening parentheses.
 * We can add a closing parenthesis if the number of closing parentheses is less than
 * the number of opening parentheses used. This ensures we never have an unmatched closing parenthesis.
 * We continue this process until we have used all n pairs of parentheses.
 * When both the opening and closing parentheses counts equal n, it means we have a valid combination,
 * so we add it to our list of answers.
 * The code uses a helper function dfs which takes 3 parameters:
 * the number of opening and closing parentheses used so far (l and r),
 * and the current combination of parentheses (t).
 *
 * By calling this function and starting our recursion with 0 used opening and closing parenthesis
 * and an empty string, we will explore all valid combinations and store them in the list ans.
 *
 * Example Walkthrough
 * Let's consider a small example where n = 2, meaning we want to generate all combinations
 * of well-formed parentheses for 2 pairs.
 *
 * Step 1: Initial Call
 * The generateParenthesis function begins by making an initial call to
 * dfs with l = 0, r = 0, and t = "" (an empty string).
 *
 * Step 2: First Level Recursive Calls
 * At this stage, we have two choices: add an opening parenthesis or add a closing parenthesis.
 * Since l < n, we can add an opening parenthesis.
 * We cannot add a closing parenthesis yet because r < l is not satisfied (both l and r are 0).
 * So, our recursive calls are:
 *
 * dfs(1, 0, "(")
 *
 * Step 3: Second Level Recursive Calls
 * After the first opening parenthesis is added,
 * we are again at a stage where we can choose to add an opening or closing parenthesis.
 * The string is now "(".
 *
 * For l < n, which is true (1 < 2), we add another opening parenthesis: dfs(2, 0, "((").
 * We still cannot add a closing parenthesis yet as r is not less than l (r < l is not true).
 *
 * Step 4: Third Level Recursive Calls
 * We now have the string "((" and l = 2, r = 0. We cannot add any more opening parentheses
 * because l is not less than n anymore (2 is not less than 2).
 * We must add a closing parenthesis now since r < l is satisfied. We get:
 *
 * dfs(2, 1, "(()")
 *
 * Step 5: Fourth Level Recursive Calls
 * Our current string is "(()" and l = 2, r = 1. We still satisfy the condition r < l,
 * so we can add another closing parenthesis:
 *
 * dfs(2, 2, "(())")
 *
 * Step 6: Base Case Reached
 * Now l = 2 and r = 2, which equals n. We have reached a base case where
 * we have a well-formed combination. This combination "(())" is added to our answer set ans.
 *
 * Backtracking
 * The algorithm will backtrack now and explore other paths,
 * but since n = 2 and we have used all our opening parentheses, there are no more paths to discover.
 *
 * Step 7: Return Result
 * The completed list ans, now containing "(())", is returned.
 *
 * Considering another branch of this example,
 * if we go back to the second level again and instead of adding another opening parenthesis,
 * we decide to add a closing parenthesis:
 *
 * After the first level, we have "(".
 * Add a closing parenthesis: dfs(1, 1, "()"),
 * because we can add a closing parenthesis as r < l.
 * Now, we have l = 1 and r = 1, we can add an opening parenthesis: dfs(2, 1, "()(").
 * We can only add a closing parenthesis now: dfs(2, 2, "()()").
 * So the complete set of combinations for n = 2 is "(())" and "()()".
 *
 *
 */
public class Q22_GenerateParentheses {
    // List to hold all the valid parentheses combinations
    private List<String> answers = new ArrayList<>();
    // The number of pairs of parentheses
    private int maxPairs;

    /**
     * Generates all combinations of n pairs of well-formed parentheses.
     *
     * @param n the number of pairs of parentheses
     * @return a list of all possible combinations of n pairs of well-formed parentheses
     */
    public List<String> generateParenthesis(int n) {
        this.maxPairs = n;
        // Start the depth-first search with initial values for open and close parentheses count
        generate(0, 0, "");
        return answers;
    }

    /**
     * Helper method to generate the parentheses using depth-first search.
     *
     * @param openCount  the current number of open parentheses
     * @param closeCount the current number of close parentheses
     * @param currentString the current combination of parentheses being built
     */
    private void generate(int openCount, int closeCount, String currentString) {
        // Check if the current counts of open or close parentheses exceed maxPairs or if closeCount exceeds openCount
        if (openCount > maxPairs || closeCount > maxPairs || openCount < closeCount) {
            // The current combination is invalid, backtrack from this path
            return;
        }
        // Check if the current combination is a valid complete set of parentheses
        if (openCount == maxPairs && closeCount == maxPairs) {
            // Add the valid combination to the list of answers
            answers.add(currentString);
            return;
        }
        // Explore the possibility of adding an open parenthesis
        generate(openCount + 1, closeCount, currentString + "(");
        // Explore the possibility of adding a close parenthesis
        generate(openCount, closeCount + 1, currentString + ")");
    }
}
