import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.FlowEdge;

/**
 * 6.4 Max flow
 * A capacitated flow network, implemented using adjacency lists.
 */
public class MyFlowNetWork {
    private static int V;
    private int E;
    private Bag<FlowEdge>[] adj;

    public MyFlowNetWork(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be non-negative");
        this.V = V;
        this.E = 0;
        adj = (Bag<FlowEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    public int E() {
        return E;
    }

    public int V() {
        return V;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    public void addEdge(FlowEdge e) {
        int v = e.from();
        int w = e.to();
        validateVertex(v);
        validateVertex(w);
        adj[v].add(e);
        adj[w].add(e);
    }

    public Iterable<FlowEdge> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public Iterable<FlowEdge> edges() {
        Bag<FlowEdge> list = new Bag<>();
        for (int v = 0; v < V; v++) {
            for (FlowEdge e : adj(v)) {
                if (e.to() != v) {
                    list.add(e);
                }
            }
        }
        return list;
    }
}
