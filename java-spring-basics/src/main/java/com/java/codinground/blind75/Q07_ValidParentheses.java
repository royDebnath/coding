package com.java.codinground.blind75;

import java.util.Stack;

/**
 * 20. Valid Parentheses
 * Easy
 *
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Every close bracket has a corresponding open bracket of the same type.
 *
 * Example 1:
 *
 * Input: s = "()"
 * Output: true
 * Example 2:
 *
 * Input: s = "()[]{}"
 * Output: true
 * Example 3:
 *
 * Input: s = "(]"
 * Output: false
 *
 *
 */
public class Q07_ValidParentheses {
    public static void main(String[] args) {
        System.out.println(validParentheses("[{}]]"));
    }

    private static boolean validParentheses(String s) {
        if (s.length() % 2 != 0) return false;
        Stack<Character> stack = new Stack();
        for (char bracket : s.toCharArray()) {
            if (bracket == ']' && !stack.isEmpty() && stack.peek() == '[') {
                stack.pop();
            } else if (bracket == ')' && !stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else if (bracket == '}' && !stack.isEmpty() && stack.peek() == '{') {
                stack.pop();
            } else {
                stack.push(bracket);
            }
        }
        return stack.isEmpty();
    }
}
