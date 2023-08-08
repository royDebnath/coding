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
}










