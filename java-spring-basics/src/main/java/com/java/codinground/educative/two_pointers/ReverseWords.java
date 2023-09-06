package com.java.codinground.educative.two_pointers;

/**
 * 151. Reverse Words in a String
 * Medium
 * 7K
 * 4.8K
 * Companies
 * Given an input string s, reverse the order of the words.
 *
 * A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
 *
 * Return a string of the words in reverse order concatenated by a single space.
 *
 * Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "the sky is blue"
 * Output: "blue is sky the"
 * Example 2:
 *
 * Input: s = "  hello world  "
 * Output: "world hello"
 * Explanation: Your reversed string should not contain leading or trailing spaces.
 * Example 3:
 *
 * Input: s = "a good   example"
 * Output: "example good a"
 * Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 *
 * Solution :
 *
 * In this problem, we first reverse the complete string.
 * Now take two pointers, start and end, initialized with the start of the list, which is index 0.
 *
 * Now, iterate a loop until start is less than the length of the list, and in each iteration,
 * move the end pointer forward until it hits a space.
 * At this point, we have a complete word starting from the start index to the end-1 index,
 * but with the characters in reverse order.
 *
 * To change the order of characters, we call the strRev function with the starting and ending positions of the word.
 * This will reverse the characters in the word.
 *
 * Now, we update the start and end pointers to the next of end pointer,
 * which is basically the first character of the next word.
 * Now, repeat this process for the next word.
 * At the end of all iterations, we get the reversed words in the string.
 *
 */

class ReverseWords {

    public static String reverseWords(String s) {
        s = s.replaceAll("\\s+", " ").trim();
        StringBuilder result = new StringBuilder(s);
        int length = s.length();
        reverseString(result, 0, length - 1);

        int start = 0, end = 0;

        while (start < length) {
            while (end < length && result.charAt(end) != ' '){
                end++;
            }
            reverseString(result, start, end - 1);
            end++; // points to next word
            start = end;
        }

        return result.toString();
    }

    // Function to reverse the whole string
    public static void reverseString(StringBuilder string, int start, int end) {
        while (start < end) {
            char temp = string.charAt(start);
            string.setCharAt(start, string.charAt(end));
            string.setCharAt(end, temp);
            start++;
            end--;
        }
    }

    // Driver code
    public static void main(String[] args) {
        String[] inputs = {
                "   Hello         World   ", "We love Python",
                "The quick brown fox jumped over the lazy dog.",
                "Hey", "To be or not to be", "AAAAA", " Hello     World "};

        for(int i=0; i<inputs.length; i++){
            System.out.print(i+1);
            System.out.println(".\tActual string:\t\t"+ inputs[i]);
            System.out.println("\tReversed String:\t"+ reverseWords(inputs[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}