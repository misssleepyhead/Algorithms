import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
/** Assignment
 * Using weighted quick union algorithm to implement percolation*/

public class Percolation {
    private int side ; // N * N grid
    private boolean[][] grid;
    private final int VIRTUAL_TOP;
    private final int VIRTUAL_BOTTOM;

    private int openSite;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        openSite=0;
        this.side= n;
        grid = new boolean[n][n]; // default false means blocked
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){}

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){}

    // returns the number of open sites
    public int numberOfOpenSites(){
        return openSite;
    }

    // does the system percolate?
    public boolean percolates(){}

    // test client (optional){}
    public static void main(String[] args){}
}