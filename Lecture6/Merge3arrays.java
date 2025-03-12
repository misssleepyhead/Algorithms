import java.util.Arrays;

/**
 * web exercise 9:
 * Merging three arrays. Given three sorted arrays a[], b[], and c[], each of size N, design an algorithm to merge
 * them into a new sorted array d[] using at most ~ 6 N compares in the worst case (or, even better, ~ 5 N compares).
 * <p>
 * Solutions:
 * finding the smallest and the second smallest element in one step
 * place both at the aux[]
 */

public class Merge3arrays {
    public static int[] merge3(int[] a, int[] b, int[] c) {
        int N = a.length;
        int[] d = new int[N * 3];
        int i = 0, j = 0, k = 0, index = 0;

        while (i < N || j < N || k < N) {
            int aVal = (i < N) ? a[i] : Integer.MAX_VALUE;
            int bVal = (j < N) ? b[j] : Integer.MAX_VALUE;
            int cVal = (k < N) ? c[k] : Integer.MAX_VALUE;

            int lo, mid;
            // find the smallest one
            if (aVal <= bVal && aVal <= cVal) {
                lo = aVal;
                i++;
                mid = Math.min(bVal, cVal); // second smallest
            } else if (bVal <= aVal && bVal <= cVal) {
                lo = bVal;
                j++;
                mid = Math.min(aVal, cVal);
            } else {
                lo = cVal;
                k++;
                mid = Math.min(aVal, bVal);
            }

            d[index++] = lo;

            // if mid is not Intege.maxvalue, insert it
            if (index < 3 * N) {
                d[index++] = mid;
                if (mid == aVal) i++;
                else if (mid == bVal) j++;
                else k++;
            }
        }
        return d;

    }

    public static void main(String[] args) {
        int[] a = {1, 4, 7, 10, 13};
        int[] b = {2, 5, 8, 11, 14};
        int[] c = {3, 6, 9, 12, 15};

        int[] merged = merge3(a, b, c);
        System.out.println(Arrays.toString(merged));
    }
}
