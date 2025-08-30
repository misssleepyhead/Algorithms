import edu.princeton.cs.algs4.Graph;

/**
 * Book example: 4.1
 * finding the connected components of a graph by using DFS.
 */
public class ConnectedComponents {
    private boolean[] marked;
    private int[] id;
    private int count;

    public ConnectedComponents(Graph g) {
        marked = new boolean[g.V()];
        id = new int[g.V()];
        for (int s = 0; s < g.V(); s++) {
            if (!marked[s]) {
                dfs(g, s);
                count++;
            }
        }
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
    }

    /**
     * the vertices in the first component processed, then the value will be 0, meaning they are connected
      */
    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    public int count() {
        return count;
    }

    /**
     * component identifier for v
     */
    public int id(int v) {
        return id[v];
    }
}
