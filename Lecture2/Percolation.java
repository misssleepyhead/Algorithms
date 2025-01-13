import edu.princeton.cs.algs4.WeightedQuickUnionUF;


/**
 * Assignment
 * Using weighted quick union algorithm to implement percolation
 */

public class Percolation {
    private int side; // N * N grid
    private boolean[][] grid;

    private WeightedQuickUnionUF connectedSites;

    private final int virtualTop;
    private final int virtualBottom;

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
        virtualTop = n * n;
        virtualBottom = n * n + 1;
        init();


    }

    /**
     * Connect the first row to virtualTop and the bottom row to virtualBottom
     */
    private void init() {
        /* Connect all first row to top*/
        for (int i = 0; i < side; i++) {
            connectedSites.union(i, virtualTop);
        }

        /* Connect all bottom row to the virtual bottom*/
        for (int i = side * (side - 1); i < side * side; i++) {
            connectedSites.union(i, virtualBottom);
        }
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
            connectedSites.union(virtualTop, index);
        } else if (row == side) {
            connectedSites.union(virtualBottom, index);
        }

        //connect to open neighbors
        int[] openNeighbors = checkNeighbors(row, col);
        for (int i = 0; i < openNeighbors.length; i++) {
            connectedSites.union(index, openNeighbors[i]);
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
        return isOpen(row, col) && connectedSites.find(virtualTop) == connectedSites.find(convert2D(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSite;
    }

    // does the system percolate?
    public boolean percolates() {
        if (side != 1) {
            return connectedSites.find(virtualTop) == connectedSites.find(virtualBottom);
        } else {
            return grid[0][0];
        }
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
    private int[] checkNeighbors(int row, int col) {
        int[] openNeighbors = new int[4];
        int count = 0;
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
                openNeighbors[count++] = convert2D(newRow, newCol);
            }

        }
        // Trim the array to include only valid neighbors
        int[] result = new int[count];
        System.arraycopy(openNeighbors, 0, result, 0, count);
        return result;

    }

    // test client (optional){}
    public static void main(String[] args) {
        Percolation p = new Percolation(1);
        p.open(1, 1);
        System.out.println(p.percolates());
        System.out.println(p.isFull(1, 1));
//        p.open(1, 2);
//        p.open(5, 5);
//        p.open(2, 1);
//        System.out.println(p.numberOfOpenSites()); // 3
//        p.open(2, 2);
//        System.out.println(p.isFull(2, 1)); // true
//        p.open(3, 2);
//        p.open(3, 3);
//        p.open(4, 3);
//        System.out.println(p.percolates()); // false
//        p.open(4, 4);
//        p.open(5, 4);
//        System.out.println(p.isFull(5, 5)); // false
//        System.out.println(p.percolates()); // true
    }
}