package com.java.codinground.programs.leetcode;

import java.util.Arrays;

/**
 * Given a schedule containing the arrival and departure time of trains in a station,
 * find the minimum number of platforms needed to avoid delay in any train’s arrival.
 * <p>
 * For example,
 * <p>
 * Trains arrival   = { 2.00, 2.10, 3.00, 3.20, 3.50, 5.00 }
 * Trains departure = { 2.30, 3.40, 3.20, 4.30, 4.00, 5.20 }
 * <p>
 * The minimum platforms needed is 2
 */
public class MinimumPlatform {
    public static void main(String[] args) {
        double[] arrivals = {2.00, 2.10, 3.00, 3.20, 3.50, 5.00};
        double[] departures = {2.30, 3.40, 3.20, 4.30, 4.00, 5.20};

        System.out.println(numberOfPlatforms(arrivals, departures));

        System.out.println(numberOfPlatforms1(arrivals, departures));
    }

    private static int numberOfPlatforms(double[] arrivals, double[] departures) {
        Arrays.sort(arrivals);
        Arrays.sort(departures);
        int result = 1;
        int j = 0;
        for (int i = 1; i < arrivals.length; i++) {
            double arrival = arrivals[i];
            double departure = departures[j];
            if (arrival <= departure) {
                result++;
            } else {
                j++;
            }
        }
        return result;
    }

    private static int numberOfPlatforms1(double[] arrivals, double[] departures) {
        Arrays.sort(arrivals);
        Arrays.sort(departures);

        int i =1;
        int j =0;
        int result=1;
        int platformNeeded=1;
        while (i<arrivals.length){
            if (arrivals[i]<=departures[j]){
                platformNeeded++;
                i++;
            }
            else {
                platformNeeded--;
                j++;
            }

            result = Math.max(result, platformNeeded);
        }
        return result;
    }

}
