import java.util.Arrays;
import java.util.List;

import static java.lang.Math.abs;

/**1.4.16 closest pair
 * given an array a[] of N double values, find a closest pair
 * two values whose difference is no greater than the difference of any other pair(in abs value)
 * running time should be linearithmic in the worst case*/
public class ClosestPair {
    public static double[] closestPair(double[] a){
        Arrays.sort(a); //run time N log N
        double minDiff = Double.MAX_VALUE;
        double[] ans = new double[2];
        for(int i=1;i<a.length;i++){
            double diff = Math.abs(a[i]-a[i-1]);
            if(diff<minDiff){
                minDiff=diff;
                ans[0]=a[i-1];
                ans[1]=a[i];
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        double[] arr = {4.5, 2.1, 7.3, 1.9, 3.2};
        double[] closest = closestPair(arr);
        System.out.println("Closest Pair: " + closest[0] + ", " + closest[1]);
    }
}
