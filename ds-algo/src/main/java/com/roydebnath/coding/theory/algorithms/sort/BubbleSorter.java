package com.roydebnath.coding.theory.algorithms.sort;

import java.util.Arrays;

public class BubbleSorter {

	public static void main(String[] args) {
		int[] intList = { 5, 2, 4, 6, 1, 3 };
		sortList(intList);
		Arrays.stream(intList).forEach(System.out::println);
	}

	public static void sortList(int[] list) {
		boolean didSwap = false;
		for (int i = 0; i < list.length; i++) {
			for (int j = 0; j < list.length - i - 1; j++) {
				if (list[j + 1] < list[j]) {
					swap(list, j);
					didSwap = true;
				}
			}
			if (!didSwap) {
				break;
			}
		}
	}

	private static void swap(int[] list, int i) {
		int k = list[i];
		list[i] = list[i + 1];
		list[i + 1] = k;
	}

}
