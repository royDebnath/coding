package com.java.codinground.programs.leetcode.top100;

import java.util.Arrays;

/**
 * Problem Description
 * You have an array coins that contains different coin denominations and an integer amount which represents the total amount of money you want to make with these coins. The task is to calculate the minimum number of coins needed to make up the given amount. If it's not possible to reach the amount with the given coin denominations, the function should return -1.
 *
 * You can use each type of coin as many times as you want; in other words, there's an unlimited supply of each coin.
 *
 * Intuition
 * The intuition behind the solution is based on a classic algorithmic problem, known as the Coin Change problem, which can be solved using Dynamic Programming (DP). The idea is to build up the solution by solving for smaller subproblems and then use those solutions to construct the answer for the larger problem.
 *
 * The approach used is called "bottom-up" DP. We initialize an array f of size amount + 1, where each element f[i] will hold the minimum number of coins needed to make the sum i. We start with f[0] = 0 since no coins are needed to achieve a total amount of 0.
 *
 * We set all other values in f to inf (infinity) which signifies that initially, we assume it's impossible to make those amounts with any combination of the coins given.
 *
 * Next, we iterate through each coin denomination, x. For each x, we go through the f array starting from f[x] to f[amount] trying to update the minimum number of coins needed for each amount j by considering the number of coins needed for j - x plus one more coin of denomination x. The inner loop uses the formula f[j] = min(f[j], f[j - x] + 1) to decide whether we have found a new minimum for amount j.
 *
 * After filling up the f array, if f[amount] is still inf, that means it's not possible to form amount with the given coins, and we return -1. Otherwise, f[amount] will hold the fewest number of coins needed to make up the amount, and that's our answer.
 *
 * Example Walkthrough
 * Let's walk through a small example to illustrate the solution approach. Suppose we have coins = [1, 3, 4] and the amount = 6.
 *
 * We initialize the DP table f with amount + 1 (7) slots: f = [0, inf, inf, inf, inf, inf, inf]. The first element f[0] is 0 because no coins are needed to make an amount of 0.
 *
 * Now we iterate through the coin denominations:
 *
 * a. For x = 1: - We iterate from 1 to amount (6). - At each j, we update f[j] = min(f[j], f[j - x] + 1). - After the loop, f looks like: [0, 1, 2, 3, 4, 5, 6]. For any j, at most j coins of denomination 1 are needed.
 *
 * b. For x = 3: - We iterate from 3 to 6. - We update f[3] to 1, f[4] to 2, f[5] to 2, and f[6] to 2, because for each of these amounts, using a 3-denomination coin is more efficient. - f now is: [0, 1, 2, 1, 2, 2, 2].
 *
 * c. For x = 4: - We iterate from 4 to 6. - We update f[4] to 1, and f[6] to 2 (f[5] remains 2 as f[5 - x] is inf), which uses one coin of 4 and then utilizes previous results for remaining amount 2. - f now looks like: [0, 1, 2, 1, 1, 2, 2].
 *
 * Our final DP table is [0, 1, 2, 1, 1, 2, 2]. Looking at the value of f[6], we see 2, which means the minimum number of coins needed to make the amount 6 is 2. This would correspond to using coins 4 and 2, which are both subsets of our initial coins array (with 2 being the sum of two 1 coins).
 *
 * We return the minimum number of coins found, which is 2. This is the least amount of coins that can make 6 with the denominations given.
 *
 * Python Solutio
 *
 *
 */
public class Q322_CoinChange {
    public int coinChange(int[] coins, int amount) {
        // Define a large value which would act as our "infinity" substitute.
        final int INF = 1 << 30;

        // 'dp' will hold our optimal solutions to sub-problems, dp[i] will store the minimum number of coins needed to make amount 'i'.
        int[] dp = new int[amount + 1];

        // Initialize the dp array with INF to signify that those amounts are currently not achievable with the given coins.
        Arrays.fill(dp, INF);

        // Base case initialization: No coins are needed to make an amount of 0.
        dp[0] = 0;

        // Iterate over each type of coin available.
        for (int coin : coins) {
            // For each coin, try to build up to the target amount, starting from the coin's value itself up to 'amount'.
            for (int currentAmount = coin; currentAmount <= amount; ++currentAmount) {
                // Check if the current coin can contribute to a solution for 'currentAmount'.
                // If so, update dp[currentAmount] to the minimum value between its current and the new possible number of coins used.
                dp[currentAmount] = Math.min(dp[currentAmount], dp[currentAmount - coin] + 1);
            }
        }

        // Return the answer for the target 'amount'. If dp[amount] is still INF, then it was not possible to make the amount using the given coins.
        return dp[amount] >= INF ? -1 : dp[amount];
    }
}
