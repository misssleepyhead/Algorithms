import edu.princeton.cs.algs4.*;


/**Interview question 1:
 * Given an edge-weighted digraph and two vertices s and t, design an ElogE,  algorithm to find a fattest path from s to t.
 * The bottleneck capacity of a path is the minimum weight of an edge on the path.
 * A fattest path is a path such that no other path has a higher bottleneck capacity.
 * Fattest path = path from s to s whose bottleneck edge is as large as possible
 */
public class FattestPath {
    private DirectedEdge[] edgeTo;
    private double[] best; // best[v] = maximum bottleneck capacity of any known path from s to v
    private boolean[] settled;
    private final int s;
    private final int t;
    private IndexMaxPQ<Double> pq; // t

    public FattestPath(EdgeWeightedDigraph G, int s, int t) {
        this.s = s;
        this.t = t;

        int n = G.V();
        edgeTo = new DirectedEdge[n];
        best = new double[n];
        settled = new boolean[n];
        pq = new IndexMaxPQ<>(n);

        for (int i = 0; i < n; i++) {
            best[i] = 0.0;
            edgeTo[i] = null;
        }
        best[s] = Double.POSITIVE_INFINITY;
        pq.insert(s, best[s]);

        while (!pq.isEmpty()) {
            int v = pq.delMax();

            if (settled[v]) continue;
            if (v == t) {
                return;
            }
            for (DirectedEdge e : G.adj(v)) {
                relax(e, pq);
            }
        }
    }

    private void relax(DirectedEdge e, IndexMaxPQ<Double> pq) {
        int v = e.from();
        int w = e.to();

        if (settled[w]) return;

        double candidate = Math.min(best[v], e.weight()); //If I reach w by going through v, what bottleneck capacity would that path have?

        if (candidate > best[w]) {
            best[w] = candidate;
            edgeTo[w] = e;

            if (!pq.contains(w)) {
                pq.changeKey(w, best[w]);
            } else {
                pq.insert(w, best[w]);
            }

        }
    }


}
