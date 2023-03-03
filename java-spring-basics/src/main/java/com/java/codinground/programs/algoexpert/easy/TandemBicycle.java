package com.java.codinground.programs.algoexpert.easy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * I am given two arrays of positive integers, one containing the speeds of riders with red shirts
 * and another one containing the speeds of riders with blue shirt.
 * The number of red-shirt riders and the number of blue-shirt riders are equal.
 * I will also receive a third input parameter called fastest and it is going to be a Boolean value.
 * I am asked to write a function
 * that is going to pair every red-shirt rider with a blue-shirt rider to ride a tandem bicycle;
 * and if the Boolean is true, the function will return the total maximum speed of all tandem bicycles being ridden;
 * otherwise it will return the total minimum speed.
 * <p>
 * A tandem bicycle is a bicycle operated by two people.
 * The speed of the bicycle is dictated by the rider who pedals faster,
 * for instance, if the speed of one rider is 5 and the speed of the other rider is 3,
 * then the speed of the bicycle is 5.
 */
public class TandemBicycle {
    public static void main(String[] args) {

        int[] redShirtSpeeds = new int[]{5, 5, 3, 9, 2};
        int[] blueShirtSpeeds = new int[]{3, 6, 7, 2, 1};
        System.out.println(tandemBicycle(redShirtSpeeds, blueShirtSpeeds, true));

    }

    private static int tandemBicycle(int[] redShirtSpeeds, int[] blueShirtSpeeds, boolean fastest) {
        int length = redShirtSpeeds.length;
        List<Integer> reds = Arrays.stream(redShirtSpeeds).boxed().collect(Collectors.toList());
        List<Integer> blues = Arrays.stream(blueShirtSpeeds).boxed().collect(Collectors.toList());
        Collections.sort(reds);
        Collections.sort(blues);
        if (fastest) {
            Collections.reverse(blues);
        }
        int sumTandemSpeed = 0;
        for (int i = 0; i < length; i++) {
            sumTandemSpeed+=Math.max(reds.get(i), blues.get(i));
        }
        return sumTandemSpeed;
    }
}
