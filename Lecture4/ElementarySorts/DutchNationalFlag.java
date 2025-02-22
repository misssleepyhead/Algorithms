package ElementarySorts;

import java.util.Arrays;

/**
 * interview question 3 DutchNationalFlag
 * Given an array of n buckets, each containing a red, white, or blue pebble, sort them by color.
 * The allowed operations are:
 * swap(i,j)
 * color(i) determine the color of index i
 * <p>
 * the performance requirements:
 * at most n calls to color and n calls to swap
 * constant extra space
 */
public class DutchNationalFlag {
    static int[] arr;
    final int red = 0;
    final int white = 1;
    final int blue = 2;

    public static void sortColors(int[] colors) {
        // 3-way partitioning
        arr=colors;
        int low = 0, mid = 0, high = colors.length - 1; // low is left boundary, mid is current, high is right boundary
        while (mid <= high) {
            if (color(mid) == 0) {
                swap(low, mid);
                low++;
                mid++;
            } else if (color(mid) == 1) {
                mid++;
            } else {
                swap(mid, high);
                high--;
            }
        }
    }

    private static void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static int color(int i) {
        return arr[i];

    }

    public static void main(String[] args) {
        int[] pebbles = {2, 0, 2, 1, 1, 0};
        System.out.println("before sorting" + Arrays.toString(pebbles));
        sortColors(pebbles);
        System.out.println("after sorting" + Arrays.toString(pebbles));
    }
}
