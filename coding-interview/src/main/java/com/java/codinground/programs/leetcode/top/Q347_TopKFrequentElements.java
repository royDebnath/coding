package com.java.codinground.programs.leetcode.top;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Problem Description
 * The LeetCode problem provides us with an integer array nums and an integer k. Our task is to find the k most frequent elements in the array. The "frequency" of an element is the number of times it occurs in the array. The problem specifies that we can return the result in any order, which means the sequence of the results does not matter.
 *
 * Intuition
 * To solve this problem, we need to count the occurrences of each element and then find the k elements with the highest frequencies. The natural approach is to use a hash map (or dictionary in Python) to achieve the frequency count efficiently.
 *
 * Once we have the frequency of each element, we want to retrieve the k elements with the highest frequency. A common data structure to maintain the k largest or smallest elements is a heap. In Python, we use a min-heap by default, which ensures that the smallest element is always at the top.
 *
 * The intuition behind the solution is:
 *
 * Count the frequency of each element using a hash map.
 * Iterate over the frequency map, adding each element along with its frequency as a tuple to a min-heap.
 * If the heap exceeds size k, we remove the smallest item, which is automatically done because of the heap's properties. This ensures we only keep the k most frequent elements in the heap.
 * After processing all elements, we're left with a heap containing k elements with the highest frequency.
 * We convert this heap into a list containing just the elements (not the frequencies) to return as our final answer.
 * This approach is highly efficient as it allows us to keep only the k most frequent elements at all times without having to sort the entire frequency map, which could be much larger than k.
 *
 * Solution Approach
 *
 * Example Walkthrough
 * Let's use a small example to illustrate the solution approach. Consider the array nums = [1,2,3,2,1,2] and k = 2. Our goal is to find the 2 most frequent elements in nums.
 *
 * We first use Counter(nums) to create a frequency map. This gives us {1: 2, 2: 3, 3: 1} where the key is the number from nums and the value is its frequency.
 *
 * We initialize an empty min-heap hp. It's going to store tuples like (frequency, num).
 *
 * We iterate over the frequency map and add each num and its frequency to hp. For example, (2, 1) for the number 1 with a frequency of 2. We use heappush to add the tuples to hp, so after this step hp might have [(1, 3), (2, 1)].
 *
 * The heap should not exceed the size k. In our case, k is 2, which means after we add the third element (3, 2), we need to pop the smallest frequency. So we end up with hp as [(2, 1), (3, 2)] after all the operations since (1, 3) would be the popped element because it had the lowest frequency.
 *
 * The heap now contains the tuples for the 2 most frequent elements. The tuple with the smallest frequency is at the top, ensuring that less frequent elements have been popped off when the size limit was exceeded.
 *
 * Finally, to build our result list, we extract the number from each tuple in the heap. Using list comprehension [v[1] for v in hp] we get [1, 2], which are the elements with the highest frequency. This is our final result and we can return it.
 *
 * Following this approach, we implemented an efficient solution to the problem that avoids sorting the entire frequency map directly and instead maintains a heap of size k to track the k most frequent elements.
 *
 *
 */
public class Q347_TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        // Create a Map to store the frequency of each number
        Map<Integer, Long> frequencyMap = Arrays.stream(nums)
                .boxed() // box the ints to Integers
                .collect(Collectors.groupingBy(Function.identity(), // group by the number itself
                        Collectors.counting())); // count the frequency

        // Initialize a min-heap based on the frequency values
        Queue<Map.Entry<Integer, Long>> minHeap = new PriorityQueue<>(Comparator.comparingLong(Map.Entry::getValue));

        // Iterate over the frequency map
        for (Map.Entry<Integer, Long> entry : frequencyMap.entrySet()) {
            // Insert the current entry into the min-heap
            minHeap.offer(entry);

            // If the heap size exceeds 'k', remove the smallest frequency element
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // Extract the top 'k' frequent numbers from the min-heap into an array
        return minHeap.stream()
                .mapToInt(Map.Entry::getKey)
                .toArray();
    }
}
