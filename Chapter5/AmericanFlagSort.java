/**
 * Exercises 4:
 * (in-place key-indexed counting) Given an array with N distinct values between 0 and R-1,
 * rearrange them in ascending order in linear time and with O(R) extra space. Leads to an (essentially) in-place string sort.
 */
public class AmericanFlagSort {
    public void sort(int[] a, int R) {
        int n = a.length;

        // count[r] will become the start index of bin r, fixed bin range
        int[] count = new int[R + 1];

        // frequency count
        for (int i = 0; i < n; i++) {
            int key = a[i];
            count[key + 1]++;
        }

        // transform counts to starting indices
        for (int r = 0; r < R; r++) {
            count[r + 1] += count[r];

        }

        // next[r] = next open position in bin r
        int[] next = count.clone();

        // process each bin
        for (int r = 0; r < R; r++) {
            int i = next[r];

            // bin r is from count[r] to count[r+1]-1
            while (i < count[r + 1]) {
                int key = a[i];
                if (key == r) { // only move forward when the current position is correct
                    // a[i] already belongs in this bin
                    i++;
                    next[r] = i;
                } else {
                    // a[i] belongs in another bin
                    // swap it into the next position fo that bin
                    int destination = next[key];
                    swap(a, i, destination);
                    next[key]++; // update next[]

                    // do not increment i here, because after swap, another unknown new value is now at a[i]
                }
            }
        }
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
