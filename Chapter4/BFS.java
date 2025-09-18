import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Queue;

public class BFS {
    private boolean[] marked;

    public BFS(Graph G, int s){
        marked = new boolean[G.V()];
        bfs(G,s);

    }

    private void bfs(Graph g, int s){
        Queue<Integer> queue = new Queue<>();
        marked[s] = true;
        queue.enqueue(s);
        while (!queue.isEmpty()){
            int v = queue.dequeue();
            for(int w:g.adj(v)){
                if(!marked[w]){
                    marked[w] = true;
                    queue.enqueue(w);
                }
            }
        }
    }
}
