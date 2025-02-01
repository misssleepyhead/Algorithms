/**
 * 22. Binary search with only addition and subtraction.
 * given an array of n distinct integers in ascending order,
 * determines whether a given integer is in the array.
 * You may use only additions and subtractions and a constant amount of extra memory.
 * The running time of your program should be proportional to log n in the worst case.
 * <p>
 * since only addition and subtraction, we can use power of 2 or fibonacci number
 * a constant amount of memory means no recursion
 */

public class BSAddSubOnly {
    // not the right answer, but can use the power of 2 if divide is not allowed
    public static int powerOf2Search(int[] arr, int target) {
        int lo = 0;
        int hi = arr.length - 1;
        while (lo <= hi) {
            int mid = lo;
            int step = 1;

            // step *=2 is to simulate "jumping forward in powers of 2" instead of dividing
            while (mid + step <= hi) {
                mid += step;
                step *= 2;
            }

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }


    // correct solution: use fibonacci number to solve
    public static int fibonacciSearch(int[] arr, int target) {
        int n = arr.length;

        // step 1: find the smallest fibonacci number >= n
        int F2 = 0; //F(k-2)
        int F1 = 1; //F(k-1)
        int F = F2 + F1; // F(k)  next Fibonacci number

        // while loop before search to ensure we find the smallest fibonacci number Fk
        // such that Fk >= n
        while (F < n) {
            F2 = F1;
            F1 = F;
            F = F1 + F2;
        }

        int offset = -1;

        while (F > 1) {
            int i = Math.min(offset + F2, n - 1);

            if (arr[i] == target) {
                return i;
            } else if (arr[i] < target) {
                // move right
                offset = i;
                F = F1;
                F1 = F2;
                F2 = F - F1;
            } else {
                // Move left
                F = F2;
                F1 = F1 - F2;
                F2 = F - F1;
            }
        }

        //Check last remaining element
        // The Fibonacci search does not always check the last element explicitly
        if (F1 == 1 && arr[offset + 1] == target) {
            return offset + 1;
        }
        return -1;

    }
}
