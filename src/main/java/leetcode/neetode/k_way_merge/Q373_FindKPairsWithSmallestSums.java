package leetcode.neetode.k_way_merge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 373. Find K Pairs with Smallest Sums
 * Medium
 * 5.8K
 * 369
 * Companies
 * You are given two integer arrays nums1 and nums2 sorted in non-decreasing order and an integer k.
 *
 * Define a pair (u, v) which consists of one element from the first array and one element from the second array.
 *
 * Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * Output: [[1,2],[1,4],[1,6]]
 * Explanation: The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * Example 2:
 *
 * Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * Output: [[1,1],[1,1]]
 * Explanation: The first 2 pairs are returned from the sequence: [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * Example 3:
 *
 * Input: nums1 = [1,2], nums2 = [3], k = 3
 * Output: [[1,3],[2,3]]
 * Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
 *
 * Solution :
 *
 * We can use the k-way merge pattern here because it helps solve problems where we need to compute a result by comparing the elements of multiple sorted lists.
 *
 * While traversing through the elements of both lists and making pairs, we use a min-heap to track the pair with the smallest sum encountered so far.
 *
 * As proposed above, we use a min-heap to store three things:
 *
 * The smallest sum (at the root of the min-heap).
 *
 * The sum of each pair.
 *
 * The list indexes of each element in the pair.
 *
 * Initially, we can start making pairs by adding only the first element of the second list to each element of the first list.
 *
 * Let’s take two lists as an example,
 * [
 * 2
 * ,
 * 8
 * ,
 * 9
 * ]
 * [2,8,9]
 *  and
 * [
 * 1
 * ,
 * 3
 * ,
 * 6
 * ]
 * [1,3,6]
 * . We can make 3 pairs in the first iteration and store them in the min-heap:
 *
 * [
 * (
 * 2
 * +
 * 1
 * )
 * =
 * 3
 * ,
 * (
 * 8
 * +
 * 1
 * )
 * =
 * 9
 * ,
 * (
 * 9
 * +
 * 1
 * )
 * =
 * 10
 * ]
 * [(2+1)=3,(8+1)=9,(9+1)=10]
 *
 * Now, we start a second loop that iterates while elements are in the min-heap and while we have yet to find all
 * �
 * k
 *  smallest pairs. In each iteration, we perform three steps:
 *
 * Pop the smallest pair from the min-heap, noting the sum of the pair and the list indexes of each element, calling the i and j indexes, respectively.
 *
 * Add this pair to the result list.
 *
 * Increment j to move forward in the second list, and compose a new pair with the same element from the first list and the next element in the second list. This step is skipped if a new pair can’t be formed when the popped pair’s j was already pointing to the end of the second list.
 *
 * Supposing
 * �
 * =
 * 9
 * k=9
 *  in our example, the sequence of pairs pushed and popped in the second step is as follows:
 *
 * 1.  Pop:  (2 + 1) = 3  // 1st pair // Min-heap: [(8 + 1) = 9, (9 + 1) = 10]
 * 2.  Push: (2 + 3) = 5              // Min-heap: [(2 + 3) = 5, (8 + 1) = 9, (9 + 1) = 10]
 * 3.  Pop:  (2 + 3) = 5  // 2nd pair // Min-heap: [(8 + 1) = 9, (9 + 1) = 10]
 * 4.  Push: (2 + 6) = 8              // Min-heap: [(2 + 6) = 8, (8 + 1) = 9, (9 + 1) = 10]
 * 5.  Pop:  (2 + 6) = 8  // 3rd pair // Min-heap: [(8 + 1) = 9, (9 + 1) = 10]
 *
 * <-- No pair to push as at the end of list 2 -->
 *
 * 6.  Pop:  (8 + 1) = 9  // 4th pair // Min-heap: [(9 + 1) = 10]
 * 7.  Push: (8 + 3) = 11             // Min-heap: [(9 + 1) = 10, (8 + 3) = 11]
 * 8.  Pop:  (9 + 1) = 10 // 5th pair // Min-heap: [(8 + 3) = 11]
 * 9.  Push: (9 + 3) = 12             // Min-heap: [(8 + 3) = 11, (9 + 3) = 12]
 * 10. Pop:  (8 + 3) = 11 // 6th pair // Min-heap: [(9 + 3) = 12]
 * 11. Push: (8 + 6) = 14             // Min-heap: [(9 + 3) = 12, (8 + 6) = 14]
 * 12. Pop:  (9 + 3) = 12 // 7th pair // Min-heap: [(8 + 6) = 14]
 * 13. Push: (9 + 6) = 15             // Min-heap: [(8 + 6) = 14, (9 + 6) = 15]
 * 14. Pop:  (8 + 6) = 14 // 8th pair // Min-heap: [(9 + 6) = 15]
 *
 * <-- No pair to push as at the end of list 2 -->
 *
 * 15. Pop:  (9 + 6) = 15 // 9th
 * At all times, the smallest sum is at the root of the min-heap. Overall, we remove
 * �
 * k
 *  pairs from the min-heap.
 */
public class Q373_FindKPairsWithSmallestSums {

    public static List<List<Integer>> kSmallestPairs(int[] list1, int[] list2, int k) {
        List<List<Integer>> pairs = new ArrayList<>();
        // storing the length of lists to use it in a loop later
        int listLength = list1.length;
        // declaring a min-heap to keep track of the smallest sums

        PriorityQueue<Pair> minHeap = new PriorityQueue<>((a, b) -> a.sum - b.sum);
        // iterate over the length of list 1
        for (int i = 0; i < Math.min(k, listLength); i++) {
            // computing sum of pairs all elements of list1 with first index
            // of list2 and placing it in the min-heap
            minHeap.add(new Pair(list1[i] + list2[0], i, 0));
        }

        int counter = 1;
        // iterate over elements of min-heap and only go upto k
        while (!minHeap.isEmpty() && counter <= k) {
            // placing sum of the top element of min-heap
            // and its corresponding pairs in i and j
            Pair pair = minHeap.poll();
            int i = pair.i;
            int j = pair.j;
            // add pairs with the smallest sum in the new list
            pairs.add(Arrays.asList(list1[i], list2[j]));
            // increment the index for 2nd list, as we've
            // compared all possible pairs with the 1st index of list2
            int nextElement = j + 1;
            // if next element is available for list2 then add it to the heap
            if (list2.length > nextElement) {
                minHeap.add(new Pair(list1[i] + list2[nextElement], i, nextElement));
            }
            counter++;
        }
        // return the pairs with the smallest sums
        return pairs;
    }
}
class Pair {
    int sum;
    int i;
    int j;

    public Pair(int sum, int i, int j) {
        this.sum = sum;
        this.i = i;
        this.j = j;
    }
}