package ElementarySorts;

import edu.princeton.cs.algs4.StdRandom;

/**Web exercise 10
 * choose two integer i and j at random. If i < j, but a[i] > a[j], swap them. Repeat until the array is
 * in ascending order. Argue that the algorithm will eventually finish (with probability 1).
 * How long will it takes as a function of N? Hint: How many swaps will it make in the worst case?
 *
 * slow sort is more structured than guess sort, it swaps only move elements forward, while guess-sort
 * can swap elements backwards.
 * since each swap decrease inversions, the algorithm will terminate eventually*/
public class SlowSort {
    public static void slowSort(int[] a){
        while (!isSorted(a)){
            int i= StdRandom.uniformInt(a.length);
            int j=StdRandom.uniformInt(a.length);
            if(i<j && a[i]>a[j]){
                int swap = a[i];
                a[i] = a[j];
                a[j] = swap;
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
}
