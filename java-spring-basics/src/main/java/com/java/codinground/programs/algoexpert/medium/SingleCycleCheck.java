package com.java.codinground.programs.algoexpert.medium;

public class SingleCycleCheck {
    public static void main(String[] args) {
        int[] input = {2, 3, 1, -4, -4, 2};
        System.out.println(hasSingleCycle(input));
    }

    private static boolean hasSingleCycle(int[] input) {
        int length = input.length;
        int numberOfVisited = 0;
        int currentIndex=0;

        while (numberOfVisited < length){
            if (numberOfVisited>0 && currentIndex==0){
                return false;
            }
            numberOfVisited++;
            currentIndex= getNextIndex(input, currentIndex);
        }
        return currentIndex==0;
    }

    private static int getNextIndex(int[] input, int currentIndex) {
        int length = input.length;
        int jump = input[currentIndex];
        int nextIndex = (currentIndex + jump)%length;
        return nextIndex>=0 ? nextIndex : nextIndex+length;
    }
}
