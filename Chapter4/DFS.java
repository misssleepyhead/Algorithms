import edu.princeton.cs.algs4.Graph;

/**
 * implement deep first search
 * a recursive way to searching in a connected graph
 * To visit a vertex, 1. mark it as visited 2. visit all the vertices that are adjacent to it and not visited yet
 */

public class DFS {
    private boolean[] marked;
    private int count;

    public DFS(Graph g, int s) {
        marked = new boolean[g.V()];
        dfs(g, s);
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        count++;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
    }

    public boolean marked(int w) {
        return marked[w];
    }

    public int getCount() {
        return count;
    }
}
