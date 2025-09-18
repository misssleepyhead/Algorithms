import edu.princeton.cs.algs4.Bag;

/**
 * 4.2 Directed graph
 */
public class MyDigraph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public MyDigraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    public int V() {
        return this.V;
    }

    public int E() {
        return this.E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    /**
     * reverse of this graph
     */
    public MyDigraph reverse() {
        MyDigraph r = new MyDigraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                r.addEdge(w, v);
            }
        }
        return r;
    }

}
