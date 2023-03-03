package com.java.codinground.programs.algoexpert.easy;

public class BinarySearch {
    public static void main(String[] args) {
        int[] array = {0, 1, 21, 33, 45, 45, 61, 71, 72, 73};
        int searchKey = 33;
        System.out.println(binarySearch(array, searchKey));
        System.out.println(binarySearchRecursive(array, searchKey));
        System.out.println();
    }

    private static int binarySearch(int[] array, int key) {
        int length = array.length;
        int left = 0;
        int right = length - 1;
        while (left <= right) {
            int middle = (left + right) / 2;

            if (key == array[middle]) {
                return middle;
            } else if (key < array[middle]) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return -1;
    }

    private static int binarySearchRecursive(int[] array, int searchKey) {
        return binarySearchRecursive(array, searchKey, 0, array.length - 1);
    }

    private static int binarySearchRecursive(int[] array, int searchKey, int left, int right) {
        if (left>right){
            return -1;
        }
        int middle = (left + right) / 2;
        if (array[middle] == searchKey) {
            return middle;
        }
        if (searchKey < array[middle]) {
            return binarySearchRecursive(array, searchKey, left, middle - 1);
        } else {
            return binarySearchRecursive(array, searchKey, middle + 1, right);
        }
    }

}

