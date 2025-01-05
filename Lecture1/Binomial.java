import edu.princeton.cs.algs4.StdOut;

public class Binomial {

    // slow
    public static double binomial1(int N, int k, double p) {
        if (N == 0 && k == 0) return 1.0;
        if (N < 0 || k < 0) return 0.0;
        return (1.0 - p) * binomial1(N - 1, k, p) + p * binomial1(N - 1, k - 1, p);
    }

    // memoization
    public static double binomial2(int N, int k, double p) {
        double[][] b = new double[N + 1][k + 1];

        // base cases
        for (int i = 0; i <= N; i++)
            b[i][0] = Math.pow(1.0 - p, i);
        b[0][0] = 1.0;

        // recursive formula
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= k; j++) {
                b[i][j] = p * b[i - 1][j - 1] + (1.0 - p) * b[i - 1][j];
            }
        }
        return b[N][k];
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);
        double p = Double.parseDouble(args[2]);
        StdOut.println(binomial1(N, k, p));
        StdOut.println(binomial2(N, k, p));
    }

}