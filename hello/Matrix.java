/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class Matrix {
    /** 1.1.33 a library Matrix implements following APIs*/

    /**
     * dot, return dot product
     * x,y should have same size
     * for example (2.0,3.0)(2.0,4.0) = (4.0,12.0)
     */
    public static double[] dot(double[] x, double[] y) {
        double[] product = new double[y.length];
        for (int i = 0; i < y.length; i++) {
            product[i] = x[i] * y[i];
        }
        return product;
    }

    public static void main(String[] args) {

    }
}
