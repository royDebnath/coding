package theory.algorithms.sort;

import java.util.Arrays;

public class BinaryInsertionSorter {

	public static void main(String[] args) {
		int[] intList = { 5, 2, 4, 6, 1, 3 };
		sortList(intList);
		Arrays.stream(intList).forEach(System.out::println);
	}
	public static void sortList(int[] list) {
		for (int i = 1; i <= list.length - 1; i++) {
			int left = 0;
			int right = i;

			while (left < right) {
				int middle = (left + right) / 2;
				if (list[i] >= list[middle]) {
					left = middle + 1;
				} else {
					right = middle;
				}
			}
			for (int j = i; j > left; j--) {
				swap(list, j);

			}

		}
	}

	private static void swap(int[] list, int j) {
		int k = list[j];
		list[j] = list[j - 1];
		list[j - 1] = k;
	}
}
