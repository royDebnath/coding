package com.roydebnath.coding.theory.datastructure.linkedlist;

public class LinkedListOperations {
  public static void main(String[] args) {
    System.out.println("/=/=/=/= TESTING /=/=/=/=");
    LinkedList linkedList = new LinkedList(10);
    linkedList.addAtHead(11);
    linkedList.addAtHead(12);
    linkedList.addAtHead(13);
    linkedList.addAtTail(8);
    linkedList.addAtTail(7);
    linkedList.addAtIndex(4, 9);
    linkedList.addAtIndex(4, 9);
    linkedList.deleteAtIndex(4);

    linkedList.printList();

    System.out.println("Length of linked list : " + linkedList.length(linkedList.getHead()));
  }
}