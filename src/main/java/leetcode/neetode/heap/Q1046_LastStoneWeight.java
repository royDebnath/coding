package leetcode.neetode.heap;

import java.util.PriorityQueue;

/**
 * You are given an array of integers stones where stones[i] is the weight of the ith stone.
 *
 * We are playing a game with the stones. On each turn, we choose the heaviest two stones and smash them together. Suppose the heaviest two stones have weights x and y with x <= y. The result of this smash is:
 *
 * If x == y, both stones are destroyed, and
 * If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
 * At the end of the game, there is at most one stone left.
 *
 * Return the weight of the last remaining stone. If there are no stones left, return 0.
 *
 *
 *
 * Example 1:
 *
 * Input: stones = [2,7,4,1,8,1]
 * Output: 1
 * Explanation:
 * We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
 * we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
 * we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
 * we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of the last stone.
 * Example 2:
 *
 * Input: stones = [1]
 * Output: 1
 *
 * In this problem, we have a collection of stones with different weights, given in an array stones, where each stone's weight is represented by stones[i]. We are simulating a game where we repeatedly smash the two heaviest stones together and determine the outcome according to the following rules:
 *
 * If both stones have the same weight (x == y), both stones get completely destroyed.
 * If the weights are different (x != y), the lighter stone gets destroyed, and the weight of the heavier stone gets reduced by that of the lighter stone (y - x).
 * The game continues until there is either one stone left or no stones remaining. The goal is to return the weight of the last remaining stone. If there are no stones left as a result of the smashes, we should return 0.
 *
 * The solution requires us to repeatedly find and remove the two heaviest stones. Since we need to do this repeatedly, a heap is an ideal data structure as it allows for efficient retrieval and updating of the largest elements.
 *
 * A max heap keeps the maximum element at the top. However, Python's heapq module provides a min heap, so we insert the negative of stone weights to simulate the behavior of a max heap.
 *
 * Example Walkthrough
 *
 * Let's consider a small example to illustrate the solution approach: Suppose we have an array of stone weights stones = [2, 7, 4, 1, 8, 1]. We need to perform the following steps:
 *
 * Convert the weights to their negative and create a min heap:
 *
 * We negate the weights: [-2, -7, -4, -1, -8, -1]
 * Create a min heap from these negated weights: h = [-8, -7, -4, -1, -2, -1]
 * While there are at least two stones in the heap:
 *
 * Pop the heaviest stone (smallest in negated form):
 * y = -heappop(h) gives us y = 8, and now h = [-7, -2, -4, -1, -1]
 * Pop the second heaviest stone:
 * x = -heappop(h) gives us x = 7, and now h = [-4, -2, -1, -1]
 * Push the weight difference negated back onto the heap since x != y:
 * The difference is 8 - 7 = 1. After negating, we push -1 back onto the heap.
 * Now, h = [-4, -2, -1, -1, -1]
 * Repeat the process:
 *
 * Next, y = -heappop(h) gives us y = 4, h = [-2, -1, -1, -1]
 * Then, x = -heappop(h) gives us x = 2, h = [-1, -1, -1]
 * The difference is 4 - 2 = 2. After negating, we push -2 back onto the heap.
 * Now, h = [-2, -1, -1]
 * Continue:
 *
 * Next, y = -heappop(h) gives us y = 2, h = [-1, -1]
 * Then, x = -heappop(h) gives us x = 1, h = [-1]
 * The difference is 2 - 1 = 1. After negating, we push -1 back onto the heap.
 * Now, h = [-1, -1]
 * Final iterations will destroy both of the stones because they have the same weight:
 *
 * Next, y = -heappop(h) gives us y = 1, h = [-1]
 * Then, x = -heappop(h) gives us x = 1, h = []
 * Since x == y, we don't push anything back onto the heap.
 * Now the heap is empty (h = []), that is, no stones are left. We return 0.
 *
 * The final result for the example input stones = [2, 7, 4, 1, 8, 1] is 0, as all stones are eventually destroyed.
 *
 *
 */
public class Q1046_LastStoneWeight {
    /**
     * Simulate the process of smashing stones together and return
     * the weight of the last remaining stone (if any).
     *
     * @param stones An array of stone weights.
     * @return The weight of the last stone, or 0 if no stones are left.
     */
    public int lastStoneWeight(int[] stones) {
        // Create a max-heap to store and compare the stone weights in descending order
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        // Add all stone weights to the max-heap
        for (int stone : stones) {
            maxHeap.offer(stone);
        }

        // Continue until there is only one stone left or none at all
        while (maxHeap.size() > 1) {
            // Get the two heaviest stones
            int stoneOne = maxHeap.poll();
            int stoneTwo = maxHeap.poll();

            // If they are not the same weight, put the difference back into the max-heap
            if (stoneOne != stoneTwo) {
                maxHeap.offer(stoneOne - stoneTwo);
            }
            // If they are equal, both stones are completely smashed, and nothing is added back
        }

        // Return the last stone's weight or 0 if no stones are left
        return maxHeap.isEmpty() ? 0 : maxHeap.poll();
    }
}
