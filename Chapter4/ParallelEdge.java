import edu.princeton.cs.algs4.Graph;

/**
 *   parallel edge detection: devise a linear time algorithm to count the parallel edges in a graph
 * parallel edges = multiple edges between the same pair of vertices
 */
public class ParallelEdge {

    public int countParallel(Graph g) {
        int n = g.V();
        int[] stamp = new int[n];
        int currentRun = 0;
        int rawCount = 0;

        for (int v = 0; v < n; v++) {
            currentRun++; // new stamp for vertex v
            for (int w : g.adj(v)) {
                if (stamp[w] == currentRun) { // already met w in this run
                    rawCount++; // another copy of v-w
                } else {
                    stamp[w] = currentRun; // first time we met w
                }
            }
        }
        return rawCount / 2; // each parallel edge was seen at both ends
    }
}
