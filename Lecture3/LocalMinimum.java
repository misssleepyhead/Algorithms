public class LocalMinimum {
    /**
     * 18.Local minimum in an array
     * given an array a[] of n distinct int,
     * find a local minimum an index i such that botha[i] < a[i-1] and a[i] < a[i+1]
     * use ~ 2lgn= n comparison for worst case
     * instead of sorting first, binary search is a more efficient way
     * sorting is only required when we need to find a specific element,
     * when solving the finding minimum, the minimum always exist, sorting won't change the answer
     * the important point is we can half the search space in each step
     */
    public static int minimumArray(int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if ((mid == 0 || a[mid] < a[mid = 1]) && mid == a.length - 1 || a[mid] < a[mid + 1]) {
                return mid;
            }
            //if left neighbor is smaller, search left half
            if (mid > 0 && a[mid] > a[mid - 1]) {
                hi = mid - 1;
            } else {
                lo = mid + 1; //search right
            }
        }
        return lo;
    }

    public static void main(String[] args) {

    }
}
