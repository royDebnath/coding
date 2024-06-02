package com.java.backup.blind75.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Design an algorithm to encode a list of strings to a string.
 * The encoded string is then sent over the network and is decoded back to the original list of strings.
 *
 * Machine 1 (sender) has the function:
 *
 * string encode(vector<string> strs) {
 *   // ... your code
 *   return encoded_string;
 * }
 * Machine 2 (receiver) has the function:
 *
 * vector<string> decode(string s) {
 *   //... your code
 *   return strs;
 * }
 * So Machine 1 does:
 *
 * string encoded_string = encode(strs);
 * and Machine 2 does:
 *
 * vector<string> strs2 = decode(encoded_string);
 * strs2 in Machine 2 should be the same as strs in Machine 1.
 *
 * Implement the encode and decode methods.
 *
 * Note:
 *
 * The string may contain any possible characters out of 256 valid ascii characters.
 * Your algorithm should be generalized enough to work on any possible characters.
 * Do not use class member/global/static variables to store states.
 * Your encode and decode algorithms should be stateless.
 * Do not rely on any library method such as eval or serialize methods.
 * You should implement your own encode/decode algorithm.
 *
 * Solution :
 *
 * concatenate the list of strings with a non ASCII delimiter between them.
 * While decoding split based on that Non-ASCII Delimiter
 *
 */
public class Q62_EncodeDecodeStrings {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        if (strs.size() == 0) {
            return Character.toString((char) 258);
        }

        String delimiter = Character.toString((char) 257); //Non-ASCII Delimiter
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            sb.append(s);
            sb.append(delimiter);
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        String delimiter = Character.toString((char) 258);
        if (s.equals(delimiter)) {
            return new ArrayList();
        }

        delimiter = Character.toString((char) 257); // Non-ASCII Delimiter
        return Arrays.asList(s.split(delimiter, -1));
    }
}
