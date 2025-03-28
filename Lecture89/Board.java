public class Board {
    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    int n;
    int[][] tiles;

    public Board(int[][] tiles) {
        n = tiles.length;
        this.tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    // string representation of this board, returns a string composed of n + 1 lines.
    // The first line contains the board size n; the remaining n lines
    // contains the n-by-n grid of tiles in row-major order, using 0 to designate the blank square.
    public String toString() {
        StringBuilder s = new StringBuilder(String.valueOf(n));
        for (int i = 0; i < n; i++) {
            s.append("/n");
            for (int j = 0; j < tiles[0].length; j++) {
                String number = String.valueOf(tiles[i][j]);
                s.append(number);
            }
        }
        return s.toString();
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        return 0;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        return 0;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return false;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        return false;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        return null;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        return null;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
    }
}
