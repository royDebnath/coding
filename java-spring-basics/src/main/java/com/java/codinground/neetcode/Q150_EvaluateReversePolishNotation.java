package com.java.codinground.neetcode;

import java.util.Stack;

/**
 * 150. Evaluate Reverse Polish Notation
 * Medium
 * 6.3K
 * 921
 * Companies
 * You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.
 *
 * Evaluate the expression. Return an integer that represents the value of the expression.
 *
 * Note that:
 *
 * The valid operators are '+', '-', '*', and '/'.
 * Each operand may be an integer or another expression.
 * The division between two integers always truncates toward zero.
 * There will not be any division by zero.
 * The input represents a valid arithmetic expression in a reverse polish notation.
 * The answer and all the intermediate calculations can be represented in a 32-bit integer.
 *
 *
 * Example 1:
 *
 * Input: tokens = ["2","1","+","3","*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 * Example 2:
 *
 * Input: tokens = ["4","13","5","/","+"]
 * Output: 6
 * Explanation: (4 + (13 / 5)) = 6
 * Example 3:
 *
 * Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * Output: 22
 * Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 *
 * Solution :
 *
 * The Reverse Polish Notation is a stack of operations, thus,
 * I decided to use java.util.Stack to solve this problem. As you can see,
 * I add every token as an integer in the stack, unless it's an operation.
 * In that case, I pop two elements from the stack and then save the result back to it.
 * After all operations are done through, the remaining element in the stack will be the result.
 * Any comments or improvements are welcome.
 */
public class Q150_EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        int a,b;
        Stack<Integer> S = new Stack<Integer>();
        for (String s : tokens) {
            if(s.equals("+")) {
                S.add(S.pop()+S.pop());
            }
            else if(s.equals("/")) {
                b = S.pop();
                a = S.pop();
                S.add(a / b);
            }
            else if(s.equals("*")) {
                S.add(S.pop() * S.pop());
            }
            else if(s.equals("-")) {
                b = S.pop();
                a = S.pop();
                S.add(a - b);
            }
            else {
                S.add(Integer.parseInt(s));
            }
        }
        return S.pop();
    }
}
