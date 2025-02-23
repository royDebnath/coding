package theory.algorithms.search;

public class BinarySearcher {

    public static void main(String[] args) {
        int[] intList = {1,3,6,9,11,24,46,54,67,86,91,99};
        System.out.println(searchList(intList, 86));
    }

    public static int searchList(int[] intList, int key) {

        int left = 0;
        int right = intList.length - 1;

        while (left <= right) {
            int middle = (left + right) / 2;
            if (key == intList[middle]) {
                return middle;
            } else if (key < intList[middle]) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return -1;
    }
}
