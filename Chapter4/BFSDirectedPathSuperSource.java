import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

/**
 * Super source BFS path: use super source to solve multiple source directed path
 */
public class BFSDirectedPathSuperSource {
    private static final int INF = Integer.MAX_VALUE;

    private final int originalV;
    private final int superS;
    private final Digraph g2;

    private final boolean[] marked;
    private final int[] edgeTo;
    private final int[] distTo;

    public BFSDirectedPathSuperSource(Digraph g, Iterable<Integer> source) {
        this.originalV = g.V();
        this.superS = originalV;
        this.g2 = withSuperSource(g, source);
        this.marked = new boolean[originalV + 1];
        this.edgeTo = new int[originalV + 1];
        this.distTo = new int[originalV + 1];

        for (int i = 0; i < distTo.length; i++) distTo[i] = INF;

        bfsSuperSource(g2, superS);
    }

    private void bfsSuperSource(Digraph g, int s) {
        Queue<Integer> queue = new Queue<>();
        marked[s] = true;
        distTo[s] = 0;
        edgeTo[s] = s;
        queue.enqueue(s);

        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w : g.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    queue.enqueue(w);
                }
            }
        }
    }

    private Digraph withSuperSource(Digraph g, Iterable<Integer> sources) {
        // add a super source into the graph
        int superS = g.V();
        Digraph g2 = new Digraph(g.V() + 1);

        // copy edges from g to g2
        for (int v = 0; v < g.V(); v++) {
            for (int w : g.adj(v)) {
                g2.addEdge(v, w);
            }
        }

        // connect super source to each real source
        for (int s : sources) {
            g2.addEdge(superS, s);
        }
        return g2;
    }

    public Iterable<Integer> pathFromSomeSourceTo(int v) {
        // one shortest path from some source to v. null if unreachable
        if (!marked[v]) return null;

        Stack<Integer> reverse = new Stack<>();
        int x = v;

        // climb parent until reaching super source
        while (x != superS) {
            reverse.push(x);
            x = edgeTo[x];
        }
        // reverse currently yields v..source; flip it
        Stack<Integer> path = new Stack<>();
        while (!reverse.isEmpty()) path.push(reverse.pop());

        // now path is source.. v
        return path;
    }
}
