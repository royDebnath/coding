package com.java.backup.top;

import lombok.EqualsAndHashCode;

/**\
 * 647. Palindromic Substrings
 * Medium
 *
 * Given a string s, return the number of palindromic substrings in it.
 *
 * A string is a palindrome when it reads the same backward as forward.
 *
 * A substring is a contiguous sequence of characters within the string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 * Example 2:
 *
 * Input: s = "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 *
 */
@EqualsAndHashCode
public class Q647_PalindromicSubstrings {
    int count =1;
    public int countSubstrings(String s) {
        if(s.length()==0)
            return 0;
        for(int i=0; i<s.length()-1; i++){
            checkPalindrome(s,i,i);     //To check the palindrome of odd length palindromic sub-string
            checkPalindrome(s,i,i+1);   //To check the palindrome of even length palindromic sub-string
        }
        return count;
    }

    private void checkPalindrome(String s, int i, int j) {
        while(i>=0 && j<s.length() && s.charAt(i)==s.charAt(j)){    //Check for the palindrome string
            count++;    //Increment the count if palindromin substring found
            i--;    //To trace string in left direction
            j++;    //To trace string in right direction
        }
    }
}
