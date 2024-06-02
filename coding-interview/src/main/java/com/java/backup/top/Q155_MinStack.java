package com.java.backup.top;

public class Q155_MinStack {
    private Node top;

    public void push(int x) {
        if (top == null) {
            top = new Node(x, x, null);
        }
        else {
            Node previousTop = top;
            top = new Node(x, Math.min(x, top.min), previousTop);
        }
    }

    public void pop() {
        top = top.next;
    }

    public int top() {
        return top.val;
    }

    public int getMin() {
        return top.min;
    }

    private class Node {
        int val;
        int min;
        Node next;

        private Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }
}
