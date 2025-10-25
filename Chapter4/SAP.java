import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.Digraph;


public class SAP {
    private final Digraph G;


    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        if (G == null) throw new IllegalArgumentException();
        this.G = new Digraph(G);
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        validateVertex(v);
        validateVertex(w);

        // covert integer to Iterable version
        Bag<Integer> bagV = new Bag<>();
        bagV.add(v);
        Bag<Integer> bagW = new Bag<>();
        bagW.add(w);
        return sap(bagV, bagW)[0];
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        // covert integer to Iterable version
        Bag<Integer> bagV = new Bag<>();
        bagV.add(v);
        Bag<Integer> bagW = new Bag<>();
        bagW.add(w);
        return sap(bagV, bagW)[1];
    }


    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        validateVertex(v);
        validateVertex(w);
        return sap(v, w)[0];
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        validateVertex(v);
        validateVertex(w);
        return sap(v, w)[1];
    }


    private int[] sap(Iterable<Integer> vs, Iterable<Integer> ws) {
        // run bfs from both set of vertices
        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(G, vs);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(G, ws);

        int shortLength = -1, shortAncestor = -1;

        // iterate over all vertices to find common ancestors
        for (int x = 0; x < G.V(); x++) {
            if (bfsV.hasPathTo(x) && bfsW.hasPathTo(x)) {
                int currentLength = bfsV.distTo(x) + bfsW.distTo(x);
                if (shortLength == -1 || currentLength < shortLength) {
                    shortLength = currentLength;
                    shortAncestor = x;
                }
            }
        }
        return new int[]{shortLength, shortAncestor};
    }

    private void validateVertex(int v) {
        int V = G.V();
        if (v < 0 || v > V) throw new IllegalArgumentException();
    }

    private void validateVertex(Iterable<Integer> vertices) {
        if (vertices == null) throw new IllegalArgumentException();
        int vertexCount = 0;
        for (Integer v : vertices) {
            vertexCount++;
            if (v == null) throw new IllegalArgumentException();
            validateVertex(v);
        }
        if (vertexCount == 0) throw new IllegalArgumentException();
    }

    // do unit testing of this class
    public static void main(String[] args) {
    }
}