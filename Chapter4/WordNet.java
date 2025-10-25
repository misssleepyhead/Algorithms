import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordNet {
    private Map<String, Bag<Integer>> nounID; // a noun might has multiple ids
    private String[] synsetOf; // id and its synset
    private Digraph graph;
    private final SAP sap;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) {
            throw new IllegalArgumentException();
        }
        readSynsetFile(synsets);
        readHypernyms(hypernyms);
        rootDAGValidate();
        sap = new SAP(graph);

    }

    private void readSynsetFile(String filename) {
        nounID = new HashMap<>();
        In in = new In(filename);
        List<String> lines = new ArrayList<>();

        while (in.hasNextLine()) {
            lines.add(in.readLine());
        }

        synsetOf = new String[lines.size()];
        for (String line : lines) {
            String[] l = line.split(",", 3); // split line by comma, maximum 3 split set, to avoid the comma in the gloss make another useless spilt
            int id = Integer.parseInt(l[0]);
            // add synset to string[id]
            String synset = l[1];
            synsetOf[id] = synset;

            for (String noun : synset.split(" ")) {
                // if noun(key) not exist, create a new key value pair
                nounID.computeIfAbsent(noun, k -> new Bag<>()).add(id);
            }
        }
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return nounID.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null) throw new IllegalArgumentException();
        return nounID.containsKey(word);
    }

    // build a directed graph by reading the Hypernyms
    private void readHypernyms(String filename) {
        In in = new In(filename);
        graph = new Digraph(synsetOf.length);
        // example : 34, 111,222 (id, hypernyms, hypernyms)
        while (in.hasNextLine()) {
            String[] l = in.readLine().split(",");
            int v = Integer.parseInt(l[0]);
            for (int i = 1; i < l.length; i++) {
                graph.addEdge(v, Integer.parseInt(l[i]));
            }
        }
    }

    // the graph must be rooted DAG
    private void rootDAGValidate() {
        // 1. check if the graph is acyclic (no cycle)
        if (new DirectedCycle(graph).hasCycle()) {
            throw new IllegalArgumentException("not acyclic");
        }
        // 2. check if there is a root (outdegree =0) and there should be "one" root
        int root = 0;
        for (int v = 0; v < graph.V(); v++) {
            if (graph.outdegree(v) == 0) {
                root++;
            }
        }
        if (root != 1) throw new IllegalArgumentException("not rooted");

    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (nounA == null || nounB == null) throw new IllegalArgumentException();
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        Bag<Integer> idsA = nounID.get(nounA);
        Bag<Integer> idsB = nounID.get(nounB);
        return sap.length(idsA, idsB);

    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (nounA == null || nounB == null) throw new IllegalArgumentException();
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        Bag<Integer> idsA = nounID.get(nounA);
        Bag<Integer> idsB = nounID.get(nounB);
        int ancestorId = sap.ancestor(idsA, idsB);

        return synsetOf[ancestorId];
    }

    // do unit testing of this class
    public static void main(String[] args) {
    }
}
