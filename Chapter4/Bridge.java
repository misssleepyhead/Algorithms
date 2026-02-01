import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;

/**
 * 4.1.36 Edge connectivity, a bridge in a graph is an edge that if removed,
 * would separate a connected graph into two disjointed sub graphs.
 * Develop a DFS based to determining whether a given graph is edge connected
 * notes:
 * 1. low[v] tells you the earliest ancestor that v’s subtree can reach without using the parent edge back up.
 * arjan’s bridge algorithm
 */
public class Bridge {
    private int[] parent;
    private int[] disc; // dfs discovery time of a vertex, a timestamp
    private int[] low; // the minimum discovery time (dist[x] reachable from v, using :
    // zero or more tree edges then at most one back edge
    private int v;
    private int time = 0;
    private boolean[] marked;
    private final List<int[]> bridges = new ArrayList<>();

    public Bridge(Graph g) {
        this.v = g.V();
        parent = new int[v];
        low = new int[v];
        disc = new int[v];
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
        disc[v] = low[v] = time++; // init

        for (int w : g.adj(v)) {
            if (!marked[w]) {
                parent[w] = v;
                // case 1: tree edge v->w and w not visted
                dfs(g, w);
                low[v] = Math.min(low[v], low[w]);
                //---bridge test,  if true that means the subtree of w cannot reach v or any ancestor of v via a back edge
                // so the only way to connect w and the rest of the graph is the edge(v,w), a bridge
                if (low[w] > disc[v]) bridges.add(new int[]{v, w});

            } else if (w != parent[v]) { // case 2, visited but not parent
                // back edge, a visited but not parent vertex is an ancestor
                low[v] = Math.min(low[v], disc[w]);

            }
        }
    }

    public boolean isTwoEdgeConnected() {
        return bridges.isEmpty(); // if no edge then the graph is two connected
    }
}

