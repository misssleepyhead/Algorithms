/**
 * Exercise 1: Frequency counts
 * Read in a list of strings and print out their frequency counts.
 * <p>
 * Note:
 * 1. sort string first then same string will be in the adjacent positions then
 * can compute the frequency.
 */
public class FrequencyCounts {
    public static void sort(String[] a) {
        sort(a, 0, a.length - 1, 0);
    }

    public static void countFrequency(String[] a) {
        sort(a);
        int count = 1;
        for (int i = 1; i < a.length; i++) {
            if (a[i].equals(a[i - 1])) {
                count++;
            } else {
                System.out.println(a[i - 1] + " " + count);
                count = 1;
            }
        }
        System.out.println(a[a.length - 1] + " " + count);
    }


    private static int charAt(String s, int d) {
        if (d < s.length()) return s.charAt(d);
        else return -1;
    }

    private static void exch(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        int v = charAt(a[lo], d);
        int i = lo + 1;

        while (i <= gt) {
            int t = charAt(a[i], d);
            if (t < v) exch(a, lt++, i++);
            else if (t > v) exch(a, i, gt--);
            else i++;
        }
        sort(a, lo, lt - 1, d);
        if (v >= 0) sort(a, lt, gt, d + 1);
        sort(a, gt + 1, hi, d);
    }

    public static void main(String[] args) {
        String[] input =new String[] {"apple","apple","apple","cat","dog","dog"};
        countFrequency(input);

    }
}
