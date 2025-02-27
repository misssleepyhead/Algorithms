package ElementarySorts;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Web exercises
 * Pick two indices i and j at random; if a[i] > a[j], then swap them.
 * Repeat until the input is sorted. Analyze the expected running time of this algorithm.
 * <p>
 * the worst case (a reverse sorted array), given m inversions in the array
 * we pick i,j = N*N=N^2 possible pairs, the probability of picking an inversion is Theta(m/N^2)
 * worst case m=O(N^2), each successful swap m-1
 * to remove all inversions, we sum over all values from m=1 to N^2 (N^2/m)
 * Total time cost O(N^2 logN)
 */
public class GuessSort {

    public static void guessSort(int[] arr) {
        while (!isSorted(arr)) {
            int size = arr.length;
            int i = StdRandom.uniformInt(size);
            int j = StdRandom.uniformInt(size);
            if (arr[i] > arr[j]) {
                int swap = arr[i];
                arr[i] = arr[j];
                arr[j] = swap;
            }
        }
    }

    private static boolean isSorted(int[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    // is the array sorted from a[lo] to a[hi]
    private static boolean isSorted(int[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args) {

    }

}
