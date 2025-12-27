import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.Topological;

/***
 * 4.4
 * single source longest paths problem in edge-weighted directed acyclic graphs. The edge can be negative, positive, zero.
 */
public class MyAcyclicLP {
    private double[] distTo;
    private DirectedEdge[] edgeTo;

    public MyAcyclicLP(EdgeWeightedDigraph digraph, int s) {
        distTo = new double[digraph.V()];
        edgeTo = new DirectedEdge[digraph.V()];

        for (int v = 0; v < digraph.V(); v++) {
            distTo[v] = Double.NEGATIVE_INFINITY;
        }
        distTo[s] = 0.0;

        Topological topo = new Topological(digraph);
        if (!topo.hasOrder()) {
            throw new IllegalArgumentException("digraph is not acyclic");
        }
        for (int v : topo.order()) {
            for (DirectedEdge e : digraph.adj(v)) {
                relax(e);
            }
        }
    }

    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] < distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
        }
    }
}
