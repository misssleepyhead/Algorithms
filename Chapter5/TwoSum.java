
/**
 * Web Exercise: 1
 * Given an array a[] of N 64-bit integers and a target value T,
 * determine whether there are two distinct integers i and j such that a[i] + a[j] equals T.
 * Your algorithm should run in linear time in the worst case.
 */
public class TwoSum {
    public static boolean twoSum(long[] a, long t) {
        radixSort(a);
        int left = 0;
        int right = a.length - 1;

        while (left < right) {
            long sum = a[left] + a[right];

            if (sum == t) return true;
            else if (sum < t) left++;
            else right--;
        }
        return false;


    }

    // LSD radix sort for signed 64-bit integers
    private static void radixSort(long[] a) {
        int n = a.length;
        int R = 256;
        int bytes = 8; // long has 64 bits, 64 bits = 8 bytes

        long[] aux = new long[n];
        int[] count = new int[R + 1];

        // start one byte at a time
        for (int d = 0; d < bytes; d++) {
            // reset count[] every round because every round counts a different byte
            for (int r = 0; r <= R; r++) {
                count[r] = 0;
            }

            int shift = 8 * d; // each byte = 8 bits

            // frequency count
            for (int i = 0; i < n; i++) {
                int c = byteAt(a[i], shift);
                count[c + 1]++;
            }

            // transform counts to indices
            for (int r = 0; r < R; r++) {
                count[r + 1] += count[r];
            }

            // distribute
            for (int i = 0; i < n; i++) {
                int c = byteAt(a[i], shift);
                aux[count[c]++] = a[i];
            }

            // copy back
            for (int i = 0; i < n; i++) {
                a[i] = aux[i];
            }

        }

    }

    // this helper function works like charAt() for integer
    private static int byteAt(long x, int shift) {
        long transformed = x^Long.MAX_VALUE;
        return (int) ((x >>> shift) & 0xFF);
    }

    public static void main(String[] args) {
        long[] a = {10, -3, 7, 5, 20, -8};
        long target = 2;

        System.out.println(twoSum(a, target)); // true: -3 + 5 = 2

    }
}
