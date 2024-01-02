package com.java.codinground.dsalgo.algorithms.sort;

import java.util.Arrays;

public class QuickSorter1 {

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

    int number = values.length;
    quicksort(values, 0, number - 1);
  }

  private static void quicksort(int[] values, int low, int high) {
    int i = low, j = high;
    // Get the pivot element from the middle of the list
    int pivot = values[low + (high - low) / 2];

    // Divide into two lists
    while (i <= j) {
      // If the current value from the left list is smaller than the pivot
      // element then get the next element from the left list
      while (values[i] < pivot) {
        i++;
      }
      // If the current value from the right list is larger than the pivot
      // element then get the next element from the right list
      while (values[j] > pivot) {
        j--;
      }

      // If we have found a value in the left list which is larger than
      // the pivot element and if we have found a value in the right list
      // which is smaller than the pivot element then we exchange the
      // values.
      // As we are done we can increase i and j
      if (i <= j) {
        exchange(values, i, j);
        i++;
        j--;
      }
    }
    // Recursion
    if (low < j)
      quicksort(values, low, j);
    if (i < high)
      quicksort(values, i, high);
  }

  private static void exchange(int[] values, int i, int j) {
    int temp = values[i];
    values[i] = values[j];
    values[j] = temp;
  }
}