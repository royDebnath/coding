package com.java.codinground.programs.leetcode.top100;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. Generate Parentheses
 * Medium
 * Topics
 * Companies
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 * Example 2:
 * <p>
 * Input: n = 1
 * Output: ["()"]
 * <p>
 * Solution :
 * <p>
 * The idea is to add ')' only after valid '('
 * We use two integer variables left & right to see how many '(' & ')' are in the current string
 * If left < n then we can add '(' to the current string
 * If right < left then we can add ')' to the current string
 * <p>
 * <p>
 * (0, 0, '')
 * |
 * (1, 0, '(')
 * /           \
 * (2, 0, '((')      (1, 1, '()')
 * /                 \
 * (2, 1, '(()')           (2, 1, '()(')
 * /                       \
 * (2, 2, '(())')                (2, 2, '()()')
 * |	                             |
 * res.append('(())')             res.append('()()')
 */
public class Q22_GenerateParentheses {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        recurse(res, 0, 0, "", n);
        return res;
    }

    public void recurse(List<String> res, int left, int right, String s, int n) {
        if (s.length() == n * 2) {
            res.add(s);
            return;
        }

        if (left < n) {
            recurse(res, left + 1, right, s + "(", n);
        }

        if (right < left) {
            recurse(res, left, right + 1, s + ")", n);
        }
    }
    // See above tree diagram with parameters (left, right, s) for better understanding
}
