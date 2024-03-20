package com.java.codinground.leetcode.top;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem Description
 * The LeetCode problem asks us to generate all the possible subsets from a given list of unique integers.
 * The term 'subset' refers to any combination of numbers that can be formed from the original list,
 * including the empty set and the set itself. For instance, if the input is [1,2,3], then
 * the subsets would include [], [1], [2], [3], [1,2], [1,3], [2,3], and [1,2,3].
 * The objective is to list down all these possibilities.
 * We should also ensure that there are no duplicate subsets in the solution and
 * the subsets can be returned in any order.
 *
 *
 */
public class Q78_Subsets {
    List<List<Integer>> answer = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> currentSet = new ArrayList<>();
        backtrack(nums, currentSet, 0);
        return answer;
    }

    private void backtrack(int [] nums, List<Integer> currentSet, int currentIndex){
        answer.add(new ArrayList<>(currentSet));
        for(int i = currentIndex; i < nums.length; i++){
            currentSet.add(nums[i]);
            backtrack(nums, currentSet, i + 1);
            currentSet.remove(currentSet.size() - 1);
        }
    }

    public static void main(String[] args) {
        Q78_Subsets app = new Q78_Subsets();
        System.out.println(app.subsets(new int[]{1, 2, 3}));
    }
}
/**
 * Pick a starting point.
 * while(Problem is not solved)
 *     For each path from the starting point.
 *         check if selected path is safe, if yes select it
 *         and make recursive call to rest of the problem
 *         before which undo the current move.
 *     End For
 * If none of the move works out, return false, NO SOLUTON.
 *
 */