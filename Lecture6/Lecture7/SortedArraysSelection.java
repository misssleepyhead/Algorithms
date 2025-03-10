package Lecture7;

/**
 * Interview question 2 selection in two sorted arrays
 * given two sorted array a[], b[], length is n1,n2, and an int where 0<=k<=n1+n2, design an algorithm to find a key of rank k,
 * the order of growth runtime worst case is log n, where n = n1+n2
 * version 1: n1=n2, k=n/2
 * <p>
 * Solution:
 * 1. since arrays are sorted, we can use binary search based algorithm
 */
public class SortedArraysSelection {

    public static int findKthElement(int[] a, int[] b, int k) {
        return findKth(a, 0, a.length, b, 0, b.length, k);

    }

    private static int findKth(int[] a, int aStart, int aLen, int[] b, int bStart, int bLen, int k) {
        // always perform the bs on the smaller array first
        if (aLen > bLen) {
            return findKth(b, bStart, bLen, a, aStart, aLen, k);
        }

        // edge case
        if (aLen == 0) return b[bStart + k - 1];
        if (k == 1) return Math.min(a[aStart], b[bStart]);

        // partitions how many elements to search on each array
        int i = Math.min(aLen, k / 2);
        int j = k - i;

        // compare the median, use binary search to adjust the search range
        if (a[aStart + i - 1] < b[bStart + j - 1]) {
            return findKth(a, aStart + i, aLen - i, b, bStart, bLen, k - i);
        } else {
            return findKth(a, aStart, aLen, b, bStart + j, bLen - j, k - j);
        }

    }

    public static void main(String[] args) {
        int[] a = {1, 3, 5, 7, 9};
        int[] b = {2, 4, 6, 8, 10};

        int k = 5; // Finding the 5th smallest element (median)
        System.out.println("The " + k + "th smallest element is: " + findKthElement(a, b, k));
    }
}
