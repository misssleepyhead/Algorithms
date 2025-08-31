import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

/**
 * Book example 4.1 Symbol graph
 */
public class MySymbolGraph {
    private ST<String, Integer> st; // String -> Integer
    private String[] keys; // index -> String
    private Graph G; // the graph with integer vertex names

    public MySymbolGraph(String stream, String sp) {
        st = new ST<>();
        In in = new In(stream);
        while (in.hasNextLine()) { // First pass: build the index
            String[] a = in.readLine().split(sp); // by reading strings to associate each distinct string with an index
            for (int i = 0; i < a.length; i++) {
                if (!st.contains(a[i])) {
                    st.put(a[i], st.size()); // "movie1" -> 0, "performer 1" ->1,..
                }
            }
        }
        keys = new String[st.size()]; // inverted index to get string keys in an array
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }
        G = new Graph(st.size());
        in = new In(stream);
        while (in.hasNextLine()) { // second pass: build the graph
            String[] a = in.readLine().split(sp); // by connecting the first vertex on each line to all the others
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                G.addEdge(v, st.get(a[i]));
            }

        }
    }

    public boolean contains(String s) {
        return st.contains(s);
    }

    public int index(String s) {
        return st.get(s);
    }

    public String name(int v) {
        return keys[v];
    }

    public Graph G() {
        return G;
    }

}
