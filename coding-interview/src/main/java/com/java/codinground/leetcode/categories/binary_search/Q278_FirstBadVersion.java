package com.java.codinground.leetcode.categories.binary_search;

import java.util.Random;

/**
 * 278. First Bad Version
 * Easy
 *
 * Topics
 * Companies
 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 *
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
 *
 * You are given an API bool isBadVersion(version) which returns whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 5, bad = 4
 * Output: 4
 * Explanation:
 * call isBadVersion(3) -> false
 * call isBadVersion(5) -> true
 * call isBadVersion(4) -> true
 * Then 4 is the first bad version.
 * Example 2:
 *
 * Input: n = 1, bad = 1
 * Output: 1
 *
 * Intuition:
 * The problem requires finding the first bad version in a sequence of versions. The sequence is such that all versions before the first bad one are good, and all versions from the first bad one onwards are bad. This immediately suggests a binary search approach because we can eliminate half of the versions at each step, which is more efficient than a linear search.
 *
 * Approach:
 *
 * Initialize Two Pointers:
 *
 * left is set to 1 (the first version).
 * right is set to n (the last version).
 * Binary Search:
 *
 * While left is less than right, calculate the midpoint version between left and right.
 * Use the isBadVersion API to check if the version is bad.
 * If the version is bad, it means the first bad version is at version or before it, so move the right pointer to version.
 * If the version is not bad, it means the first bad version is after version, so move the left pointer to version + 1.
 * Termination:
 *
 * Continue the process until left equals right, at which point left will be pointing to the first bad version.
 * Return left.
 * This approach ensures that we find the first bad version efficiently with a time complexity of O(log(n)), auxiliary space complexity of O(1), and total space complexity of O(1).
 */
public class Q278_FirstBadVersion extends VersionControl {
    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;

        while (left < right) {
            int version = left + (right - left) / 2;
            if (isBadVersion(version)) {
                right = version;
            } else {
                left = version + 1;
            }
        }
        return left;
    }
}

/**
 * This is a dummy code to avoid syntax error
 */
class VersionControl {
    Random random = new Random();
    boolean isBadVersion(int version) {
        // The implementation of this method is provided by the system.
        // It returns true if the version is bad, false otherwise.
        return random.nextBoolean(); // Example: Let's assume version 4 and later are bad.
    }
}