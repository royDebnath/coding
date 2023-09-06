package com.java.codinground.educative.two_pointers;

/**
 * Statement
 * Write a function that takes a string, s, as an input and determines
 * whether it is a palindrome.
 *
 *
 *
 * Solution summary
 * 1. Initialize two pointers and move them from opposite ends.
 * 2. The first pointer starts at the beginning of the string and moves toward the middle,
 *    while the second pointer starts at the end and moves toward the middle.
 * 3. Compare the elements at each position to detect a nonmatching pair.
 * 4. If both pointers reach the middle of the string without encountering a nonmatching pair,
 *    the string is a palindrome.
 *
 * Time complexity
 * The time complexity is O(n), where n is the number of characters in the string.
 * However, our algorithm will only run (n/2)  times, since two pointers are traversing toward each other.
 *
 * Space complexity
 * O(1)
 * since we use constant space to store two indexes.
 */
class ValidPalindrome {

    public static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right))
            {
                return false;
            }
            left = left + 1;
            right = right - 1;
        }
        return true;
    }

    //Driver code
    public static void main(String[] arg) {
        String[] testCase = {
                "RACEACAR",
                "A",
                "ABCDEFGFEDCBA",
                "ABC",
                "ABCBA",
                "ABBA",
                "RACEACAR"
        };
        for (int k = 0; k < testCase.length; k++) {
            System.out.println("Test Case #" + (k + 1));
            System.out.println(new String(new char[100]).replace('\0', '-'));
            System.out.println("The input string is " + testCase[k] + "' and the length of the string is " + testCase[k].length() + ".");
            System.out.println("\nIs it a palindrome?..... " + isPalindrome(testCase[k]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}