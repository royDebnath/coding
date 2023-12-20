package com.java.codinground.educative.modified_binary_search;

/**
 * Statement
 * The latest version of a software product fails the quality check. Since each version is developed upon the previous one, all the versions created after a bad version are also considered bad.
 *
 * Suppose you have n versions with the IDs
 * [
 * 1
 * ,
 * 2
 * ,
 * .
 * .
 * .
 * ,
 * �
 * ]
 * [1,2,...,n]
 * , and you have access to an API function that returns TRUE if the argument is the ID of a bad version.
 *
 * Find the first bad version that is causing all the later ones to be bad. Additionally,
 * the solution should also return the number of API calls made during the process and should
 * minimize the number of API calls too.
 *
 * Naive approach
 * The naive approach is to find the first bad version in the versions range by linear search. We traverse the whole version range one element at a time until we find the target version.
 *
 * The time complexity of this approach is
 * �
 * (
 * �
 * )
 * O(n)
 * , because we may have to search the entire range in this process. This approach ignores an important piece of information: the range of version numbers is sorted from
 * 1
 * 1
 *  to
 * �
 * n
 * . Let’s see if we can use this information to design a faster solution.
 *
 * Optimized approach using modified binary search
 *
 * Since the versions range is sorted, binary search is the most efficient approach for finding the first bad version. It executes in
 * �
 * (
 * log
 * ⁡
 * �
 * )
 * O(logn)
 *  time, which is faster than the alternate linear scanning method.
 *
 * Our goal is to find the first bad version by making the minimum number of calls to the API function. We use the binary search algorithm. The idea is to check the middle version to see if it’s bad. If it’s good, this means that the first bad version occurs later in the range, allowing us to entirely skip checking the first half of the range. If it’s bad, we need to check the first half of the range to find the first bad version. Therefore, we can skip checking the second half of the range.
 *
 * When we go to check the identified half of the range, we use the same approach again. We check the middle version to figure out which half of the current range to check.
 *
 *
 */
public class FirstBadVersion {
    public static int v;

    static boolean isBadVersion(int s) {
        return s >= v;
    }

    static int[] firstBadVersion(int n) {
        int[] result = new int[2];
        int first = 1;
        int last = n;
        int calls = 0;

        while (first < last) {
            int mid = first + (last - first) / 2;

            if (isBadVersion(mid)) {
                last = mid;
            } else {
                first = mid + 1;
            }

            calls += 1;
        }

        result[0] = first;
        result[1] = calls;
        return result;
    }

    public static void main(String args[]) {
        int[] testCaseVersions = new int[]{38, 13, 29, 40, 23};
        int[] firstBadVersion = new int[]{28, 10, 10, 28, 19};

        for (int i = 0; i < testCaseVersions.length; i++) {
            v = firstBadVersion[i];

            if (i > 0) {
                System.out.println();
            }

            System.out.println(i + 1 + ".\tNumber of versions: " + testCaseVersions[i]);
            int[] result = firstBadVersion(testCaseVersions[i]);
            System.out.println("\n\tFirst bad version: " + result[0] + ". Found in " + result[1] + " API calls.");
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
