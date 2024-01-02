package com.java.codinground.educative.top_k_elements;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Statement#
 * Find the kth largest element in an unsorted array.
 *
 * We can use a min-heap to efficiently return the
 * �
 * �
 * ℎ
 * k
 * th
 *
 *  largest number from our unsorted array. Min-heap ensures the minimum element is always at the root, and it will keep the
 * �
 * k
 *  largest elements on top. Once we have inserted all the elements from the array onto the min-heap, we can simply return the root as our
 * �
 * �
 * ℎ
 * k
 * th
 *
 *  largest element.
 *
 * The algorithm above can be broken down into simpler steps as follows:
 *
 * Insert the first
 * �
 * k
 *  elements onto the min-heap.
 *
 * For each subsequent element in the array, compare it with the root (minimum element) of the min-heap.
 *
 * If the current element in the array is greater than the root element in our min-heap, remove the root element and insert the current element from the array.
 *
 * Continue inserting and removing elements in the min-heap until we have processed all elements in the array.
 *
 * After processing all the elements, the min-heap will contain the
 * �
 * k
 *  largest elements from the input array. The smallest of them, the
 * �
 * �
 * ℎ
 * k
 * th
 *
 *  largest element, will be at the root of the min-heap.
 *
 *
 */
public class KthLargestElement{
    public static int findKthLargest(int[] nums, int k){
        // Initialize an empty list
        PriorityQueue<Integer> kNumbersMinHeap = new PriorityQueue<Integer>((n1, n2) -> n1 - n2);

        // Add first k elements to the list
        for (int i = 0; i < k; i++)
            kNumbersMinHeap.add(nums[i]);

        // Loop through the remaining elements in the 'nums' array
        for (int i = k; i < nums.length; i++) {
            // Compare the current element with the minimum
            // element (root) of the min-heap
            if (nums[i] > kNumbersMinHeap.peek()) {
                // Remove the smallest element
                kNumbersMinHeap.poll();
                // Add the current element
                kNumbersMinHeap.add(nums[i]);
            }
        }

        // The root of the heap has the Kth largest element
        return kNumbersMinHeap.peek();
    }

    // Driver code
    public static void main(String[] args) {
        int[][] inputs = {
                {1, 5, 12, 2, 11, 9, 7, 30, 20},
                {5, 2, 9, -3, 7},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 4, 6, 0, 2},
                {3, 5, 2, 3, 8, 5, 3}
        };

        int[] K = {3, 1, 9, 1, 4};

        for(int i=0; i<K.length; i++){
            System.out.print(i+1);
            System.out.println(".\tInput array: "+ Arrays.toString(inputs[i]));
            System.out.println("\tValue of k: "+ K[i]);
            System.out.println("\tkth largest element: " + findKthLargest(inputs[i], K[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
