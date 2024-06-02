package com.java.backup.blind75.linked_list;

import com.java.codinground.leetcode.support.ListNode;

/**
 * 21. Merge Two Sorted Lists
 * Easy
 *
 * You are given the heads of two sorted linked lists list1 and list2.
 *
 * Merge the two lists in a one sorted list.
 * The list should be made by splicing together the nodes of the first two lists.
 *
 * Return the head of the merged linked list.
 */
public class Q08_MergeTwoSortedLists {

    public static void main(String[] args) {
        ListNode list1 = new ListNode(0);
        ListNode l1n1 = new ListNode(2);
        ListNode l1n2 = new ListNode(4);
        ListNode l1n3 = new ListNode(6);
        ListNode l1n4 = new ListNode(8);
        list1.next=l1n1;l1n1.next=l1n2;l1n2.next=l1n3;l1n3.next=l1n4;

        ListNode list2 = new ListNode(1);
        ListNode l2n1 = new ListNode(3);
        ListNode l2n2 = new ListNode(5);
        ListNode l2n3 = new ListNode(7);
        ListNode l2n4 = new ListNode(9);
        list2.next=l2n1;l2n1.next=l2n2;l2n2.next=l2n3;l2n3.next=l2n4;


        printList(mergeTwoLists(list1, list2));
    }

    private static void printList(ListNode head) {
        ListNode current = head;
        System.out.print(current.val+"->");
        while (current.next!=null){
            if (current.next.next==null){
                System.out.print(current.next.val+"->|");
            }
            else {
                System.out.print(current.next.val+"->");
            }
            current=current.next;
        }
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        //Define a new Node
        ListNode answer = new ListNode(-1);
        ListNode newNode = answer;

        //If list1 has no element then add list2 to new node
        if (list1 == null)
            return newNode.next = list2;

        //If list2 has no element then add list1 to new node
        if (list2 == null)
            return newNode.next = list1;


        //Use Merge sort logic to merge nodes value
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                newNode.next = list1;
                list1 = list1.next;
            } else {
                newNode.next = list2;
                list2 = list2.next;
            }
            newNode = newNode.next;
        }

        //If any node left in list1
        if (list1 != null)
            newNode.next = list1;

        //If any node left in list2
        if (list2 != null)
            newNode.next = list2;

        return answer.next;

    }
}
