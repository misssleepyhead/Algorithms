import edu.princeton.cs.algs4.*;

/**
 * 4.4 book imp
 * Critical path method for parallel precedence-constrained job scheduling
 */
public class MyCPM {
    public static void main(String[] args) {
        // number of jobs
        int n = StdIn.readInt();

        // source and sink
        int source = 2*n;
        int sink   = 2*n + 1;

        // build network
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(2*n + 2);
        for (int i = 0; i < n; i++) {
            double duration = StdIn.readDouble();
            G.addEdge(new DirectedEdge(source, i, 0.0)); // source connect to every start(i)
            G.addEdge(new DirectedEdge(i+n, sink, 0.0)); // after finishing job i, may proceed to sink
            G.addEdge(new DirectedEdge(i, i+n,    duration));  // start(i) -> finish(i) weight duration

            // precedence constraints
            int m = StdIn.readInt();
            for (int j = 0; j < m; j++) {
                int precedent = StdIn.readInt();
                G.addEdge(new DirectedEdge(n+i, precedent, 0.0)); // jon i mut complete before job precedent
                // finish(i) -> start(precedent)
            }
        }
        AcyclicLP lp = new AcyclicLP(G, source);
        System.out.println("start times:");
        for (int i = 0; i < n; i++) {
            StdOut.printf("%4d: %5.1f\n", i, lp.distTo(i));
        }
        StdOut.printf("Finish time: %5.1f\n", lp.distTo(sink));
    }
}
