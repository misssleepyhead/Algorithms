import edu.princeton.cs.algs4.Insertion;

/**
 * 5.2 MSD string sort
 */
public class MyMSD {
    private static int R = 256; //raidx
    private static final int M = 15; // cutoff for small subarrays
    private static String[] aux; //auxiliary array for distribution

    private static int charAt(String s, int d) {
        if (d < s.length()) return s.charAt(d);
        else return -1; // end of string
    }

    public static void sort(String[] a) {
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N - 1, 0);
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        // sort from a[lo] to a[hi], starting at the dth character
        if (hi <= lo + M) {
            Insertion.sort(a, lo, hi, d);
            return;
        }
        int[] count = new int[R+2];

    }
}
