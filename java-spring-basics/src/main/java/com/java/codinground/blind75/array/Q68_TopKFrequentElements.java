package com.java.codinground.blind75.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q68_TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {

        // freq map
        Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
        for (int n : nums) {
            freq.put(n, freq.getOrDefault(n, 0) + 1);
        }
        // bucket sort on freq
        List<Integer>[] bucket = new List[nums.length + 1];
        for (int i = 0; i < bucket.length; i++) bucket[i] = new ArrayList();
        for (int key : freq.keySet()) {
            bucket[freq.get(key)].add(key);
        }
        // gather result
        List<Integer> res = new ArrayList();
        for (int i = bucket.length - 1; i >= 0; i--) {
            res.addAll(bucket[i]);
            if (res.size() >= k) break;
        }
        return res.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
