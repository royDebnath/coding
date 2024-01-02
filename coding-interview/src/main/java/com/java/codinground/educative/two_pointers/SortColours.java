package com.java.codinground.educative.two_pointers;

/**
 * Statement
 * Given an array, colors, which contains a combination of the following three elements:
 *
 * 0 (representing red)
 * 1 (representing white)
 * 2 (representing blue)
 *
 * Sort the array in place so that the elements of the same color are adjacent,
 * with the colors in the order of red, white, and blue. The function should return the same array.
 *
 * Solution summary :
 *
 * The solution to this problem can be divided into five main parts:
 *
 * 1. We traverse the array using three pointers, red, white, and blue.
 *
 * 2. If the element pointed to by the white pointer is 0,
 *    we swap it with the element pointed to by the red pointer if it’s not pointing to 0
 *    and increment both the red and white pointers.
 *
 * 3. If the element pointed to by the white pointer is 1, we increment the white pointer.
 *
 * 4. If the element pointed to by the white pointer is 2, we swap it with the element pointed to by the blue pointer
 *    if it’s not pointing to 2
 *    and decrement the blue pointer.
 *
 * 5. The array is sorted when the blue pointer becomes less than the white pointer.
 *
 * Time complexity
 * The time complexity of this solution is  O(n)
 *  since we’re only traversing the array once.
 *
 * Space complexity
 * The space complexity of this solution is O(1)
 *  since no extra space is used.
 *
 *  Here’s how the algorithm works:
 *
 * Condition 1: If colors[white] is  0(red), So we check a further condition:
 *   Condition 1.1: If colors[red] is not 0(not red), we swap the elements of colors[white] and colors[red].
 *                  now colors[red] points to 0()red
 *                  Next, we move both the red and white pointers one position forward.
 *   Condition 1.2: Otherwise, colors[red] will be 0(red), and there is no point in swapping.
 *                  So, we move both the red and white pointers one position forward.
 *
 * Condition 2: Otherwise, if colors[white] is 1(white), the white pointer points to white.
 *              So we increment the white pointer by 1 to analyze the next element.
 *
 * Condition 3: Otherwise, the colors[white] will be 2(blue), i.e., the white pointer points to blue.
 *              So we check two further conditions:
 *  Condition 3.1: If colors[blue] is not 2(blue), we swap the elements of colors[white] and colors[blue].
 *                 Next, we move the blue pointer one position backward.
 *  Condition 3.2: Otherwise, both colors[white] and colors[blue] will be 2, so there is no point in swapping.
 *                 So, we move the blue pointer one position backward.
 *
 * > Note: When we decrement the blue pointer, the white pointer remains unchanged since it has to analyze
 *   the swapped element to determine if further swapping is required with the red pointer.
 *
 * The three steps above are repeated until the blue pointer becomes less than the white pointer, i.e., no elements are left to swap.
 */
class SortColours {
    public static int[] sortColors(int[] colors) {

        int red = 0;
        int white = 0;
        int blue = colors.length - 1;

        while (white <= blue) {
            if (colors[white] == 0) { //white pointer points to red
                if (colors[red] != 0) { // red pointer not pointing to red
                    swap(colors, red, white);
                }
                white++;
                red++;
            }
            else if (colors[white] == 1) { // white is pointing to white
                white++;
            }
            else { // white is pointing to blue
                if (colors[blue] != 2) {//blue is not pointing to blue
                    swap(colors, white, blue);
                }else {
                    blue--; //blue is pointing to blue
                }
            }
        }
        return colors;
    }

    private static void swap(int[] colors, int red, int white) {
        int temp = colors[red];
        colors[red] = colors[white];
        colors[white] = temp;
    }

    // Driver code
    public static void main(String[] args) {
        int[][] inputs = {
            {0, 1, 0},
            {1, 1, 0, 2},
            {2, 1, 1, 0, 0},
            {2, 2, 2, 0, 1, 0},
            {2, 1, 1, 0, 1, 0, 2}
        };

        for (int i = 0; i < inputs.length; i++) {
            System.out.println((i + 1) + ".\tcolors: ");

            int[] sortedColors = sortColors(inputs[i].clone());
            System.out.println("\n\tThe sorted array is: ");
        }
    }
}