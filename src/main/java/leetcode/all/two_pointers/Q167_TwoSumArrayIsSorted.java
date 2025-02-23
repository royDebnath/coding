package leetcode.all.two_pointers;

/**
 * 167. Two Sum II - Input Array Is Sorted
 * Medium
 * 10.4K
 * 1.3K
 * Companies
 * Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order, find two numbers such that they add up to a specific target number. Let these two numbers be numbers[index1] and numbers[index2] where 1 <= index1 < index2 < numbers.length.
 *
 * Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2.
 *
 * The tests are generated such that there is exactly one solution. You may not use the same element twice.
 *
 * Your solution must use only constant extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: numbers = [2,7,11,15], target = 9
 * Output: [1,2]
 * Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].
 * Example 2:
 *
 * Input: numbers = [2,3,4], target = 6
 * Output: [1,3]
 * Explanation: The sum of 2 and 4 is 6. Therefore index1 = 1, index2 = 3. We return [1, 3].
 * Example 3:
 *
 * Input: numbers = [-1,0], target = -1
 * Output: [1,2]
 * Explanation: The sum of -1 and 0 is -1. Therefore, index1 = 1, index2 = 2. We return [1, 2].
 *
 * Approach
 *
 * If you know Two Sum question that is the first question of LeetCode, you can solve this question with HashMap. But HashMap solution should O(n)O(n)O(n) space. The description says "Your solution must use only constant extra space", so you need to change your strategy.
 *
 * Let me explain my strategy with this example.
 *
 * Input: numbers = [2,7,11,15], target = 18
 * My strategy is to use two pointers. Let's say left and right.
 *
 * [2,7,11,15]
 *  L      R
 * left pointer starts from index 0 and right pointer starts from the last index.
 *
 * Every time we calculate addition with left number + right number.
 *
 * [2,7,11,15]
 *  L      R
 *
 * 2 + 15 = 17
 * target = 18
 * Now current total (= 17) is smaller than the target, so current combination is not answer.
 *
 * After that, we have to move left or right to find the target number.
 *
 * But how can we decide to move the one of them?
 *
 * It's simple. Current total is smaller than the target, that's why we should move left pointer to the next, because input array is sorted, so right number is definitely greater than left number. If we move left pointer to the next, we will get bigger total next time.
 *
 * [2,7,11,15]
 *    L    R
 *
 * 7 + 15 = 22
 * target = 18
 * We got bigger total this time. In that case, we need to move right pointer to the next, because of the same reason above. Input array is sorted, so if we move right pointer to the next, we will get smaller total next time.
 *
 * [2,7,11,15]
 *    L R
 *
 * 7 + 11 = 18
 * target = 18
 * Now, current total is equal to the target. The description says "Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2".
 *
 * return [2, 3]
 *
 *
 */
public class Q167_TwoSumArrayIsSorted {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int total = numbers[left] + numbers[right];

            if (total == target) {
                return new int[]{left + 1, right + 1};
            } else if (total > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[]{-1, -1}; // If no solution is found
    }
}
