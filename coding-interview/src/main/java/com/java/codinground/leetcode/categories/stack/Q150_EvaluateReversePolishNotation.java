package com.java.codinground.leetcode.categories.stack;

import java.util.Stack;

/**
 * 150. Evaluate Reverse Polish Notation
 * Solved
 * Medium
 *
 * Topics
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
 * Problem Description
 *
 * You are asked to evaluate an arithmetic expression provided as an array of strings, tokens, which uses Reverse Polish Notation (RPN). This notation places the operator after the operands. For example, the expression "3 4 +" in RPN is equivalent to "3 + 4" in standard notation. Your task is to calculate the result of the expression and return the resulting integer value.
 *
 * Several points to consider for this problem are:
 *
 * The expression only contains the operators +, -, *, and /.
 * Operands could be either integers or sub-expressions.
 * When performing division, the result is always truncated towards zero.
 * The expression does not contain any division by zero.
 * The expression given is always valid and can be evaluated without error.
 * The result of the evaluated expression and any intermediate operations will fit within a 32-bit integer.
 * Intuition
 *
 * To solve this problem, we need to understand the nature of Reverse Polish Notation. In RPN, every time we encounter an operator, it applies to the last two operands that were seen. A stack is the perfect data structure for this evaluation because it allows us to keep track of the operands as they appear and then apply the operators in the correct order.
 *
 * The intuition for the solution is as follows:
 *
 * We iterate through each string (token) in the tokens array.
 * If the token is a number (single digit or multiple digits), we convert it to an integer and push it onto a stack.
 * If the token is an operator, we pop the last two numbers from the stack and apply this operator; these two numbers are the operands for the operator.
 * The result of the operation is then pushed back onto the stack.
 * After applying an operator, the stack should be in a state that is ready to process the next token.
 * When we've processed all the tokens, the stack will contain only one element, which is the final result of the expression.
 *
 * Solution Approach
 *
 * The solution makes use of a very simple yet powerful algorithm that utilizes a stack data structure to process the given tokens one by one. Here are the steps it follows:
 *
 * Initialize an empty list nums that will act as a stack to store the operands.
 * Iterate over each token in the tokens array.
 * If the token is a numeric value (identified by either being a digit or having more than one character, which accounts for numbers like "-2"), we convert the token to an integer and push it onto the stack.
 * If the token is an operator (+, -, *, /), we perform the following:
 * Pop the top two elements from the stack. Since the last element added is at the top of the stack, we'll refer to these as the second operand (at nums[-1]) and the first operand (at nums[-2]) in that order.
 * Apply the operator to these two operands. For addition, subtraction, and multiplication, this is straightforward.
 * For division, we apply integer division which is the same as dividing and then applying the int function to truncate towards zero. This is important as it handles the truncation towards zero for negative numbers correctly. The simple floor division operator // in Python truncates towards negative infinity, which can give incorrect results for negative quotients.
 * The result of the operation is then placed back into the stack at the position of the first operand (nums[-2]).
 * The second operand (nums[-1]), which has already been used, is removed from the stack.
 * After processing all the tokens, there should be a single element left in the nums stack. This element is the result of evaluating the expression.
 * The algorithm used here is particularly efficient because it has a linear time complexity, processing each token exactly once. The space complexity of this approach is also linear, as it depends on the number of tokens that are pushed into the stack. The use of the stack ensures that the operands for any operator are always readily available at the top of the stack.
 *
 * Here is a snippet of how the arithmetic operations are processed:
 *
 * Addition: nums[-2] += nums[-1]
 * Subtraction: nums[-2] -= nums[-1]
 * Multiplication: nums[-2] *= nums[-1]
 * Division: nums[-2] = int(nums[-2] / nums[-1])
 * Once finished, the program returns nums[0] as the result of the expression.
 *
 * Example Walkthrough
 *
 * Let's use the following RPN expression as our example: "2 1 + 3 *" which, in standard notation, translates to (2 + 1) * 3.
 *
 * By following the solution approach:
 *
 * We initialize an empty list nums to serve as our stack: nums = [].
 *
 * We iterate through the tokens: ["2", "1", "+", "3", "*"].
 *
 * a. The first token is "2", which is a number. We push it onto the stack: nums = [2].
 *
 * b. The second token is "1", which is also a number. We push it onto the stack: nums = [2, 1].
 *
 * c. The third token is "+", which is an operator. We need to pop the top two numbers and apply the operator: - We pop the first operand: secondOperand = nums.pop(), which is 1. Now nums = [2]. - We pop the second operand: firstOperand = nums.pop(), which is 2. Now nums = []. - We add the two operands: stackResult = firstOperand + secondOperand, which equals 2 + 1 = 3. - We push the result onto the stack: nums = [3].
 *
 * d. The fourth token is "3", a number. We push it onto the stack: nums = [3, 3].
 *
 * e. The fifth token is "*", an operator: - We pop the second operand, secondOperand = nums.pop(), which is 3, leaving nums = [3]. - We pop the first operand, firstOperand = nums.pop(), which is 3, leaving nums = []. - We multiply the two operands: stackResult = firstOperand * secondOperand, which equals 3 * 3 = 9. - We push the result onto the stack: nums = [9].
 *
 * After processing all the tokens, we are left with a single element in our stack nums = [9], which is our result.
 *
 * So, the given RPN expression "2 1 + 3 *" evaluates to 9. Thus, the function would return 9 as the result of the expression.
 *
 */
public class Q150_EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>(); // Create a stack to hold integer values

        // Iterate over each token in the input array
        for (String token : tokens) {
            // Check if the token is a number (either single digit or multi-digit)
            if (token.length() > 1 || Character.isDigit(token.charAt(0))) {
                // Push the number onto the stack
                stack.push(Integer.parseInt(token));
            } else {
                // Pop the top two elements for the operator
                int secondOperand = stack.pop();
                int firstOperand = stack.pop();

                // Apply the operator on the two operands based on the token
                switch (token) {
                    case "+":
                        stack.push(firstOperand + secondOperand); // Add and push the result
                        break;
                    case "-":
                        stack.push(firstOperand - secondOperand); // Subtract and push the result
                        break;
                    case "*":
                        stack.push(firstOperand * secondOperand); // Multiply and push the result
                        break;
                    case "/":
                        stack.push(firstOperand / secondOperand); // Divide and push the result
                        break;
                }
            }
        }

        // The final result is the only element in the stack, return it
        return stack.pop();
    }
}
