import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;

import java.util.Stack;

/**
 * 4.4.12
 */
public class EdgeWeightedCycleFinder {
    private boolean[] marked;
    private DirectedEdge[] edgeTo;
    private Stack<DirectedEdge> cycle;
    private boolean[] onStack;

    public EdgeWeightedCycleFinder(EdgeWeightedDigraph g){
        int n=g.V();
        marked = new boolean[n];
        edgeTo = new DirectedEdge[n];
        onStack = new boolean[n];
        for(int v=0;v<n;v++){
            if(!marked[v] && cycle==null) dfs(g,v);
        }
    }

    private void dfs(EdgeWeightedDigraph g, int v){
        onStack[v]=true;
        marked[v]=true;

        for(DirectedEdge e:g.adj(v)){
            int w = e.to();
            if(this.hasCycle()) return;
            else if(!marked[w]){
                edgeTo[w]=e;
                dfs(g,w);
            } else if (onStack[w]) {
                cycle=new Stack<>();
                DirectedEdge f=e;
                while (f.from()!=w){
                    cycle.push(f);
                    f=edgeTo[f.from()];
                }
                cycle.push(f);
            }

        }
        onStack[v]=false;

    }
    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<DirectedEdge> cycle() {
        return cycle;
    }
}
