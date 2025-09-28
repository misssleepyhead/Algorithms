import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

import java.util.NoSuchElementException;

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

    public MyDigraph(In in) {
        if (in == null) throw new IllegalArgumentException("argument is null");
        try {
            this.V = in.readInt();
            if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must not be negative");
            for (int v = 0; v < V; v++) {
                adj[v] = new Bag<>();
            }

            int E = in.readInt();
            if (E < 0) throw new IllegalArgumentException("Number of edges in a Digraph must not be negative");
            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                addEdge(v, w);
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in digraph constructor", e);

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
