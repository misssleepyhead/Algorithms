import edu.princeton.cs.algs4.Bag;

/***
 *  4.4 textbook imp
 *  edge-weighted digraph
 *  if an edge connects v-w, it appears in v's adj list
 */
public class MyEdgeWeightedDigraph {
    private final int V; //number of vertices
    private int E;
    private Bag<MyDirectedEdge>[] adj;


    public MyEdgeWeightedDigraph(int V) {
        this.V = V;
        E = 0;
        adj = (Bag<MyDirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(MyDirectedEdge e) {
        adj[e.from()].add(e);
        E++;
    }

    public Iterable<MyDirectedEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<MyDirectedEdge> edges() {
        Bag<MyDirectedEdge> bag = new Bag<>();
        for (int v = 0; v < V; v++) {
            for (MyDirectedEdge e : adj[v]) {
                bag.add(e);
            }
        }
        return bag;
    }
}
