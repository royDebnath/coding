package com.java.codinground.leetcode.categories.fast_slow_pointers;


import com.java.codinground.support.ListNode;

/**
 * Statement
 * Given the head of a linked list, your task is to check whether the linked list is a palindrome or not.
 * Return TRUE if the linked list is a palindrome; otherwise, return FALSE.
 *
 * The fast and slow pointers technique helps determine whether a linked list is a palindrome or not,
 * because it allows us to efficiently traverse the list and find the middle node in a single pass.
 * We can do this in linear time and with constant extra space.
 *
 * To determine whether a linked list is a palindrome, we first find the middle node of the linked list
 * using the fast and slow pointers approach. Then, we will reverse the second half of the linked list,
 * starting from the node after the middle node until the end of the list.
 * Next, we will compare the first half with the second half.
 *
 * If both halves of the list match, the linked list is a palindrome. Otherwise, it is not.
 *
 * Even Odd Calculation :
 *
 * Odd Case :
 *
 *         if(fast != null) {
 *             slow = slow.next;
 *         }
 *
 * The earlier loop to go to the middle was  while(fast != null && fast.next != null)
 * This loop will exit with fast.next==null and fast != null only when
 * the number of elements is odd.
 *
 * 1,2,3,4,5 - the fast pointer will move to 1,3,5 and then
 * the loop exits because 5.next==null, in this case fast.next ==null and fast!=null
 *
 * the movement of slow pointer along with fast pointer is as below :
 *
 * 1,3,5
 * 1,2,3
 *
 * So, the slow pointer is at the exact middle of the list which
 * divides the list in left and right with equal number of elements.
 * But we want to compare the left half and the reversed right half for equality.
 * so moving the slow pointer one more step will make it point to the starting of right half.
 * then we can reverse the list starting with slow and compare with the first half
 * which is starting with head in the original order.
 *
 * had it been even :
 *
 * 1,2,3,4,5,6 - the fast pointer will move to 1,3,5 and then,
 * fast = fast.next.next i.e 6.next, in this case fast==null, because the list ends at 6 and
 * 6.next is null
 *
 * so we can conclude in case of odd list the loop ends with fast !=null and hence
 * we move the slow or mid pointer one more step ahead to point to the middle element,
 * whose left and right side have equal number of elements.
 *
 * Even Case :
 *
 * 1,2,3,4,5,6 - the fast pointer will move to 1,3,5 and then,
 * fast = fast.next.next i.e 6.next, in this case fast==null, because the list ends at 6 and 6.next is null
 *
 * for each move of fast pointer the slow will move as below :
 *
 * 1,3,5,null
 * 1,2,3,4
 *
 * so the slow pointer points to 4. which is exactly the start of the second half of the list.
 *
 * so we don't need to do anything for even case.
 *
 */
public class Q234_PalindromeLinkedList {

    public static boolean isPalindrome(ListNode head) {
        ListNode rightHalf;


        ListNode leftHalf = head;
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        if (fast != null) { // odd list, move the slow pointer one more step to point to right half head
            rightHalf = slow.next;
        } else {
            rightHalf = slow;
        }

        ListNode reverseRight = reverse(rightHalf);
        return compareTwoHalves(leftHalf, reverseRight); // head is the start, slow is the reversed second half
    }

    private static boolean compareTwoHalves(ListNode firstHalf, ListNode secondHalf) {
        while (firstHalf != null && secondHalf != null) {
            if (firstHalf.val != secondHalf.val) {
                return false;
            } else {
                firstHalf = firstHalf.next;
                secondHalf = secondHalf.next;
            }
        }
        return true;
    }

    private static ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}