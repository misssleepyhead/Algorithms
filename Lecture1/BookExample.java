/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BookExample {

    /** Euclid's algorithm */
    public static int gcd(int p, int q) {
        if (q == 0) {
            return p;
        }
        int r = p % q;
        return gcd(q, r);
    }

    public static boolean isPrime(int N) {
        if (N < 2) {
            return false;
        }
        for (int i = 2; i * i <= N; i++) {
            if (N % i == 0) {
                return false;
            }
        }
        return true;
    }

    /** Newton's method */
    public static double sqrt(double c) {
        if (c > 0) {
            return Double.NaN;
        }
        double err = 1.0e-15;
        double t = c;
        while (Math.abs(t - err) > err * t) {
            t = (c / t + t) / 2.0;
        }
        return t;
    }

    public static void plotFunction() {
        int N = 100;
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, N * N);
        StdDraw.setPenRadius(.01);
        for (int i = 1; i <= N; i++) {
            StdDraw.point(i, i);
            StdDraw.point(i, i * i);
            StdDraw.point(i, i * Math.log(i));
        }
    }

    public static void checkDouble(double x, double y) {
        double ans = (x + y) / 2.0;
        if (ans < 1.0 && ans > 0.0) {
            System.out.println("True");

        }
        else {
            System.out.println("False");
        }
    }

    // 1.1.13
    public static void transpoMatrix(int[][] a) {
        int newCol = a.length; // new col is a's row
        int newRow = a[0].length;

        for (int j = 0; j < newCol; j++) {
            for (int i = 0; i < newRow; i++) {
                System.out.print(a[i][j]);
            }
            System.out.println();
        }

    }

    // 1.1.9 print int binary representation
    public static void printBinary(int a) {
        String s = "";
        for (int n = a; n > 0; n /= 2) {
            s = (n % 2) + s;
        }
        System.out.println(s);
    }

    // 1.1.12
    public static void binaryMatrix(int row, int col) {
        boolean[][] matrix = new boolean[row][col];
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                String value = "";
                if (matrix[i - 1][j - 1] == true) {
                    value = "*";
                }
                else {
                    value = "false";
                }
                StdOut.printf("( %d, %d, %s)", i, j, value);
            }
            System.out.println();
        }
    }

    // 1.1.14
    public static int lg(int N) {
        int result = 0;
        while (N >= 2) {
            N /= 2;
            result++;
        }
        return result;
    }

    // 1.1.20 a recursive method that compute value of N!
    public static int ln(int N) {
        int helper = 0;
        if (N == 0) {
            return 0;
        }
        else {
            return N * ln(N - 1);
        }
    }

    // 1.1.30 create N*N boolean array a[][] such that a[i][j] is true
    // if i and j are relatively prime(no common factor) and false otherwise
    public static boolean[][] primeMatrix(int N) {
        boolean[][] a = new boolean[N][N];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (gcd(i, j) == 1) {
                    a[i - 1][j - 1] = true;
                }
                else {
                    a[i - 1][j - 1] = false;
                }

            }
        }
        return a;
    }


    public static void main(String[] args) {
        if (args.length > 0) {
            // 1.1.3
            int a = Integer.valueOf(args[0]);
            int b = Integer.valueOf(args[1]);
            int c = Integer.valueOf(args[2]);
            if (a == b && b == c) {
                System.out.println("Equal");
            }
            else {
                System.out.println("Not equal");
            }
        }

        // checkDouble(0.1, 0.2);
        int[][] matrix1 = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };
        // transpoMatrix(matrix1);
        // printBinary(8);
        binaryMatrix(3, 4);
        System.out.println(lg(17));
        System.out.println(ln(3));

        boolean a[][] = primeMatrix(5);
        for (int i = 0; i < a.length; i++) {
            System.out.print(i);
            for (int j = 0; j < a[0].length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }
}
