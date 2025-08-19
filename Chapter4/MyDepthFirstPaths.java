import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Stack;

/**depth-first search to find paths in a graph
 * maintains a vertex-indexed array edgeTo[] such that edgeTo[w]=v means that v-w was the edge used to access w.
 * the edgeTo[] array is a parent-link representation of a tree rooted at s that contains all edges connected to s*/
public class MyDepthFirstPaths {
    private boolean[] marked; // visited vertices list
    private int[] edgeTo; // last vertex on know path to this vertec
    private final int s; // source

    public MyDepthFirstPaths(Graph g, int s){
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        this.s = s;
        dfs(g,s);
    }

    private void dfs(Graph g, int v){
        marked[v] = true;
        for(int w:g.adj(v)){
            if(!marked[w]){
                edgeTo[w]=v;
                dfs(g,w);
            }
        }
    }
    public boolean hasPathTo(int v){
        return marked[v];
    }
    public Iterable<Integer> pathTo(int v){
        if(!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        // putting each vertex encountered onto a stack until met s
        for(int x=v;x!=s;x=edgeTo[x]){
            path.push(x);
        }
        path.push(s);
        return path;

    }
}
