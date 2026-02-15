import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.KosarajuSharirSCC;

import java.util.Iterator;
import java.util.Stack;

/**
 * 4.2.20 Directed eulerian cycle: A directed Eulerian cycle is a directed cycle that contains each edge exactly once.
 * Write a digraph client DirectedEulerianCycle.java that find a directed Eulerian cycle or reports that no such cycle exists.
 * Hint: Prove that a digraph G has a directed Eulerian cycle if and only if vertex in G has its indegree equal to its outdegree
 * and all vertices with nonzero degree belong to the same strong component.
 */
public class MyDirectedEulerianCycle {
    private Stack<Integer> cycle; // eulerian cycle; null if no such cycle

    public MyDirectedEulerianCycle(Digraph digraph) {
        if (digraph.E() == 0) {
            cycle = new Stack<>();
            return;
        }


        // compute indegree/outdegree
        int[] indeg = new int[digraph.V()];
        int[] outdeg = new int[digraph.V()];
        for (int v = 0; v < digraph.V(); v++) {
            for (int w : digraph.adj(v)) {
                outdeg[v]++;
                indeg[w]++;
            }
        }

        // necessary condition 1: indegree(v) == outdegree(v) for each vertex v
        for (int v = 0; v < digraph.V(); v++) {
            if (indeg[v] != outdeg[v]) {
                cycle = null;
                return;
            }
        }

        // pick a start vertex with nonzero outdegree
        int s = -1;
        for (int v = 0; v < digraph.V(); v++) {
            if (outdeg[v] > 0){
                s = v;
                break;
            }

        }
        if (s == -1) { // should not happen cause E>0 but defensive
            cycle = new Stack<>();
            return;
        }

        // necessary condition 2: all non-zero vertices are in the same scc
        KosarajuSharirSCC scc = new KosarajuSharirSCC(digraph);
        int sid = scc.id(s);
        for (int v = 0; v < digraph.V(); v++) {
            if (indeg[v] + outdeg[v] > 0 && scc.id(v) != sid) {
                cycle = null;
                return;
            }
        }
        @SuppressWarnings("unchecked")
        // create a local view of adj lists, to iterate one vertex at a time
        Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[digraph.V()];
        for (int v = 0; v < digraph.V(); v++) {
            adj[v] = digraph.adj(v).iterator();
        }

        // construct eulerian cycle(hierholzer)
        Stack<Integer> path = new Stack<>();
        // initialize stack with any non-isolated vertex
        path.push(s);
        cycle = new Stack<>();
        while (!path.isEmpty()) {
            int v = path.pop();
            while (adj[v].hasNext()) {
                path.push(v);
                v = adj[v].next();
            }
            cycle.push(v);
        }

        // check if all edges have been used
        if (cycle.size() != digraph.E() + 1) {
            cycle = null;
        }
    }
    public boolean hasEulerianCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }
}
