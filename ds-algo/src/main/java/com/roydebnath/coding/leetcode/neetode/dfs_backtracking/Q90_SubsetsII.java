package com.roydebnath.coding.leetcode.neetode.dfs_backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an integer array nums that may contain duplicates, return all possible
 * subsets
 *  (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,2]
 * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
 *
 *
 * The task is to return all possible subsets (also known as the power set) of a given integer array nums, which may contain duplicates. The important constraint is that the solution set must not include duplicate subsets. These subsets can be returned in any order. A subset is a set containing elements that are all found in another set, which in this case is the array nums. The power set is the set of all subsets including the empty set and the set itself.
 *
 * For example, if nums is [1,2,2], the possible unique subsets without considering order are:
 *
 * 1- []
 * 2- [1]
 * 3- [2]
 * 4- [1,2]
 * 5- [2,2]
 * 6- [1,2,2]
 * The challenge here is to ensure that while generating the subsets, duplicates are not created.
 *
 * The core idea to avoid duplicates in the subsets is to sort the array nums. Sorting brings identical elements next to each other, making it easier to avoid duplicates when constructing the subsets.
 *
 * The solution uses Depth-First Search (DFS) to explore all the possible subsets. The process starts with an empty subset, and at each level, it includes the next number from the array into the current subset, then recursively continues to add the remaining numbers to form new subsets. This is done until all numbers have been considered. After each number is processed for a given subset, it is removed again (this operation is also known as backtracking), and the next number is tried.
 *
 * To ensure no duplicated subsets are generated, there are two conditions:
 *
 * Sort the Numbers: As mentioned, we start by sorting the array to bring duplicates together.
 *
 * Skip over duplicates: While generating subsets, if the current element is the same as the previous element and the previous element was not included in the current subset being generated (checked by i != u), it is skipped to avoid creating a subset that has already been created.
 *
 * This way, the dfs function systematically constructs all subsets, but avoids any repeats, ensuring that the power set it returns is free from duplicate subsets.
 *
 */
public class Q90_SubsetsII {
    // List to store the final subsets
    private List<List<Integer>> subsets;

    // The provided array of numbers, from which we will form subsets
    private int[] numbers;

    // Public method to find all subsets with duplicates
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        subsets = new ArrayList<>(); // Initialize the subsets list
        Arrays.sort(nums); // Sort the array to handle duplicates
        this.numbers = nums; // Store the sorted array in the numbers variable
        backtrack(0, new ArrayList<>()); // Start the backtrack algorithm
        return subsets; // Return the list of subsets
    }

    // Helper method to perform the backtrack algorithm
    private void backtrack(int index, List<Integer> currentSubset) {
        // Add a new subset to the answer list, which is a copy of the current subset
        subsets.add(new ArrayList<>(currentSubset));

        // Iterate through the numbers starting from index
        for (int i = index; i < numbers.length; ++i) {
            // Skip duplicates: check if the current element is the same as the previous one
            if (i != index && numbers[i] == numbers[i - 1]) {
                continue; // If it's a duplicate, skip it
            }

            // Include the current element in the subset
            currentSubset.add(numbers[i]);

            // Recursively call backtrack for the next elements
            backtrack(i + 1, currentSubset);

            // Exclude the current element from the subset (backtrack step)
            currentSubset.remove(currentSubset.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] input = {1,2,2};
        Q90_SubsetsII app = new Q90_SubsetsII();
        System.out.println(app.subsetsWithDup(input));
    }
}

