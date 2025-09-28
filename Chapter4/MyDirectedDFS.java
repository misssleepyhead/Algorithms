import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * Book example page 571
 * Reachability in digraphs
 */

public class MyDirectedDFS {
    private boolean[] marked;

    public MyDirectedDFS(MyDigraph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    public MyDirectedDFS(MyDigraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        for (int s : sources) {
            if (!marked[s]) dfs(G, s);
        }
    }

    private void dfs(MyDigraph g, int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public static void main(String[] args) {
        MyDigraph g = new MyDigraph(new In(args[0]));
        Bag<Integer> sources = new Bag<>();
        for (int i = 1; i < args.length; i++) {
            sources.add(Integer.parseInt(args[i]));
        }

        MyDirectedDFS reachable = new MyDirectedDFS(g, sources);
        for (int v = 0; v < g.V(); v++) {
            if (reachable.marked(v)) System.out.println(v + " ");
        }
        System.out.println();

    }
}
