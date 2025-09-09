import edu.princeton.cs.algs4.Graph;

/**
 * Book example: 4.1
 * finding the connected components of a graph by using DFS.
 */
public class ConnectedComponents {
    private boolean[] marked;
    private int[] id; //if[v]=id of component containing v
    private int count; // number of components

    public ConnectedComponents(Graph g) {
        marked = new boolean[g.V()];
        id = new int[g.V()];
        for (int s = 0; s < g.V(); s++) {
            if (!marked[s]) {
                dfs(g, s); // run DFS from one vertex in each component
                count++;
            }
        }
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        id[v] = count; // all vertices discovered in same cell of dfs have same id
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
    } // number of components

    /**
     * component identifier for v
     */
    public int id(int v) {
        return id[v];
    } // id of component containing v
}
