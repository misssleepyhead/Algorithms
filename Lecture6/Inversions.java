
/**
 * Creative problems
 * 19. Inversions, implement a linearithmic algorithm Inversions.java for computing the number of inversions in a given array
 * (the number of exchanges that would be performed by insertion sort for that array
 * <p>
 * solution:
 * 1. count inversions while sorting the array using Merge sort, which is more efficient than count linear
 * example: left subarray[2,4] right[1,4,5]
 * compare 2 and 1, 1 is smaller, 2,4 are both larger so 2 inversions
 * compare 2,3, no inversion
 * compare 4 , 3 -> 4 is grater, so 1 inversion
 */
public class Inversions {
    public Inversions() {
    }

    public static int countInversions(int[] arr) {
        int[] aux = new int[arr.length];
        return mergeSortAndCount(arr, aux, 0, arr.length - 1);
    }

    private static int mergeSortAndCount(int[] arr, int[] aux, int lo, int hi) {
        if (lo >= hi) return 0;

        int mid = lo + (hi - lo) / 2;
        int count = 0;

        //count inversions in left and right halves
        count += mergeSortAndCount(arr, aux, lo, mid);
        count += mergeSortAndCount(arr, aux, mid + 1, hi);

        //merge two halves and count cross inversions
        count += mergeAncCount(arr, aux, lo, mid, hi);
        return count;

    }


    private static int mergeAncCount(int[] arr, int[] aux, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;
        int k = lo;
        int count = 0;

        while (i <= mid && j <= hi) {
            if (arr[i] <= arr[j]) {
                aux[k++] = arr[i++];
            } else {
                aux[k++] = aux[j++];
                count += (mid - i + 1); // count inversions
            }
        }

        // count remaining elements
        while (i <= mid) {
            aux[k++] = arr[i++];
        }

        while (j <= hi) {
            aux[k++] = arr[j++];
        }

        System.arraycopy(aux, lo, arr, lo, hi - lo + 1);
        return count;
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 3, 1, 5};
        System.out.println("Number of inversions: " + countInversions(arr));
    }
}
