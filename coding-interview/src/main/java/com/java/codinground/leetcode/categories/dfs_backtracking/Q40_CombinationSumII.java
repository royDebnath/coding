package com.java.codinground.leetcode.categories.dfs_backtracking;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40. Combination Sum II
 * Medium
 * Topics
 * Companies
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.
 * <p>
 * Each number in candidates may only be used once in the combination.
 * <p>
 * Note: The solution set must not contain duplicate combinations.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: candidates = [10,1,2,7,6,1,5], target = 8
 * Output:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 * Example 2:
 * <p>
 * Input: candidates = [2,5,2,1,2], target = 5
 * Output:
 * [
 * [1,2,2],
 * [5]
 * ]
 */
public class Q40_CombinationSumII {

    public static void main(String[] args) {
        int[] input = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        Q40_CombinationSumII app = new Q40_CombinationSumII();
        System.out.println(app.combinationSum2(input, target));
    }

    List<List<Integer>> answers = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] input, int target) {

        if (input == null || input.length == 0) {
            return answers;
        }

        Arrays.sort(input);
        ArrayList<Integer> currentCombination = new ArrayList<>();
        backtrack(input, target, currentCombination, 0);
        return answers;
    }

    private void backtrack(int[] input, int remain, ArrayList<Integer> currentCombination, int currentIndex) {
        if (remain == 0) {
            answers.add(new ArrayList<>(currentCombination));
            return;
        }

        for (int i = currentIndex; i < input.length; i++) {
            // Skipping duplicate numbers.
            int current = input[i];
            int currentRemaining = remain - current;
            if (i > currentIndex && input[i - 1] == current) { // diff from combination sum 1, not adding duplicate combination
                /**
                 * for array [1, 1, 2, 5, 6, 7, 10] and target 8, combination 1,2,5 can be twice for indexes 0,2,3 and 1,2,3
                 * this logic removes this. same for 1,7 combination
                 */
                continue;
            }

            if (currentRemaining >= 0) { // in case of currentRemaining negetive, element will be skipped
                currentCombination.add(current);
                backtrack(input, currentRemaining, currentCombination, i + 1); //diff from combination sum 1, calling the next index i+1, same element cannot be used twice
                currentCombination.remove(currentCombination.size() - 1);
            }
        }
    }
}

/**
 * Backtracking
 * <p>
 * Time Complexity:
 * 1. The length of the potential combinations can vary from 1 to k where k = min(T/M , N).
 * 2. Total number of combinations of size k is C(N,k) and time to add each such combination in the result list is O(K).
 * Therefore the total time complexity will be O(1*C(N,1) + 2*C(N,2) + ... + k*C(N,k))
 * = (i = 1 -> k) ∑ (i * C(N, i)).
 * If k = N, then above time complexity becomes O(N * 2^(N-1))
 * <p>
 * Space Complexity: O(min(T/M , N))
 * <p>
 * N = Length of input array. T = Target. M = Minimum value in the input array.
 */