import edu.princeton.cs.algs4.MinPQ;

import java.util.LinkedList;

/**
 * Slider puzzle
 * implement A* search to solve n by n slider puzzles
 *
 * We use A* in the 8-puzzle because it's an efficient and strategic way to find the shortest solution path. A* is a search algorithm that explores nodes in an order determined by a priority function:
 * f(n) = g(n) + h(n)
 * where n is the current board state, g(n) is the actual cost (i.e., number of moves so far), and h(n) is a heuristic — an estimate of the remaining cost to reach the goal.
 *
 * For the heuristic, we use Manhattan distance, because it's both admissible (never overestimates the true cost) and consistent (respects the triangle inequality). These properties are crucial to guarantee that A* finds an optimal solution.
 *
 * One interesting part of this problem is that not all board configurations are solvable. This is determined by the number of inversions in the board. A board is solvable if and only if the number of inversions is even.
 *
 * Rather than computing inversions directly, we use a smart trick: we create a twin board by swapping any two non-zero tiles, which flips the inversion parity. We then run A* on both the original board and its twin in parallel. Only one of them can be solvable — if the twin reaches the goal first, we know the original board is unsolvable.
 *
 * During the A* process, we maintain a priority queue (min-heap) of search nodes. In each iteration, we:
 *
 * Remove the board with the lowest priority (f(n))
 *
 * Check if it's the goal
 *
 * If not, we add all its valid neighbors (next possible moves) back into the queue
 *
 * This continues for both the original and twin boards until one of them reaches the goal. If the original board reaches it first, we return the solution path; if the twin does, we return that the original puzzle is unsolvable.
 */
public class Solver {
    private static class SearchNode implements Comparable<SearchNode> {
        private Board board;
        private int manhattan;
        private int moves;
        private int priority;
        private SearchNode prev;

        public SearchNode(Board board, int moves, SearchNode prev) {
            this.board = board;
            this.manhattan = board.manhattan();
            this.moves = moves;
            this.priority = this.manhattan + this.moves;
            this.prev = prev;
        }

        @Override
        public int compareTo(SearchNode other) {
            return Integer.compare(this.priority, other.priority);
        }
    }

    private SearchNode solutionNode;
    private boolean isSolvable;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();

        // add initial node to PQs
        MinPQ<SearchNode> pq = new MinPQ<>();
        MinPQ<SearchNode> twinPQ = new MinPQ<>();
        pq.insert(new SearchNode(initial, 0, null));
        twinPQ.insert(new SearchNode(initial.twin(), 0, null));


        while (true) {
            // delete the min from pq
            SearchNode current = pq.delMin();
            SearchNode twinCurrent = twinPQ.delMin();

            if (current.board.isGoal()) {
                solutionNode = current;
                isSolvable = true;
                break;
            }

            // if the twin board is solvable then main one must be unsolvable
            if (twinCurrent.board.isGoal()) {
                isSolvable = false;
                break;
            }

            // add its neighbors to the pq
            for (Board b : current.board.neighbors()) {
                if (current.prev == null || !b.equals(current.prev.board)) {
                    pq.insert(new SearchNode(b, current.moves + 1, current));
                }
            }

            for (Board tb : twinCurrent.board.neighbors()) {
                if (twinCurrent.prev == null || !tb.equals(twinCurrent.prev.board)) {
                    twinPQ.insert(new SearchNode(tb, twinCurrent.moves + 1, twinCurrent));
                }
            }

        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return isSolvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return isSolvable ? solutionNode.moves : -1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable) return null;
        LinkedList<Board> solutionBoards = new LinkedList<>();
        for (SearchNode node = solutionNode; node != null; node = node.prev) {
            solutionBoards.addFirst(node.board); // add first function is to inverse the order, so initial is in the first position
        }
        return solutionBoards;
    }

    // test client (see below)
    public static void main(String[] args) {
        int[][] tiles = new int[][]{{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
        Solver solver = new Solver(new Board(tiles));
        System.out.println(solver.solution());
    }

}