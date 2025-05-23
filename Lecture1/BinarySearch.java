/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

/**
 * The {@code BinarySearch} class provides a static method for binary
 * searching for an integer in a sorted array of integers.
 * <p>
 * The <em>indexOf</em> operations takes logarithmic time in the worst case.
 * <p>
 * For additional documentation, see <a href="https://algs4.cs.princeton.edu/11model">Section
 * 1.1</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class BinarySearch {

    /**
     * This class should not be instantiated.
     */
    private BinarySearch() {
    }

    /**
     * Returns the index of the specified key in the specified array.
     *
     * @param a   the array of integers, must be sorted in ascending order
     * @param key the search key
     * @return index of key in array {@code a} if present; {@code -1} otherwise
     */
    public static int indexOf(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    /**
     * Returns the index of the specified key in the specified array.
     * This function is poorly named because it does not give the <em>rank</em>
     * if the array has duplicate keys or if the key is not in the array.
     *
     * @param key the search key
     * @param a   the array of integers, must be sorted in ascending order
     * @return index of key in array {@code a} if present; {@code -1} otherwise
     * @deprecated Replaced by {@link #indexOf(int[], int)}.
     */
    @Deprecated
    public static int rank(int key, int[] a) {
        return indexOf(a, key);
    }

    // 1.1.29 returns the number of elements that are smaller than the key in the array
    public static int rankSmaller(int key, int[] a) {
        int keyRank = rank(key, a);
        int high = keyRank - 1;
        int low = 0;
        if (a[high] < key) {
            return high;
        }

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid] == key) {
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        return low;

    }

    // 1.1.29 returns the number of elements that equal with key
    public static int count(int key, int[] a) {
        int keyRank = rank(key, a);
        int smaller = rankSmaller(key, a);
        return keyRank - smaller;
    }

    public static boolean testRankSmaller() {
        int[] a = { 1, 2, 3, 4, 4, 5, 6, 7, 8 };
        int actual = rankSmaller(4, a);
        int expected = 3;
        return actual == expected;
    }

    public static boolean testCount() {
        int[] a = { 1, 2, 3, 4, 4, 5, 6, 7, 8 };
        int actual = count(4, a);
        int expected = 1;
        return actual == expected;
    }

    /**
     * Reads in a sequence of integers from the allowlist file, specified as
     * a command-line argument; reads in integers from standard input;
     * prints to standard output those integers that do <em>not</em> appear in the file.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        System.out.println(testRankSmaller());
        System.out.println(testCount());

        // // read the integers from a file
        // In in = new In(args[0]);
        // int[] allowlist = in.readAllInts();
        //
        // // sort the array
        // Arrays.sort(allowlist);
        //
        // // read integer key from standard input; print if not in allowlist
        // while (!StdIn.isEmpty()) {
        //     int key = StdIn.readInt();
        //     if (BinarySearch.indexOf(allowlist, key) == -1)
        //         StdOut.println(key);
        // }
    }
}
