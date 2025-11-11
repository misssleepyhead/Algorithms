import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.IndexMinPQ;

/**
 * 4.3 Eager version prime's MST
 */
public class MyEagerPrimeMST {
    private Edge[] edgeTo; //shortest edge from tree vertex
    private double[] distTo; // distTo[w]== = edgeTo[v] .weight()
    private boolean[] marked; // true if v on tree
    private IndexMinPQ<Double> pq; //eligible crossing edges

    public MyEagerPrimeMST(EdgeWeightedGraph g) {
        int n = g.V();
        edgeTo = new Edge[n];
        distTo = new double[n];
        marked = new boolean[n];
        for (int v = 0; v < n; v++) {
            distTo[v] = Double.POSITIVE_INFINITY; // init distance
        }
        pq = new IndexMinPQ<>(n);
        distTo[0] = 0.0;
        pq.insert(0, 0.0);
        while (!pq.isEmpty()) visit(g, pq.delMin()); // add closest vertex to tree
    }

    private void visit(EdgeWeightedGraph g, int v) {
        // add v to tree, update data structure
        marked[v] = true;
        for (Edge e : g.adj(v)) {
            int w = e.other(v);
            if (marked[w]) continue;
            if (e.weight() < distTo[w]) {
                // edge e is the new best connection from tree to w
                edgeTo[w] = e;
                distTo[w] = e.weight();
                if (pq.contains(w)) pq.change(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }
        }
    }
}
