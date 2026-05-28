/**
 * 5.1 String sort
 */
public class MyLSD {
    public static void sort(String[] a, int w) {
        // sort a[] on leading w characters
        int n = a.length;
        int R = 256;
        String[] aux = new String[w];

        for (int d = w - 1; d >= 0; d--) {
            //sort by key-indexed counting on dth char
            int[] count = new int[R + 1]; //compute frequencies count
            for (int i = 0; i < n; i++) {
                count[a[i].charAt(d) + 1]++;
            }

            // transform count to indices
            for (int r = 0; r < R; r++) {
                count[r + 1] += count[r];
            }

            // distribute
            for (int i = 0; i < n; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }

            // copy back
            for (int i = 0; i < n; i++) {
                a[i] = aux[i];
            }
        }
    }
}
