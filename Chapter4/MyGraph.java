import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;

/**
 * Class practice 4.1
 */
public class MyGraph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj; // adjacency lists

    public MyGraph(int V) {
        this.V = V;
        E = 0;
        adj = (Bag<Integer>[]) new Bag[V]; // create array of lists
        for (int v = 0; v < V; v++) { // Initialize all lists to empty
            adj[v] = new Bag<Integer>();
        }
    }

    public MyGraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w); // Read vertexes and add edge connecting them
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    // compute the degree of v
    public static int degree(MyGraph g, int v) {
        int degree = 0;
        for (int w : g.adj(v)) {
            degree++;
        }
        return degree;
    }

    // compute maximum degree
    public static int maxDegree(MyGraph g) {
        int max = 0;
        for (int v = 0; v < g.V(); v++) {
            if (degree(g, v) > max) {
                max = degree(g, v);
            }
        }
        return max;
    }

    // compute average degree
    public static int avgDegree(MyGraph g) {
        return 2 * g.E() / g.V();
    }

    // count self-loops
    public static int numberOfSelfLoops(Graph g) {
        int count = 0;
        for (int v = 0; v < g.V(); v++) {
            for (int w : g.adj(v)) {
                if (v == w) count++;
            }
        }
        return count / 2; // each edge counted twice
    }

    public String toString() {
        String s = V + " vertices," + E + " edges\n";
        for (int v = 0; v < V; v++) {
            s += v + ":";
            for (int w : this.adj(v)) {
                s += w + " ";
            }
        }
        return s;
    }
}
