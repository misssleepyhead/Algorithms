import edu.princeton.cs.algs4.Graph;

/**
 * Book example 4.1
 * solving:
 * 1. cycle detection: Is the given graph acylic?
 */
public class Cycle {
    private boolean[] marked;
    private boolean hasCycle;

    public Cycle(Graph g){
        marked = new boolean[g.V()];
        for(int s=0;s<g.V();s++){
            if(!marked[s]){
                dfs(g,s,s);
            }
        }
    }

    private void dfs(Graph g, int v, int u){
        marked[v] = true;
        for(int w:g.adj(v)){
            if(!marked[w]){
                dfs(g,w,v); // pass current node v as "parent" u
            } else if (w!=u) {
                hasCycle = true; // visit a visited neighbor that is not parent -> cycle
            }
        }
    }
    public boolean isHasCycle(){
        return hasCycle;
    }
}
