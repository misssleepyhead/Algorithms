import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;

import java.util.*;

/**
 * Interview question 1: Nonrecursive dfs
 */

public class IterativeDFS {
    private boolean[] marked;
    private int count;

    public IterativeDFS(Graph g, int s) {
        marked = new boolean[g.V()];
        dfs(g, s);
    }

    private void dfs(Graph g, int s) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(s);
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (marked[v]) continue; // skip the rest of loop body go back to the top
            marked[v] = true;
            count++;

            // 1. collect w in iteration order  (A, B, C …)
            List<Integer> nbrs = new ArrayList<>();
            for (int w : g.adj(v)) nbrs.add(w);   // collect

            Collections.reverse(nbrs);            // reverse once
            // push in that order; the stack is LIFO
            for (int w : nbrs) stack.push(w);
        }
    }

    public void NonrecursiveDFS(Graph g, int s) {
        marked = new boolean[g.V()];
        Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[g.V()];
        for (int v = 0; v < g.V(); v++) {
            adj[v] = g.adj(v).iterator();
        }

        Stack<Integer> stack = new Stack<>();
        marked[s] = true;
        stack.push(s);

        while (!stack.isEmpty()) {
            int v = stack.peek();
            if (adj[v].hasNext()) {
                int w = adj[v].next();
                if (!marked[w]) {
                    marked[w] = true;
                    stack.push(w);
                }
            } else {
                stack.pop();
            }
        }
    }
}
