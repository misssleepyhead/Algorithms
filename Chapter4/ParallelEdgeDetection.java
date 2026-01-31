import edu.princeton.cs.algs4.Graph;

/**
 * 4.1.32 Parallel edge detection.
 * Devise a linear-time algorithm to count the parallel edges in a graph.
 */
public class ParallelEdgeDetection {
    public boolean parallelDetect(Graph g) {
        int n = g.V();
        boolean[] seen = new boolean[n];
        for (int v = 0; v < n; v++) {
            for (int w : g.adj(v)) {
                if(seen[w]) return true; // duplicate neighbor => parallel edge exists
                seen[w]=true;
            }
            for(int w:g.adj(v)){
                seen[w]=false; // reset only touched neighbors
            }
        }
        return false; // no parallel edges detected

    }

}
