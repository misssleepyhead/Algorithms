package Lecture7;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Random;

/**
 * Interview question1: nuts and bolts
 * Nuts and bolts. A disorganized carpenter has a mixed pile of
 * n nuts and n bolts. The goal is to find the corresponding pairs of nuts and bolts. Each nut fits exactly one bolt
 * and each bolt fits exactly one nut. By fitting a nut and a bolt together, the carpenter can see which one is bigger
 * (but the carpenter cannot compare two nuts or two bolts directly). Design an algorithm for the problem that uses at most proportional to
 * nlogn compares (probabilistically).
 * <p>
 * Observations:
 * 1. Each nut exactly has one matching bolt, so no duplicate keys
 * 2. Since 2 bolts/nuts can't compare each other, we use pivot nut to partition bolts, vice versa
 */
public class Carpenter {

    public static void matchNutsAndBolts(int[] nuts, int[] bolts) {
        StdRandom.shuffle(nuts);
        StdRandom.shuffle(bolts);
        quickSort(nuts, bolts, 0, nuts.length - 1);
    }


    private static void quickSort(int[] nuts, int[] bolts, int low, int high) {
        if (low < high) {
//            // randomly select a pivot for nuts
//            Random random = new Random();
//            int pivotIndex = low + random.nextInt(high-low+1);
//            swap(nuts,pivotIndex, high);

            // use nut pivot to partition bolt, and get the matching bolt as next pivot
            int pivotIndex = partition(bolts, low,high,nuts[high]);
            partition(nuts, low,high,bolts[pivotIndex]);

            // recursively process left and right partitions
            quickSort(nuts, bolts,low,pivotIndex-1);
            quickSort(nuts,bolts,pivotIndex+1,high);
        }
    }

    private static int partition(int[] arr, int lo, int hi, int pivot) {
        int i = lo;
        for (int j = lo; j < hi; j++) {
            if (arr[j] < pivot) {
                swap(arr, i, j);
                i++;
            } else if (arr[j] == pivot) {
                swap(arr, j, hi); // move pivot to end
                j--;  // recheck this position
            }
        }
        swap(arr, i, hi); // place pivot in correct position
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void main(String[] args) {
        int[] nuts = {4, 2, 7, 1, 3, 6, 5};
        int[] bolts = {5, 1, 4, 2, 6, 3, 7};

        matchNutsAndBolts(nuts, bolts);

        System.out.println("Matched Nuts:  " + java.util.Arrays.toString(nuts));
        System.out.println("Matched Bolts: " + java.util.Arrays.toString(bolts));
    }
}
