package leetcode.all.linkedlist;

/**
 * 138. Copy List with Random Pointer
 * Medium
 * <p>
 * Topics
 * Companies
 * <p>
 * Hint
 * A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.
 * <p>
 * Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.
 * <p>
 * For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.
 * <p>
 * Return the head of the copied linked list.
 * <p>
 * The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:
 * <p>
 * val: an integer representing Node.val
 * random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
 * Your code will only be given the head of the original linked list.
 *
 * The problem provides a special type of linked list where each node has two pointers: a next pointer to the next node in the sequence and a random pointer that can point to any node in the list or be null. The task is to create a deep copy of this linked list. A deep copy means that you need to create a new list where each node in the new list corresponds to a node in the original list, but no pointers in the new list should reference nodes from the original list. The newly created list should maintain the original structure, including the next and random linkages. The input is the head of the original linked list, and the output should be the head of the newly copied linked list.
 *
 * Intuition
 *
 * To solve this problem, we need to create a copy of each node and also map the random pointers from the original list to the corresponding nodes in the copied list. This can become complex because the random pointer can point to any node, creating a non-linear structure.
 *
 * The solution follows these steps:
 *
 * Iterate through the original list and create a copy of each node, insert the copied node directly after its original node. This links the original and the copied nodes in a single mixed list.
 *
 * Go through the mixed list again and set the random pointers for the copied nodes. Since we've interleaved the copied nodes with their corresponding originals, each original node's next is now its copy. This makes it easy to find and set the copied node's random pointer: if originalNode.random exists, then copiedNode.random will be originalNode.random.next.
 *
 * Finally, we need to restore the original list and separate the copied list from it. We iterate through the mixed list, re-establish the original next links for the original nodes, and create the next links for the copied nodes.
 *
 * Following this approach ensures that we create a deep copy of the list without needing extra space for a map to track the random pointer relationships which is a common approach for such problems.
 *
 * Solution Approach
 *
 * The solution for creating a deep copy of the linked list with a random pointer is implemented in three major steps:
 *
 * Step 1: Interleaving the Original and Copied Nodes
 *
 * We start by iterating through the original list. For each node in the list, we perform the following actions:
 *
 * Create a new node with the same value as the current node (cur.val).
 * Insert this new node right after the current one by setting the next pointer of the new node to point to the node that cur.next was originally pointing to.
 * Update cur.next to point to the newly created node.
 * Move forward in the list by setting cur to the next original node (cur = cur.next.next).
 * This step essentially weaves the original and new nodes together.
 *
 * Step 2: Setting the random Pointers for Copied Nodes
 *
 * In the second pass over this interleaved list, we set the random pointer for each copied node. If the random pointer of the original node is not null, we can find the corresponding random pointer for the copied node as follows:
 *
 * Given an original node cur, its copied node is cur.next.
 * The random node of cur is cur.random.
 * Therefore, the corresponding copied random node would be cur.random.next.
 * We set this with cur.next.random = cur.random.next.
 *
 * Step 3: Separating the Copied List from the Original List
 *
 * Finally, we restore the original list to its initial state and extract the deep copy. We iterate over the interleaved list and do the following actions:
 *
 * Set nxt to cur.next, which is the copied node following the original cur node.
 * Re-assign cur.next to nxt.next, which is the next original node in the list (or null if we are at the end).
 * Then, we update cur to point to the copied node's next node, which should also be a copied node (or null), using cur = nxt.
 * Continue this process until all nodes have been visited.
 * By the end of step 3, we will have the original list restored and a separately linked new list, which is a deep copy of the original list.
 *
 * This approach is efficient as it does not require extra space for maintaining a hash table to map the original to copied nodes, and it completes the copying in linear time.
 *
 * Example Walkthrough
 *
 * Let's illustrate the solution approach with a small example. Suppose we have the following linked list where the next pointers form a simple sequence and the random pointers point to various nodes, including null.
 *
 * 1Original List:
 * 21 -> 2 -> 3 -> null
 * 3|    |    |
 * 4v    v    v
 * 52   null  1
 * Node 1 has a random pointer to Node 2, Node 2's random pointer is null, and Node 3's random pointer points back to Node 1.
 *
 * Step 1: Interleaving the Original and Copied Nodes
 *
 * We create a copy of each node and interleave it with the original list.
 *
 * 1After interleaving:
 * 21 -> 1' -> 2 -> 2' -> 3 -> 3' -> null
 * 3|           |           |
 * 4v           v           v
 * 52          null         1
 * Here, 1', 2', and 3' are the copied nodes of 1, 2, and 3 respectively. Each original node's next pointer now points to its newly created copy.
 *
 * Step 2: Setting the random Pointers for Copied Nodes
 *
 * Now, we set up the random pointers for the copied nodes.
 *
 * 1Interleaved list with `random` pointers set:
 * 21 -> 1' -> 2 -> 2' -> 3 -> 3' -> null
 * 3|     |     |     |     |     |
 * 4v     v     v     v     v     v
 * 52    2'   null   null   1    1'
 * Copied node 1''s random pointer goes to 2', 2''s random pointer remains null, and copied node 3''s random pointer goes to 1'.
 *
 * Step 3: Separating the Copied List from the Original List
 *
 * Finally, we untangle the two lists into the original list and its deep copy.
 *
 * 1Restored original list:
 * 21 -> 2 -> 3 -> null
 * 3|    |    |
 * 4v    v    v
 * 52   null  1
 * 6
 * 7Separated copied list (deep copy):
 * 81' -> 2' -> 3' -> null
 * 9|      |      |
 * 10v      v      v
 * 112'    null    1'
 * We're left with two separate lists: the original list is unchanged, and we have a new deep copy list which maintains the original structure, including the random links.
 *
 * This process creates a deep copy of a complex linked list with random pointers in O(n) time and O(1) additional space.
 *
 *
 */
public class Q138_CopyListWithRandomPointer {
    public Node copyRandomList(Node head) {
        // Return null if the original list is empty
        if (head == null) {
            return null;
        }

        // 1. Create a cloned node for each node in the original list and insert it
        // right next to the original node.
        for (Node current = head; current != null; ) {
            Node clone = new Node(current.val); // Clone node
            clone.next = current.next; // Set clone's next to current node's next
            current.next = clone; // Insert cloned node after the current node
            current = clone.next; // Move to the next original node
        }

        // 2. Assign random pointers for the cloned nodes.
        for (Node current = head; current != null; current = current.next.next) {
            if (current.random != null) {
                // Set the cloned node's random to the cloned node of the original node's random
                current.next.random = current.random.next;
            }
        }

        // 3. Restore the original list and extract the cloned list.
        Node copyHead = head.next; // Head of the cloned list
        for (Node current = head; current != null; ) {
            Node clone = current.next; // The cloned node
            // Restore the original list's 'next' pointers
            current.next = clone.next;
            // Set the cloned node's 'next' pointers. Check if the next original node exists.
            clone.next = (clone.next != null) ? clone.next.next : null;
            // Move to the next original node
            current = current.next;
        }

        // Return the head of the cloned list
        return copyHead;
    }
}



class Node {
    public int val; // Value of the node
    public Node next; // Reference to the next node in the list
    public Node random; // Reference to a random node in the list

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}