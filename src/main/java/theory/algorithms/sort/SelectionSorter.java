package theory.algorithms.sort;

import java.util.Arrays;

public class SelectionSorter {

	public static void main(String[] args) {
		int[] intList = { 5, 2, 4, 6, 1, 3 };
		sortList(intList);
		Arrays.stream(intList).forEach(System.out::println);
	}

	public static void sortList(int[] list) {

		for (int i = 0; i < list.length; i++) {
			int j = i + 1;
			while (j < list.length) {
				if (list[j] < list[i]) {
					swap(list, i, j);
				}
				j++;
			}
		}
	}

	private static void swap(int[] list, int i, int j) {
		int k = list[i];
		list[i] = list[j];
		list[j] = k;
	}

}
