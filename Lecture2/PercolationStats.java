import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Monte Carlo simulation estimate percolation
 */
public class PercolationStats {
    private int trials;
    private int totalSites;
    private final int side;
    private final double[] thresholds; // Array to store percolation thresholds for each trial

    private double mean;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;

    private double confidence95 = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {

        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Grid side and trials cannot be 0");
        }
        this.side = n;
        this.trials = trials;
        this.totalSites = n * n;
        this.thresholds = new double[trials];
        simulation();
        // Calculate statistics
        this.mean = StdStats.mean(thresholds);
        this.stddev = StdStats.stddev(thresholds);
        this.confidenceLo = mean - (confidence95 * stddev) / Math.sqrt(trials);
        this.confidenceHi = mean + (confidence95 * stddev) / Math.sqrt(trials);
    }

    private void simulation() {
        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(side);
            while (!perc.percolates()) {
                int row = StdRandom.uniformInt(1, side + 1);
                int col = StdRandom.uniformInt(1, side + 1);
                perc.open(row, col);

            }
            thresholds[i] = (double) perc.numberOfOpenSites() / totalSites;
        }

    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return confidenceLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return confidenceHi;
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats newPercStats = new PercolationStats(n, trials);
        StdOut.println("mean                    = " + newPercStats.mean());
        StdOut.println("stddev                  = " + newPercStats.stddev());
        StdOut.println("95% confidence interval = ["
                + newPercStats.confidenceLo() + ", "
                + newPercStats.confidenceHi() + "]");
    }

}