import edu.princeton.cs.algs4.Stack;

/**
 * book page 577
 * finding a directed cycle
 */
public class MyDirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle; // vertices on a cycle if one exists
    private boolean[] onStack; //vertices on recursive call stack

    public MyDirectedCycle(MyDigraph g) {
        int n = g.V();
        marked = new boolean[n];
        edgeTo = new int[n];
        onStack = new boolean[n];
        for (int v = 0; v < n; v++) {
            if (!marked[v]) dfs(g, v);
        }
    }

    private void dfs(MyDigraph g, int v) {
        onStack[v] = true; // v is now on recursion stack
        marked[v] = true;

        for (int w : g.adj(v)) {
            if (this.hasCycle()) return;
            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            } else if (onStack[w]) {
                // Found a back edge v->w; reconstruct cycle w... v -> w
                cycle = new Stack<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
                // cycle now holds vertices along the loop; consumers can iterate it
            }
        }
        onStack[v] = false; // backtrack, v leaves recursion stack
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }


}
