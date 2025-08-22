import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

/**Text book practice: breadth first search tp find paths in a grapg*/
public class MyBFSPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public MyBFSPaths(Graph g, int s){
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        this.s = s;
        bfs(g,s);
    }
    private void bfs(Graph g, int s){
        Queue<Integer> queue = new Queue<>();
        marked[s] = true;
        queue.enqueue(s);
        while (!queue.isEmpty()){
            int v = queue.dequeue();
            for(int w:g.adj(v)){
                if(!marked[w]){
                    edgeTo[w]=v;
                    marked[w]=true;
                    queue.enqueue(w);
                }
            }
        }
    }
    public boolean hasPathTo(int v){
        return marked[v];
    }
    public Iterable<Integer> pathTo(int v){
        if(!hasPathTo(v)) return null;
        Queue<Integer> path = new Queue<>();
        for(int x=v;x!=s;x=edgeTo[x]){
            path.enqueue(x);
        }
        path.enqueue(s);
        return path;
    }
}
