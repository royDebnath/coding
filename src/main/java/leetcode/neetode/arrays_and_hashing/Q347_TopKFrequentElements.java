package leetcode.neetode.arrays_and_hashing;

import java.util.*;

public class Q347_TopKFrequentElements {
    public static void main(String[] args) {
        int[] input = {1,1,1,2,2,3,4,4,4};
        System.out.println(Arrays.toString(topKFrequent(input, 2)));
    }
    public static int[] topKFrequent(int[] nums, int k) {

        // freq map
        Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
        for (int n : nums) {
            freq.put(n, freq.getOrDefault(n, 0) + 1);
        }
        // bucket sort on freq
        List<Integer>[] bucket = new List[nums.length + 1]; // for array with 1 element it will go to bucket[1], so bucket length has to be [0,1] -> 2, i.e nums.length+1

        for (int i = 0; i < bucket.length; i++){ //initialize list at each index of bucket
            bucket[i] = new ArrayList();
        }
        /**
         * each index of the bucket[] denotes the frequency value.
         * For example bucket[4] means the numbers that have occurred 4 times in the list
         * is going to be here in bucket 4. there might be more than one number which has occurred
         * 4 times so the index contains an array list.
         */
        for (int key : freq.keySet()) {
            Integer keyFrequency = freq.get(key);
            bucket[keyFrequency].add(key);
        }
        // gather result
        List<Integer> res = new ArrayList();
        for (int i = bucket.length - 1; i >= 0; i--) { // getting the frequency in descending order
            if (bucket[i] != null){
                res.addAll(bucket[i]);
            }
            if (res.size() >= k) { //top k
                break;
            }
        }
        return res.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
