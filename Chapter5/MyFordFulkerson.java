import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.Queue;

/**
 * 6.14 Ford- Fulkerson shortest-augumenting path maxflow algorithm
 */
public class MyFordFulkerson {
    private boolean[] marked; // is s->v path in residual graph?
    private FlowEdge[] edgeTo; // last edge on the shortest s->v path
    private double value;

    public MyFordFulkerson(FlowNetwork G, int s, int t) {
        // find maxflow in flow network g from s to t
        while (hasAugmentingPath(G, s, t)) {
            // while there exists augmenting path, use it
            // compare bottleneck capacity
            double bottle = Double.POSITIVE_INFINITY;
            for (int v = t; v != s; v = edgeTo[v].other(v)) {
                bottle = Math.min(bottle, edgeTo[v].residualCapacityTo(v));
            }
            // augment flow
            for (int v = t; v != s; v = edgeTo[v].other(v)) {
                edgeTo[v].addResidualFlowTo(v, bottle);
            }
            value += bottle;
        }
    }

    /*
     * Simialr to bfs
     */

    private boolean hasAugmentingPath(FlowNetwork g, int s, int t) {
        marked = new boolean[g.V()]; // is path to this vertex known?
        edgeTo = new FlowEdge[g.E()];
        Queue<Integer> queue = new Queue<>();

        marked[s] = true;
        queue.enqueue(s);
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (FlowEdge e : g.adj(v)) {
                int w = e.other(v);
                if (e.residualCapacityTo(w) > 0 && !marked[w]) {
                    // for every edge to an unmarked vertex in residual graph
                    edgeTo[w] = e; // save the last edge on the path
                    marked[w] = true; // mark w because the path is known
                    queue.enqueue(w); // and add it to the queue
                }
            }
        }
        return marked[t];
    }

    public double value() {
        return value;
    }

    public boolean inCut(int v){
        return marked[v];
    }
}
