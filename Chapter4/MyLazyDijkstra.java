import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.MinPQ;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 4.4.39 lazy dijkstra
 * idea: avoid decrease-key by pushing a new entry every time you find an improvement, even if the vertex is already in the pq
 */
public class MyLazyDijkstra {
    private double[] distTo;
    private boolean[] marked;
    private DirectedEdge[] edgeTo;
    private MinPQ<DirectedEdge> pq;

    private class ByDistanceFromSource implements Comparator<DirectedEdge> {
        public int compare(DirectedEdge e, DirectedEdge f) {
            double dist1 = distTo[e.from()] + e.weight();
            double dist2 = distTo[f.from()] + f.weight();
            return Double.compare(dist1, dist2);
        }
    }


    public MyLazyDijkstra(EdgeWeightedDigraph graph, int src) {
        int n = graph.V();

        pq = new MinPQ<DirectedEdge>(new ByDistanceFromSource());
        marked = new boolean[n];
        distTo = new double[n];
        edgeTo = new DirectedEdge[n];

        // init
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        distTo[src] = 0.0;
        relax(graph, src);

        while (!pq.isEmpty()) {
            DirectedEdge e = pq.delMin();
            int v = e.from(), w = e.to();
            if (!marked[w]) relax(graph, w); // lazy, so w might already have been relaxed
        }

    }

    private void relax(EdgeWeightedDigraph graph, int v) {
        marked[v] = true;
        for (DirectedEdge e : graph.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                pq.insert(e);
            }
        }
    }
}
