package com.roydebnath.coding.leetcode.neetode.linkedlist;

import com.roydebnath.coding.leetcode.support.ListNode;

/**
 * 92. Reverse Linked List II
 * Medium
 * 9.8K
 * 446
 * Companies
 * Given the head of a singly linked list and two integers left and right where left <= right,
 * reverse the nodes of the list from position left to position right, and return the reversed list.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 * Example 2:
 *
 * Input: head = [5], left = 1, right = 1
 * Output: [5]
 *
 * Solution :
 *
 * To understand this problem let's take an example:
 *
 * Input: head = [1,2,10,20,30,40,5], left = 3, right = 6
 * Output: [1,2,40,30,20,10,5]
 * So, first of all I would like to show you if we do reverse one-by-one then, how it'll gonna looks like
 *
 * Initial : 1->2->10->20->30->40->5
 * Step 1  : 1->2->20->10->30->40->5
 * Step 2  : 1->2->30->20->10->40->5
 * Step 3  : 1->2->40->30->20->10->5
 *
 * Now, let's talk about how we gonna achieve this result,
 *
 * So, for that we gonna use 3 pointers:
 *
 * Pre
 * Curr
 * Forw
 *
 * So, the pre pointer will be assigned on just before left position
 * Curr pointer & forw pointer will help in reversing the linkedlist
 *
 * So, we gonna perform these steps,
 *
 *             forw = curr.next; // forw pointer will be after curr
 *             curr.next = forw.next;
 *             forw.next = prev.next;
 *             prev.next = forw;
 *
 *
 */
public class Q92_ReverseLinkedListII {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(10);
        ListNode fourth = new ListNode(20);
        ListNode fifth = new ListNode(30);
        ListNode sixth = new ListNode(40);
        ListNode seventh = new ListNode(5);
        head.next=second;second.next=third;third.next=fourth;fourth.next=fifth;fifth.next=sixth;sixth.next=seventh;
        printLinkedList(head);
        reverseBetween(head, 3, 6);
        printLinkedList(head);


    }
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        //if left = 1 then our prev will be at dummy node
        ListNode dummy = new ListNode(0); // created dummy node
        dummy.next = head;
        ListNode prev = dummy; // intialising prev pointer on dummy node

        for(int i = 0; i < left - 1; i++)
            prev = prev.next; // adjusting the prev pointer on it's actual index

        ListNode curr = prev.next; // curr pointer will be just after prev
        // reversing
        for(int i = 0; i < right - left; i++){
            ListNode forw = curr.next; // forw pointer will be after curr
            curr.next = forw.next;
            forw.next = prev.next;
            prev.next = forw;
        }
        return dummy.next;
    }

    private static void printLinkedList(ListNode head) {
        ListNode current = head;
        System.out.print(current.val + "->");
        while (current.next != null) {
            if (current.next.next == null) {
                System.out.print(current.next.val + "->|\n");
            } else {
                System.out.print(current.next.val + "->");
            }
            current = current.next;
        }
    }
}
