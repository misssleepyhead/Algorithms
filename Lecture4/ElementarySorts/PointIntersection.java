package ElementarySorts;

import edu.princeton.cs.algs4.In;

import java.util.Arrays;

/**
 * Interview Question 1
 * Given two arrays a, b, each containing n distinct 2D points in the plane
 * Design a subquadratic algorithm to count the number of points that are contained both in array
 */
public class PointIntersection {
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

    public static void main(String[] args) {
        int[][] a = {{1, 2}, {3, 4}, {5, 6}};
        int[][] b = {{3, 4}, {1, 2}, {7, 8}};

        System.out.println("Intersection count: " + intersection(a, b)); // Output: 2
    }
}
