/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import java.util.Arrays;

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

    /** Implement matrix-matrix multiplication */
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

    /** Transpose */
    public static double[][] transpoMatrix(double[][] a) {
        int newCol = a.length; // new col is a's row
        int newRow = a[0].length;
        double[][] tran = new double[newRow][newCol];

        for (int i = 0; i < newRow; i++) {
            for (int j = 0; j < newCol; j++) {
                tran[i][j] = a[j][i];
            }
        }
        return tran;

    }

    /** matrix-vector product */
    public static double[] mult(double[][] a, double[] x) {
        if (a[0].length != x.length) {
            throw new IllegalArgumentException(
                    "The column number  of a and row number of b must be the same");
        }
        int row = a.length;
        int col = x.length;
        double[] product = new double[row];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                product[i] += a[i][j] * x[j];
            }
        }
        return product;
    }

    /** vector-matrix product */
    public static double[] mult(double[] x, double[][] a) {
        if (a.length != x.length) {
            throw new IllegalArgumentException(
                    "The column number  of vector and row number of matrix must be the same");
        }

        int row = x.length;
        int col = a[0].length;
        double[] product = new double[col];
        for (int j = 0; j < col; j++) {         // Iterate over columns of matrix a
            for (int i = 0; i < row; i++) {     // Iterate over rows of matrix a
                product[j] += x[i] * a[i][j];   // Sum the products for the j-th result
            }
        }
        return product;
    }

    public static void printMatrix(double[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        double[][] a = {
                { 1, 2, 3 },
                { 4, 5, 6 }
        };
        double[][] tran = transpoMatrix(a);
        printMatrix(tran);
        double[][] b = {
                { 7, 8 },
                { 9, 10 },
                { 11, 12 }
        };
        double[][] tran1 = multi(a, b);
        printMatrix(tran1);

        double[] x = { 1, 1, 1 };
        double[] result = mult(a, x);
        System.out.println(Arrays.toString(result));

        double[] x1 = { 1, 1 };
        double[] result1 = mult(x1, a);
        System.out.println(Arrays.toString(result1));

    }
}
