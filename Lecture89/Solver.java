import edu.princeton.cs.algs4.MinPQ;

import java.util.PriorityQueue;

/**
 * Slider puzzle
 * implement A* search to solve n by n slider puzzles
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


        while(true){
            // delete the min from pq
            SearchNode current= pq.delMin();
            SearchNode twinCurrent = twinPQ.delMin();

            if(current.board.isGoal()){
                solutionNode=current;
                isSolvable=true;
                break;
            }else {

            }


            // add its neighbors to the pq

        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return isSolvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return isSolvable? solutionNode.moves : -1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
    }

    // test client (see below)
    public static void main(String[] args) {
    }

}