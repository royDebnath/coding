package com.java.codinground.programs.leetcode.top;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem Description
 * The LeetCode problem asks us to generate all the possible subsets from a given list of unique integers. The term 'subset' refers to any combination of numbers that can be formed from the original list, including the empty set and the set itself. For instance, if the input is [1,2,3], then the subsets would include [], [1], [2], [3], [1,2], [1,3], [2,3], and [1,2,3]. The objective is to list down all these possibilities. We should also ensure that there are no duplicate subsets in the solution and the subsets can be returned in any order.
 *
 * Intuition
 * To solve the problem, we are using a concept known as Depth-First Search (DFS). The process involves exploring each element and deciding whether to include it in the current subset (t). At each step, we have the choice to either include or not include the current element in our subset before moving to the next element.
 *
 * For example, if our set is [1, 2, 3], the DFS approach starts at the first element (1 then 2 then 3). For each element, we take two paths: in one path we include the element into the subset, and in the other, we do not.
 *
 * Here's what the decision tree would look like for [1, 2, 3]:
 *
 * Start with an empty subset [].
 * Choose whether to include 1 or not, resulting in subsets [] and [1].
 * For each of these subsets, choose whether to include 2, leading to [], [1], [2], and [1, 2].
 * Finally, for each of these, choose whether to include 3, ending with [], [1], [2], [1, 2], [3], [1, 3], [2, 3], and [1, 2, 3].
 * At the end of this process, we have explored all possible combinations, and our ans list contains all of them. Each recursive call represents a decision, and by exploring each decision, we exhaust all possibilities and build the power set.
 *
 * Example Walkthrough
 * Let's take a smaller example set [1, 2] to illustrate the solution approach.
 *
 * Initially, our subset t is empty, and we start at index 0. The first decision is whether to include 1 in our subset or not.
 *
 * We first choose not to include 1, so our subset t remains []. We call dfs(1) to handle the next element (index 1 now refers to 2 in our set).
 *
 * At index 1, again we decide whether to include 2 or not. First, we choose not to include it, so subset t remains as []. We've now considered every element, so we append this subset to our answer list ans, which now has [[]].
 *
 * Backtracking to the previous choice for 2, this time we include it in the subset t, which now becomes [2]. Again, each element has been considered, so we add [2] to ans, which becomes [[], [2]].
 *
 * Now we have finished exploring the possibilities for index 1 (with element 2), so we backtrack to the situation before including 2. This means we remove 2 from our subset t by calling t.pop().
 *
 * We then backtrack to the first decision at index 0 and choose the path where we include 1 in our subset. Now, t contains [1]. We move to index 1 by calling dfs(1) to make decisions for 2.
 *
 * At index 1, we start with decision not to include 2 first, which means our subset t does not change, and is [1]. Since we are at the end, we add this to ans, resulting in [[], [2], [1]].
 *
 * Finally, we include 2 in our subset to have [1, 2] and since all elements are now considered, we include this subset in ans, resulting in [[], [2], [1], [1, 2]].
 *
 * Throughout this process, we've been adding our subset t only when we have considered all elements, which means when i is equal to the length of nums. This ensures we include every possible combination in our ans, the list of all subsets ([], [1], [2], [1, 2] for our example).
 *
 * The solution methodically explores all combinations through recursive DFS calls and includes backtracking to make sure that we cover different subsets as we make different decisions (to include/not include an element). The incremental nature of adding to the subset t, along with the corresponding backtracking step, ensures the completeness and correctness of the algorithm.
 *
 *
 */
public class Q78_Subsets {
    // A list to store all subsets
    private List<List<Integer>> subsetsList = new ArrayList<>();

    // A temporary list to store one subset
    private List<Integer> tempSubset = new ArrayList<>();

    // An array to store the given numbers
    private int[] numbers;

    /**
     * This is the main method that returns all possible subsets of the given array.
     * @param nums Array of integers for which subsets are to be found.
     * @return A list of all possible subsets of the given array.
     */
    public List<List<Integer>> subsets(int[] nums) {
        this.numbers = nums;
        // Start the Depth-First Search (DFS) with the first index
        depthFirstSearch(0);
        return subsetsList;
    }

    /**
     * This method uses Depth-First Search (DFS) to explore all potential subsets.
     * @param index The current index in the numbers array being explored.
     */
    private void depthFirstSearch(int index) {
        // If the current index has reached the length of the array,
        // it means we've formed a subset which can now be added to the list of subsets.
        if (index == numbers.length) {
            subsetsList.add(new ArrayList<>(tempSubset));
            return;
        }

        // Case 1: The current number is excluded from the subset,
        // so we simply call dfs on the next index.
        depthFirstSearch(index + 1);

        // Case 2: The current number is included in the subset.
        // Add the current number to the tempSubset.
        tempSubset.add(numbers[index]);

        // Move on to the next index to explore further with the current number included.
        depthFirstSearch(index + 1);

        // Backtrack: remove the last number added to the tempSubset,
        // this effectively removes the current number from the subset.
        tempSubset.remove(tempSubset.size() - 1);
    }
}
