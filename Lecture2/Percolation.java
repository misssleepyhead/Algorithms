import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
/** Assignment
 * Using weighted quick union algorithm to implement percolation*/

public class Percolation {
    private int side ; // N * N grid
    private boolean[][] grid;

    private WeightedQuickUnionUF connectedSites;

    private final int VIRTUAL_TOP;
    private final int VIRTUAL_BOTTOM;

    private int openSite;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        if(n<=0){
            throw new IllegalArgumentException("Side can not be smaller than 1");
        }
        openSite=0;
        this.side= n;
        grid = new boolean[n][n]; // default false means blocked
        connectedSites=new WeightedQuickUnionUF(n*n+2); // grid and virtual top/bottom
        VIRTUAL_TOP= n*n;
        VIRTUAL_BOTTOM=n*n+1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        validateIndices(row,col);
        if (isOpen(row, col)) return;

        grid[row-1][col-1] = true;
        openSite ++;

        int index = (row-1)*side+(col-1); // map 2D to 1D index

        // connect to VIRTUAL-TOP/BOTTOM if it is in the top row or bottom row
        if(row==1){
            connectedSites.union(VIRTUAL_TOP,index);
        } else if (row==side) {
            connectedSites.union(VIRTUAL_BOTTOM,index);
        }

        //connect to open neighbors



    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        validateIndices(row,col);
        return grid[row-1][col-1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        validateIndices(row,col);
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return openSite;
    }

    // does the system percolate?
    public boolean percolates(){}

    // validate col, row value
    private void validateIndices(int row, int col){
        if (row < 1 || row > side || col < 1 || col > side) {
            throw new IllegalArgumentException("Row and column indices must be between 1 and " + side);
        }

    }
    // test client (optional){}
    public static void main(String[] args){}
}