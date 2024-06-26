package com.java.codinground.leetcode.categories.greedy;

/**
 * 134. Gas Station
 * Medium
 *
 * Topics
 * Companies
 * There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].
 *
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station to its next (i + 1)th station. You begin the journey with an empty tank at one of the gas stations.
 *
 * Given two integer arrays gas and cost, return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1. If there exists a solution, it is guaranteed to be unique
 *
 *
 *
 * Example 1:
 *
 * Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 * Output: 3
 * Explanation:
 * Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 * Travel to station 4. Your tank = 4 - 1 + 5 = 8
 * Travel to station 0. Your tank = 8 - 2 + 1 = 7
 * Travel to station 1. Your tank = 7 - 3 + 2 = 6
 * Travel to station 2. Your tank = 6 - 4 + 3 = 5
 * Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
 * Therefore, return 3 as the starting index.
 * Example 2:
 *
 * Input: gas = [2,3,4], cost = [3,4,3]
 * Output: -1
 * Explanation:
 * You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
 * Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 * Travel to station 0. Your tank = 4 - 3 + 2 = 3
 * Travel to station 1. Your tank = 3 - 3 + 3 = 3
 * You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
 * Therefore, you can't travel around the circuit once no matter where you start.
 *
 * Explanation:
 *
 * Initialization:
 *
 * int n = gas.length;: Determines the number of gas stations.
 * int start = 0;: Initially assumes the starting point is at the first gas station.
 * int gasCount = 0;: Tracks the net gas available from the current starting point.
 * First Loop (Finding a Valid Starting Point):
 *
 * for (int i = 0; i < n; i++) { : Iterates through each gas station.
 * gasCount += gas[i] - cost[i];: Calculates the net gas available at the current station (gas[i] - cost[i]).
 * if (gasCount < 0) { ... }: Checks if at any station the net gas count becomes negative.
 * If so, it means starting from the current start is not possible because the car would run out of gas before completing the circuit.
 * Therefore, start is updated to i + 1 (next station), and gasCount is reset to 0 because starting from the current start is not feasible.
 * Second Loop (Verifying Feasibility):
 *
 * Calculates totalGas as the sum of all gas[i] values across all stations.
 * Calculates totalCost as the sum of all cost[i] values across all stations.
 * if (totalGas < totalCost) { return -1; }: Checks if the total gas available is less than the total cost required to complete the circuit.
 * If true, it means it's impossible to complete the circuit regardless of the starting point, so -1 is returned.
 * Returning the Result:
 *
 * If the total gas is sufficient (totalGas >= totalCost), start is returned as the index of the starting gas station from which the circuit can be completed.
 * Complexity
 *
 * Time complexity:O(n)
 *
 * Space complexity:O(1)
 *
 *
 */
public class Q134_GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int start = 0; // Starting point index
        int gasCount = 0; // Net gas count from current start

        for (int i = 0; i < n; i++) {
            gasCount += gas[i] - cost[i]; // Calculate net gas at each station
            if (gasCount < 0) {
                start = i + 1; // Reset start to next station
                gasCount = 0; // Reset gasCount
            }
        }

        // Check if the total gas count starting from 'start' is sufficient
        int totalGas = 0;
        int totalCost = 0;
        for (int i = 0; i < n; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
        }

        if (totalGas < totalCost) {
            return -1; // If total gas is less than total cost, return -1
        }

        return start;
    }
}
