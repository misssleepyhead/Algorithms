import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

/**
 * This class is finding directed paths from a source to every other vertex in the digraph
 * use dfs
 */


public class MyDepthFirstDirectedPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public MyDepthFirstDirectedPaths(Digraph g, int s) {
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        this.s = s;
        validateVertex(s);
        dfs(g, s);

    }

    private void dfs(Digraph g, int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();

        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }

    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v > V) throw new IllegalArgumentException();
    }
}
