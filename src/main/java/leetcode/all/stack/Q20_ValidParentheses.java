package leetcode.all.stack;

import java.util.Stack;

/**
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * <p>
 * An input string is valid if:
 * <p>
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "()"
 * Output: true
 * Example 2:
 * <p>
 * Input: s = "()[]{}"
 * Output: true
 * Example 3:
 * <p>
 * Input: s = "(]"
 * Output: false
 */
public class Q20_ValidParentheses {
    public static void main(String[] args) {
        System.out.println(validParentheses("([}}])"));
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
