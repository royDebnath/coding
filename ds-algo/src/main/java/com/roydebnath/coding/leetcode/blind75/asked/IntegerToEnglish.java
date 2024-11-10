package com.roydebnath.coding.leetcode.blind75.asked;

public class IntegerToEnglish {
    private static final String[] belowTen = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private static final String[] belowTwenty = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private static final String[] belowHundred = new String[]{"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        return intToEnglish(num);
    }

    private static String intToEnglish(int num) {
        String result;
        if (num < 10) result = belowTen[num];
        else if (num < 20) result = belowTwenty[num - 10];
        else if (num < 100) result = belowHundred[num / 10] + " " + intToEnglish(num % 10);
        else if (num < 1000) result = intToEnglish(num / 100) + " Hundred " + intToEnglish(num % 100);
        else if (num < 1000000) result = intToEnglish(num / 1000) + " Thousand " + intToEnglish(num % 1000);
        else if (num < 1000000000) result = intToEnglish(num / 1000000) + " Million " + intToEnglish(num % 1000000);
        else result = intToEnglish(num / 1000000000) + " Billion " + intToEnglish(num % 1000000000);
        return result.trim();
    }

    public static void main(String[] args) {
        System.out.println(intToEnglish(900000));
    }
}
