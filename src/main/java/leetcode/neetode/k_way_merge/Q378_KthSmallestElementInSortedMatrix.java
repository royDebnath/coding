package leetcode.neetode.k_way_merge;

import java.util.PriorityQueue;

/**
 * 378. Kth Smallest Element in a Sorted Matrix
 * Medium
 * 9.5K
 * 326
 * Companies
 * Given an n x n matrix where each of the rows and columns is sorted in ascending order, return the kth smallest element in the matrix.
 *
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 *
 * You must find a solution with a memory complexity better than O(n2).
 *
 *
 *
 * Example 1:
 *
 * Input: matrix = [[1,5,9],
 *                  [10,11,13],
 *                  [12,13,15]],
 *
 *                  k = 8
 * Output: 13
 * Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13
 * Example 2:
 *
 * Input: matrix = [[-5]], k = 1
 * Output: -5
 *
 *
 * Solution
 * A key observation when tackling this problem is that the matrix is sorted along rows and columns. This means that whether we look at the matrix as a collection of rows or as a collection of columns, we see a collection of sorted lists.
 *
 * In a k-way merge pattern, we have k-sorted arrays and imagine them as a single, merged sorted array. We need to find the
 * �
 * �
 * ℎ
 * k
 * th
 *
 *  smallest element, which is the
 * �
 * �
 * ℎ
 * k
 * th
 *
 *  element in the merged and sorted array of all the elements in the matrix.
 *
 *  Here’s how we implement our algorithm using a min-heap to find the
 * �
 * �
 * ℎ
 * k
 * th
 *
 *  smallest element in a sorted matrix:
 *
 * We push the first element of each row of the matrix in the min-heap, storing each element along with its row and column index.
 *
 * Remove the top (root) of the min-heap.
 *
 * If the popped element has the next element in its row, push the next element in the heap.
 *
 * Repeat steps 2 and 3 as long as there are elements in the min-heap, and stop as soon as we’ve popped
 * �
 * k
 *  elements from it.
 *
 * The last popped element in this process is the
 * �
 * �
 * ℎ
 * k
 * th
 *
 *  smallest element in the matrix.
 *
 *
 *
 *
 */
public class Q378_KthSmallestElementInSortedMatrix {

    public static int kthSmallest(int[][] matrix, int k) {
        // storing the number of rows in the matrix to use it in later
        int rowCount = matrix.length;
        // declaring a min-heap to keep track of smallest elements
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        for (int i = 0; i < rowCount; i++) {
            // pushing the first element of each row in the min-heap
            // The offer() method pushes an element into an existing heap
            // in such a way that the heap property is maintained.
            minHeap.offer(new int[]{matrix[i][0], i, 0});
        }

        int numbersChecked = 0;
        int smallestElement = 0;
        // iterating over the elements pushed in our minHeap
        while (!minHeap.isEmpty()) {
            // get the smallest number from top of heap and its corresponding row and column
            int[] curr = minHeap.poll();
            smallestElement = curr[0];
            int rowIndex = curr[1];
            int colIndex = curr[2];
            numbersChecked++;
            // when numbersChecked equals k, we'll return smallestElement
            if (numbersChecked == k) {
                break;
            }
            // if the current element has a next element in its row,
            // add the next element of that row to the minHeap
            if (colIndex + 1 < matrix[rowIndex].length) {
                minHeap.offer(new int[]{matrix[rowIndex][colIndex + 1], rowIndex, colIndex + 1});
            }
        }
        // return the Kth smallest number found in the matrix
        return smallestElement;
    }

    /**
     * Time complexity
     * The time complexity of the first step is:
     *
     * �
     * (
     * �
     * )
     * O(n)
     *  for iterating the first element of each row, where
     * �
     * n
     *  is the number of rows of the matrix.
     *
     * The cost of pushing
     * �
     * n
     *  elements in the heap is as follows:
     *
     * log
     * ⁡
     * 1
     * +
     * log
     * ⁡
     * 2
     * +
     * log
     * ⁡
     * 3
     * +
     * …
     * +
     * log
     * ⁡
     * �
     * =
     * log
     * ⁡
     * (
     * 1
     * ∗
     * 2
     * ∗
     * 3
     * ∗
     * .
     * .
     * .
     * ∗
     * �
     * )
     * =
     * log
     * ⁡
     * (
     * �
     * !
     * )
     * log1+log2+log3+…+logn=log(1∗2∗3∗...∗n)=log(n!)
     *
     * As per Stirling’s approximation,
     * �
     * (
     * log
     * ⁡
     * �
     * !
     * )
     * ≈
     * �
     * (
     * �
     * log
     * ⁡
     * �
     * )
     * O(logn!)≈O(nlogn)
     * .
     *
     * Therefore, the time complexity of the first loop is
     * �
     * (
     * �
     * log
     * ⁡
     * �
     * )
     * O(nlogn)
     * .
     *
     * In the while-loop, we pop and push
     * �
     * k
     *  elements in the heap until we find the
     * �
     * �
     * ℎ
     * k
     * th
     *
     *  smallest element. Therefore, the time complexity of this step is
     * �
     * (
     * �
     * log
     * ⁡
     * �
     * )
     * O(klogn)
     * .
     * Overall, the total time complexity of this solution is
     * �
     * (
     * �
     * log
     * ⁡
     * �
     * +
     * (
     * �
     * log
     * ⁡
     * �
     * )
     * )
     * =
     * �
     * (
     * (
     * �
     * +
     * �
     * )
     * log
     * ⁡
     * �
     * )
     * O(nlogn+(klogn))=O((n+k)logn)
     *
     * Space complexity
     * The space complexity is
     * �
     * (
     * �
     * )
     * O(n)
     * , where
     * �
     * n
     *  is the total number of elements in the min-heap.
     */

        public int kthSmallestBsearch(int[][] matrix, int k) {
            int n= matrix.length;
            int low = matrix[0][0];
            int high = matrix[n-1][n-1];

            while(low < high){
                int mid = low + (high - low)/2;
                int count = lessEqual(matrix,mid);
                if(count < k){
                    low = mid+1;
                }
                else{
                    high = mid;
                }
            }
            return low;

        }

        //from left bottom or right top we can count how many numbers are equal or less than our target

        public int lessEqual(int[][] matrix, int target){
            int count = 0 , len = matrix.length, i = len-1, j=0;

            while(i >=0 && j<len){
                if(matrix[i][j] > target){
                    i--;
                }
                else
                {
                    count = count + i +1;
                    j++;
                }
            }
            return count;
        }
}
