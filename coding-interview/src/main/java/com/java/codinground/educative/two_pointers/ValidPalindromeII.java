package com.java.codinground.educative.two_pointers;

/**
 * Statement
 * Write a function that takes a string as input and checks whether it can be a valid palindrome
 * by removing at most one character from it.
 */
public class ValidPalindromeII {
    public static void main(String[] args) {
        System.out.println(validPalindrome("abca"));
    }
    public static boolean validPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;

        while(start <= end){
            if(s.charAt(start) == s.charAt(end)){
                start++;
                end--;
            }
            else return isPalindrome(s, start + 1, end) || isPalindrome(s, start, end - 1);
        }
        return true;
    }
    public static boolean isPalindrome(String input, int start, int end) {
        while (start <= end) {
            if (input.charAt(start) != input.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
