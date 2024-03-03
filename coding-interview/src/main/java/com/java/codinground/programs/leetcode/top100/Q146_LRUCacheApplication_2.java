package com.java.codinground.programs.leetcode.top100;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem Description
 * The problem asks us to design a Least Recently Used (LRU) cache data structure. This cache has to have a specified maximum capacity. The important aspect of an LRU cache is how it determines which items to discard: when the cache reaches its capacity limit, the least recently accessed item is removed to make room for a new item.
 *
 * The LRU cache must support two operations:
 *
 * get(key): This operation must return the value associated with a provided key. If the key is not in the cache, it should return -1. If the key exists in the cache, it should be marked as recently used.
 *
 * put(key, value): This operation must insert a key-value pair into the cache. If the key already exists, it updates the current value with the new value provided, marking the key as recently used. If the key doesn't exist, the function must add the new key-value pair. If adding a new item exceeds the cache's capacity, the least recently used item should be evicted.
 *
 * The challenge is to implement both get and put functions with a time complexity of O(1), ensuring that these operations are performed very quickly, which is critical for a performant cache system.
 *
 * Intuition
 * To achieve the O(1) time complexity for both get and put operations, we make use of a hash map (dictionary in Python) to store the keys and their associated nodes, since access in a hash map has an average time complexity of O(1). The value in the hash map points to a node in a doubly-linked list, where each node represents a cache entry with a key and value. The doubly-linked list is used to efficiently maintain the ordering of the nodes based on recent usage.
 *
 * Intuitively, whenever an item is accessed using get or updated/added using put, it must be moved to the head of the linked list since it is now the most recently used item. When the cache exceeds capacity and we need to evict an item, we remove the item from the tail end of the list, as it is the least recently used item.
 *
 * The solution involves the following operations:
 *
 * add_to_head(node): Inserts the node right after the dummy head node of the doubly-linked list.
 * remove_node(node): Removes the node from its current position in the list.
 * move_to_head(node): Combines remove_node and add_to_head to move an existing node to the head of the list.
 * remove_tail(): Removes and returns the node right before the dummy tail node, which is the least recently used item.
 * In the put method, if a key doesn't already exist, we create a new node and add it to the hash map and the linked list. If the size exceeds the capacity, the tail node is removed both from the linked list and the hash map. If the key does exist, we update the node's value and move the node to the head of the list. The get method checks if the key is in the hash map and, if found, moves the node to the head of the list and returns its value.
 *
 * By combining the constant-time lookups provided by the hash map with the ordered structure of the doubly-linked list, we can ensure that both get and put operations run in O(1) average time complexity, fulfilling the requirements of an LRU cache.
 *
 * Solution Approach
 * To implement the LRU cache, the solution makes use of two primary data structures: a hash map and a doubly-linked list. The hash map allows quick access to the nodes of the list, which in turn represent each key-value pair in the cache. The doubly-linked list is necessary to maintain the order of elements by their recency of use.
 *
 * Here is a step-by-step walkthrough of the implementation:
 *
 * Node class: This inner class represents an element in the doubly-linked list, containing properties for the key, value, and pointers to the previous and next nodes.
 *
 * LRUCache.__init__(capacity: int): The constructor initializes the LRUCache with a specific capacity. It also initializes the hash map (named cache), a dummy head and tail node for the doubly-linked list, and a size variable to keep track of the current number of elements in the cache. The head and tail are linked to each other, forming an empty doubly-linked list.
 *
 * LRUCache.get(key: int) -> int: This method attempts to retrieve the value for the given key.
 *
 * If the key is not present in the hash map, it returns -1.
 * If the key exists, the corresponding node is fetched from the hash map, moved to the head of the linked list using move_to_head() to mark it as recently used, and its value is returned.
 * LRUCache.put(key: int, value: int) -> None: This method updates the value of the key if it exists, or inserts a new key-value pair into the cache.
 *
 * If the key exists, update the value of the node and use move_to_head() to mark it as recently used.
 * If the key does not exist, create a new node, add it to the hash map, and insert it at the head of the list with add_to_head(). Increase the size counter by 1.
 * If after insertion, the cache size exceeds its capacity, the least recently used element, which is right before the tail, is removed through remove_tail(), and its key is removed from the hash map. Decrease the size counter by 1.
 * LRUCache.move_to_head(node): This utility method takes a node and places it at the head of the doubly-linked list. It calls remove_node(node) followed by add_to_head(node).
 *
 * LRUCache.remove_node(node): Removes a node from the list by updating the pointers of its neighboring nodes, thus effectively extracting it from its current position.
 *
 * LRUCache.add_to_head(node): This places a node right after the dummy head node, effectively making it the new first element of the list.
 *
 * LRUCache.remove_tail(): Removes the last non-dummy node (tail's previous node) from the list, which represents the least recently used cache item, and returns it.
 *
 * The combination of these methods and the use of a hash map and a doubly-linked list ensures that both get and put methods achieve the required O(1) average time complexity.
 *
 * Example Walkthrough
 * Let's walk through a small example to illustrate how the LRU Cache would be implemented according to the solution approach.
 *
 * Suppose we initialize an LRU Cache with a capacity of 2, and we perform a sequence of put and get operations:
 *
 * put(1, 1): The cache is empty, so we create a new node with the key-value pair (1, 1) and place it at the head of the doubly-linked list. The cache now contains {[1,1]}.
 *
 * put(2, 2): No existing node with key 2, so we create a new node and add it to the head, moving the previous head node behind it. The cache now looks like {[2,2], [1,1]}.
 *
 * get(1): The key 1 exists, so the corresponding node is moved to the head, as it is now the most recently used. The cache order is now {[1,1], [2,2]} after retrieving the value, which is 1.
 *
 * put(3, 3): There is no key 3 in the cache, but adding it would exceed the cache's capacity. So, we remove the node at the tail, which is currently key 2 since it's the least recently used. Then we create a new node for key 3 and add it to the head. The cache now holds {[3,3], [1,1]}.
 *
 * get(2): Key 2 was just evicted, so it's no longer in the cache. The get operation returns -1.
 *
 * put(4, 4): Again, adding a new node for key 4 will exceed the capacity, so we remove the least recently used item, which is now key 1. The cache is updated to {[4,4], [3,3]}.
 *
 * get(1): Key 1 was evicted in the previous step, so get returns -1.
 *
 * get(3): Key 3 is present, so its value is returned, which is 3, and it is moved to the head, making the cache order {[3,3], [4,4]}.
 *
 * Through each step, the put and get methods dynamically adjust the order of the doubly-linked list to ensure the most recently used items are nearest to the head, and the least recently used are near the tail, ready to be evicted when the capacity is exceeded. The hash map provides constant-time access to the nodes in the list during these operations.
 *
 *
 */
// Definition for a doubly linked list node
class DoublyLinkedNode {
    int key;
    int value;
    DoublyLinkedNode prev;
    DoublyLinkedNode next;

    // Constructor for creating a new node with key and value
    public DoublyLinkedNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

// LRUCache class implementing LRU caching mechanism
public class Q146_LRUCacheApplication_2 {
    private final Map<Integer, DoublyLinkedNode> cacheMap; // Hash map to store key-node pairs for O(1) access
    private final DoublyLinkedNode head;                   // Dummy head of the doubly linked list
    private final DoublyLinkedNode tail;                   // Dummy tail of the doubly linked list
    private final int capacity;                            // Maximum capacity of the cache
    private int size;                                      // Current size of the cache

    // Constructor to initialize the LRU cache
    public Q146_LRUCacheApplication_2(int capacity) {
        this.capacity = capacity;
        this.cacheMap = new HashMap<>();
        this.size = 0;
        head = new DoublyLinkedNode(0, 0);
        tail = new DoublyLinkedNode(0, 0);
        head.next = tail; // Initially, the dummy head points to the dummy tail
        tail.prev = head; // and the dummy tail points back to the dummy head
    }

    // Retrieves the value of the node associated with the given key
    public int get(int key) {
        if (!cacheMap.containsKey(key)) {
            return -1;
        }
        DoublyLinkedNode node = cacheMap.get(key);
        moveToHead(node); // Move the accessed node to the head to maintain LRU order
        return node.value;
    }

    // Adds a new key-value pair to the cache or updates the value if it already exists
    public void put(int key, int value) {
        if (cacheMap.containsKey(key)) {
            DoublyLinkedNode node = cacheMap.get(key);
            node.value = value;
            moveToHead(node); // Move the updated node to the head
        } else {
            DoublyLinkedNode newNode = new DoublyLinkedNode(key, value);
            cacheMap.put(key, newNode);
            addToHead(newNode); // Add the new node to the head of the linked list
            size++;
            if (size > capacity) {
                DoublyLinkedNode tail = removeTail(); // Remove the least recently used node
                cacheMap.remove(tail.key); // Also remove it from the map
                size--;
            }
        }
    }

    // Moves a node to the head of the doubly linked list (marks it as recently used)
    private void moveToHead(DoublyLinkedNode node) {
        removeNode(node); // Remove the node from its current position
        addToHead(node); // Add it back to the head
    }

    // Removes a node from the doubly linked list
    private void removeNode(DoublyLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Adds a node right after the dummy head node (marks it as recently used)
    private void addToHead(DoublyLinkedNode node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    // Removes and returns the tail node (least recently used node)
    private DoublyLinkedNode removeTail() {
        DoublyLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }
}

// The usage of LRUCache class would be the same as before, following the given interface.

