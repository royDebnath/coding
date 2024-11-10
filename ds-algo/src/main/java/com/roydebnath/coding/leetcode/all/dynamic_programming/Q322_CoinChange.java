package com.roydebnath.coding.leetcode.all.dynamic_programming;

import java.util.Arrays;

/**
 * 322. Coin Change
 * Medium
 *
 * You are given an integer array coins representing coins of different denominations
 * and an integer amount representing a total amount of money.
 *
 * Return the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 *
 *
 * Example 1:
 *
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * Example 2:
 *
 * Input: coins = [2], amount = 3
 * Output: -1
 * Example 3:
 *
 * Input: coins = [1], amount = 0
 * Output: 0
 *
 * Solution :
 *
 * Input: coins = [1,3,4,5], amount = 7
 * Output: 2
 * Explanation: 7 can be formed in the following ways :
 *
 * 1. [1+1+1+1+1+1+1]
 * 2. [3+3+1]
 * 3. [3+4] ------> this is minimumg, this has to be our answer
 * 4. [5+1+1]
 *
 *
 * Create a dp array of length targetAmount+1, where
 * each index denotes the targetAmount
 * the value at the index denotes the coins required to reach the targetAmount
 *
 * Input: coins = [1,3,4,5], amount = 7
 *
 * The DP array would look like this:
 *
 *  amount : [0,1,2,3,4,5,6,7]
 *  dp     : [0,1,2,1,1,1,2,2]
 *
 * We will foloow a bottom up approach wher we will start with building the dp array,
 * starting from amount 0,1,2...to eventually 7.
 * to fill the dp array at each i-th index we will check the dp array at previous indexes
 * to figure the coins required for a lesser amount.
 *
 * Now lets say we have figured till dp[6], then how we would calculate for dp[7]
 *
 * dp[0] = 0
 * dp[1] = 1
 * dp[2] = 2
 * dp[3] = 1
 * dp[4] = 1
 * dp[5] = 1
 * dp[6] = 2
 *
 * Now, to calculate dp[7], we will iterate over all the coins and see if we can make it,
 *
 * for coin 1 :
 * remaining amount is 7-1 = 6. we have calculated dp[6].
 * so coins required = dp[6] + 1 (here 1 coin as dp[1]=1)
 *                   = 2+1 = 3
 *
 * for coin 3 :
 * remaining amount is 7-3 = 4. we have calculated dp[6].
 * so coins required = dp[4] + 1 (here 1 coin as dp[3]=1)
 *                   = 1+1 = 2
 *
 * for coin 4 :
 * remaining amount is 7-4 = 3. we have calculated dp[3].
 * so coins required = dp[3] + 1 (here 1 coin as dp[4] =1)
 *                   = 1+1 = 2
 *
 * for coin 5 :
 * remaining amount is 7-5 = 2. we have calculated dp[6].
 * so coins required = dp[2] + 1 (here 1 coin as dp[5]=1)
 *                   = 2+1 = 3
 *
 *
 * Now, the minimum among them is 2 i.e dp[4] + dp[3]
 *
 * we will fill the dp array starting from 1 to the targetAmount by running a loop.
 * the basecase dp[0] = 0, for obvious reason.
 *
 * for each amount starting from 1 to 7,
 *  we will iterate over each coin and see the remaining deficit by calculating (amount-coin).
 *  will have the (amount-coin) value from previous iterations.
 *
 *  At each iteration of will check for the minimum and update the amount.
 *
 *  Will initialize the dp array with Integer.Max value to initially denote no solution
 *  and will fill eventually if possible. If the target amount remain Integer.Max at the end
 *  of the loop will return -1 as per requirement.
 *
 *
 *
 */
public class Q322_CoinChange {
    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1,3,4,5}, 7));
    }
    public static int coinChange(int[] coins, int targetAmount) {
        int[] dp = new int[targetAmount+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int amount=1; amount<=targetAmount; amount++){
            for(int coin : coins){
                int deficit = amount - coin;
                if(deficit >=0 && dp[deficit] != Integer.MAX_VALUE){ //dp[deficit] exists in the dp array and has valid value
                    dp[amount] = Math.min(dp[amount], dp[deficit]+1); // 1 is for the current coin
                }
            }
        }
        return dp[targetAmount] == Integer.MAX_VALUE ? -1 : dp[targetAmount];
    }
}
