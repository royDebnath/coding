package com.java.backup.blind75.array;

/**
 * 238. Product of Array Except Self
 * Medium
 *
 * Given an integer array nums, return an array answer such that answer[i]
 * is equal to the product of all the elements of nums except nums[i].
 *
 * The product of any prefix or suffix of nums is
 * guaranteed to fit in a 32-bit integer.
 *
 * You must write an algorithm that runs in O(n) time and without using
 * the division operation.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 * Example 2:
 *
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 *
 * Solution :
 *
 * Lets take S = [2,3,4,5] as an example.
 * The expected answer is P = [60, 40, 30, 24].
 *
 * "DIAGRAM" :
 * "1"<- 2 ->[3,4,5]. product = "1", 3,4,5.
 * [2]<- 3 ->[4,5]. product = 2,4,5.
 * [2,3]<- 4 ->[5]. product = 2,3,5.
 * [2,3,4]<- 5 ->"1". product = 2,3,4, "1".
 *
 * S = the given array
 * Ni = the i-th element of S
 * Pi = product for Ni
 * Li = product of numbers on left of Ni
 * Ri = product of numbers on right of Ni.
 * Thus, Pi = Li * Ri, for each Ni. We also see it the above "diagram".
 *
 * STEPS -
 * (1) First find only Li for each element in S ["1", {1 * 2}, {2 * 3} , {6 * 4}] == [1, 2, 6, 24]
 * (2) Next we find only Ri for each element in S [{20 * 3}, {5 * 4}, {5 * 1}, "1"] == [60, 20, 5, 1]
 * (3) Now, multiply the corresponding elements of Li & Ri to get Pi == [60, 40, 30,24].
 *
 * Indeed, step 3 gives us the answer we expected. Notice that we can combine steps 2,3 like the solution given in this page. We can also test with more inputs besides our example.
 */
public class Q55_ProductOfArrayExceptSelf {

    public static void main(String[] args) {
        System.out.println(productExceptSelf(new int[]{1,2,3,4}));
    }
    public static int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int[] result = new int[length];

        for (int i = 0; i < length; i++) {
            result[i] = 1;
        }

        int productAtIndex = 1;
        for (int i = 0; i < length; i++) {
            result[i] = result[i] * productAtIndex;
            productAtIndex = productAtIndex * nums[i]; // set productAtIndex for next iteration
        }

        productAtIndex = 1;
        for (int i = length - 1; i >= 0; i--) {
            result[i] = result[i] * productAtIndex;
            productAtIndex = productAtIndex * nums[i];
        }
        return result;
    }

    public int[] productExceptSelf2(int[] nums) {
        int length = nums.length;
        int[] result = new int[length];
        result[0] = 1;
        for (int i = 1; i < length; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int i = length - 1; i >= 0; i--) {
            result[i] = result[i]*right;
            right = nums[i]*right;
        }
        return result;
    }

    public int[] productExceptSelf22(int[] nums) {
        int length = nums.length;
        int[] result = new int[length];
        result[0] = 1;
        for (int i =1; i< length;i++ ){
            result[i] = nums[i-1]*result[i-1];
        }
        int right=1;
        for (int i=length-1; i>=0;i--){
            result[i] = result[i]*right;
            right = nums[i]*nums[right];
        }

        return result;
    }
}
