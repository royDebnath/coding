package com.java.codinground.programs.algoexpert.easy;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * I am given a "special" array, which is a non-empty array that consists of integers and optionally other "special" arrays.
 * I am asked to write a function that is going to return the sum of the elements in the "special" array.
 * Inside the "special" array, if I get to another "special" array,
 * I need to sum up the elements of that "special" array and then multiply the sum by the depth of that "special" array.
 * For instance, if the input array is [3, [4, 5]], then the result should be 3 + 2 * (4 + 5) = 21;
 * if the input array is [6, [4, [5]]], then the result should be 6 + 2 * (4 + 3 * 5) = 44.
 */
public class ProductSum {
    public static void main(String[] args) {
        ArrayList<Object> specialArray = new ArrayList<>(Arrays.asList(
                5,
                2,
                new ArrayList<Object>(Arrays.asList(7, -1)),
                3,
                new ArrayList<Object>(
                        Arrays.asList(6, new ArrayList<Object>(Arrays.asList(-13, 8)), 4))));
        // [5,2,[7,-1],3,[6,[-13,8],4]]
        int output = productSum(specialArray);
        System.out.println(output);
    }

    private static int productSum(ArrayList<Object> specialArray) {
        int depth = 1;
        return productSum(specialArray, depth);
    }

    private static int productSum(ArrayList<Object> specialArray, int depth) {
        int sum = 0;
        for (Object element : specialArray) {
            if (!(element instanceof ArrayList)) {
                sum += (int) element;
            } else {
                sum += productSum((ArrayList<Object>) element, depth + 1);
            }
        }
        return sum * depth; // returns to the previous recursive call from line 39
    }
}
