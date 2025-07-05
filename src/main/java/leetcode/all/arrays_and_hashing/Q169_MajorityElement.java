package leetcode.all.arrays_and_hashing;

import java.util.HashMap;
import java.util.Map;

/**
 * 169. Majority Element
 * Easy
 *
 * Topics
 * Companies
 * Given an array nums of size n, return the majority element.
 *
 * The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,2,3]
 * Output: 3
 * Example 2:
 *
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 *
 * Follow-up: Could you solve the problem in linear time and in O(1) space?
 */
public class Q169_MajorityElement {

    public static int majorityElement(int[] nums) {
        int candidate = 0, count = 0;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }

    /**
     *
     * Input:
     * nums = [2,2,1,1,1,2,2]
     *
     * Initialization:
     * candidate = 0 (not set yet)
     * count = 0
     * Step-by-step Execution:
     * Index	Num	Candidate	Count	Explanation
     * 0	    2	    2	      1	        count == 0, so we set candidate = 2 and increase count.
     * 1	    2	    2	      2	        num == candidate, so we increase count.
     * 2	    1	    2	      1	        num != candidate, so we decrease count.
     * 3	    1	    2	      0	        num != candidate, so we decrease count to 0. Candidate is reset next step.
     * 4	    1	    1	      1	        count == 0, so we set candidate = 1 and increase count.
     * 5	    2	    1	      0	        num != candidate, so we decrease count to 0. Candidate is reset next step.
     * 6	    2	    2	      1	        count == 0, so we set candidate = 2 and increase count.
     * Final Result:
     * The majority element is 2, which appears more than ⌊n/2⌋ times.
     *
     * Key Observations:
     * Even when the candidate is changed mid-way, the true majority element always regains dominance by the end.
     * The algorithm ensures that the final candidate is the element that appears most frequently in the array.
     *
     * Why does this work?
     * If there is a majority element, it will always remain as the final candidate,
     * because its occurrences outweigh all other elements combined.
     *
     * Core Idea of the Algorithm
     *
     * The majority element appears more than ⌊n/2⌋ times, meaning it outnumbers all other elements combined.
     * By maintaining a single candidate and a count, we "cancel out" non-majority elements while preserving the majority one.
     *
     * Why It Fails Without a Majority Element
     *
     * If no element appears more than n/2 times, no single element can stay dominant throughout the process.
     *
     * Example: nums = [1, 2, 3, 4, 5, 6, 7]
     * Every element cancels out, leaving the last element (7) as the "candidate."
     * However, 7 does not appear more than n/2 = 3.5 times, so it is not a majority element.
     * This is why we need an extra verification step to count occurrences of the final candidate.
     *
     * Verification Step
     *
     * Recount how many times the final candidate appears in the array.
     * If it appears more than n/2 times, return it.
     * Otherwise, return -1 (or any indicator of "no majority element").
     *
     */


    public static int majorityElementHashing(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        for (Integer num : freq.keySet()) {
            if (freq.get(num) > nums.length / 2) {
                return num;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {2,2,1,1,1,2,2};
        System.out.println(majorityElement(nums));
    }
}
