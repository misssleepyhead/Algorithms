import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * an outcast means given a list of WordNet nouns(x1,x2,...xn) which noun is the least related ti the others?
 * di = distance(xi,x1)+distance(xi,x2)+..+ distance(xi,xn) and return a noun xt for which dt is maximum, note that distance(xi,xi) is 0
 */

public class Outcast {
    private final WordNet wordNet;

    // constructor takes a WordNet object
    public Outcast(WordNet wordnet) {
        if (wordnet == null) throw new IllegalArgumentException();
        this.wordNet = wordnet;
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {
        if (nouns == null) throw new IllegalArgumentException();

        int n = nouns.length;
        int[] sum = new int[n];

        for (int i = 0; i < n; i++) {
            if (!wordNet.isNoun(nouns[i])) throw new IllegalArgumentException();
            for (int j = i + 1; j < n; j++) {
                if (!wordNet.isNoun(nouns[j])) throw new IllegalArgumentException();

                int d = wordNet.distance(nouns[i], nouns[j]);
                sum[i] += d;
                sum[j] += d;
            }
        }

        int max = -1, idx = -1;
        for (int i = 0; i < n; i++) {
            if (sum[i] > max) {
                max = sum[i];
                idx = i;
            }
        }
        return idx == -1 ? null : nouns[idx];
    }

    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}
