import edu.princeton.cs.algs4.Graph;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Find a general cycle that uses every edge exactly once
 */
public class Hierholzer {
    static final class EdgeRef {
        final int id, to;
        EdgeRef(int id, int to) {
            this.id = id;
            this.to = to;
        }
    }

    private List<Integer> cycle;
    private List<EdgeRef>[] adj;
    private List<int[]> edgeList = new ArrayList<>();
    private final boolean[] used;
    private final int V,E;

    @SuppressWarnings("unchecked")
    public Hierholzer(Graph graph) {
        V=graph.V();
        E = graph.E();
        adj = (List<EdgeRef>[]) new ArrayList[graph.V()];
        for (int v = 0; v < graph.V(); v++) {
            adj[v] = new ArrayList<>();
        }
        // copy edges, assign IDs 0..E-1
        int id = 0;
        for (int v = 0; v < graph.V(); v++) {
            for (int w : graph.adj(v)) {
                if (w > v) {                 // avoid adding twice
                    adj[v].add(new EdgeRef(id, w));
                    adj[w].add(new EdgeRef(id, v));
                    id++;
                }
            }
        }
        used = new boolean[id];
    }

    private int addEdge(int u, int v) {
        int id = edgeList.size();
        edgeList.add(new int[]{u, v});
        adj[u].add(new EdgeRef(id, v));
        adj[v].add(new EdgeRef(id, u));
        return id;
    }

    private List<Integer> findCycle(List<Integer>[] adj) {
        // 1. check all degrees are even and connected
        if(!isEulerian()) return List.of();

        // 2. pick a start with degree >0
        int s = firstNonIsolated();

        // 3. Create a stack and a list to store cycle
        Deque<Integer> stack = new ArrayDeque<>();
        List<Integer> tour = new ArrayList<>();
        stack.push(s);

        while (!stack.isEmpty()) {
            int v = stack.peek();
            if (!adj[v].isEmpty()) {
                int w = adj[v].remove(adj[v].size() - 1);
                adj[w].remove((Integer) v);
                stack.push(w);
            } else { //stuck, all edges of current v are used
                tour.add(stack.pop());
            }
        }
        return tour;

    }

    private int firstNonIsolated() {
        for (int v = 0; v < V; v++) if (!adj[v].isEmpty()) return v;
        return 0;          // graph has no edges
    }

    private boolean isEulerian(){
        // is all degree even
        for(int v=0;v<V;v++){
            if(adj[v].size()%2!=0) return false;
        }

        // connected on non-isolated vertices
        boolean[] seen = new boolean[V];
        Deque<Integer> dq = new ArrayDeque<>();
        int start = firstNonIsolated();
        dq.add(start);
        seen[start]=true;
        while(!dq.isEmpty()){
            int v = dq.remove();
            for(EdgeRef e:adj[v]){
                if(!seen[e.to]){
                    seen[e.to] = true;
                    dq.add(e.to);
                }
            }
        }
        for(int v=0;v<V;v++){
            if(!adj[v].isEmpty()&& !seen[v]) return false;
        }
        return true;
    }
}
