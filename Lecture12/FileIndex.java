import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;

import java.io.File;

/**
 * 3.5 example, take file names from command line and use symbol table to build
 * and inverted index associating every word in any of the file with a set of file names where
 * the word can be found, then takes keyword queries from standard input, and produces its associated list of files
 */


public class FileIndex {
    public static void main(String[] args) {
        ST<String, SET<File>> st = new ST<>();

        for (String filename : args) {
            File file = new File(filename);
            In in = new In(file);
            while (!in.isEmpty()) {
                String word = in.readString();
                if (!st.contains(word)) st.put(word, new SET<>());
                SET<File> set = st.get(word);
                set.add(file);
            }
        }

        while (!StdIn.isEmpty()) {
            String query = StdIn.readString();
            if (st.contains(query)) {
                for (File file : st.get(query)) {
                    System.out.println(" " + file.getName());
                }
            }
        }
    }
}
