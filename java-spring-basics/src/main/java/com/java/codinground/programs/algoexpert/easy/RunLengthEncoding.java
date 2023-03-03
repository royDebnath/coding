package com.java.codinground.programs.algoexpert.easy;

/****
 * Write a function that takes in a non-empty string and returns its run-length encoding.
 * For this problem, a run of data is any sequence of consecutive, identical characters.
 * So the run "AAA" would be run-length-encoded as "3A" .
 * The input string can contain all sorts of special characters, including numbers.
 * And since encoded data must be decodable, this means that we can't naively run-length-encode long runs.
 * For example, the run "AAAAAAAAAAAA" (12 A s), can't naively be encoded as "12A" ,
 * since this string can be decoded as either "AAAAAAAAAAAA" or "1AA" .
 * Thus, long runs (runs of 10 or more characters) should be encoded in a split fashion;
 * the aforementioned run should be encoded as "9A3A" .
 */
public class RunLengthEncoding {
    public static void main(String[] args) {
        String input = "AAAAAAAAAAAAABBCCCCDD"; //9A4A2B4C2D
        System.out.println("Encoded String is : " + runLengthEncode(input));
    }

    private static String runLengthEncode(String input) {
        StringBuilder encodedString = new StringBuilder();
        int currentRunLength = 1;
        for (int i =1; i< input.length(); i++){
            char currentCharacter = input.charAt(i);
            char previousCharacter = input.charAt(i-1);
            if (currentCharacter==previousCharacter && currentRunLength<9){
                currentRunLength++;
            }
            else {
                encodedString.append(Integer.toString(currentRunLength));
                encodedString.append(previousCharacter);
                currentRunLength=1;
            }
        }
        encodedString.append(currentRunLength);
        encodedString.append(input.charAt(input.length()-1));
        return encodedString.toString();
    }
}
