
/**
 * 23. Binary search with duplicates.
 * Modify binary search so that it always returns the smallest (largest)
 * index of a key of an item matching the search key.
 */
public class BSduplicates {
    public static int searchDuplicatesSmallest(int[] arr, int target) {
        int lo = 0;
        int hi = arr.length - 1;
        int result = -1; // store the index of the first occurrence

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] == target) {
                // Instead of returning immediately,store the index and continue searching in the left half.
                result = mid;
                hi = mid - 1;
            } else if (arr[mid] > target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return result;
    }

    public static int searchDuplicatesLargest(int[] arr, int target) {
        int lo = 0;
        int hi = arr.length - 1;
        int result = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] == target) {
                result = mid;  // Store the current index
                lo = mid + 1;  // Search in the right half for a later occurrence
            } else if (arr[mid] > target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return result;

    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 2, 3, 4, 5};

        System.out.println(searchDuplicatesSmallest(arr, 2)); // Output: 1 (First occurrence)
        System.out.println(searchDuplicatesLargest(arr, 2)); // Output: 3 (Last occurrence)
    }
}
