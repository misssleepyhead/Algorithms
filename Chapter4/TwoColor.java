import edu.princeton.cs.algs4.Graph;

/**
 * Book example 4.1
 * solving :
 * 1. Two-colorability: is the graph bipartite? meaning if the graph can be "two-colored"
 * with no two adjacent vertices sharing the same color
 */
public class TwoColor {
    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColorable = true;

    public TwoColor(Graph g) {
        marked = new boolean[g.V()];
        color = new boolean[g.V()];
        for (int s = 0; s < g.V(); s++) {
            if (!marked[s]) {
                dfs(g, s);

            }
        }
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                color[w] = !color[v]; // give the neighbor the opposite color
                dfs(g, w);
            } else if (color[w] == color[v]) {
                isTwoColorable = false; // if encounter a neighbor that is already visited but has the same color then this graph is not bipartite
            }
        }
    }

    public boolean isBipartite() {
        return isTwoColorable;
    }
}
