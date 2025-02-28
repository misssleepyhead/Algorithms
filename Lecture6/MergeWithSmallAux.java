
/**
 * Interview Question 1
 * Merging with smaller auxiliary array. Given Two subarrays, one is a[0] to a[n]
 * another is a[n+1] to a[2n-1], how to merge two subarrays so that a[0] to a[2n-1] is sorted using
 * an aux array of length n instead 2n
 * <p>
 * solutions:
 * 1. Copy one of the two subarrays into the aux array (size n)
 * 2. Merge the aux and the other half to a[]
 */
public class MergeWithSmallAux {
    public static void mergeWithSmallAux(int[] a, int n) {
        int[] aux = new int[n];
        System.arraycopy(a, 0, aux, 0, n); // copy the left halves to aux[]

        int i = 0, j = n, k = 0;

        while (i < n && j < 2 * n) {
            if (aux[i] <= a[j]) {
                a[k++] = aux[i++];
            } else {
                a[k++] = a[j++];
            }

        }

        while (i < n) {
            a[k++] = aux[i++];
        }

        // no need to copy remaining j, as they already in the array a

    }

    public static void main(String[] args) {
        int[] a = {1, 3, 5, 7, 2, 4, 6, 8}; // Two sorted subarrays [1,3,5,7] and [2,4,6,8]
        int n = a.length / 2;

        mergeWithSmallAux(a, n);

        for (int num : a) {
            System.out.print(num + " "); // Expected Output: 1 2 3 4 5 6 7 8
        }
    }
}
