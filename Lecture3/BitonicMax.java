import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
/**20. Bitonic search*/
public class BitonicMax {

    // create a bitonic array of size N
    public static int[] bitonic(int N) {
        int mid = StdRandom.uniformInt(N);
        int[] a = new int[N];
        for (int i = 1; i < mid; i++) {
            a[i] = a[i-1] + 1 + StdRandom.uniformInt(9);
        }

        if (mid > 0) a[mid] = a[mid-1] + StdRandom.uniformInt(10) - 5;

        for (int i = mid + 1; i < N; i++) {
            a[i] = a[i-1] - 1 - StdRandom.uniformInt(9);
        }

        for (int i = 0; i < N; i++) {
            StdOut.println(a[i]);
        }
        return a;
    }

    // find the index of the maximum in a bitonic subarray a[lo..hi]
    public static int max(int[] a, int lo, int hi) {
        if (hi == lo) return hi;
        int mid = lo + (hi - lo) / 2;
        if (a[mid] < a[mid + 1]) return max(a, mid+1, hi);
        if (a[mid] > a[mid + 1]) return max(a, lo, mid);
        else return mid;
    }



    public static void main(String[] args) {

        int N = Integer.parseInt(args[0]);
        int[] a = bitonic(N);
        StdOut.println("max = " + a[max(a, 0, N-1)]);
    }
}
