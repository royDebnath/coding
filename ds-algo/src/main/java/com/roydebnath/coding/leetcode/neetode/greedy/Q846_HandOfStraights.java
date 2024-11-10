package com.roydebnath.coding.leetcode.neetode.greedy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 846. Hand of Straights
 * Medium
 *
 * Topics
 * Companies
 * Alice has some number of cards and she wants to rearrange the cards into groups so that each group is of size groupSize, and consists of groupSize consecutive cards.
 *
 * Given an integer array hand where hand[i] is the value written on the ith card and an integer groupSize, return true if she can rearrange the cards, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
 * Output: true
 * Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8]
 * Example 2:
 *
 * Input: hand = [1,2,3,4,5], groupSize = 4
 * Output: false
 * Explanation: Alice's hand can not be rearranged into groups of 4.
 *
 * 🎯Problem Explaination:
 *
 * The "Hand of Straights" problem involves determining whether a given list of integers (hand) can be divided into groups of a specified size (groupSize) where each group contains consecutive numbers. If it's possible to rearrange the hand into such groups, return True; otherwise, return False.
 *
 * 📥Input
 *
 * hand: A list of integers representing the cards. Each integer in the list is a card value.
 * groupSize: An integer representing the size of each group of consecutive cards.
 * 📤Output
 *
 * True if the hand can be rearranged into groups of consecutive cards of size groupSize.
 * False if it is not possible to rearrange the hand into such groups.
 * 🧠Thinking Behind the Solution:
 *
 * Check Group Size Compatibility:
 *
 * First, we check if the number of cards can be evenly divided by the group size (groupSize). If not, it's impossible to form the required groups, so we return False immediately.
 * Count the Occurrences:
 *
 * We count how many times each card appears in the hand. This helps us know exactly how many of each card we have.
 * Sort the Cards:
 *
 * We sort the unique card values in ascending order. Sorting helps us easily find and form consecutive groups starting from the smallest card.
 * Form Consecutive Groups:
 *
 * For each unique card (starting from the smallest), we try to form a group of consecutive cards.
 * If the current card (let's call it key) appears in the hand, it means we need to form a group starting from this card. We check if there are enough cards to form a full group of groupSize starting from key.
 * For example, if groupSize is 3 and key is 2, we need the cards 2, 3, and 4 to form a valid group.
 * Reduce the Count:
 *
 * For each card in the group, we reduce its count.
 * Check for Insufficient Cards:
 *
 * If at any point, a card needed to complete a group is not available in the required quantity, we return False.
 * Success:
 *
 * If we successfully form all required groups, we return True.
 * Let's walkthrough🚶🏻‍♂️ the implementation process with an example for better understanding🎯:
 *
 * Input:
 *
 * hand = [1,2,3,6,2,3,4,7,8]
 * groupSize = 3
 * Steps:
 *
 * Check Group Size Compatibility:
 *
 * len(hand) % groupSize == 0, so proceed (9 cards, group size 3).
 * Count the Occurrences:
 *
 * Count: {1: 1, 2: 2, 3: 2, 4: 1, 6: 1, 7: 1, 8: 1}
 * Sort the Cards:
 *
 * Sorted unique cards: [1, 2, 3, 4, 6, 7, 8]
 * Form Consecutive Groups:
 *
 * Start with 1: Form group [1, 2, 3] (reduce counts: 1 from each).
 * Now counts: {1: 0, 2: 1, 3: 1, 4: 1, 6: 1, 7: 1, 8: 1}
 * Next smallest card is 2: Form group [2, 3, 4] (reduce counts: 1 from each).
 * Now counts: {1: 0, 2: 0, 3: 0, 4: 0, 6: 1, 7: 1, 8: 1}
 * Next smallest card is 6: Form group [6, 7, 8] (reduce counts: 1 from each).
 * Now counts: {1: 0, 2: 0, 3: 0, 4: 0, 6: 0, 7: 0, 8: 0}
 * Success:
 *
 * All cards are used up successfully in consecutive groups of size 3. Return True.
 * ✅Approach:
 *
 * Check if Grouping is Possible:
 *
 * Ensure the total number of cards can be evenly divided by groupSize. If not, return False.
 * Count the Occurrences of Each Card:
 *
 * Use a dictionary to count how many times each card appears in the hand.
 * Sort the Unique Card Values:
 *
 * Sort the keys (unique card values) of the dictionary in ascending order. This helps in forming consecutive groups starting from the smallest card.
 * Form Consecutive Groups:
 *
 * For each card in the sorted list, if it appears in the dictionary, try to form a group starting from that card.
 * Check if there are enough consecutive cards to form a complete group. If any card needed for the group is not available in the required quantity, return False.
 * Decrease the count of each card used to form the group.
 * Return True if All Groups are Formed Successfully:
 *
 * If all cards are used up in forming valid groups, return True.
 *
 *
 */
public class Q846_HandOfStraights {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        // Step 1: Check if grouping is possible
        if (hand.length % groupSize != 0) {
            return false;
        }

        // Step 2: Count the occurrences of each card
        Map<Integer, Integer> count = new HashMap<>();
        for (int card : hand) {
            count.put(card, count.getOrDefault(card, 0) + 1);
        }

        // Step 3: Sort the unique card values
        int[] sortedKeys = new int[count.size()];
        int index = 0;
        for (int key : count.keySet()) {
            sortedKeys[index++] = key;
        }
        Arrays.sort(sortedKeys);

        // Step 4: Form consecutive groups
        for (int key : sortedKeys) {
            if (count.get(key) > 0) {  // If this card is still available
                int startCount = count.get(key);
                // Check and form a group starting from `key`
                for (int i = key; i < key + groupSize; i++) {
                    if (count.getOrDefault(i, 0) < startCount) {
                        return false;
                    }
                    count.put(i, count.get(i) - startCount);
                }
            }
        }

        // Step 5: Return true if all groups are formed successfully
        return true;
    }
}
