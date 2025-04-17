import java.util.ArrayList;
import java.util.List;

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
    private int n;
    private int[][] tiles;
//    private int[][] goal;  remove goal variable to save memory space

    public Board(int[][] tiles) {
        n = tiles.length;
        this.tiles = new int[n][n];
//        this.goal = new int[n][n];
        int correct = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.tiles[i][j] = tiles[i][j];
                // Fill goal with correct numbers
//                if (i == n - 1 && j == n - 1) {
//                    this.goal[i][j] = 0;  // last cell is blank
//                } else {
//                    this.goal[i][j] = correct++;
//                }
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
        int correctTile = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int tile = tiles[i][j];
//                int goalTile = goal[i][j];
                if (tile != correctTile && tile != 0) {
                    wrongTile++;
                }
                correctTile++;
            }
        }
        return wrongTile;
    }

    // sum of Manhattan distances between tiles and goal
    // The Manhattan distance formula is : abs(currentRow - goalRow) + abs(currentCol - goalCol)
    // goalRow = (val - 1) / n;
    // goalCol = (val - 1) % n;
    public int manhattan() {
        int distance = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int tile = tiles[i][j];
//                int goalTile = goal[i][j];
                if (tile != 0) {
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
    // return a list of board contains all the possible movement results of boards
    public Iterable<Board> neighbors() {
        List<Board> neighbors = new ArrayList<>();

        // find the empty tile
        int blankRow = 0, blankCol = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] == 0) {
                    blankRow = i;
                    blankCol = j;
                    break;
                }
            }
        }


        // try 4 directions: up,down,left,right
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] d : dir) {
            int newRow = blankRow + d[0];
            int newCol = blankCol + d[1];

            if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n) {
                // copy the current tiles
                int[][] newBoard = new int[n][n];
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        newBoard[i][j] = tiles[i][j];
                    }
                }

                newBoard[blankRow][blankCol] = newBoard[newRow][newCol];
                newBoard[newRow][newCol] = 0;
                neighbors.add(new Board(newBoard));
            }
        }
        return neighbors;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] copy = new int[n][n];
        // deep copy tiles
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j] = tiles[i][j];
            }
        }
        // swap tiles
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (copy[i][j] != 0 && copy[i][j + 1] != 0) {
                    swap(copy, new int[]{i, j}, new int[]{i, j + 1});
                    return new Board(copy);
                }
            }
        }
        return null;

    }

    private void swap(int[][] b, int[] first, int[] second) {
        int helper = b[first[0]][first[1]];
        b[first[0]][first[1]] = b[second[0]][second[1]];
        b[second[0]][second[1]] = helper;

    }

    // unit testing (not graded)
    public static void main(String[] args) {
        int[][] tiles = {{1, 0, 3}, {4, 2, 5}, {7, 8, 6}};
        Board board = new Board(tiles);
        System.out.println(board);
        System.out.println(board.isGoal());
        System.out.println("Neighbors:");
        for (Board neighbor : board.neighbors()) {
            System.out.println(neighbor);
        }

        // test hamming()
        int[][] tiles2 = {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
        Board b2 = new Board(tiles2);
        System.out.println(b2.hamming());
    }
}
