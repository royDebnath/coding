package leetcode.neetode.top_k_elements;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 692. Top K Frequent Words
 * Medium
 * 7.3K
 * 336
 * Companies
 * Given an array of strings words and an integer k, return the k most frequent strings.
 *
 * Return the answer sorted by the frequency from highest to lowest. Sort the words with the same frequency by their lexicographical order.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["i","love","leetcode","i","love","coding"], k = 2
 * Output: ["i","love"]
 * Explanation: "i" and "love" are the two most frequent words.
 * Note that "i" comes before "love" due to a lower alphabetical order.
 * Example 2:
 *
 * Input: words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
 * Output: ["the","is","sunny","day"]
 * Explanation: "the", "is", "sunny" and "day" are the four most frequent words, with the number of occurrence being 4, 3, 2 and 1 respectively.
 *
 */
public class Q692_TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> wordCounts = new HashMap<>();
        for(String word : words) {
            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<String> minHeap = new PriorityQueue<>(
                (s1, s2) ->  wordCounts.get(s1) == wordCounts.get(s2) ? s2.compareTo(s1) : wordCounts.get(s1) - wordCounts.get(s2));

        for(String word : wordCounts.keySet()) {
            minHeap.offer(word);

            if(minHeap.size() > k) {
                minHeap.poll();
            }
        }

        LinkedList<String> toReturn = new LinkedList<>();
        while(!minHeap.isEmpty()) {
            toReturn.addFirst(minHeap.poll());
        }

        return toReturn;
    }
}
