package com.java.codinground.leetcode.categories.heap;

/**
 * 621. Task Scheduler
 * Solved
 * Medium
 *
 * Topics
 * Companies
 *
 * Hint
 * You are given an array of CPU tasks, each represented by letters A to Z, and a cooling time, n. Each cycle or interval allows the completion of one task. Tasks can be completed in any order, but there's a constraint: identical tasks must be separated by at least n intervals due to cooling time.
 *
 * ​Return the minimum number of intervals required to complete all tasks.
 *
 *
 *
 * Example 1:
 *
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 *
 * Output: 8
 *
 * Explanation: A possible sequence is: A -> B -> idle -> A -> B -> idle -> A -> B.
 *
 * After completing task A, you must wait two cycles before doing A again. The same applies to task B. In the 3rd interval, neither A nor B can be done, so you idle. By the 4th cycle, you can do A again as 2 intervals have passed.
 *
 * Example 2:
 *
 * Input: tasks = ["A","C","A","B","D","B"], n = 1
 *
 * Output: 6
 *
 * Explanation: A possible sequence is: A -> B -> C -> D -> A -> B.
 *
 * With a cooling interval of 1, you can repeat a task after just one other task.
 *
 * Example 3:
 *
 * Input: tasks = ["A","A","A", "B","B","B"], n = 3
 *
 * Output: 10
 *
 * Explanation: A possible sequence is: A -> B -> idle -> idle -> A -> B -> idle -> idle -> A -> B.
 *
 * There are only two types of tasks, A and B, which need to be separated by 3 intervals. This leads to idling twice between repetitions of these tasks.
 *
 * Intuition:
 *
 * The goal of this problem is to find the minimum time required to execute all tasks with a cooling period of ‘n’ between identical tasks.
 * We want to minimize the idle time (gaps) between tasks.
 * The maximum frequency of any task determines the number of chunks (cycles) required to execute all tasks.
 * The number of chunks is equal to the maximum frequency.
 * The length of each chunk is ‘n+1’ (including the task and cooling periods).
 * The last chunk may not have ‘n+1’ tasks if there are fewer tasks left.
 *
 * Approach:
 *
 * Initialize an array called ‘freq’ to store the frequency of each task (indexed by task character).
 * Calculate the maximum frequency ‘max’ and count the number of tasks with maximum frequency ‘c’.
 * Calculate the number of gaps between tasks (chunks) as ‘max-1’.
 * Calculate the available slots in each gap as ‘n-(c-1)’ (since we need to leave one slot for the task itself).
 * Calculate the total available slots as ‘gaps * onegap’.
 * Calculate the total remaining tasks (excluding the tasks with maximum frequency) as ‘tasks.length - max * c’.
 * Calculate the idle time as ‘max(0, av_slot - av_task)’.
 * Return the total time required as ‘tasks.length + idle’.
 *
 * Complexity:
 *
 * The time complexity is O(N), where N is the number of tasks.
 * The space complexity is O(1) since we use a fixed-size array ‘freq’.
 * The given code efficiently computes the minimum time needed to execute the tasks while considering the cooling period. It ensures that tasks are executed optimally without unnecessary idle time.
 *
 */
public class Q621_TaskScheduler {
        public int leastInterval(char[] tasks, int n) {
            int[] freq=new int[26];
            for(int i=0;i<tasks.length;i++){
                freq[tasks[i]-'A']++;
            }
            int max=0,c=0;
            for(int i=0;i<26;i++){
                max=Math.max(freq[i], max);
            }
            for(int i=0;i<26;i++){
                if(freq[i]==max) c++;
            }
            int gaps=max-1;
            int onegap=n-(c-1);
            int av_slot=gaps*onegap;
            int av_task=tasks.length-max*c;
            int idle=Math.max(0, av_slot-av_task);
            return tasks.length+idle;
        }
}
