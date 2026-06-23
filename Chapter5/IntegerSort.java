import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Exercise 3:
 * Given an array of N decimal integers of different lengths,
 * describe how to sort them in O(N + K) time,
 * where K is the total number of digits overall all the N integers.
 * <p>
 * Note:
 * 1. first sort by each integer's length (9<10<100)
 * 2. Then sort by digital in each same group
 */
public class IntegerSort {
    public static void sort(String[] a) {
        if (a == null || a.length <= 1) return;

        // step 1: group numbers by length
        sortLength(a);

        // step 2: sort each same-length group by LSD radix sort
        int lo = 0;
        while (lo < a.length) {
            int w = a[lo].length();
            int hi = lo;
            while (hi + 1 < a.length && a[hi + 1].length() == w) {
                hi++;
            }

            // now a[lo..hi] all have same length w
            lsdSort(a, lo, hi, w);

            lo = hi+1;
        }

    }


    // 1. first sort integers by length
    private static void sortLength(String[] a) {
        int maxLen = 0;
        for (String s : a) {
            maxLen = Math.max(maxLen, s.length());
        }
        int[] count = new int[maxLen + 2];

        // frequency count
        for (String s : a) {
            int len = s.length();
            count[len + 1]++;
        }


        // transform count to indices
        for (int r = 0; r <= maxLen; r++) {
            count[r + 1] += count[r];
        }

        // distribute
        String[] aux = new String[a.length];
        for (String s : a) {
            int len = s.length();
            int pos = count[len]; // where this length group place next item
            aux[pos] = s;
            count[len] ++; // move pointer to next available slot
        }
        // aux becomes [  9   7   3   45   88   12   123   501   1000]

        // copy back
        for (int i = 0; i < a.length; i++) {
            a[i] = aux[i];
        }
    }

    // use lsd sort each group
    private static void lsdSort(String[] a, int lo, int hi, int w) {
        int n = hi - lo + 1;
        int R = 10; // decimal radix
        String[] aux = new String[n];

        for (int d = w - 1; d >= 0; d--) {
            int[] count = new int[R + 1];
            for (int i = lo; i <= hi; i++) {
                int digit = a[i].charAt(d) - '0'; // convert unicode value to real integer 0..9
                count[digit + 1]++;
            }

            for (int r = 0; r < R; r++) {
                count[r + 1] += count[r];
            }

            for (int i = lo; i <= hi; i++) {
                int digit = a[i].charAt(d) - '0';
                aux[count[digit]++] = a[i];
            }

            for (int i = lo; i <= hi; i++) {
                a[i] = aux[i - lo];
            }

        }
    }

    public static void main(String[] args) {
        String[] a = {"9", "123", "45", "7", "1000", "88", "3", "501", "12"};

        sort(a);

        for (String s : a) {
            System.out.println(s);
        }
    }
}
