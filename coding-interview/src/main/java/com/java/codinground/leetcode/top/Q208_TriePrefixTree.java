package com.java.codinground.leetcode.top;

/**
 * Problem Description
 * The problem requires implementing a Trie, also known as a prefix tree,
 * which is a special type of tree used to store associative data structures.
 * A trie stores keys in a way that makes it fast and efficient to retrieve them by their prefix.
 * It can be used for various applications, notably for the efficient retrieval and storage of strings
 * within a set for tasks like autocomplete or spell checking.
 *
 * The task is to create a Trie class with the following operations:
 *
 * Trie(): A method to initialize the trie object.
 *
 * void insert(String word): This method inserts the string word into the trie.
 *
 * boolean search(String word): This method checks if the string word exists in the trie
 *              (i.e., it has been inserted before), returning true if it exists and false otherwise.
 *
 * boolean startsWith(String prefix): This method checks if there is any word in the trie that
 *              starts with the given prefix, returning true if such a word exists and false otherwise.
 *
 * Intuition
 * The intuition for building a trie starts with the basic necessity of storing strings
 * in a way that common prefixes are shared to save space and make search operations efficient.
 * A Trie is a rooted tree where each node represents a common prefix of some strings.
 * Each node might have up to 26 children (in the case of lowercase alphabetic characters)
 * and a boolean flag indicating whether a word ends at this node.
 *
 * Here's how we arrive at the solution:
 *
 * Trie Structure:
 * The Trie class is visualized as a tree where each node contains
 * an array of 26 elements representing the possible lowercase alphanumeric children
 * ('a' to 'z'), and a boolean marker that signifies the end of a word (is_end).
 *
 * Insertion:
 * To insert a word, we iterate through each character of the word,
 * map it to the appropriate child index
 * (by subtracting the ASCII value of 'a' from the character's ASCII value to keep it within a 0-25 range),
 * and move to the corresponding child node, creating a new node if it doesn't exist.
 * When the end of the word is reached, we mark is_end as true at the final node.
 *
 * Search: Searching for a whole word is similar to insertion,
 * but instead of creating new nodes, we just traverse the nodes corresponding to each character.
 * If we reach the end of the word, we check the is_end flag to see if the word is a complete
 * and distinct entry in our trie.
 *
 * Prefix Search: To search for a prefix, the process is almost identical to the word search,
 * but we don't need to check the is_end flag; any reachable node after traversing the prefix
 * indicates that a word with that prefix exists in the trie.
 *
 * The auxiliary method _search_prefix encapsulates the common logic of traversing
 * the trie that is shared between searching for a complete word and checking for a word that
 * starts with a given prefix.
 *
 * Using such a structure allows operations like insert, search and prefix search to be performed
 * in O(m) time complexity, where m is the length of the word or prefix being processed.
 *
 * Example Walkthrough
 * Let's walk through a small example using the solution approach outlined
 * for the implementation of a Trie:
 *
 * Suppose we want to execute the following operations:
 *
 * Initialize the Trie.
 * Insert the word "apple".
 * Search for the word "apple".
 * Search for the word "app" to check if it exists as a complete word.
 * Check if a prefix "app" exists.
 * Let's begin with step-by-step operations on the Trie:
 *
 * Initialization:
 *
 * We create a Trie object.
 *
 * trie = Trie()
 * At this point, the trie is empty, with only the root node, and no words are added.
 * Inserting the word "apple":
 *
 * We call insert("apple") on our trie.
 *
 * trie.insert("apple")
 * We iterate through each character in "apple".
 * For 'a', calculate the index (0) and go to the child node at index 0. As it is None,
 * create a new Trie node there.
 * Repeat the process for 'p', 'p', 'l', and 'e'.
 * At the end of insertion, mark the is_end as True at the node representing 'e'.
 * Searching for the word "apple":
 *
 * We call search("apple").
 *
 * trie.search("apple") # Should return True
 * Internally _search_prefix method is called that goes through each character in "apple".
 * Since we just inserted "apple", each of these nodes exist, and the final node's is_end is True.
 * Therefore, it returns True as "apple" exists in the trie.
 * Searching for the word "app":
 *
 * We call search("app").
 *
 * trie.search("app") # Should return False
 * _search_prefix method is used to traverse for "app".
 * While 'a' and 'p' exist, the is_end at the second 'p' is False because we inserted "apple" and not "app".
 * Hence, it returns False, indicating "app" is not registered as a distinct word in the trie.
 * Checking if prefix "app" exists:
 *
 * We call startsWith("app").
 *
 * trie.startsWith("app") # Should return True
 * _search_prefix method is used to traverse the trie for "app".
 * Since nodes for 'a' and 'p' exist, and we do not care about is_end when checking for a prefix,
 * It returns True as there is a word in the trie that starts with "app" (which is "apple").
 * Through this example, you can see how each of the operations work and how the methods _insert, _search, and _search_prefix interact with each other to perform trie operations efficiently.
 *
 *
 */
public class Q208_TriePrefixTree {

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));   // return True
        System.out.println(trie.search("app"));     // return False
        System.out.println(trie.startsWith("app")); // return True
        trie.insert("app");
        System.out.println(trie.search("app"));     // return True
    }
}


class TrieNode {
    boolean isWord;
    TrieNode[] children;

    public TrieNode() {
        isWord = false;
        children = new TrieNode[26]; // 26 English lowercase letters
    }
}

class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for (char letter : word.toCharArray()) {
            int index = letter - 'a';
            if (current.children[index] == null) { // if character not present create current else point to existing current
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.isWord = true; // mark it true at the end of loop of word
    }

    private TrieNode searchPrefix(String word) {
        TrieNode currentNode = root;
        for (char letter : word.toCharArray()) {
            int index = letter - 'a';
            if (currentNode.children[index] == null) {
                // If the next node doesn't exist, return null indicating the prefix doesn't exist.
                return null;
            }
            // Move to the next Trie node.
            currentNode = currentNode.children[index];
        }
        return currentNode; // The node where the search ended, could represent the prefix
        // or the whole word.
    }

    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        // If the node is not null and isEndOfWord is true, the word exists in the trie.
        return node != null && node.isWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        // If the node is not null and isEndOfWord is true, the word exists in the trie.
        return node != null;
    }
}