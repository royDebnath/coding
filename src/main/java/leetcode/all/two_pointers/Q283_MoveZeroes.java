package leetcode.all.two_pointers;

/**
 * 283. Move Zeroes
 * Solved
 * Easy
 * <p>
 * Topics
 * Companies
 * <p>
 * Hint
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * <p>
 * Note that you must do this in-place without making a copy of the array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * Example 2:
 * <p>
 * Input: nums = [0]
 * Output: [0]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 104
 * -231 <= nums[i] <= 231 - 1
 * <p>
 * <p>
 * Follow up: Could you minimize the total number of operations done?
 */
public class Q283_MoveZeroes {

    public void moveZeroes(int[] nums) {
        int left = 0, right = 0;
        int n = nums.length;

        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++; // Move the left pointer to the next available position
            }
            right++; // Always move the right pointer
        }
    }

    /**
     *Explanation:
     * Use two pointers (left and right) to move non-zero numbers to the front.
     * right scans the array from start to end.
     * If nums[right] is non-zero, swap it with nums[left] and increment left.
     * All zeroes are naturally pushed to the end.
     *
     *
     * Dry Run:
     * Input:
     *
     * nums = [0, 1, 0, 3, 12]
     *
     * Step	left	right	nums
     * 1	  0	       0	[0, 1, 0, 3, 12] (0 → no swap)
     * 2	  0	       1	[1, 0, 0, 3, 12] (swap 1 & 0)
     * 3	  1	       2	[1, 0, 0, 3, 12] (0 → no swap)
     * 4	  1	       3	[1, 3, 0, 0, 12] (swap 3 & 0)
     * 5	  2	       4	[1, 3, 12, 0, 0] (swap 12 & 0)
     * Output:
     *
     * [1, 3, 12, 0, 0] ✅
     *
     * Example Input:
     * nums = [4, 2, 0, 1, 0, 3]
     *
     * Step-by-Step Execution:
     * Step	left	right	nums	Explanation
     * 1	  0	       0	[4, 2, 0, 1, 0, 3]	nums[right] != 0, swap 4 with itself.
     * 2	  1	       1	[4, 2, 0, 1, 0, 3]	nums[right] != 0, swap 2 with itself.
     * 3	  2	       2	[4, 2, 0, 1, 0, 3]	nums[right] == 0, no swap, just move right.
     * 4	  2	       3	[4, 2, 1, 0, 0, 3]	nums[right] != 0, swap 1 with 0.
     * 5	  3	       4	[4, 2, 1, 0, 0, 3]	nums[right] == 0, no swap, just move right.
     * 6	  3	       5	[4, 2, 1, 3, 0, 0]	nums[right] != 0, swap 3 with 0.
     * Final Output:
     * [4, 2, 1, 3, 0, 0] ✅
     *
     * The key is right pointer keeps moving and left moves only when there is a swap.
     * Thus the right moves from 0 to non zero elements in array but left is stuck at the index with zero
     * which makes sure swapping with left pointer moves the zero to the right
     * */

    public void moveZeroesForLoop(int[] nums) {
        int left = 0; // Pointer for the position to place the next non-zero element

        // Move non-zero elements to the front
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++; // Move the left pointer to the next available position
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        if (i != j) { // Swap only if indices are different
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}

