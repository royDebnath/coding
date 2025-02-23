package theory.datastructure.linkedlist;


class LinkedList {
  // Class variables for the Linked List
  private Node head;
  private int numNodes;

  public LinkedList(Object dat) {
    head = new Node(dat);
  }

  public void addAtHead(Object dat) {
    Node temp = head;
    head = new Node(dat);
    head.next = temp;
    numNodes++;
  }

  public void addAtTail(Object dat) {
    Node temp = head;
    while (temp.next != null) {
      temp = temp.next;
    }

    temp.next = new Node(dat);
    numNodes++;
  }

  public void addAtIndex(int index, Object dat) {
    Node temp = head;
    Node holder;
    for (int i = 0; i < index - 1 && temp.next != null; i++) {
      temp = temp.next;
    }
    holder = temp.next;
    temp.next = new Node(dat);
    temp.next.next = holder;
    numNodes++;
  }

  public void deleteAtIndex(int index) {
    Node temp = head;
    for (int i = 0; i < index - 1 && temp.next != null; i++) {
      temp = temp.next;
    }
    temp.next = temp.next.next;
    numNodes--;
  }

  public int find(Node n) {
    Node temp = head;
    int index = 0;
    while (temp != n) {
      index++;
      temp = temp.next;
    }
    return index;
  }

  public Node find(int index) {
    Node temp = head;
    for (int i = 0; i < index; i++) {
      temp = temp.next;
    }
    return temp;
  }

  /* Length Of Linked List Iterative */
  public int length() {
    int count = 0;
    Node current = this.head;
    while (current != null) {
      count++;
      current = current.next;
    }
    return count;
  }

  /* Length Of Linked List Recursive */
  public int length(Node head) {
    Node current = head;
    if (current == null) { // base case
      return 0;
    }
    return 1 + length(current.next);
  }

  /*
   * If singly LinkedList contains Cycle then following would be true 1) slow and fast will point to same node i.e. they
   * meet On the other hand if fast will point to null or next node of fast will point to null then LinkedList does not
   * contains cycle.
   */
  public boolean isCyclic(Node headRef) {
    Node fast = headRef;
    Node slow = headRef;
    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next; // if fast and slow pointers are meeting then LinkedList is cyclic
      if (fast == slow) {
           return true;
      }
    }
    return false;
  }
  
  /* Java method to reverse a linked list without recursion */
  public void reverse() {
    Node current = head;
    Node previous = null, next =null;
    while (current != null) {
      next = current.next;  //store the next node for reference
      current.next = previous; //reverse the link
      previous = current; //make the current previous so that next could be current
      current = next; //make the next as current
    }
  }

  public void printList() {
    Node temp = head;
    while (temp != null) {
      System.out.println(temp.data);
      temp = temp.next;
    }
  }

  public int getSize() {
    return numNodes;
  }

  public Node getHead() {
    return head;
  }

  class Node {
    // Declare class variables
    private Node next;
    private Object data;

    public Node(Object dat) {
      data = dat;
    }

    public Object getData() {
      return data;
    }
  }
}