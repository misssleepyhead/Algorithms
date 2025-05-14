/**
 * merge sort web exercises 4:
 * k-near-sorting. Suppose you have an array a[] of N distinct items which is nearly sorted: each item at most k positions away from its position in the sorted order.
 * Design an algorithm to sort the array in time proportional to N log k.
 * Hint: First, sort the subarray from 0 to 2k; the smallest k items will be in their correct position. Next,
 * sort the subarray from k to 3k; the smallest 2k items will now be in their correct position.
 * <p>
 * Note:
 * 1. the input is k-near-sorted, each element is at most k pos away from it sorted pos.
 * 2. the current pos might be +- k pos, left or right. so we first sort 2k
 */
public class KNearSorting {
    public static void sort(Comparable[] a, int k) {
        Comparable[] aux = new Comparable[a.length];
        int n = a.length;
        for (int i = 0; i < a.length; i += k) {
            int lo = i;
            int hi = Math.min(i + 2 * k, n - 1);
            sort(a, aux, lo, hi);

        }

    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);

    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            aux[i] = a[i];
        }
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (aux[j].compareTo(aux[i]) < 0) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }
}
