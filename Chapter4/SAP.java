import edu.princeton.cs.algs4.Digraph;

public class SAP {
    private final Digraph G;
    private int shortLength = -1;
    private int shortAncestor = -1;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        if (G == null) throw new IllegalArgumentException();
        this.G = new Digraph(G);
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        if(v==w) shortLength=0;

    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        if(v==w) shortAncestor=v;
    }


    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if(v==null || w==null) throw new IllegalArgumentException();
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if(v==null || w==null) throw new IllegalArgumentException();
    }

    private void validateVertex(int v){
        int V = G.V();
        if(v<0 ||v>V) throw new IllegalArgumentException();
    }
    // do unit testing of this class
    public static void main(String[] args) {
    }
}