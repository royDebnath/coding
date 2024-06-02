package com.java.backup.top;

import com.java.codinground.support.LinkedList;

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
        LinkedList list1 = new LinkedList(2);
        list1.next = new LinkedList(4);
        list1.next.next = new LinkedList(3);

        LinkedList list2 = new LinkedList(5);
        list2.next = new LinkedList(6);
        list2.next.next = new LinkedList(4);

        LinkedList result = addTwoNumbers(list1, list2);
        while (result!=null){
            System.out.println(result.data);
            result = result.next;
        }
    }

    private static LinkedList addTwoNumbers(LinkedList list1, LinkedList list2) {
        int number1 = getNumberFromList(list1);
        int number2 = getNumberFromList(list2);
        int resultNumber = number1+number2;
        LinkedList result = new LinkedList(-1);
        LinkedList head = result;
        while (resultNumber!=0){
            int digit = resultNumber % 10; //7
            resultNumber = resultNumber/10; //80
            result.next = new LinkedList(digit);
            result=result.next;
        }
        head = head.next;
        return head;
    }

    private static int getNumberFromList(LinkedList node) {
        int quotient =0;
        int result =0;
        while (node!=null){
           result+=node.data*Math.pow(10, quotient);
           quotient++;
           node=node.next;
        }
        return result;
    }
}

