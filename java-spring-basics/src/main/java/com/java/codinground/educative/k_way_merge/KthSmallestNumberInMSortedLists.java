package com.java.codinground.educative.k_way_merge;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Statement
 * Given an m  number of sorted lists in ascending order and an integer, k, find the kth  smallest number
 * among all the given lists.
 *
 * Although there can be repeating values in the lists, each element is considered unique and,
 * therefore, contributes to calculating the kth smallest element.
 *
 * If k is greater than the total number of elements in the input lists,
 * return the greatest element from all the lists, and if there are no elements in the input lists, return 0.
 *
 * Solution :
 *
 * 1. Push the first element of each list in the min-heap along with
 *    the index of the list and the index of the element in the list.
 *    a[0] = element, a[1] = index of the list, a[2] = index of the element in that list
 *    so the minHeap priority queue will be int[] to store all these three.
 *    Ordering of the minHeap will be based on a[0], i.e. the smallest element in the root/first element of the queue.
 *
 *
 * 2. Pop the top element of the min-heap.
 *   (the smallest element among the first elements of each list we just pushed,
 *   which is also the smallest element of all, as the lists are sorted.)
 *
 * 3. If the popped element has the next element in its list,
 *    push the next element of this list in the min-heap. (pushing the second smallest).
 *
 *    If the popped/smallest element does not have anymore elements in its list,
 *    the loop will pop the next smallest element and do the same thing repeating step 2 and 3.
 *
 * 4. Repeat steps 2 and 3 until there are elements in the min-heap or we’ve removed k  elements from the heap.
 *
 * 5. The last element popped from the min-heap during this process is the required kth smallest
 *    element in the m sorted lists.
 *
 * Example :
 *
 * list 1 : 2, 6, 8
 * list 2 : 3, 6, 10
 * list 3 : 5, 8, 11
 *
 * Step 1 : populate the minHeap with the first elements of the lists
 *
 *          2
 *         / \
 *        3   5
 *
 * Loop :
 * Step 2 : pop the smallest from the minHeap, take 2
 * smallestElementList : 2
 *
 * Step 3 : next element of 2, in its list is 6, push 6 to heap, making the heap as :
 *
 *      3
 *     / \
 *    6   5
 *
 * Step 2 : pop the smallest from the minHeap, take 3
 * smallestElementList : 2,3
 *
 * Step 3 : next element of 3, in its list is 6, push 6 to heap, making the heap as :
 *
 *       5
 *      / \
 *     6   6
 *
 * Step 2 : pop the smallest from the minHeap, take 5
 * smallestElementList : 2,3,5
 *
 * Step 3 : next element of 5, in its list is 8, push 8 to heap, making the heap as :
 *       6
 *      / \
 *     6   8
 *
 * Step 2 : pop the smallest from the minHeap, take 6
 * smallestElementList : 2,3,5,6
 *
 * keep on doing this till the smallestElementList reaches k elements. The kth element is our answer.
 */
public class KthSmallestNumberInMSortedLists {
    public static int kSmallestNumber(List<List<Integer>> lists, int k) {
        // storing the length of lists to use it in a loop later
        int listLength = lists.size();
        // declaring a min-heap to keep track of smallest elements
        PriorityQueue<int[]> kthSmallest = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        for (int index = 0; index < listLength; index++) {
            // if there are no elements in the input lists, continue
            if (lists.get(index).size() == 0) {
                continue;
            } else {
                // placing the first element of each list in the min-heap
                kthSmallest.offer(new int[] {lists.get(index).get(0), index, 0});
            }
        }

        // set a counter to match if our kth element
        // equals to that counter, return that number
        int numbersChecked = 0, smallestNumber = 0;
        while (!kthSmallest.isEmpty()) {  // iterating over the elements pushed in our min-heap
            // get the smallest number from top of heap and its corresponding list and index
            int[] smallest = kthSmallest.poll();
            smallestNumber = smallest[0];
            int listIndex = smallest[1];
            int numIndex = smallest[2];
            numbersChecked++;

            if (numbersChecked == k) {
                break;
            }

            // if there are more elements in list of the top element,
            // add the next element of that list to the min-heap
            if (numIndex + 1 < lists.get(smallest[1]).size()) {
                kthSmallest.offer(new int[] {lists.get(listIndex).get(numIndex + 1), listIndex, numIndex + 1});
            }
        }

        // return the Kth number found in input lists
        return smallestNumber;
    }
    // Driver code
    public static void main(String[] args) {

        List<List<List<Integer>>> lists = Arrays.asList(
                Arrays.asList(
                        Arrays.asList(2, 6, 8),
                        Arrays.asList(3, 6, 10),
                        Arrays.asList(5, 8, 11)
                ),
                Arrays.asList(
                        Arrays.asList(1, 2, 3),
                        Arrays.asList(4, 5),
                        Arrays.asList(6, 7, 8, 15),
                        Arrays.asList(10, 11, 12, 13),
                        Arrays.asList(5, 10)
                ),
                Arrays.asList(
                        Arrays.asList(),
                        Arrays.asList(),
                        Arrays.asList()
                ),
                Arrays.asList(
                        Arrays.asList(1, 1, 3, 8),
                        Arrays.asList(5, 5, 7, 9),
                        Arrays.asList(3, 5, 8, 12)
                ),
                Arrays.asList(
                        Arrays.asList(5, 8, 9, 17),
                        Arrays.asList(),
                        Arrays.asList(8, 17, 23, 24)
                )
        );

        int[] k = {5, 50, 7, 4, 8};

        // loop to execute till the length of list k
        for (int i = 0; i < k.length; i++) {
            System.out.println(i + 1 + ".\t Input lists: " + lists.get(i) +
                    "\n\t K = " + k[i] +
                    "\n\t " + k[i] + "th smallest number from the given lists is: " +
                    kSmallestNumber(lists.get(i), k[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }

    }
}
