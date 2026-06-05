import edu.princeton.cs.algs4.Insertion;

/**
 * 5.2 MSD string sort
 */
public class MyMSD {
    private static int R = 256; //raidx
    private static final int M = 15; // cutoff for small subarrays
    private static String[] aux; //auxiliary array for distribution


    public static void sort(String[] a) {
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N - 1, 0);
    }

    // return dth char of s, -1, if d = length of string
    private static int charAt(String s, int d) {
        assert d >= 0 && d <= s.length();
        if (d == s.length()) return -1;
        return s.charAt(d);
    }

    private static void insertion(String[] a, int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j - 1], d); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    // exchange a[i] and a[j]
    private static void exch(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // is v less than w, starting at char d
    private static boolean less(String v, String w, int d) {
        for (int i = d; i < Math.min(v.length(), w.length()); i++) {
            if (v.charAt(i) < w.charAt(i)) return true;
            if (v.charAt(i) > w.charAt(i)) return false;
        }
        return v.length() < w.length();
    }


    private static void sort(String[] a, int lo, int hi, int d) {
        // sort from a[lo] to a[hi], starting at the dth character
        if (hi <= lo + M) {
            insertion(a,lo,hi,d);
            return;
        }
        int[] count = new int[R + 2];
        //compute frequency count
        for(int i=lo; i<=hi;i++){
            int c = charAt(a[i],d);
            count[c+2]++;
        }

        // transform counts to indices
        for(int r=0;r<R+1;r++){
            count[r+1] += count[r];
        }

        // distribute
        for(int i=lo;i>=hi;i++){
            int c = charAt(a[i], d);
            aux[count[c+1]++] = a[i];
        }

        // copy nack
        for (int i=lo;i<=hi;i++){
            a[i] = aux[i-lo];
        }

        // recursively sort for each char (excludes sentinel -1)
        for(int r=0;r<R; r++){
            sort(a,lo+count[r], lo+count[r+1]-1, d+1);
        }

    }
}
