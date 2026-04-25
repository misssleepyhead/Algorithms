import edu.princeton.cs.algs4.*;

/**
 * Book page 581
 * topological sort
 */
public class MyTopological {
    private Iterable<Integer> order;

    public MyTopological(MyDigraph g) {
        MyDirectedCycle cycleFinder = new MyDirectedCycle(g);
        // check for cycles
        if (!cycleFinder.hasCycle()) {
            // then topological sort
            MyDFOrder dfs = new MyDFOrder(g);
            order = dfs.reversePost();
        }
    }

    public MyTopological(Digraph g) {
        DirectedCycle cycleFinder = new DirectedCycle(g);
        if (!cycleFinder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(g);
            order = dfs.reversePost();
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean isDAG() {
        return order == null;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: MyTopological <graphfile> [delimiter]");
            System.exit(1);
        }
        String filename = args[0];
        String separator = args[1];
        SymbolDigraph sg = new SymbolDigraph(filename, separator);
        MyTopological topo = new MyTopological(sg.G());

        for (int v : topo.order()) {
            System.out.println(sg.name(v));
        }

    }
}
