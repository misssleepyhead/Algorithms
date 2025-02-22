package ElementarySorts;

import edu.princeton.cs.algs4.In;

import java.util.Arrays;

/**
 * Interview Question 1
 * Given two arrays a, b, each containing n distinct 2D points in the plane
 * Design a subquadratic algorithm to count the number of points that are contained both in array
 */
public class PointIntersection {
    // first solution, sort array a first, then binary search each element in b to a
    public static int intersection(int[][] a, int[][] b) {
        // sort array a based on (x,y)
        Arrays.sort(a, (p1, p2) -> {
            // compare x first, if x are same then compare y-coordinate
            if (p1[0] == p2[0]) return Integer.compare(p1[1], p2[1]);
            return Integer.compare(p1[0], p2[0]);
        });

        int count = 0;

        // 2. for each point in b, perform binary search in a
        for (int[] point : b) {
            if (binarySearch(a, point)) {
                count++;
            }
        }
        return count;
    }

    private static boolean binarySearch(int[][] arr, int[] target) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid][0] == target[0] && arr[mid][1] == target[1]) {
                return true;
            } else if (arr[mid][0] < target[0] || (arr[mid][0] == target[0] && arr[mid][1] < target[1])) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }

    // solution 2, answer provided by class, use shell sort on two arrays
    public static int countIntersection(int[][] a, int[][] b) {
        shellSort(a);
        shellSort(b);

        int i = 0, j = 0, count = 0;

        // two pointer method to find intersections in o(n)
        while (i < a.length && j < b.length) {
            if (Arrays.equals(a[i], b[j])) {
                count++;
                i++;
                j++;
            } else if (comparePoints(a[i], b[j]) < 0) {
                i++;
            } else {
                j++;
            }
        }
        return count;
    }

    private static void shellSort(int[][] arr) {
        int n = arr.length;

        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && comparePoints(arr[j], arr[j - h]) < 0; j -= h) {
                    exchange(arr, j,j-h);

                }
            }
            h /= 3;
        }
    }

    private static int comparePoints(int[] p1, int[] p2) {
        if (p1[0] == p2[0]) return Integer.compare(p1[1], p2[1]);
        return Integer.compare(p1[0], p2[0]);
    }

    private static void exchange(int[][] arr, int i, int j) {
        int[] temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[][] a = {{1, 2}, {3, 4}, {5, 6}};
        int[][] b = {{3, 4}, {1, 2}, {7, 8}};

        System.out.println("Intersection count: " + countIntersection(a, b)); // Output: 2
    }
}
