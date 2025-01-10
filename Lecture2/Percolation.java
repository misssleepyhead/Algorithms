import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.ArrayList;
import java.util.List;

/**
 * Assignment
 * Using weighted quick union algorithm to implement percolation
 */

public class Percolation {
    private int side; // N * N grid
    private boolean[][] grid;

    private WeightedQuickUnionUF connectedSites;

    private final int VIRTUAL_TOP;
    private final int VIRTUAL_BOTTOM;

    private int openSite;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Side can not be smaller than 1");
        }
        openSite = 0;
        this.side = n;
        grid = new boolean[n][n]; // default false means blocked
        connectedSites = new WeightedQuickUnionUF(n * n + 2); // grid and virtual top/bottom
        VIRTUAL_TOP = n * n;
        VIRTUAL_BOTTOM = n * n + 1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateIndices(row, col);
        if (isOpen(row, col)) return;

        grid[row - 1][col - 1] = true;
        openSite++;

        int index = convert2D(row, col); // map 2D to 1D index

        // connect to VIRTUAL-TOP/BOTTOM if it is in the top row or bottom row
        if (row == 1) {
            connectedSites.union(VIRTUAL_TOP, index);
        } else if (row == side) {
            connectedSites.union(VIRTUAL_BOTTOM, index);
        }

        //connect to open neighbors
        List<Integer> openNeighbors = checkNeighbors(row, col);
        for (int i = 0; i < openNeighbors.size(); i++) {
            connectedSites.union(index, openNeighbors.get(i));
        }


    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateIndices(row, col);
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full? connected to virtual top and must open too.
    public boolean isFull(int row, int col) {
        validateIndices(row, col);
        return isOpen(row, col) && connectedSites.find(VIRTUAL_TOP)==connectedSites.find(convert2D(row,col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSite;
    }

    // does the system percolate?
    public boolean percolates() {
        return connectedSites.find(VIRTUAL_TOP)==connectedSites.find(VIRTUAL_BOTTOM);
    }

    // helper function: validate col, row value
    private void validateIndices(int row, int col) {
        if (row < 1 || row > side || col < 1 || col > side) {
            throw new IllegalArgumentException("Row and column indices must be between 1 and " + side);
        }

    }

    //helper function: convert row,col to 1D indices
    private int convert2D(int row, int col) {
        return (row - 1) * side + (col - 1);
    }

    // helper function: return the open neighbor of the given indices
    private List<Integer> checkNeighbors(int row, int col) {
        List<Integer> openNeighbors = new ArrayList<>();
        int[][] directions = {
                {-1, 0}, // Top
                {1, 0},  // Bottom
                {0, -1}, // Left
                {0, 1}   // Right
        };

        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if (newRow >= 1 && newRow <= side && newCol <= side && newCol >= 1 && isOpen(newRow, newCol)) {
                openNeighbors.add(convert2D(newRow, newCol));
            }
        }
        return openNeighbors;

    }

    // test client (optional){}
    public static void main(String[] args) {
        Percolation p = new Percolation(5);
        p.open(1, 2);
        p.open(5, 5);
        p.open(2, 1);
        System.out.println(p.numberOfOpenSites()); // 3
        p.open(2, 2);
        System.out.println(p.isFull(2, 1)); //true
        p.open(3, 2);
        p.open(3, 3);
        p.open(4, 3);
        System.out.println(p.percolates());
        p.open(4, 4);
        p.open(5, 4);
        System.out.println(p.isFull(5, 5));
        System.out.println(p.percolates());
    }
}