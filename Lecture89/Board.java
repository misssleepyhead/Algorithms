/**
 * Slider puzzle
 * 8-puzzle is a sliding puzzle on a 3 by 3 grid with 8 tiles from 1 to 8
 * the goal is to arrange the tiles so that they are in row-major order,
 * use as few as moves as possible. Tiles can be moved either horizontally or vertically into
 * blank square.
 */

public class Board {
    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    int n;
    int[][] tiles;
    int[][] goal;

    public Board(int[][] tiles) {
        n = tiles.length;
        this.tiles = new int[n][n];
        this.goal = new int[n][n];
        int correct = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.tiles[i][j] = tiles[i][j];
                // Fill goal with correct numbers
                if (i == n - 1 && j == n - 1) {
                    this.goal[i][j] = 0;  // last cell is blank
                } else {
                    this.goal[i][j] = correct++;
                }
            }
        }
    }

    // string representation of this board, returns a string composed of n + 1 lines.
    // The first line contains the board size n; the remaining n lines
    // contains the n-by-n grid of tiles in row-major order, using 0 to designate the blank square.
    public String toString() {
        StringBuilder s = new StringBuilder(String.valueOf(n));
        s.append("\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                String number = String.valueOf(tiles[i][j]);
                s.append(number).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    // the hamming distance between a board and the goal board is the number of tiles in the wrong position
    public int hamming() {
        int wrongTile = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int tile = tiles[i][j];
                int goalTile = goal[i][j];
                if (tile != goalTile) {
                    wrongTile++;
                }
            }
        }
        return wrongTile;
    }

    // sum of Manhattan distances between tiles and goal
    // The Manhattan distance formula is : abs(currentRow - goalRow) + abs(currentCol - goalCol)
    // goalRow = (val - 1) / n;
    //goalCol = (val - 1) % n;
    public int manhattan() {
        int distance = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int tile = tiles[i][j];
                int goalTile = goal[i][j];
                if (tile != 0 && tile != goalTile) {
                    int goalRow = (tile - 1) / n;
                    int goalCol = (tile - 1) % n;
                    distance += Math.abs(i - goalRow) + Math.abs(j - goalCol);
                }
            }
        }
        return distance;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return manhattan() == 0 && hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (this == y) return true;
        if (y == null || this.getClass() != y.getClass()) return false;
        Board that = (Board) y;
        if (this.dimension() != that.dimension()) return false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.tiles[i][j] != that.tiles[i][j]) return false;
            }
        }
        return true;
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
