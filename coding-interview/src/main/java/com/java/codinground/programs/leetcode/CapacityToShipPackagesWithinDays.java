package com.java.codinground.programs.leetcode;

/**
 * A conveyor belt has packages that must be shipped from one port to another within days days.
 * <p>
 * The ith package on the conveyor belt has a weight of weights[i]. Each day, we load the ship with packages on the conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.
 * <p>
 * Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within days days.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
 * Output: 15
 * Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
 * 1st day: 1, 2, 3, 4, 5
 * 2nd day: 6, 7
 * 3rd day: 8
 * 4th day: 9
 * 5th day: 10
 * <p>
 * Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.
 * Example 2:
 * <p>
 * Input: weights = [3,2,2,4,1,4], days = 3
 * Output: 6
 * Explanation: A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
 * 1st day: 3, 2
 * 2nd day: 2, 4
 * 3rd day: 1, 4
 * Example 3:
 * <p>
 * Input: weights = [1,2,3,1,1], days = 4
 * Output: 3
 * Explanation:
 * 1st day: 1
 * 2nd day: 2
 * 3rd day: 3
 * 4th day: 1, 1
 */
public class CapacityToShipPackagesWithinDays {
    public static void main(String[] args) {
        int[] weight = { 9, 8, 10 };
        int D = 3;
        shipWithinDays(weight, D);
    }

    static boolean isValid(int[] weight, int n,
                           int D, int capacity) {

        // Stores the count of days required
        // to ship all the weights if the
        int days = 1;

        //will traverse the weight array and keep summing to track till capacity
        int sum = 0;

        // Traverse all the weights
        for (int i = 0; i < n; i++) {
            sum += weight[i];

            // If total weight is more than
            // the maximum capacity
            if (sum > capacity) {
                days++;
                sum = weight[i];
            }

            // If days are more than D,
            // then return false
            if (days > D)
                return false;
        }

        // Return true for the days < D
        return true;
    }

    // Function to find the least weight
// capacity of a boat to ship all the
// weights within D days
    static void shipWithinDays(int[] weight, int D) {
        int length = weight.length;
        // Stores the total weights to
        // be shipped

        // Find the sum of weights
        int totalWeight = 0;
        for (int i = 0; i < length; i++)
            totalWeight += weight[i];

        // Stores the maximum weight in the
        // array that has to be shipped
        int maxWeight = weight[0];
        for (int i = 1; i < length; i++) {
            maxWeight = Math.max(maxWeight, weight[i]);
        }

        /**
         * The minimum weight the ship can carry has to be the highest/max individual weight, which is maxWeight
         * The maximum weight the ship can carry has to be the total weight of all totoalWeight, which will ship in one day
         * Thats why to define the search space of binary search setting the lower bound as maxWeight and upper as totalWeight
         */

        // The lower bound
        int left = maxWeight;
        // The upper bound
        int right = totalWeight;

        // Store the required result
        int result = -1;

        // Perform binary search
        while (left <= right) {
            // Store the middle value
            int mid = left + (right - left) / 2;

            // If mid can be shipped, then
            // update the result and right
            // value of the search space
            if (isValid(weight, length, D, mid)) {
                result = mid;
                right = mid - 1; // we are searching for minimum so even after finding result going left to minimize result
            }

            // Search for minimum value
            // in the right part
            else
                left = mid + 1;
        }

        // Print the result
        System.out.println(result);
    }
}
