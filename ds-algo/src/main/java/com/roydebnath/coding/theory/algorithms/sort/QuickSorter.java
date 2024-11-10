package com.roydebnath.coding.theory.algorithms.sort;

import java.util.Arrays;

public class QuickSorter {

	public static void main(String[] args) {
		int[] intList = { 5, 2, 4, 6, 1, 3 };
		sortList(intList);
		Arrays.stream(intList).forEach(System.out::println);
	}


	public static void sortList(int[] values) {
		// check for empty or null array
		if (values == null || values.length == 0) {
			return;
		}
		quickSort(values, 0, values.length - 1);
	}

	private static void quickSort(int[] arr, int start, int end) {
		if (start < end) {
			int partitionIndex = partition(arr, start, end);
			quickSort(arr, start, partitionIndex - 1);
			quickSort(arr, partitionIndex + 1, end);
		}
	}

	static int partition(int arr[], int start, int end) {
		int partitionIndex = start;
		int pivot = arr[end];
		for (int i = start; i < end; i++) {
			if (arr[i] <= pivot) {
				exchange(arr, i, partitionIndex);
				partitionIndex++;
			}
		}
		exchange(arr, partitionIndex, end);
		return partitionIndex;
	}

	private static void exchange(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}