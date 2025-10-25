import java.util.HashMap;
import java.util.Map;

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
        int maxSum = -1;
        String worst = null;

        Map<String, Integer> cache = new HashMap<>();

        for (int i = 0; i < nouns.length; i++) {
            String current = nouns[i];
            if (!wordNet.isNoun(current)) throw new IllegalArgumentException();

            int sum = 0;
            for (int j = 0; j < nouns.length; j++) {
                String b = nouns[j];
                if (i == j) continue;

                String key = current.compareTo(b) < 0 ? current + "#" + b : b + "#" + current;
                Integer d = cache.get(key);
                if (d == null) {
                    d = wordNet.distance(current, b);
                    cache.put(key, d);
                }
                sum += d;
            }
            if (sum > maxSum) {
                maxSum = sum;
                worst = current;
            }
        }
        return worst;
    }

    public static void main(String[] args) {
    }
}
