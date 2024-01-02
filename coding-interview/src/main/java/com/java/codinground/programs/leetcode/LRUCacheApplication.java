package com.java.codinground.programs.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 * <p>
 * Implement the LRUCache class:
 * <p>
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 * The functions get and put must each run in O(1) average time complexity.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 * <p>
 * Explanation
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // cache is {1=1}
 * lRUCache.put(2, 2); // cache is {1=1, 2=2}
 * lRUCache.get(1);    // return 1
 * lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 * lRUCache.get(2);    // returns -1 (not found)
 * lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 * lRUCache.get(1);    // return -1 (not found)
 * lRUCache.get(3);    // return 3
 * lRUCache.get(4);    // return 4
 */

public class LRUCacheApplication{
    public static void main(String[] args) {
        System.out.println();LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        lRUCache.get(1);    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        lRUCache.get(2);    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        lRUCache.get(1);    // return -1 (not found)
        lRUCache.get(3);    // return 3
        lRUCache.get(4);    // return 4
    }
}
class LRUCache {
    final Node head = new Node(); //dummy node to keep track of position while insertion/deletion
    final Node tail = new Node(); //dummy node to keep track of position while insertion/deletion
    Map<Integer, Node> cache;
    int CAPACITY;

    public LRUCache(int capacity) {
        this.CAPACITY = capacity;
        cache = new HashMap<>(capacity);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        int returnValue = -1;
        Node node = cache.get(key);
        if (node != null) {
            returnValue = node.value;
            remove(node);
            add(node); //removing and adding just to place the node in the front
        }
        return returnValue;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node != null) { // if the node is already present, updating with new value
            node.value = value;
            remove(node);
            add(node); // doing the add will always place the node in the front of the linked list.
            // since this key is touched, keeping it in front by explicitly removing and adding
        } else { // new node, does not already exist in the map/cache
            if (CAPACITY == cache.size()) {//checking if cache is full
                Node last_node = tail.prev;
                remove(last_node);
                cache.remove(last_node.key);
            }
            Node newNode = new Node(); //create new node
            newNode.key = key; // assign key to new node
            newNode.value = value; // assign value to new node
            cache.put(key, newNode); // putting the new key in cache with newNode
            add(newNode); // adding new node in the linked list which automatically puts it in the front
        }
    }

    private void add(Node node) {
        Node headNext = head.next; // head -> headNext
        node.next = headNext; // node -> headNext
        headNext.prev = node; // node <- headNext
        head.next = node; // head -> node
        node.prev = head; // head <-node
        // so finally head <=> node <=> headNext
    }

    private void remove(Node node) {
        Node prevNode = node.prev; //prevNode -> node
        Node nextNode = node.next; //prevNode -> node -> nextNode
        prevNode.next = nextNode; //prevNode -> nextNode
        nextNode.prev = prevNode; //prevNode <- nextNode
    }

    class Node {
        int key;
        int value;
        Node prev;
        Node next;
    }
}
