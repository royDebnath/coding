package com.java.codinground.leetcode.categories.linkedlist;

/**
 * 19. Remove Nth Node From End of List
 * Medium
 *
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 * Example 2:
 *
 * Input: head = [1], n = 1
 * Output: []
 * Example 3:
 *
 * Input: head = [1,2], n = 1
 * Output: [1]
 *
 * Solution :
 *
 * We are given a linked list and a number n,
 * and we are required to remove the nth from the end of the list.
 * Since we are removing the nth node, we need to link next pointer of (n - 1)th node to the (n + 1)th node.
 *
 * Once we remove the node, we need to return the head of the modified list.
 *
 * 1. Initialize two pointers slow and fast, pointing to the head of the linked list.
 * 2. Move fast pointer n steps ahead.
 * 3. Now, move both slow and fast one step at a time unless fast reaches to the end. The fast pointer will definitely reach to the end before slow because it is ahead.
 * 4. When we fast pointer reaches to the end, the slow pointer will be n steps behind it i.e., n steps behind the end of the list.
 * 5. At that point, we will remove the node and link it to the next of current node.
 */
public class Q19_RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        head.next=n1;n1.next=n2;n2.next=n3;n3.next=n4;
       // System.out.println("List Length : "+listLength(head));
        pirntLinkedList(head);
        System.out.println("\n"+"After Removing Nth node");
        pirntLinkedList(removeNthFromEnd(head,1));
        //System.out.println("\n"+"List Length : "+listLength(head));
    }


    public static ListNode removeNthFromEnd(ListNode head, int n) {

        if (n == 0) { // return the list as it is. nothing to delete
            return head;
        }
        // Two pointers - fast and slow
        ListNode slow = head;
        ListNode fast = head;
        // Move fast pointer n steps ahead
        for (int i = 0; i < n; i++) {
            if (fast.next == null) {
                if (n > i + 1) { // at this point i+1 is the length of the list, n cannot be greater than that (i+1 because 0 based index)
                    System.out.println("Invalid Input");
                }
                // If n is equal to the number of nodes, head is the nth from last; delete the head node
                if (n == i + 1) {
                    head = head.next;
                }
                return head;
            }
            fast = fast.next;
        }
        // Loop until we reach to the end.
        // Now we will move both fast and slow pointers
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }//at the end of this loop slow pointer will reach the nth node from last. as fast has already travelled n nodes

        slow.next = slow.next.next; // Delink the nth node from last

        return head;
    }

    private static int listLength(ListNode head) {
        ListNode current = head;
        int length=1;
        if (current==null){
            return 0;
        }else {
            while (current.next!=null){
                length++;
                current=current.next;
            }
        }
        return length;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }


    private static void pirntLinkedList(ListNode head) {
        ListNode current = head;
        System.out.print(current.val + "->");
        while (current.next != null) {
            if (current.next.next == null) {
                System.out.print(current.next.val + "->|");
            } else {
                System.out.print(current.next.val + "->");
            }
            current = current.next;
        }
    }
}

