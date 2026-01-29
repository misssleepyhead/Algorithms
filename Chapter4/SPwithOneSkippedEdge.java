import edu.princeton.cs.algs4.DijkstraSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * interview question 4-3:
 * Shortest path with one skippable edge. Given an edge-weighted digraph, design an
 * ElogV algorithm to find a shortest path from s to t
 * where you can change the weight of any one edge to zero. Assume the edge weights are nonnegative.
 * Notes:
 * 1. since all edges are non-negative, use dijkstra's
 * 2. think of the path should be s to u with original weight, then u to v with 0 weight then v to t with original path
 * 3. use 2 dijkstra + scan algorithm
 */
public class SPwithOneSkippedEdge {
    private DirectedEdge skippedEdge;
    private final double bestDist;
    private final List<DirectedEdge> bestPathEdges;  // path edges in original direction (suffix edges reconstructed)
    private final int s, t;
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    public SPwithOneSkippedEdge(EdgeWeightedDigraph G, int s, int t){
        this.s = s;
        this.t = t;

        // 1. dijkstra from s to t on original graph
        DijkstraSP spFromS = new DijkstraSP(G,s);

        // 2. dijkstra from t on reversed graph, get distance from v to t in original graph
        EdgeWeightedDigraph revG = reverseGraph(G);
        DijkstraSP spToT = new DijkstraSP(revG, t);

        // 3. scan all edges : candidate if we skip edges (u->v)
        // candidate = dist(s,u)+0+ sit(v,t) == spFromS.distTo(u) + spToT.distTo(v)
        double best = spToT.distTo(t); // current best is the sp from original graph
        DirectedEdge skipEdge = null;

        for(DirectedEdge e:G.edges()){
            int u = e.from();
            int v = e.to();

            double du = spFromS.distTo(u);
            double dv = spToT.distTo(v);

            if(Double.isInfinite(du)||Double.isInfinite(dv)) continue; // skip unreachable vertex

            double candidate = du+dv;
            if(candidate<best){
                best = candidate;
                skipEdge = e;
            }


        }
        this.bestDist=best;
        this.skippedEdge=skipEdge;

        this.bestPathEdges = buildBestPath(G, revG, spFromS, spToT,skipEdge);

        // Build edgeTo/distTo arrays for this chosen best path (optional but matches your fields)
        int V = G.V();
        this.edgeTo = new DirectedEdge[V];
        this.distTo = new double[V];
        for (int i = 0; i < V; i++) distTo[i] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        for (DirectedEdge e : bestPathEdges) {
            double w = (skippedEdge != null && e == skippedEdge) ? 0.0 : e.weight();
            if (!Double.isInfinite(distTo[e.from()])) {
                distTo[e.to()] = distTo[e.from()] + w;
                edgeTo[e.to()] = e;
            }
        }

    }



    // helper functions
    private EdgeWeightedDigraph reverseGraph(EdgeWeightedDigraph G){
        EdgeWeightedDigraph reverse = new EdgeWeightedDigraph(G.V());
        for(DirectedEdge e:G.edges()){
            reverse.addEdge(new DirectedEdge(e.to(), e.from(),e.weight()));
        }
        return reverse;
    }

    /**
     * Build the best path:
     * - If bestSkip == null: normal shortest path s->t from spFromS
     * - Else: shortest s->u  + (u->v) skipped + shortest v->t
     *   where v->t is obtained by reversing the path t->v in reversed graph.
     */
    private List<DirectedEdge> buildBestPath(
            EdgeWeightedDigraph G,
            EdgeWeightedDigraph Grev,
            DijkstraSP spFromS,
            DijkstraSP spToT,
            DirectedEdge bestSkip

    ) {

        // If no path to t at all (even without skip), we still might have a skip-candidate.
        // But our scanning logic would keep best = dist(s,t) = INF and bestSkip stays null.
        // In that case return empty.
        if (bestSkip == null) {
            if (!spFromS.hasPathTo(t)) {
                // DijkstraSP doesn't have t(), so handle below in safer way:
                return Collections.emptyList();
            }
            List<DirectedEdge> path = new ArrayList<>();
            for(DirectedEdge e:spFromS.pathTo(t)) path.add(e);
            return path;
        }

        int u = bestSkip.from();
        int v = bestSkip.to();

        if(!spFromS.hasPathTo(u)) return Collections.emptyList();
        if(!spToT.hasPathTo(v)) return Collections.emptyList();

        List<DirectedEdge> path = new ArrayList<>();

        // 1) s -> u (original shortest)
        for (DirectedEdge e : spFromS.pathTo(u)) path.add(e);

        // 2) skip edge u -> v
        path.add(bestSkip);

        // 3) v -> t (use reversed-graph path t -> v, reverse it and flip directions)
        List<DirectedEdge> revEdges = new ArrayList<>();
        for (DirectedEdge e : spToT.pathTo(v)) revEdges.add(e); // edges from t to v in Grev

        // Convert t->...->v in Grev into v->...->t in original:
        for (int i = revEdges.size() - 1; i >= 0; i--) {
            DirectedEdge e = revEdges.get(i); // e: a -> b in Grev
            // original direction is b -> a
            path.add(new DirectedEdge(e.to(), e.from(), e.weight()));
        }

        return path;
    }


}
