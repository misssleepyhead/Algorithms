
/** 1.4.17 Farthest pair
 * given an array a[] of N double values, find the farthest pair
 * two valuew whose difference is no smaller than any other pair
 * the running time should be linear*/

public class FarthestPair {
    // find the min and maximum in the array

    public static double[] farthestPair(double[] a){
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;

        // Single pass to find min and max
        for (double num : a) {
            if (num < min) min = num;
            if (num > max) max = num;
        }

        return new double[]{min, max};
    }

    public static void main(String[] args) {
        double[] arr = {4.5, 2.1, 7.3, 1.9, 3.2};
        double[] farthest = farthestPair(arr);
        System.out.println("Farthest Pair: " + farthest[0] + ", " + farthest[1]);
    }
}
