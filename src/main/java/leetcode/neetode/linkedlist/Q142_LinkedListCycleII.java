package leetcode.neetode.linkedlist;

import leetcode.support.ListNode;

/**
 * Given the head of a linked list, return the node where the cycle begins.
 * If there is no cycle, return null.
 *
 * There is a cycle in a linked list if there is some node in the list
 * that can be reached again by continuously following the next pointer.
 * Internally, pos is used to denote the index of the node that
 * tail's next pointer is connected to (0-indexed).
 * It is -1 if there is no cycle. Note that pos is not passed as a parameter.
 *
 * A linked list cycle is conceptually akin to a running track,
 * where the entry point of the cycle is the "gate" to the track,
 * and the cycle itself is the loop. Our goal is to figure out where this "gate" is located
 * within the list.
 *
 * Intuition
 * To resolve the problem of finding out the cycle’s starting point, we can use the two-pointer technique,
 * which is efficient and doesn't require extra memory for storage.
 *
 * The intuition behind this algorithm involves a faster runner (the fast pointer) and
 * a slower runner (the slow pointer), both starting at the head of the linked list.
 * The fast pointer moves two steps at a time while the slow pointer moves only one.
 * If a cycle exists, the fast pointer will eventually lap the slow pointer within the cycle,
 * indicating that a cycle is present.
 *
 * Once they meet, we can find the start of the cycle.
 * To do this, we set up another pointer, called ans, at the head of the list and move it
 * at the same pace as the slow pointer.
 * The place where ans and the slow pointer meet again will be the starting node of the cycle.
 *
 * Why does this work? If we consider that the distance from the list head to the cycle-entrance is x,
 * and the distance from the cycle-entrance to the meeting point is y,
 * with the remaining distance back to the cycle- entrance being z, we can make an equation.
 *
 * Since the fast pointer travels the distance of x + y + n * (y + z) (where n is the number of laps made) and slow travels x + y, and fast is twice as fast as slow, then we can deduce that x = n * (y + z) - y, which simplifies to x = (n - 1) * (y + z) + z. This shows that starting a pointer at the head (x distance to the entrance) and one at the meeting point (z distance to the entrance) and moving them at the same speed will cause them to meet exactly at the entrance of the cycle.
 *
 * Solution Approach
 * In this solution, we use the two-pointer technique, which involves having two iterators moving through the linked list at different speeds: slow and fast. slow moves one node at a time, while fast moves two nodes at a time.
 *
 * The algorithm is divided into two main phases:
 *
 * Detecting the cycle: Initially, both slow and fast are set to start at the head of the list. We then enter a loop in which fast advances two nodes and slow advances one node at a time. If there is no cycle, the fast pointer will eventually reach the end of the list and we can return null at this point, as there is no cycle to find the entrance of.
 *
 * However, if there is a cycle, fast is guaranteed to meet slow at some point within the cycle. The meeting point is not necessarily the entrance to the cycle, but it indicates that a cycle does exist.
 *
 * Finding the cycle starting node: When fast and slow meet, we introduce a new pointer called ans and set it to the head of the list. Now, we move both ans and slow one node at a time. The node at which they conjoin is the start of the cycle.
 *
 * Why does the above approach lead us to the start of the cycle? We derive this from the fact that:
 *
 * The distance from the list's head to the cycle's entrance is denoted as x.
 * The distance from the cycle's entrance to the first meeting point is y.
 * The remaining distance from the meeting point back to the entrance is z.
 * Using these variables, we know that when fast and slow meet, fast has traveled x + y + n * (y + z) which is the distance to the meeting point plus n laps of the cycle.
 *
 * Since fast travels at twice the speed of slow, the distance slow has traveled (x + y) is half that of fast, leading us to the equation 2(x + y) = x + y + n * (y + z). Simplifying this, we find x = (n - 1)(y + z) + z.
 *
 * This equation essentially states that the distance from the head to the cycle entrance (x) is equal to the distance from the meeting point to the entrance (z) plus some multiple of the cycle's perimeter (y + z). This is why moving the ans pointer from the head and slow from the meeting point at the same pace will lead them to meet at the cycle's entrance.
 *
 * The Python code provided implements this approach efficiently, using only two extra pointers (fast and slow) for detection and one extra (ans) for locating the cycle's entrance.
 *
 * Example Walkthrough
 * Let's consider a simple linked list example to walk through the solution approach:
 *
 * Suppose we have the linked list 1 -> 2 -> 3 -> 4 -> 5 -> 2 (the last node points back to the second one,
 * creating a cycle). Here, the node with the value 2 is the start of the cycle.
 *
 * Detecting the cycle:
 *
 * Initially, both slow and fast pointers are at the head of the list (node with value 1).
 * Move slow to the next node (2) and fast two nodes forward (3).
 * Move slow to the next node (3) and fast two nodes forward (5).
 * Continue this process until fast and slow meet. In our case, after few iterations,
 * fast and slow both point to one of the nodes inside the cycle (let's say they meet at node with value 4).
 * Finding the cycle starting node:
 *
 * Place the ans pointer at the head of the list (node with value 1).
 * Move ans to the next node (2) and slow to the next node (5).
 * Continue moving both ans and slow one node at a time. As the pointers move one step each turn, they will meet at the node that is the start of the cycle.
 * In our case, ans and slow will both meet at the node with value 2, which is the correct entrance to the cycle.
 * By following these steps and the reasonings behind the solution approach, we are able to find that the node with the value 2 is the entry point of the cycle in the linked list without using any extra memory for storage, only the two pointer variables fast, slow, and later the ans pointer.
 *
 *
 */
public class Q142_LinkedListCycleII {
    // This method detects the node where the cycle begins in a linked list
    public ListNode detectCycle(ListNode head) {
        // Two pointers initialized to the start of the list
        ListNode fast = head;
        ListNode slow = head;

        // Loop until the fast pointer reaches the end of the list
        while (fast != null && fast.next != null) {
            // Move the slow pointer by one step
            slow = slow.next;
            // Move the fast pointer by two steps
            fast = fast.next.next;
            // If they meet, a cycle is detected
            if (slow == fast) {
                // Initialize another pointer to the start of the list
                ListNode start = head;
                // Move both pointers at the same pace
                while (start != slow) {
                    // Move each pointer by one step
                    start = start.next;
                    slow = slow.next;
                }
                // When they meet again, it's the start of the cycle
                return start;
            }
        }
        // If we reach here, no cycle was detected
        return null;
    }
    public static void main(String[] args) {

    }
}
