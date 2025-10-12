import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;

/**Interview question 2: Hamiltonian path in a DAG. Given a directed acyclic graph,
 * design a linear-time algorithm to determine whether it has a Hamiltonian path (a simple path that visits every vertex)
 *
 * use kahn's algorithm to producing the topological order*/
public class KahnTopological {
    private int[] order;
    private boolean hasHamilton;

    public KahnTopological(Digraph g){
        int n = g.V();
        int[] indeg = new int[n];
        for(int v=0;v<n;v++){
            for(int w:g.adj(v)) indeg[w]++; // initial in-degree list
        }

        Queue<Integer> q = new Queue<>();
        for(int v=0;v<n;v++) {
            if(indeg[v]==0) q.enqueue(v); // add initial source to the queue
        }

        order = new int[n];
        int idx = 0;
        hasHamilton = true;

        while(!q.isEmpty()){
            if(q.size()>1) hasHamilton = false; // more than one source meaning no edge between them(gap), a hamiltonian path is impossible
            int v =q.dequeue();
            order[idx++] = v;
            for(int w:g.adj(v)){
                if(--indeg[w]==0) q.enqueue(w); // decrement indegree of w, if w becomes a source, add to the queue
            }
        }

        if(idx!=n){
            order = null;
            hasHamilton = false;
            // if queue is empty but the graph still has vertex, meaning cycle
        }
    }
    public int[] order()         { return order; }
    public boolean isDAG()       { return order != null; }
    public boolean hasHamiltonPath() { return hasHamilton && isDAG(); }


}
