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

    /** Implement matrix multiplication */
    public static double[][] multi(double[][] a, double[][] b) {
        int row = a.length;
        int col = b[0].length;
        if (a[0].length != b.length) {
            throw new IllegalArgumentException(
                    "The column number  of a and row number of b must be the same");
        }
        double[][] product = new double[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                for (int k = 0; k < a[0].length; k++) {     // Iterate over the shared dimension
                    product[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return product;
    }

    public static void main(String[] args) {

    }
}
