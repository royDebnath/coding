package com.java.codinground.educative.binary_search;

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
 * Find the first bad version that is causing all the later ones to be bad. Additionally, the solution should also return the number of API calls made during the process and should minimize the number of API calls too.
 *
 * Optimized approach using modified binary search
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
import java.util.*;
public class FirstBadVersion {
    public static int v;

    static boolean isBadVersion(int s) {
        return s >= v;
    }

    static int[] firstBadVersion(int n) {
        int[] result = new int[2];
        // Assigning first pointer with the first version that is 1
        int first = 1;
        // Assigning last pointer with n that is the number of versions
        int last = n;
        int calls = 0;
        System.out.print("\n\tVersions:  ");
        for (int x = first; x < last; x++) {
            System.out.print(x + ", ");
        }
        System.out.println(last + "\n");

        // Binary search to find the target version
        while (first < last) // Last should be equal to n. But here last = 2. Because we are calculating mid only one time here just to show how it works
        {
            int mid = first + (last - first) / 2;
            // isBadVersion() is the API function that returns true
            // or false depending upon whether the provided version ID
            // is bad or good
            if (isBadVersion(mid)) {
                last = mid;
                System.out.println("\tVersion " + mid + " is bad, so we search in the first half of the list.\n");
            } else {
                first = mid + 1;
                System.out.println("\tVersion " + mid + " is good, so we search in the second half of the list.\n");
            }
            calls += 1;
            System.out.printf("\t| mid    |   first    |    last    |    calls    |\n");
            System.out.printf("\t ------------------------------------------------\n");
            System.out.printf("\t| " + mid + "     |   " + first + "       |    " + last + "      |    " + calls + "        |");
            System.out.print("\n\n\tVersions to check: ");
            for (int x = first; x < last; x++) {
                System.out.print(x + ", ");
            }
            System.out.println(last + "\n");
        }

        result[0] = first;
        result[1] = calls;
        return result;
    }

    // Driver code
    public static void main(String args[]) {
        int[] testCaseVersions = new int[] {38, 13, 29, 40, 23};
        int[] firstBadVersion = new int[] {28, 10, 10, 28, 19};

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
