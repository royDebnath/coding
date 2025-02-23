package leetcode.neetode.dfs_backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array nums of distinct integers, return all the possible permutations.
 * You can return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * Example 2:
 *
 * Input: nums = [0,1]
 * Output: [[0,1],[1,0]]
 * Example 3:
 *
 * Input: nums = [1]
 * Output: [[1]]
 *
 * Intuition
 * The intuition behind the solution is to use Depth-First Search (DFS).
 * DFS is a common algorithm for traversing or searching tree or graph data structures.
 * The idea is to follow one branch of the tree down as many levels as possible until the end is reached, and then proceed to the next branch.
 *
 * When dealing with permutations, we can imagine each permutation as a path in a decision tree, where each level represents an element in the permutation and each branch represents a choice of which element to place next.
 *
 * Here's how we can visualize this approach:
 *
 * Start with an empty permutation.
 * For every index in the permutation, try placing each unused element
 * (one that has not been used in this particular path/branch).
 * After an element is placed at the current index, mark it as used and move to the next index.
 * Once an index is placed with all possible elements, backtrack (step back) and
 * try the next possible element for the previous index.
 * Repeat until all elements are placed in the permutation,
 * meaning we've reached the end of the branch.
 * This permutation is then added to the list of results.
 * When all branches are explored, we will have found all the permutations.
 *
 * The dfs() function is a recursive method that implements this approach.
 * It uses the index i to keep track of the depth of recursion
 * (which corresponds to the current position in the permutation).
 * The visited array helps track which elements have been used,
 * and the t array represents the current permutation being constructed.
 * Once index i reaches n, the length of nums, it means a full permutation has been created
 * and it's added to the answer ans.
 *
 *
 * Solution Approach
 *
 * Here's a step-by-step breakdown of the algorithm:
 *
 * Initialize an n-length boolean array vis to keep track of which elements from nums have been used
 * in the current branch of the DFS.
 * At first, all values are False indicating no elements have been used yet.
 *
 * Create a temporary array t of length n. This array will hold the current permutation
 * as it is being built.
 *
 * An empty list ans is initialized to store all the completed permutations.
 *
 * The dfs function is defined to perform the DFS.
 * It takes an argument i which is the current depth of the DFS,
 * corresponding to the index in the permutation where we are placing the next element.
 *
 * In the dfs function, the base condition checks if i is equal to n. If true,
 * it means we've reached the end of a branch in the DFS and a full permutation has been constructed,
 * so we append a copy of this permutation to ans.
 *
 * If the base condition is not met, the function proceeds to iterate over all elements of nums.
 *
 * For each element, if it has not already been used (i.e., vis[j] is False), we mark it as used by setting vis[j] to True.
 * We then place this element in the i-th position of the current permutation being built t[i].
 * Call dfs(i + 1) to proceed to the next level of depth, attempting to find an element for the next position.
 * After returning from the deeper recursive call, we reset vis[j] to False to "unchoose" the element, thus enabling it to be used in a different branch or permutation. This is the backtracking step.
 * The initial call to dfs is made with an argument of 0 to start the DFS from the first position in the permutation.
 *
 * Finally, the ans list is returned which now contains all possible permutations.
 *
 * Example Walkthrough
 * Let's illustrate the solution approach using a simple example where our array nums is [1,2,3].
 * We want to find all permutations of this array.
 *
 * We initialize a boolean array vis of length 3 (n=3 in this case), all set to False,
 * which will help us keep track of the elements that have been used in the current permutation.
 * We also create a temporary array t to build the current permutation and an empty list ans
 * to store all permutations.
 *
 * vis = [False, False, False] t = [0, 0, 0] ans = []
 *
 * Start the DFS with dfs(i=0). At this level of recursion,
 * we are looking to fill in the first position of the t array.
 *
 * Our for loop in the dfs function will consider each element in nums:
 *
 * a. On the first iteration, we choose 1. We set vis[0] to True and place 1 in t[0].
 *
 * b. We call dfs(i=1) to decide on the second element of the permutation.
 * Now we are in a new level of recursion, trying to fill t[1].
 * Again, we traverse nums. We skip 1 because vis[0] is True.
 * We pick 2, set vis[1] to True, and place 2 in t[1].
 *
 * a. We call dfs(i=2).
 *
 * The next level of recursion is to place the third element. 1 and 2 are marked as used,
 * so we pick 3, set vis[2] to True, and place 3 in t[2].
 *
 * Now, i equals to n. We've reached the end of the branch and have a complete permutation [1,2,3],
 * which we add to ans.
 *
 * We backtrack by returning to where we picked 3. We unmark vis[2] (vis[2]=False),
 * trying to explore other possibilities for this position, but there are no more elements left to use.
 * So, we backtrack further.
 *
 * Back at the second element decision step (dfs(i=1)), we backtrack off of element 2 and pick 3 for t[1].
 * vis is now [True, False, True]. We call dfs(i=2) to decide the third element.
 *
 * In this call, 2 is the only unused element, so we put it in t[2], making the permutation [1,3,2].
 * We add this to ans.
 *
 * This recursive process continues, systematically exploring each possible permutation
 * and backtracking after exploring each branch to the fullest extent.
 * This ensures that we explore all permutations without duplication.
 *
 * In the end, ans would be:
 *
 * ans = [
 *     [1, 2, 3],
 *     [1, 3, 2],
 *     [2, 1, 3],
 *     [2, 3, 1],
 *     [3, 1, 2],
 *     [3, 2, 1]
 * ]
 *
 *
 */
public class Q46_Permutations {
    // List to hold all the permutations
    private List<List<Integer>> permutations = new ArrayList<>();

    // Temporary list to hold the current permutation
    private List<Integer> currentPermutation = new ArrayList<>();

    // Visited array to keep track of the elements already included in the permutation
    private boolean[] visited;

    // Array of numbers to create permutations from
    private int[] elements;

    // Method to initiate the process of finding all permutations
    public List<List<Integer>> permute(int[] nums) {
        elements = nums;
        visited = new boolean[nums.length];
        backtrack(0);
        return permutations;
    }

    // Helper method to perform backtracking
    private void backtrack(int index) {
        // Base case: if the permutation size is equal to the number of elements, add it to the answer
        if (index == elements.length) {
            permutations.add(new ArrayList<>(currentPermutation));
            return;
        }

        // Iterate through the elements array
        for (int i = 0; i < elements.length; ++i) {
            // If the element at index j has not been visited, include it in the permutation
            if (!visited[i]) {
                // Mark the element at index j as visited
                visited[i] = true;
                // Add the element to the current permutation
                currentPermutation.add(elements[i]);
                // Continue to the next level of depth (next index)
                backtrack(index + 1);
                // Backtrack: remove the last element added and mark it as not visited
                currentPermutation.remove(currentPermutation.size() - 1);
                visited[i] = false;
            }
        }
    }
}
