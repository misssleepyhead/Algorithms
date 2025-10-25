import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;

/**
 * 4.2.7
 * Write a Degrees class to imp following api:
 * int indegree(int v): indegree of a vertex is the number of directed edges that point to that vertex
 * int outdegree(int v): outdegree of a vertex is the number of directed edges from that vertex
 * Iterable<Integer> sources(): a vertex of indegree =0
 * Iterable<Integer> sinks(): a vertex which outdegree =0
 * boolean isMap(): a digraph where self-loops allowed and every vertex has outdegree 1 is a map
 */
public class Degrees {
    private final Digraph G;
    private int[] indegrees;
    private int[] outdegrees;

    public Degrees(Digraph G) {
        this.G = G;
        indegrees = new int[G.V()];
        outdegrees = new int[G.V()];

        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                outdegrees[v]++;
                indegrees[w]++;
            }
        }
    }
    private void validateVertex(int v) {
        int V = G.V();
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    public int indegree(int v) {
        validateVertex(v);
        return indegrees[v];
    }

    public int outdegree(int v) {
        validateVertex(v);

        return outdegrees[v];
    }

    public Iterable<Integer> sources() {
        Bag<Integer> sourcesSet = new Bag<>();
        for (int v = 0; v < G.V(); v++) {
            if (indegrees[v] == 0) {
                sourcesSet.add(v);
            }
        }
        return sourcesSet;
    }

    public Iterable<Integer> sinks() {
        Bag<Integer> sinksSet = new Bag<>();
        for (int v = 0; v < G.V(); v++) {
            if (outdegrees[v] == 0) {
                sinksSet.add(v);
            }
        }
        return sinksSet;
    }

    public boolean isMap() {
        for (int v = 0; v < G.V(); v++) {
            if (outdegrees[v] != 1) {
                return false;
            }
        }
        return true;
    }
}
