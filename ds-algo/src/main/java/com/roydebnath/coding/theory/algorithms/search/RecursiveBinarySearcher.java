package com.roydebnath.coding.theory.algorithms.search;

public class RecursiveBinarySearcher {

	public static void main(String[] args) {
		int[] intList = {1,3,6,9,11,24,46,54,67,86,91,99};
		System.out.println(searchList(intList, 86));
	}

	public static int searchList(int[] intList, int key) {
		return recursiveBinarySearch(intList, 0, intList.length - 1, key);

	}

	private static int recursiveBinarySearch(int[] intList, int left, int right, int key) {
		if (right >= left) {
			int middleIndex = left + (right - left) / 2;
			if (intList[middleIndex] == key) {
				return middleIndex;
			} else if (key < intList[middleIndex]) {
			return	recursiveBinarySearch(intList, left, middleIndex - 1, key);
			} else {
				return recursiveBinarySearch(intList, middleIndex + 1, right, key);
			}
		}
		return -1;
	}

}
