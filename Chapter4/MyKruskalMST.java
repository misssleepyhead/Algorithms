import edu.princeton.cs.algs4.*;

import java.util.Comparator;

/**
 * 4.3 Kruskal's algorithm
 * use a queue to hold mst edges, a pq to hold edges not yet examined and a uf data structure for identifying ineligible edges
 */
public class MyKruskalMST {
    private Queue<Edge> mst;
    private double weight = 0.0;

    public MyKruskalMST(EdgeWeightedGraph g) {
        mst = new Queue<>();
        MinPQ<Edge> pq = new MinPQ<>();
        UF uf = new UF(g.V());

        while (!pq.isEmpty() && mst.size() < g.V() - 1) {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (uf.connected(v, w)) continue;
            uf.union(v, w); //merge components
            mst.enqueue(e); // add edge to mst
            weight+=e.weight();

        }
    }

    public double weight() {
        return weight;
    }
    public Iterable<Edge> edges() { return mst; }


}
