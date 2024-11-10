package com.roydebnath.coding.leetcode.all.binary_search;

/**
 * 875. Koko Eating Bananas
 * Solved
 * Medium
 *
 * Topics
 * Companies
 * Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.
 *
 * Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.
 *
 * Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
 *
 * Return the minimum integer k such that she can eat all the bananas within h hours.
 *
 *
 *
 * Example 1:
 *
 * Input: piles = [3,6,7,11], h = 8
 * Output: 4
 * Example 2:
 *
 * Input: piles = [30,11,23,4,20], h = 5
 * Output: 30
 * Example 3:
 *
 * Input: piles = [30,11,23,4,20], h = 6
 * Output: 23
 *
 * 875. Koko Eating Bananas
 * Medium
 * Array
 * Binary Search
 * Leetcode Link
 * Problem Description
 *
 * In the given LeetCode problem, Koko loves bananas and has n piles of them, with the i-th pile containing piles[i] bananas. Koko has h hours to eat all the bananas before the guards return, and she can set a constant eating speed of k bananas per hour. At each hour, she eats k bananas from a pile; if the pile has fewer than k bananas, she'll finish that pile and then stop eating for that hour. The goal is to determine the minimum eating speed k that allows Koko to consume all the bananas within the h hours available.
 *
 * The problem is essentially about finding the lowest possible rate of eating bananas per hour, which allows Koko to eat all bananas in the given time without running out of time. The speed k must be an integer, and the challenge is to find the smallest such k that still meets the time constraint.
 *
 * Intuition
 *
 * The solution is based on a binary search approach. Since we're looking for the minimum integer value of k that enables Koko to finish all bananas within h hours, and k can range from 1 (eating very slowly) to the maximum number of bananas in any pile in the worst-case scenario (eating very fast), we can use binary search to narrow down the possible values of k.
 *
 * We start with the lower bound left of 1 (the slowest possible eating speed) and an upper bound right which is set to a high enough value (like 10^9) that it's guaranteed to be higher than the maximum necessary speed.
 *
 * At each step of the binary search, we calculate the midpoint mid between left and right. We then calculate the total hours s it would take for Koko to eat all the bananas at this speed. If s is less than or equal to h, this means mid is a viable eating speed and could possibly be the answer, so we move the right bound to mid, potentially reducing the range. If s is greater than h, then eating at speed mid is too slow and we need to increase the eating speed, so we move the left bound up to mid + 1.
 *
 * The binary search continues until left and right converge to the minimum possible value of k that allows Koko to eat all bananas in h hours.
 *
 * Learn more about Binary Search patterns.
 *
 * Solution Approach
 *
 * The reference solution approach suggests employing a binary search algorithm to efficiently find the minimum eating speed k. This approach is chosen due to the nature of the problem, which quite clearly forms a sorted space where the speed can be increased or decreased based on whether Koko can finish the bananas within the given time h or not.
 *
 * Here is a detailed walk-through of the algorithm:
 *
 * Initialize two pointers: one for the lower bound left set to 1 (since Koko has to eat at least one banana per hour), and another for the upper bound right set to a large number, such as 10^9, to ensure that it is larger than any real-world scenario would require.
 *
 * While left is less than right, we continue the binary search:
 *
 * We find the midpoint mid by averaging the left and right pointers ((left + right) >> 1). Here, the >> 1 operation is a bitwise shift that effectively divides the sum by two.
 *
 * We then compute the total number of hours s it would take to eat all the piles at the eating speed mid. This is done by iterating over each pile in piles and calculating the hours needed for each pile using (x + mid - 1) // mid, which is a way to perform ceiling division without using floats to ensure we get an integer result. This formula accounts for the fact that if there are less than mid bananas in a pile, Koko will spend an entire hour eating it (represented by the x + mid - 1 part).
 *
 * After calculating s, we make a decision based on whether this proposed eating speed mid is fast enough:
 *
 * If s is less than or equal to h, we know that mid is at least as fast as the eating speed needs to be, so we adjust the upper bound right to mid. This narrows the search and potentially lowers the eating speed.
 *
 * If s is greater than h, the proposed eating speed mid is too slow, and we need to look for a faster eating speed; thus, we move the lower bound left up to mid + 1.
 *
 * The binary search ends when left equals right, at which point left represents the minimum integer eating speed k that allows Koko to eat all bananas within h hours.
 *
 * The binary search efficiently zeros in on the optimal value without having to try every possible eating speed, which significantly reduces the number of calculations and, thus, the running time of the algorithm.
 */
public class Q875_KokoEatingBananas {
    /**
     * Finds the minimum eating speed to eat all bananas in 'h' hours.
     *
     * @param piles Array of banana piles.
     * @param h Total hours within which all bananas must be eaten.
     * @return The minimum integer eating speed.
     */
    public int minEatingSpeed(int[] piles, int h) {
        // Initialize the lower bound of the eating speed, cannot be less than 1.
        int minSpeed = 1;
        // Initialize the upper bound of the eating speed to a high number, (int) 1e9 is used as an approximation.
        int maxSpeed = (int) 1e9;

        // Binary search to find the minimum eating speed.
        while (minSpeed < maxSpeed) {
            // Find the mid point which is the candidate for our potential eating speed.
            int midSpeed = minSpeed + (maxSpeed - minSpeed) / 2;

            // Initialize the total hours needed with the chosen speed.
            int totalHours = 0;

            // Calculate the total hours needed to eat the piles at midSpeed.
            for (int bananas : piles) {
                // The time to eat a pile is the pile size divided by eating speed, rounded up.
                totalHours += (bananas + midSpeed - 1) / midSpeed;
            }

            // If the total hours with midSpeed is less or equal to h, we might be able to do better,
            // so we bring down the maximum speed to midSpeed.
            if (totalHours <= h) {
                maxSpeed = midSpeed;
            } else {
                // Otherwise, if we need more than 'h' hours, the speed is too slow.
                // We need to increase our eating speed, so we update minSpeed to midSpeed + 1.
                minSpeed = midSpeed + 1;
            }
        }

        // Loop finishes when minSpeed == maxSpeed, which is the minimum speed to eat all bananas in 'h' hours.
        return minSpeed;
    }
}
