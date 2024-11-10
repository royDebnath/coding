package com.roydebnath.coding.leetcode.neetode.heap;

import java.util.PriorityQueue;

/**
 * 703. Kth Largest Element in a Stream
 * Solved
 * Easy
 *
 * Topics
 * Companies
 * Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Implement KthLargest class:
 *
 * KthLargest(int k, int[] nums) Initializes the object with the integer k and the stream of integers nums.
 * int add(int val) Appends the integer val to the stream and returns the element representing the kth largest element in the stream.
 *
 *
 * Example 1:
 *
 * Input
 * ["KthLargest", "add", "add", "add", "add", "add"]
 * [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
 * Output
 * [null, 4, 5, 5, 8, 8]
 *
 * Explanation
 * KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
 * kthLargest.add(3);   // return 4
 * kthLargest.add(5);   // return 5
 * kthLargest.add(10);  // return 5
 * kthLargest.add(9);   // return 8
 * kthLargest.add(4);   // return 8
 *
 * In this problem, we are asked to design a KthLargest class that can determine the kth largest element in a stream of numbers. Importantly, when we refer to the kth largest element, we mean according to sorted order, not that the element is distinct from the others. The numbers can be repeated in the stream.
 *
 * The class should be able to handle two types of actions:
 *
 * Initialization (__init__): When an instance of the class is created, it should be initialized with an integer k and an array of integers nums. The k represents the position of the largest element we are interested in, and nums is the initial stream of numbers.
 *
 * Adding New Elements (add): The class should provide a method to add a new integer val to the stream. After adding the new integer, this method should return the current kth largest element from the stream.
 *
 * The straightforward approach to find the kth largest element would be to sort the stream of numbers each time we insert a new element and then pick the kth largest. However, sorting every time we insert a new element leads to a less efficient solution. To solve the problem more optimally, we can use a data structure that does the hard work for us - a Min Heap.
 *
 * The reason behind choosing a Min Heap is its useful property: the smallest element is always at the root and can be accessed in constant time. If we maintain a Min Heap of exactly k largest elements from the current stream, the root of the heap (the smallest element in the heap) is our desired kth largest element.
 *
 * Here's how we construct our solution using this intuition:
 *
 * Initialize a Min Heap (self.q) and a variable self.size to store k.
 * Insert the initial stream of numbers to the Min Heap using the add method.
 * During each insert, we push the new value into the heap.
 * If the heap size exceeds k, it means we have more than k elements, so we can remove the smallest one (the root).
 * The kth largest element will then be the new root of the heap.
 * The add method maintains the Min Heap invariant and ensures it always contains the k largest elements after each insertion.
 * Finally, we can return the kth largest element by looking at the root of the Min Heap.
 * Using this approach, we avoid the need to sort the entire stream every time, thereby improving the efficiency of finding the kth largest element in the stream.
 *
 * 703. Kth Largest Element in a Stream
 * Easy
 * Tree
 * Design
 * Binary Search Tree
 * Binary Tree
 * Data Stream
 * Heap (Priority Queue)
 * Leetcode Link
 * Problem Description
 *
 * In this problem, we are asked to design a KthLargest class that can determine the kth largest element in a stream of numbers. Importantly, when we refer to the kth largest element, we mean according to sorted order, not that the element is distinct from the others. The numbers can be repeated in the stream.
 *
 * The class should be able to handle two types of actions:
 *
 * Initialization (__init__): When an instance of the class is created, it should be initialized with an integer k and an array of integers nums. The k represents the position of the largest element we are interested in, and nums is the initial stream of numbers.
 *
 * Adding New Elements (add): The class should provide a method to add a new integer val to the stream. After adding the new integer, this method should return the current kth largest element from the stream.
 *
 * Intuition
 *
 * The straightforward approach to find the kth largest element would be to sort the stream of numbers each time we insert a new element and then pick the kth largest. However, sorting every time we insert a new element leads to a less efficient solution. To solve the problem more optimally, we can use a data structure that does the hard work for us - a Min Heap.
 *
 * The reason behind choosing a Min Heap is its useful property: the smallest element is always at the root and can be accessed in constant time. If we maintain a Min Heap of exactly k largest elements from the current stream, the root of the heap (the smallest element in the heap) is our desired kth largest element.
 *
 * Here's how we construct our solution using this intuition:
 *
 * Initialize a Min Heap (self.q) and a variable self.size to store k.
 * Insert the initial stream of numbers to the Min Heap using the add method.
 * During each insert, we push the new value into the heap.
 * If the heap size exceeds k, it means we have more than k elements, so we can remove the smallest one (the root).
 * The kth largest element will then be the new root of the heap.
 * The add method maintains the Min Heap invariant and ensures it always contains the k largest elements after each insertion.
 * Finally, we can return the kth largest element by looking at the root of the Min Heap.
 * Using this approach, we avoid the need to sort the entire stream every time, thereby improving the efficiency of finding the kth largest element in the stream.
 *
 * Learn more about Tree, Binary Search Tree, Binary Tree, Data Stream and Heap (Priority Queue) patterns.
 *
 * Solution Approach
 *
 * To implement the solution, we employ Python's heapq module, which provides an implementation of the Min Heap data structure.
 *
 * Here's a step-by-step breakdown of the implementation:
 *
 * When an instance of KthLargest class is created, the __init__ method is called with two parameters: k and nums.
 *
 * k is stored in self.size which represents the position of the kth largest element we are interested in.
 * self.q is initialized as an empty list, which will be used as the Min Heap.
 * The nums list is iterated over and each number is added to the Min Heap using the add method.
 *
 * The add method handles inserting a new element into the Min Heap and ensures the Min Heap contains only the k largest elements.
 *
 * heappush(self.q, val) is used to add the new value val to the Min Heap.
 * We check if the size of the heap exceeds k by comparing len(self.q) to self.size.
 * If the heap size is greater than k, we remove the smallest element (root of the Min Heap) using heappop(self.q) to ensure that only the k largest elements remain.
 * This keeps the heap size at max k elements at all times.
 * The root element, which can be accessed with self.q[0], is the kth largest element because all elements bigger than it are also in the heap.
 * By following this approach, we ensure that:
 *
 * The add operation has a time complexity of O(log k) since it involves heap operations which work in logarithmic time relative to the number of elements in the heap.
 * The space complexity of the solution is O(k) because the Min Heap holds at most k elements at any given time.
 * This provides a balanced and efficient way to continuously find the kth largest element in the stream without having to sort the entire array each time a new element is added.
 *
 * Discover Your Strengths and Weaknesses: Take Our 2-Minute Quiz to Tailor Your Study Plan:
 * Which algorithm should you use to find a node that is close to the root of the tree?
 *
 * DFS
 * BFS
 * Binary search
 * Backtracking
 * I don’t know
 * Example Walkthrough
 *
 * Let us consider an example to illustrate the solution approach.
 *
 * Suppose k is 3, and the initial array of numbers nums is [4, 5, 8, 2]. We want to design a KthLargest class where we can add elements and always be able to find the 3rd largest element in the current stream.
 *
 * Here is a step-by-step walkthrough of how the KthLargest class processes the stream of numbers:
 *
 * We create an instance of KthLargest by passing in our values k=3 and nums=[4,5,8,2]. The __init__ method initializes self.size to 3 and self.q as an empty Min Heap.
 *
 * We add each element of nums into the Min Heap using the add method, ensuring that after every addition, the Min Heap holds at most k largest elements. Initially, the Min Heap (self.q) is empty, and we proceed as follows:
 *
 * Add 4: self.q becomes [4].
 * Add 5: self.q becomes [4, 5].
 * Add 8: self.q becomes [4, 5, 8].
 * Add 2: self.q remains [4, 5, 8] because 2 is not among the 3 largest elements.
 * Now the Min Heap contains k largest elements, the kth largest (3rd largest) among them is 4 (the root of the Min Heap).
 *
 * If we call the add method to insert a new element, let's say 3, we again ensure that the Min Heap only contains the k largest elements.
 *
 * Add 3: self.q remains [4, 5, 8] because 3 is not among the 3 largest elements. The 3rd largest element is still 4.
 * Let's insert a larger element, 9. The Min Heap needs to accommodate this change, and the smallest element in the heap should be removed if the heap's size exceeds k.
 *
 * Add 9: self.q becomes [5, 8, 9] after removing 4 because the size exceeded k. Now the 3rd largest element is 5.
 * So after adding the element 9, our Min Heap has elements [5, 8, 9], and the 3rd largest (kth largest) element is 5.
 *
 * The class now effectively and efficiently maintains the 3rd largest element as we add new values into the stream. The add method updates the Min Heap in logarithmic time, making the operation swift compared to the linear time complexity of sorting the numbers upon each insertion.
 *
 *
 */
public class Q703_KthLargestElementInStream {
}

// Class to find the kth largest element in a stream of numbers
class KthLargest {

    // Priority queue to maintain the smallest 'k' elements seen so far
    private PriorityQueue<Integer> minHeap;

    // The kth position we are interested in for the largest element
    private int k;

    /**
     * Constructor to initialize the data structure and populate with initial elements.
     *
     * @param k    The kth position to track in the list of largest elements.
     * @param nums An array of initial numbers to be added to the kth largest tracker.
     */
    public KthLargest(int k, int[] nums) {
        // Initialize a min-heap with the capacity to hold 'k' elements
        this.minHeap = new PriorityQueue<>(k);
        this.k = k;

        // Add the initial elements to the kth largest tracker
        for (int num : nums) {
            add(num);
        }
    }

    /**
     * Adds a new number into the stream and returns the kth largest element.
     *
     * @param val The new number to be added to the stream.
     * @return The kth largest element after adding the new number.
     */
    public int add(int val) {
        // Always add the new value to the min-heap
        minHeap.offer(val);

        // If the size of the min-heap exceeds 'k', remove the smallest element
        if (minHeap.size() > k) {
            minHeap.poll();
        }

        // The root of the min-heap represents the kth largest element
        return minHeap.peek();
    }
}

/* Usage example:
 * KthLargest kthLargest = new KthLargest(3, new int[]{4, 5, 8, 2});
 * kthLargest.add(3);   // returns 4
 * kthLargest.add(5);   // returns 5
 * kthLargest.add(10);  // returns 5
 * kthLargest.add(9);   // returns 8
 * kthLargest.add(4);   // returns 8
 */