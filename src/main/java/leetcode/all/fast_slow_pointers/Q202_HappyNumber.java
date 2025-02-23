package leetcode.all.fast_slow_pointers;

import java.util.HashSet;
import java.util.Set;

/**
 * Statement
 * Write an algorithm to determine if a number n is a happy number.
 *
 * We use the following process to check if a given number is a happy number:
 *
 * Starting with the given number n, replace the number with
 * the sum of the squares of its digits.
 * Repeat the process until: The number equals 1
 * which will depict that the given number  is a happy number.
 * It enters a cycle, which will depict that the given number is not a happy number
 *
 *
 * Solution :
 *
 * We know that a unhappy number eventually gets stuck in an infinite loop.
 * However, there is no way for our program to detect this loop and terminate,
 * unless we keep track of the calculated sums, which requires additional space.
 *
 * If we use the fast and slow pointers approach here,  slow=n, fast = n;
 *
 *       while (fast != 1) {
 *             slow = getNext(slow);
 *             fast =  getNext(getNext(fast));
 *             if (fast==slow & fast!=1){ // cycle = true i.e happyNumber=false
 *                 return false;
 *             }
 *         }
 *         return true;
 *
 *
 * fast pointer is moving one step ahead of slow pointer.
 * if there is no cycle and it is linear, fast.next will be 1 at some point.
 * if there is a cycle the while(fast != 1) will always be true.
 * But, since fast pointer is one step ahead, at some point it will catch the slow pointer.
 * The if condition inside will be true and return false.
 *
 * If no cycle the while loop will be false at a point where fast==1
 *
 * default true will be retured.
 *
 * Following can be the cases:
 *
 * Given n =2 :
 *
 * slow=2, fast=2 -- intialization
 *
 * loop :
 *
 * slow=2->4,    fast=2->4->16
 * slow=4->16,   fast=16->37->58
 * slow=16->37,  fast=58->89->145
 * slow=37->58,  fast=145->42->20
 * slow=58->89,  fast=20->4->16
 * slow=89->145, fast=16->37->58
 * slow=145->42, fast=58->89->145
 * slow=42->20,  fast=145->42->20
 *
 * Break, as there is a loop, return false.
 *
 */

public class Q202_HappyNumber {

    public static int getNext(int number) {
        int totalSum = 0;
        while (number != 0) {
            int digit = number % 10;
            number = number / 10;
            totalSum += (Math.pow(digit, 2));
        }
        return totalSum;
    }
    public static boolean isHappyNumber(int n) {
        int slow=n, fast = n;

        while (fast != 1) {
            slow = getNext(slow);
            fast =  getNext(getNext(fast));
            if (fast==slow & fast!=1){ // cycle = true i.e happyNumber=false
                return false;
            }
        }
        return true;
    }

    /**
     * naive approach, keeping track in a set.
     * Requires extra memory
     */
    public boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<>();

        /**
         * Loop until either n becomes 1 (in which case n is a happy number) or
         * n is seen again (in which case n is not a happy number)
         */
        while (n != 1) {
            n = getNext(n);
            if (!seen.add(n)){ //couldn't be added to the set, because duplicate
                break;
            }
        }
        // If n is 1, it is a happy number; otherwise, it is not
        return n == 1;
    }

    public static void main(String args[]) {
        int a[] = {1, 5, 19, 25, 7};
        for (int i = 0; i < a.length; i++) {
            System.out.println((i + 1) + ".\tInput Number: " + a[i]);
            String output = isHappyNumber(a[i]) ? "True" : "False";

            System.out.println("\n\tIs it a happy number? " + output);
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
