import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;

/**Class example: given a word, final all occurrences with their immediate contexts*/
public class Concordance {
    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] words = in.readAllStrings();
        ST<String, SET<Integer>> st = new ST<>(); // the place in the array that the word appears

        // read text and build index
        for(int i=0;i< words.length;i++){
            String s = words[i];
            if(!st.contains(s)){
                st.put(s,new SET<>());
            }
            SET<Integer> set = st.get(s);
            set.add(i);
        }

        while (!StdIn.isEmpty()){
            String query = StdIn.readString();
            SET<Integer> set=st.get(query);
            for(int k:set){
                System.out.println(k); // print out work[k-4] to k+4
            }
        }
    }
}
