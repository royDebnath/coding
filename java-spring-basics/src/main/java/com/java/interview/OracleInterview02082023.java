package com.java.interview;


// buySell stock and parentheses balance

public class OracleInterview02082023 {
}



// You are given an array prices where prices[i] is the price of a given stock on the ith day.

// You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

// Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

// */


// def addNumbers(a,b):
//     sum = a + b
//     return sum

// num1 = int(input())
// num2 = int(input())

// print("The sum is", addNumbers(num1, num2))

class Solution {

    public static void main(String[] args){

        System.out.println("expected : " + 12 + " Actual : " + maxProfit(new int[]{1,12,6,5,13,3,9}));

        System.out.println("expected : " + 22 + " Actual : " + maxProfit(new int[]{7,12,6,1,13,3,23}));

        System.out.println("expected : " + 8 + " Actual : " + maxProfit(new int[]{27,12,6,5,13,3,9}));



    }

    //[1,12,6,5,13,3,9]
    static int maxProfit(int[] input){
        int maxProfit = 0; // 0,11,11,11,12,12,12
        int leastSoFar=Integer.MAX_VALUE; //1,1,1,1,1,1,1
        int profitToday = Integer.MIN_VALUE;//0,11,5,4,12,2,8

        for(int i = 0; i< input.length; i++){
            int current = input[i];
            leastSoFar = Math.min(current, leastSoFar);
            profitToday = current - leastSoFar;
            maxProfit = Math.max(maxProfit, profitToday);

        }


        return maxProfit;
    }

    static Price prices(int[] input){
        Price priceTrack = new Price();
        int maxProfit = 0; // 0,11,11,11,12,12,12
        int lowestBuyDay =0;
        int leastSoFar=Integer.MAX_VALUE; //1,1,1,1,1,1,1
        int profitToday = Integer.MIN_VALUE;//0,11,5,4,12,2,8

        for(int i = 0; i< input.length; i++){
            int current = input[i];

            if(current < leastSoFar){
                leastSoFar = current;
                priceTrack.buyDay = i;
                priceTrack.buyingPrice = current;
            }

            profitToday = current - leastSoFar;



            if(profitToday > maxProfit){
                priceTrack.sellDay=i;
                priceTrack.sellingPrice=current;
            }


        }


        return priceTrack;
    }


}

class Price {
    int buyDay;
    int buyingPrice;
    int sellDay;
    int sellingPrice;
    int maxProfit;

    Price(){
        // Constructor
    }


    public class Solution {

/*
upto a trillion

123 - one hundred and twenty three
10,234 - ten thousand two hundred and thirty four
100,500,000 - hundred million five hundred thousand
*/

        static String convertToString(int a) {
            // 0-9 : zero,one...
            //11-19 : eleven ...nineteen
            // 20, 30 ,40 ... twenty, thirty, forty
            // 20 - 99 : twenty one ...twenty nine...
            // 129 -- one hundred ...
            // 3245 --- three thousand... two hundred
            // 43245 --- fort three thousand
            // 343,245 -- 343 thousand...
            // 5,343,245 -- 5 million 343 thousand 245

            String[] numbers = {"zero","one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "forteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
            numbers[20] = "twenty";
            numbers[30] = "thirty";
            numbers[40] = "forty";
            numbers[50] = "fifty";


            String input = Integer.toString(a);

            int length = input.length();

            if(a>=0 && a<99) {
                return calcZeroTo99(a, numbers);

            }//
            else if(a>99 && a<999){
                int thirdDigit = a/100;
                int twoDigits = a%100;
                return numbers[thirdDigit] + " " + calcZeroTo99(twoDigits, numbers);

            }
            else if(a>999 && a<9999){

            }


            return input;
        }

        static String calcZeroTo99(int a, String[] numbers) {
            if(a < 19){
                return numbers[a];
            }
            else if(a>19 && a<99){
                int secondDigit = a/10;

                // calcSecondDigit(int digit)
                if(a%10==0){
                    return calcSecondDigit(secondDigit, numbers);
                }
                else {
                    int firstDigit = a%10;
                    return calcSecondDigit(secondDigit, numbers) + " " + firstDigit;
                }

            }

            return null;
        }

        static String calcSecondDigit(int secondDigit, String[] numbers) {
            if(secondDigit==2){
                return numbers[20];
            }
            else if (secondDigit==3){
                return numbers[secondDigit];
            }
            return null;
        }




        public static void main(String[] args) {

        }
    }

}










