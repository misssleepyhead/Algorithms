package Lecture7;

import java.util.Random;
/**interview question 2, version 1, second approach*/
public class QuickSelect {
    private static final Random random = new Random();

    public static int quickSelect(int[] arr, int k) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int pivotIndex = partition(arr, low, high);
            if (pivotIndex == k) return arr[pivotIndex];
            else if (pivotIndex < k) low = pivotIndex + 1; //search right
            else high = pivotIndex - 1; //search left
        }
        return -1;
    }

    private static int partition(int[] arr, int low, int high) {
        int pivotIndex = low + random.nextInt(high - low + 1);
        int pivot = arr[pivotIndex];

        swap(arr, pivotIndex, high); // move pivot to the end

        int i = low;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, high);
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {7, 10, 4, 3, 20, 15};
        int k = 3; // Find the 3rd smallest element
        System.out.println("The " + k + "th smallest element is: " + quickSelect(arr, k - 1));
    }
}
