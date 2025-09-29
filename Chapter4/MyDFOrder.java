import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;


/**
 * Book page 580
 * pre/post/reverse post order of dfs
 */
public class MyDFOrder {
    private boolean[] marked;
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversePost;

    public MyDFOrder(MyDigraph g) {
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();
        marked = new boolean[g.V()];

        for (int v = 0; v < g.V(); v++) {
            if (!marked[v]) dfs(g, v);
        }
    }

    private void dfs(MyDigraph g, int v) {
        pre.enqueue(v);
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
        post.enqueue(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }


}
