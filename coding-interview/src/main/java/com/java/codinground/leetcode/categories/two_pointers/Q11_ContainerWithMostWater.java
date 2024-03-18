package com.java.codinground.leetcode.categories.two_pointers;

/**
 * 11. Container With Most Water
 * Medium
 *
 * You are given an integer array height of length n.
 * There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 *
 * Find two lines that together with the x-axis form a container,
 * such that the container contains the most water.
 *
 * Return the maximum amount of water a container can store.
 *
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 *
 * Two vertical lines here would be at height[1]=8 and height[8]=7. 7*7=49
 *
 * Solution :
 * =================
 *
 * As, height is already given in our Array!
 * But what about width?
 *
 * So, to find width of a container, all we have to do is get the difference of line.
 *
 * > max area which is intially 0
 * > Then, we going to have 2 pointers. One in left start at 0th index & one right start from last index.
 *
 * 8				|	                                    |
 * 7				|	                                    |	            |                             width = 8 - 0 = 8
 * 6				|	    |	                            |	            |                             height = min(1, 7)
 * 5				|	    |	            |	            |	            |                             Area = 8 * 1 = 8
 * 4				|	    |	            |	    |	    |	            |
 * 3				|	    |	            |	    |	    |	    |	    |                             max = 0 -> max = 8
 * 2				|	    |	    |	    |	    |	    |	    |	    |
 * 1	   |	    |	    |	    |	    |	    |	    |	    |	    |
 *        0        1       2       3       4       5       6       7       8
 * 	      ^                                                                ^
 * 	     left                                                            right
 *
 * By this pretty much we have get one formula all it is area = width * height
 * i.e. area = (right - left) * min(height[left], height[right])
 *
 * So, now you ask which pointer we suppose to move. It's preety simple. We gonna move the smaller height pointer. Why?
 * Because, we are trying to find very max. container
 *
 * If we have smaller height on left or right we don't care about it. We always want a higher height line on our left & right.
 *
 * Okay, so now moving forward. left pointer has smaller height than right, so it will move forward
 *
 * 8				|	                                    |
 * 7				|	                                    |	            |                             width = 8 - 1 = 7
 * 6				|	    |	                            |	            |                             height = min(8, 7)
 * 5				|	    |	            |	            |	            |                             Area = 7 * 7 = 49
 * 4				|	    |	            |	    |	    |	            |
 * 3				|	    |	            |	    |	    |	    |	    |                             max = 8 -> max = 49
 * 2				|	    |	    |	    |	    |	    |	    |	    |
 * 1	   |	    |	    |	    |	    |	    |	    |	    |	    |
 *        0        1       2       3       4       5       6       7       8
 * 	               ^                                                       ^
 * 	             left                                                   right
 *
 * Okay, so now moving forward. right pointer has smaller height than left, so it will move backward
 *
 * 8				|	                                    |
 * 7				|	                                    |	            |                             width = 7 - 1 = 6
 * 6				|	    |	                            |	            |                             height = min(8, 3)
 * 5				|	    |	            |	            |	            |                             Area = 6 * 3 = 18
 * 4				|	    |	            |	    |	    |	            |
 * 3				|	    |	            |	    |	    |	    |	    |                             max = 49
 * 2				|	    |	    |	    |	    |	    |	    |	    |
 * 1	   |	    |	    |	    |	    |	    |	    |	    |	    |
 *        0        1       2       3       4       5       6       7       8
 * 	               ^                                               ^
 * 	             left                                           right
 *
 * 	Okay, so now moving forward. right pointer has smaller height, so it will move backward
 *
 * 	8				|	                                    |
 * 7				|	                                    |	            |                             width = 6 - 1 = 5
 * 6				|	    |	                            |	            |                             height = min(8, 8)
 * 5				|	    |	            |	            |	            |                             Area = 8 * 5 = 40
 * 4				|	    |	            |	    |	    |	            |
 * 3				|	    |	            |	    |	    |	    |	    |                             max = 49
 * 2				|	    |	    |	    |	    |	    |	    |	    |
 * 1	   |	    |	    |	    |	    |	    |	    |	    |	    |
 *        0        1       2       3       4       5       6       7       8
 * 	               ^                                       ^
 * 	              left                                   right
 *
 * Okay, so now moving forward. now left & right pointer both have same height,
 * so in this case we gonna move both the pointer's!!
 *
 * 8				|	                                    |
 * 7				|	                                    |	            |                             width = 5 - 2 = 3
 * 6				|	    |	                            |	            |                             height = min(4, 6)
 * 5				|	    |	            |	            |	            |                             Area = 4 * 3 = 12
 * 4				|	    |	            |	    |	    |	            |
 * 3				|	    |	            |	    |	    |	    |	    |                             max = 49
 * 2				|	    |	    |	    |	    |	    |	    |	    |
 * 1	   |	    |	    |	    |	    |	    |	    |	    |	    |
 *        0        1       2       3       4       5       6       7       8
 * 	                       ^                       ^
 * 	                      left                   right
 *
 * Okay, so now moving forward. right pointer has smaller height, so it will move backward
 *
 * 8				|	                                    |
 * 7				|	                                    |	            |                             width = 4 - 2 = 2
 * 6				|	    |	                            |	            |                             height = min(5, 6)
 * 5				|	    |	            |	            |	            |                             Area = 5 * 2 = 10
 * 4				|	    |	            |	    |	    |	            |
 * 3				|	    |	            |	    |	    |	    |	    |                             max = 49
 * 2				|	    |	    |	    |	    |	    |	    |	    |
 * 1	   |	    |	    |	    |	    |	    |	    |	    |	    |
 *        0        1       2       3       4       5       6       7       8
 * 	                       ^               ^
 * 	                      left           right
 *
 *Okay, so now moving forward. right pointer has smaller height, so it will move backward
 *
 * 8				|	                                    |
 * 7				|	                                    |	            |                             width = 3 - 2 = 1
 * 6				|	    |	                            |	            |                             height = min(2, 6)
 * 5				|	    |	            |	            |	            |                             Area = 2 * 1 = 2
 * 4				|	    |	            |	    |	    |	            |
 * 3				|	    |	            |	    |	    |	    |	    |                             max = 49
 * 2				|	    |	    |	    |	    |	    |	    |	    |
 * 1	   |	    |	    |	    |	    |	    |	    |	    |	    |
 *        0        1       2       3       4       5       6       7       8
 * 	                       ^       ^
 * 	                      left   right
 *The max area we get is 49
 *
 *
 *
 *
 */

public class Q11_ContainerWithMostWater {

    public static void main(String[] args) {
        int[] input = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(input));
    }
    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
            int w = right - left;
            int h = Math.min(height[left], height[right]);
            int area = h * w;
            maxArea = Math.max(maxArea, area);
            if (height[left] < height[right]) {
                left++;
            } else if (height[left] > height[right]) {
                right--;
            } else {
                left++;
                right--;
            }
        }
        return maxArea;
    }
}
