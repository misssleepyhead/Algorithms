import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

/**
 * symbol table client
 * <p>
 * finds the number of occurrences of each string in a sequence of strings from standard input
 */

public class FrequencyCounter {
    public static void main(String[] args) {
        int minlen = Integer.parseInt(args[0]); // key-length cutoff
        MyST<String, Integer> st = new MyST<>();
        while (!StdIn.isEmpty()) {
            // build st and count frequencies
            String word = StdIn.readString();
            if(word.length()<minlen) continue;
            if(!st.contains(word)) st.put(word,1);
            else st.put(word,st.get(word)+1);
        }

        // find a key with the highest frequency count
        String max="";
        st.put(max,0);
        for(String s:st.keys()){
            if(st.get(s)>st.get(max)){
                max=s;
            }
        }
        System.out.println(max+""+st.get(max));

    }


}
