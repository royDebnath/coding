package com.java.codinground.programs.algoexpert.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * I am given the head of a singly linked list.
 * The nodes of the linked list are going to be sorted in ascending order with respect to their values
 * and the values are going to be integers.
 * I am asked to write a function that is going to remove all the nodes with duplicate values
 * and return the modified linked list.
 * The linked list should be modified in place
 * and the nodes should remain in their original order.
 */
public class RemoveDuplicatesLinkedList {

    public static class LinkedList {
        public int value;
        public LinkedList next;

        public LinkedList(int value) {
            this.value = value;
            this.next = null;
        }
    }

    public static LinkedList addMany(LinkedList ll, List<Integer> values) {
        LinkedList current = ll;
        while (current.next != null) {
            current = current.next;
        }
        for (int value : values) {
            current.next = new LinkedList(value);
            current = current.next;
        }
        return ll;
    }

    public static void main(String[] args) {
        LinkedList input = new LinkedList(1);
        addMany(input, new ArrayList<Integer>(Arrays.asList(1, 3, 4, 4, 4, 5, 6, 6)));

        LinkedList uniqueList = removeDuplicatesFromLinkedList(input);

        printList(uniqueList);

    }

    private static LinkedList removeDuplicatesFromLinkedList(LinkedList linkedList) {
        LinkedList current = linkedList;
        while (current!=null && current.next!=null){
            if (current.value==current.next.value){
                current.next=current.next.next;
            }
            else {
                current = current.next;
            }
        }
        return linkedList;
    }

    private static void printList(LinkedList uniqueList) {
        LinkedList current = uniqueList;
        while (current!=null){
            if (current.next!=null) {
                System.out.print(current.value + "->");
            }
            if (current.next==null){
                System.out.println(current.value + "->|");
            }
            current=current.next;
        }
    }

}
