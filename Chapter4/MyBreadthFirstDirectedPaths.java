import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class MyBreadthFirstDirectedPaths {
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;

    public MyBreadthFirstDirectedPaths(Digraph g, int s) {
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        distTo = new int[g.V()];
        for (int v = 0; v < g.V(); v++) {
            distTo[v] = INFINITY;
        }
    }

    public MyBreadthFirstDirectedPaths(Digraph g, Iterable<Integer> s) {
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        distTo = new int[g.V()];
        for (int v = 0; v < g.V(); v++) {
            distTo[v] = INFINITY;
        }
    }

    private void bfs(Digraph g, int s) {
        Queue<Integer> queue = new Queue<>();
        marked[s] = true;
        distTo[s] = 0;
        queue.enqueue(s);
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w : g.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    queue.enqueue(w);
                }
            }
        }
    }

    private void bfs(Digraph g, Iterable<Integer> sources) {
        Queue<Integer> q = new Queue<>();
        for (int s : sources) {
            marked[s] = true;
            edgeTo[s] = 0;
            q.enqueue(s);
        }

        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : g.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    q.enqueue(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public int distTo(int v) {
        return distTo[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(x);
        return path;
    }


    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v > V) throw new IllegalArgumentException();
    }

    private void validateVertex(Iterable<Integer> vertices) {
        if (vertices == null) throw new IllegalArgumentException();
        int vertexCount = 0;
        for (Integer v : vertices) {
            vertexCount++;
            if (v == null) throw new IllegalArgumentException();
            validateVertex(v);
        }
        if (vertexCount == 0) throw new IllegalArgumentException();
    }


}
