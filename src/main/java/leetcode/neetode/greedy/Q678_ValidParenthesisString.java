package leetcode.neetode.greedy;

/**
 * 678. Valid Parenthesis String
 * Medium
 *
 * Topics
 * Companies
 *
 * Hint
 * Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.
 *
 * The following rules define a valid string:
 *
 * Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 * Any right parenthesis ')' must have a corresponding left parenthesis '('.
 * Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 * '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".
 *
 *
 * Example 1:
 *
 * Input: s = "()"
 * Output: true
 * Example 2:
 *
 * Input: s = "(*)"
 * Output: true
 * Example 3:
 *
 * Input: s = "(*))"
 * Output: true
 *
 * Intuition
 *
 * To solve the problem, we have to deal with the ambiguity of the '*', determining when it should act as a '(', a ')', or be ignored as an empty string ' "" '.
 *
 * The algorithm employs two passes to ensure that each parenthesis is paired up. In the first pass, we treat every '' as '(', increasing our "balance" or counter whenever we encounter a '(' or '', and decreasing it when we encounter a ')'. If our balance drops to zero, it means we have matched all parentheses thus far. However, if it drops below zero, there are too many ')' characters without a matching '(' or '*', so the string can't be valid.
 *
 * On the second pass, we reverse the string and now treat every '' as ')'. We then perform a similar count, increasing the balance when we see a ')' or '', and decreasing it for '('. If the balance drops below zero this time, it means that there are too many '(' characters without a matching ')' or '*', and the string is again invalid.
 *
 * If we complete both passes without the balance going negative, it means that for every '(' there is a corresponding ')', and the string is valid. We have effectively managed the ambiguity of '*' by checking that they can serve as a viable substitute for whichever parenthesis is needed to complete a valid set.
 *
 * Our method ensures that all '(' have a corresponding ')', and vice versa, by treating '*' as placeholder for the correct parenthesis needed, thus validating the input string.
 *
 * Example Walkthrough
 *
 * Let's take the string s = "(*))" as an example to illustrate the solution approach.
 *
 * First Pass (Left to Right):
 *
 * We will initialize our balance counter x to 0. We iterate through the string one character at a time.
 *
 * s[0] = '(': Since it is a '(', we increment x to 1.
 * s[1] = '*': Since it is a '*', it could be '(', ')', or empty. We treat it as '(', so we increment x to 2.
 * s[2] = ')': We have a ')', so this could potentially pair with the previous '(' or '*'. We decrement x to 1.
 * s[3] = ')': Another ')'. It can pair with the '(' or '*' we assumed in step 2. We decrement x to 0.
 * At the end of this pass, x is not negative, which means that we have not encountered a ')' that could not be matched with a previous '(' or '*' treated as '('.
 *
 * Second Pass (Right to Left):
 *
 * Now, we will reset x to 0 and traverse from right to left, treating '*' as ')'.
 *
 * s[3] = ')': We increment x to 1 since it could pair with an '(', or a '*', treated as '('.
 * s[2] = ')': We increment x to 2—similar reasoning as step 1.
 * s[1] = '*': Treating it as ')', we increment x to 3.
 * s[0] = '(': We have an '(', so we pair it with one of the ')' or '*' we treated as ')'. We decrement x to 2.
 * After the second pass, x is not negative, confirming that we have never encountered a '(' character that could not be matched with a subsequent ')' or '*' treated as ')'.
 *
 * Since we finished both passes without the balance x going negative, we can conclude that the string s = "(*))" can be rearranged into a valid sequence. Therefore, our function would return true for this input.
 *
 */
public class Q678_ValidParenthesisString {

    // Method to check if a string with parentheses and asterisks (*) is valid
    public boolean checkValidString(String s) {
        int balance = 0; // This will keep track of the balance of open parentheses
        int n = s.length(); // Length of the input string

        // First pass goes from left to right
        for (int i = 0; i < n; ++i) {
            char currentChar = s.charAt(i);
            if (currentChar != ')') {
                // Increment balance for '(' or '*'
                ++balance;
            } else if (balance > 0) {
                // Decrement balance if there is an unmatched '(' before
                --balance;
            } else {
                // A closing ')' without a matching '('
                return false;
            }
        }

        // Reset balance for the second pass
        balance = 0;

        // Second pass goes from right to left
        for (int i = n - 1; i >= 0; --i) {
            char currentChar = s.charAt(i);
            if (currentChar != '(') {
                // Increment balance for ')' or '*'
                ++balance;
            } else if (balance > 0) {
                // Decrement balance if there is an unmatched ')' after
                --balance;
            } else {
                // An opening '(' without a matching ')'
                return false;
            }
        }

        // If we did not return false so far, the string is valid
        return true;
    }
}
