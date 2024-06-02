package com.java.backup.top;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of distinct integers candidates and a target integer target,
 * return a list of all unique combinations of candidates where the chosen numbers sum to target.
 * You may return the combinations in any order.
 *
 * The same number may be chosen from candidates an unlimited number of times.
 * Two combinations are unique if the frequency
 *  of at least one of the chosen numbers is different. *
 *
 * Example 1:
 *
 * Input: candidates = [2,3,6,7], target = 7
 * Output: [[2,2,3],[7]]
 * Explanation:
 * 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
 * 7 is a candidate, and 7 = 7.
 * These are the only two combinations.
 * Example 2:
 *
 * Input: candidates = [2,3,5], target = 8
 * Output: [[2,2,2,2],[2,3,3],[3,5]]
 * Example 3:
 *
 * Input: candidates = [2], target = 1
 * Output: []
 *
 * Solution:
 *
 * Generally, in problems such as this, we are required to use backtracking.
 * Because in such questions we are required to do a complete search and find the answer.
 * we create a recursive function that keeps on adding an integer to the
 * current temporary list of integers
 * and keeping track of the remaining sum required to add up to the given target.
 * So inside the recursive function, we keep two base cases.
 * The first base case is to check if the remaining sum required goes negative.(not present)
 * In that case, we will return from the recursive function.
 * The second base case is our required condition if the remaining sum equals zero.
 * If the remaining sum is zero this means that we have reached our required target.
 * At that point, we push the current temporary list of integers into the output array of arrays.
 *
 */
public class Q39_CombinationSum {
    List<List<Integer>> answers = new ArrayList<>();

    public static void main(String[] args) {
        int[] nums1 = {2, 3, 6, 7};
        int target1 = 7;

        int[] nums2 = {7, 2, 6, 5};
        int target2 = 16;

        int[] nums3 = {1, 2};
        int target3 = 3;
        Q39_CombinationSum app = new Q39_CombinationSum();
        System.out.println(app.combinationSum(nums1, target1));
    }

    public List<List<Integer>> combinationSum(int[] input, int target) {
        // answers will contain the combinations

        // Container will temporarily contain the members of the input and as we traverse
        // the array will check if sum of the currentCombination is target
        ArrayList<Integer> currentCombination = new ArrayList<>();
        backtrack(input, target, currentCombination, 0);
        return answers;
    }

    private void backtrack(int[] input, int remain, ArrayList<Integer> currentCombination, int currentIndex) {
        if (remain == 0) {
            // Adding deep copy of list to answers
            answers.add(new ArrayList<>(currentCombination));
            return;
        }
        for (int i = currentIndex; i < input.length; i++) {
            // checking that remain does not become negative
            int current = input[i];
            int currentRemaining = remain - current;
            if (currentRemaining >= 0) { // in case of currentRemaining negetive, element will be skipped
                // adding element which can contribute to remain
                currentCombination.add(current);
                backtrack(input, currentRemaining, currentCombination, i); // calling with same index i not i+1 because same number can be used twice
                // removing element from list (backtracking)
                currentCombination.remove(currentCombination.size() - 1);
            }
        }
    }
}