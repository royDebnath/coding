package com.java.codinground.blind75;

/**
 * You are given an integer array nums. You are initially positioned at the array's first index,
 * and each element in the array represents your maximum jump length at that position.
 *
 * Return true if you can reach the last index, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 *
 * Input: nums = [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0,
 * which makes it impossible to reach the last index.
 *
 * Algorithm:
 * 		1). We will maintain a variable lastAccuratePosition from which
 * 			we can reach the last position. Since we can reach the last position from
 * 			the last index we initialize lastAccuratePosition with the index of last element (i.e nums.length-1).
 * 		2). Now we will start iterating the input array from right (second last position) to the left.
 * 		3). In each iteration we will calculate furthestJump which is the summation of
 * 			index and the value at that index (i.e nums[i]+i).
 * 		4). We will check if furthestJump is greater than or equal to lastAccuratePosition.
 * 		 	If yes, then we will update the value of lastAccuratePosition with the current index.
 * 		5). After the iteration we will check if lastAccuratePosition is zero return true, else return false.
 *
 */
public class Q16_JumpGame {
    public static void main(String[] args) {
        int[] input1 = {2,3,1,1,4};
        int[] input2 = {3,2,1,0,4};
        System.out.println(canJump(input2));
    }
    public static boolean canJump(int[] nums) {
        int lastAccuratePosition=nums.length-1;
        int furthestJump;
        for(int i=nums.length-2;i>=0;i--) {
            furthestJump=nums[i]+i;
            if(furthestJump>=lastAccuratePosition)
                lastAccuratePosition=i;
        }
        return lastAccuratePosition==0;
    }
}
