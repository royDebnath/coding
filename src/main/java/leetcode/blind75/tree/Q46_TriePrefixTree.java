package leetcode.blind75.tree;

/**
 * 208. Implement Trie (Prefix Tree)
 * Medium
 * <p>
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently
 * store and retrieve keys in a dataset of strings. There are various applications of this data structure,
 * such as autocomplete and spellchecker.
 * <p>
 * Implement the Trie class:
 * <p>
 * Trie() Initializes the trie object.
 *
 * void insert(String word) Inserts the string word into the trie.
 *
 * boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
 * boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * Output
 * [null, null, true, false, true, null, true]
 * <p>
 * Explanation
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // return True
 * trie.search("app");     // return False
 * trie.startsWith("app"); // return True
 * trie.insert("app");
 * trie.search("app");     // return True
 * <p>
 * Solution :
 * <p>
 * For the TrieNode, each node has two fields:
 * <p>
 * a boolean isEnd that stores whether the current character is the end of a word
 * a TrieNode[] array of size 26 that stores its children
 * search and startsWith are combined into a helper method search(String str, int type) to save coding.
 */
public class Q46_TriePrefixTree {

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
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (current.children[index] == null) { // if character not present create current else point to existing current
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.isWord = true; // mark it true at the end of loop of word
    }

    private TrieNode searchPrefix(String s) {
        TrieNode currentNode = root;
        for (char letter : s.toCharArray()) {
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

