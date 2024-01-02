package com.java.codinground.educative.heap;

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * A "heap" is a binary tree in which each node is either greater than or equal to its children ("max heap") –
 * or less than or equal to its children ("min-heap").
 *
 * For the priority queue in this article, we use a min heap because the highest priority is the one with the
 * lowest number (priority 1 is usually higher than priority 2).
 *
 * Here is an example of what such a min-heap might look like:
 *
 *          1
 * 		   / \
 * 		  2   4
 * 		 /\   /\
 * 		3  7|9  6
 * 	   / \
 * 	  8   5
 *
 * The element at each node of this tree is less than the elements of its two child nodes:
 *
 * Array Representation of a Heap
 * We can store a heap in an array by mapping its elements row by row – from top left to bottom right – to the array
 *
 * [1, 2, 4, 3, 7, 9, 6, 8, 5]
 *
 * In a min-heap, the smallest element is always at the top, i.e., in the array, it is always at the first position.
 * This is why, when you print a Java PriorityQueue as a string, you see the smallest element on the left.
 * What you see is the array representation of the min-heap underlying the PriorityQueue.
 *
 * The following lines of code demonstrate this well:
 *
 *
 */
public class MinMaxHeap {
    public static void main(String[] args) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.addAll(List.of(4, 7, 3, 8, 2, 9, 6, 5, 1));
        System.out.println("minHeap = " + minHeap); // prints the array representation of the min heap.

        /**
         * passing a reverse comparator, priority queue can be used as a max heap
         */
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // the constructor takes a comparator
        PriorityQueue<Integer> maxHeap1 =new PriorityQueue<>((x, y) -> Integer.compare(y, x)); // lambda comparator
        PriorityQueue<Integer> maxHeap2 = new PriorityQueue<>((x,y) -> y - x);
        maxHeap.addAll(List.of(4, 7, 3, 8, 2, 9, 6, 5, 1));
        maxHeap1.addAll(List.of(4, 7, 3, 8, 2, 9, 6, 5, 1));
        maxHeap2.addAll(List.of(4, 7, 3, 8, 2, 9, 6, 5, 1));
        System.out.println("maxHeap = " + maxHeap); // prints the array representation of the max heap.
        System.out.println("maxHeap1 = " + maxHeap1);
        System.out.println("maxHeap2 = " + maxHeap2);
    }
    /***
     *  the smallest/largest element is always on the left for min/max heap. That tells how the peek() operation has to work:
     *  it simply has to return the first element of the array.
     */
}
