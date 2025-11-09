
import edu.princeton.cs.algs4.Digraph;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;


/**
 * 4.2.30
 * Queue-based topological order algorithm. Develop a nonrecursive topological sort implementation TopologicalX.java
 * that maintains a vertex-indexed array that keeps track of the indegree of each vertex.
 * Initialize the array and a queue of sources in a single pass through all the edges. Then, perform the following operations until the source queue is empty:
 * Remove a source from the queue and label it.
 * Decrement the entries in the indegree array corresponding to the destination vertex of each of the removed vertex's edges.
 * If decrementing any entry causes it to become 0, insert the corresponding vertex onto the source queue.
 * <p>
 * kahn's algorithm
 */
public class TopologicalX {
    private ArrayList<Integer> order;
    private boolean isDAG;

    public TopologicalX(Digraph digraph) {
        int n = digraph.V();
        ;
        // an array to track the indegree of every vertex
        int[] indegrees = new int[n];
        for (int v = 0; v < n; v++) {
            for (int w : digraph.adj(v)) indegrees[w]++;
        }

        Deque<Integer> queue = new ArrayDeque<>();
        for (int v = 0; v < n; v++) {
            if (indegrees[v] == 0) queue.addLast(v);
        }

        while (!queue.isEmpty()) {
            int v = queue.removeFirst();
            order.add(v);
            for (int w : digraph.adj(v)) {
                if (--indegrees[w] == 0) queue.addLast(w);
            }
        }
        isDAG = (order.size() == n);
    }

    public boolean isDAG() {
        return isDAG;
    }

    public Iterable<Integer> order() {
        return isDAG ? order : null;
    }

}
