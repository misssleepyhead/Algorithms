import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * {@code MyHeap} provides a static method to sort an array using heapsort
 */
public class MyHeap {
    private MyHeap() {
    }

    public static void sort(Comparable[] pq) {
        int n = pq.length;

        // heapify phase
        for (int k = n / 2; k >= 1; k--) {
            sink(pq, k, n);
        }

        // sortdown phase
        int k = n;
        while (k > 1) {
            exch(pq, 1, k--); // swap the lastest item with the root
            sink(pq, 1, k);
        }


    }

    private static void sink(Comparable[] pq, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(pq, j, j + 1)) {
                j++;
            }
            if (!less(pq, k, j)) {
                break;
            }
        }
    }


    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i - 1].compareTo(pq[j - 1]) < 0;
    }

    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i - 1];
        pq[i - 1] = pq[j - 1];
        pq[j - 1] = swap;
    }
    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    /**
     * Reads in a sequence of strings from standard input; heapsorts them;
     * and prints them to standard output in ascending order.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        MyHeap.sort(a);
        show(a);
    }

}
