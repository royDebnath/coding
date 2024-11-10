package com.roydebnath.coding.leetcode.neetode.linkedlist;


import com.roydebnath.coding.leetcode.support.ListNode;

/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order, and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Example :
 * Input:
 * 2->4->3
 * 5->6->4
 * <p>
 * Output :
 * <p>
 * 7->0->8
 */
public class Q02_AddTwoNumbers {
    public static void main(String[] args) {
        ListNode list1 = new ListNode(2);
        list1.next = new ListNode(4);
        list1.next.next = new ListNode(3);

        ListNode list2 = new ListNode(5);
        list2.next = new ListNode(6);
        list2.next.next = new ListNode(4);

        ListNode result = addTwoNumbers(list1, list2);
        while (result!=null){
            System.out.println(result.val);
            result = result.next;
        }
    }

    private static ListNode addTwoNumbers(ListNode list1, ListNode list2) {
        int number1 = getNumberFromList(list1);
        int number2 = getNumberFromList(list2);
        int resultNumber = number1+number2;
        ListNode result = new ListNode(-1);
        ListNode head = result;
        while (resultNumber!=0){
            int digit = resultNumber % 10; //7
            resultNumber = resultNumber/10; //80
            result.next = new ListNode(digit);
            result=result.next;
        }
        head = head.next;
        return head;
    }

    private static int getNumberFromList(ListNode node) {
        int quotient =0;
        int result =0;
        while (node!=null){
           result+=node.val*Math.pow(10, quotient);
           quotient++;
           node=node.next;
        }
        return result;
    }
}

