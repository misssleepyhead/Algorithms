package ElementarySorts;

import java.util.Comparator;

/**
 * Experiments
 * Insertion sort with sentinel. Develop an implementation InsertionX.java
 * of insertion sort that eliminates the j > 0
 * test in the inner loop by first putting the smallest item into position.
 * <p>
 * In standard insertion sort, we insert each element by shifting larger element right
 * the inner loop
 */
public class InsertionX {
    public InsertionX() {
    }

    public static void sort(Comparable[] a) {
        int n = a.length;

        // put the smallest element in the first position
        int exchanges = 0;
        for (int i = n - 1; i > 0; i--) {
            if (less(a[i], a[i - 1])) {
                exch(a, i, i - 1);
                exchanges++;
            }
        }
        if (exchanges == 0) return;

        // insertion sort
        // i started at 2 because we know a[0] <= a[1], so a[1] is already in the correct place
        for (int i = 2; i < n; i++) {
            Comparable v = a[i]; // store the value
            int j = i;
            // shift elements right till we find the current spot
            while (less(v, a[j - 1])) { // use while because it allows early termination
                a[j] = a[j - 1]; // shift right
                j--;
            }
            // insert temp in the correct position
            a[j] = v;

        }
    }


    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static boolean less(Comparator comparator, Object v, Object w) {
        return comparator.compare(v, w) < 0;
    }

    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}
