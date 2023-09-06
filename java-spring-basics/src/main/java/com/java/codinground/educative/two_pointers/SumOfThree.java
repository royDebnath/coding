package com.java.codinground.educative.two_pointers;

import java.util.Arrays;

/**
 * Statement
 * Given an array of integers, nums, and an integer value, target,
 * determine if there are any three integers in nums whose sum is equal to the target,
 * that is, nums[i] + nums[j] + nums[k] == target. Return TRUE if three such integers exist
 * in the array. Otherwise, return FALSE.
 *
 * Solution summary
 * First, sort the array in ascending order. To find a triplet whose sum is equal to the target value,
 * loop through the entire array. In each iteration:
 *
 * 1. Store the current array element and set up two pointers (low and high) to
 *    find the other two elements that complete the required triplet.
 *		- The low pointer is set to the current loop’s index + 1.
 *  	- The high is set to the last index of the array.
 *
 * 2. Calculate the sum of array elements pointed to by the current loop’s index and
 *    the low and high pointers.
 *
 * 3. If the sum is equal to target, return TRUE.
 *
 * 4. If the sum is less than target, move the low pointer forward.
 *
 * 5. If the sum is greater than target, move the high pointer backward.
 */
class SumOfThree {
	public static boolean findSumOfThree(int nums[], int target) {
		Arrays.sort(nums);
		int low, high, triples;

		for (int i = 0; i < nums.length - 2; i++) {
			low = i + 1;
			high = nums.length - 1;

			while (low < high) {
				triples = nums[i] + nums[low] + nums[high];

				if (triples == target) {
					return true;
				}
				else if (triples < target) {
					low++;
				} 
				else {
					high--;
				}
			}
		}

		return false;
	}

	// Driver code
	public static void main(String[] args) {
		int[][] numsList = {{3, 7, 1, 2, 8, 4, 5},
                       {-1, 2, 1, -4, 5, -3},
                       {2, 3, 4, 1, 7, 9},
                       {1, -1, 0},
                       {2, 4, 2, 7, 6, 3, 1}};
					   
		int[] testList = {10, 7, 20, -1, 8};

		
		for (int i=0; i<testList.length; i++) {
			System.out.print(i+1);
			System.out.println(".\tInput array: " + Arrays.toString(numsList[i]));
			
			if (findSumOfThree(numsList[i], testList[i])) {
			System.out.println("\tSum for " + testList[i] + " exists ");
			} else {
			System.out.println("\tSum for " + testList[i] + " does not exist ");
			}
		}
	}
}