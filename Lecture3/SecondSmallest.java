/**
 * Web exercises: 7. Second smallest
 * Give an algorithm to find the smallest and
 * the second smallest elements from a list of N items using the minimum number of comparisons.
 * <p>
 * Binary search is useful for searching but not finding two smallest elements efficiently
 * pairwise comparison is better because it reduces the number of comparisons
 */
public class SecondSmallest {
    public static int[] secondSmallest(int[] arr) {

        int min1, min2;
        // step 1: Initialize min1 and min2 with the first two elements
        if (arr[0] > arr[1]) {
            min1 = arr[1];
            min2 = arr[0];
        } else {
            min1 = arr[0];
            min2 = arr[1];
        }

        // step2: scan the remaining elements in pairs
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] < min1) { // update both min1, min2 if the element is smaller than both
                min2 = min1;
                min1 = arr[i];
            } else if (arr[i] < min2) { //or check if it is smaller than min2
                min2 = arr[i];
            }
        }
        return new int[]{min1, min2};

    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 8, 1, 7, 3};
        int[] result = secondSmallest(arr);
        System.out.println("Smallest: " + result[0] + ", Second Smallest: " + result[1]);
    }
}
