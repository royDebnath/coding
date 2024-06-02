package com.java.backup.top;

/**
 * Given an encoded string, return its decoded string.
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly
 * �
 * k times. Note that
 * �
 * k is guaranteed to be a positive integer.
 *
 * You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers,
 * �
 * k. For example, there will not be input like 3a or 2[4].
 *
 * The test cases are generated so that the length of the output will never exceed
 * 1
 * 0
 * 5
 * 10
 * 5
 *  .
 *
 * Example 1:
 *
 * Input: s = "3[a]2[bc]"
 * Output: "aaabcbc"
 *
 * Example 2:
 *
 * Input: s = "3[a2[c]]"
 * Output: "accaccacc"
 *
 * Example 3:
 *
 * Input: s = "2[abc]3[cd]ef"
 * Output: "abcabccdcdcdef"
 *
 * Constraints:
 *
 * 1 <= s.length <= 30
 * s consists of lowercase English letters, digits, and square brackets '[]'.
 * s is guaranteed to be a valid input.
 * All the integers in s are in the range [1, 300].
 * Solution
 * Solution
 * Here is the definition of an encoded string:
 *
 * a string is encoded if it only consists of lowercase English letters
 * a string is encoded if it's in the form k[s] where
 * �
 * k is a positive integer and s is a encoded string
 * a string is encoded if it's a concatenation of two encoded strings
 * We can notice that an encoded string can be a concatenation of multiple encoded strings. To decode this string, we can separate the string into the multiple encoded strings, decode them separately and finally concatenate all the decoded strings together.
 *
 * Here, we're solving a problem that can be broken down into the same repetitive problem. Thus, we can use recursion. The function decodeString() will return the decoded string of any encoded string.
 *
 * There's two basic cases we should consider:
 *
 * The string is consists of only lowercase English letters. In this case, we can just return the original string.
 * a string in the form k[s] where s is an encoded string and
 * �
 * k is an integer. We can build the answer by concatenating
 * �
 * k copies of decodeString(s).
 * Any encoded string is just a concatenation of these cases. For any string, we'll first break it down into a bunch of strings that follow one of the two basic cases. Then we'll decode those separately and concatenate them together in the end.
 *
 * For example, the string 5[abc3[ba]]jkl4[xyz] can be separated into 5[abc3[ba]], jkl, and 4[xyz]. We can find the decoded strings separately by using the function decodeString() and then we'll concatenate them together.
 *
 * For the basic case in the form k[s], how will we find the matching close bracket? When iterating through the string, we know we have found the correct close bracket when we reach a point in the string where the number of open brackets match the number of close brackets. In the same example, 5[abc3[ba] is currently incomplete since we only have
 * 1
 * 1 close bracket but
 * 2
 * 2 open brackets. 5[abc3[ba]] however, is complete since we have
 * 2
 * 2 open and close brackets.
 *
 *
 */
public class Q394_DecodeString {
}
