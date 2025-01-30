
/**
 * Search in a bitonic array. An array is bitonic if it is comprised of
 * an increasing sequence of integers followed immediately by a decreasing
 * sequence of integers
 */
public class BitonicBinarySearch {


    /**
     * Standard version, use ~ 3 lg n comparison in the worst case
     */
    public static int standardSearch(int[] nums, int target) {
        // step 1: Search the largest element using binary search
        int peak = findPeak(nums, 0, nums.length - 1);
        // step 2: Cut the array into two parts, one is increasing and another is decreasing
        // step 3: Binary search on each part, increasing part> normal bs
        int result = bsIncreasing(nums, 0,peak,target);
        if (result!=-1){
            return result;
        }
        // decreasing part> bs on a decreasing sequence
        return bsDecreasing(nums, peak+1, nums.length-1, target);
    }

    // 1. find the peach index (maximum element in the array
    // passing lo and hi for flexible
    private static int findPeak(int[] nums, int lo, int hi) {
        while (lo < hi) { // using lo< hi, cause loop stops when lo==hi
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] < nums[mid + 1]) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    // binary search on increasing part
    private static int bsIncreasing(int[] nums, int lo, int hi, int target) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return -1;
    }

    // binary search on decreasing part
    private static  int bsDecreasing(int[] nums, int lo, int hi, int target){
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(nums[mid]>target){
                lo = mid+1;
            } else if (nums[mid]<target) {
                hi=mid-1;
            }else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 12, 9, 5, 2}; // Bitonic array
        int target = 9;
        int index = standardSearch(arr, target);
        System.out.println("Target found at index: " + index);
    }
}
