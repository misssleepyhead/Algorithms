import edu.princeton.cs.algs4.Bag;

/**
 * 4.3 Edge-weighted graph data type
 **/
public class MyEdgeWeighted {
    private final int V;
    private int E;
    private Bag<MyEdge>[] adj;

    public MyEdgeWeighted(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<MyEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<MyEdge>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(MyEdge e) {
        int v = e.either(), w = e.other(v);
        adj[v].add(e); // every edge appears twice
        adj[w].add(e);
        E++;
    }

    public Iterable<MyEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<MyEdge> edges() {
        Bag<MyEdge> b = new Bag<>();
        for (int v = 0; v < V; v++) {
            for (MyEdge e : adj[v]) {
                if (e.other(v) > v) b.add(e);
            }
        }
        return b;
    }
}
