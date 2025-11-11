import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

/**
 * 4.3 Lazy version of prime's MST algorithm
 * use a priority queue to hold crossing edges, a vertex-indexed arrays to marked tree vertices and a queue to hold MST.
 */
public class MyLazyPrimeMST {
    private boolean[] marked; // MST vertices
    private Queue<Edge> mst; //MST edges
    private MinPQ<Edge> pq;  // crossing edges

    public MyLazyPrimeMST(EdgeWeightedGraph graph) {
        pq = new MinPQ<Edge>();
        marked = new boolean[graph.V()];
        mst = new Queue<>();
        visite(graph, 0);
        while (!pq.isEmpty()) {
            Edge e = pq.delMin(); // get lowest-weight edge from pw
            int v = e.either(), w = e.other(v);
            if (marked[v] && marked[w]) continue; // skip if ineligible
            mst.enqueue(e); //add edge to the tree
            if (!marked[v]) visite(graph, v);
            if (!marked[w]) visite(graph, w); // add vertex to tree, either v or w
        }

    }

    private void visite(EdgeWeightedGraph g, int v) {
        // mark v and add to pq all edges from v to unmarked vertices.
        marked[v] = true;
        for (Edge e : g.adj(v)) {
            if (!marked[e.other(v)]) pq.insert(e);
        }
    }
}
