import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;

/**
 * 3.5  application, inverted index lookup
 */

public class InvertedLookUp {
    public static void main(String[] args) {
        In in = new In(args[0]); // file
        String sp = args[1]; // separator

        ST<String, Queue<String>> st = new ST<>();
        ST<String, Queue<String>> ts = new ST<>();

        while (in.hasNextLine()) {
            String[] a = in.readLine().split(sp);
            String key = a[0];
            for (int i = 1; i < a.length; i++) {
                String val = a[i];
                if (!st.contains(key)) st.put(key, new Queue<>());
                if (!ts.contains(val)) ts.put(val, new Queue<>());
                st.get(key).enqueue(val);
                ts.get(val).enqueue(key);
            }
        }

        while (!StdIn.isEmpty()) {
            String query = StdIn.readLine();
            if (st.contains(query)) {
                for (String s : st.get(query)) {
                    System.out.println(" " + s);
                }
            }

            if (ts.contains(query)) {
                for (String s : ts.get(query)) System.out.println(" " + s);
            }
        }
    }
}
