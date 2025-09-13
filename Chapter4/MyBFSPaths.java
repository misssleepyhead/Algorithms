import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Queue;


/**
 * Text book practice: breadth first search tp find paths in a grapg
 */
public class MyBFSPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo; // distTo[v] = number of edges shortest s-v path
    private final int s;

    public MyBFSPaths(Graph g, int s) {
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        distTo = new int[g.V()];
        for (int v = 0; v < g.V(); v++) {
            distTo[v] = Integer.MAX_VALUE;
        }
        this.s = s;
        bfs(g, s);
    }
    // BFS FROM A SINGLE SOURCE
    private void bfs(Graph g, int s) {
        Queue<Integer> queue = new Queue<>();
        marked[s] = true;
        queue.enqueue(s);
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w : g.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    queue.enqueue(w);
                }
            }
        }
    }

    // breadth-first search from multiple sources
    private void bfs(Graph G, Iterable<Integer> sources) {
        Queue<Integer> q = new Queue<Integer>();
        for (int s : sources) {
            marked[s] = true;
            distTo[s] = 0;
            q.enqueue(s);
        }
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Queue<Integer> path = new Queue<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.enqueue(x);
        }
        path.enqueue(s);
        return path;
    }

    // Exercise 13, return the number of edges on the shortest from the source  to a given vertex.
    // A distTo() query should run in constant time.
    public int distTo(int v) {
        return distTo[v];
    }
}
