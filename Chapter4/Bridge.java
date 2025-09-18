import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;

/**
 * 4.1.36 Edge connectivity, a bridge in a graph is an edge that if removed,
 * would separate a connected graph into two disjointed sub graphs.
 * Develop a DFS based to determining whether a given graph is edge connected
 */
public class Bridge {
    private int[] parent;
    private int[] dist;
    private int[] low;
    private int v;
    private int time = 0;
    private boolean[] marked;
    private final List<int[]> bridges = new ArrayList<>();

    public Bridge(Graph g) {
        this.v = g.V();
        parent = new int[v];
        low = new int[v];
        dist = new int[v];
        marked = new boolean[v];
        for (int i = 0; i < v; i++) {
            parent[i] = -1;
        }
        // run dfs
        for (int s = 0; s < v; s++) {
            if (!marked[s]) dfs(g, s);
        }
    }

    public void dfs(Graph g, int v) {
        marked[v] = true;
        dist[v] = low[v] = time++; // init

        for (int w : g.adj(v)) {
            if (!marked[w]) {
                parent[w] = v;
                dfs(g, w);
                low[v] = Math.min(low[v], low[w]);
                //---bridge test
                if (low[w] > dist[v]) bridges.add(new int[]{v, w});

            } else if (w != parent[v]) {
                // back edge, a visited but not parent vertex is an ancestor
                low[v] = Math.min(low[v], dist[w]);

            }
        }
    }

    public boolean isTwoEdgeConnected() {
        return bridges.isEmpty(); // if no edge then the graph is two connected
    }
}

