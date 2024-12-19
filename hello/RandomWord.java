/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String output = "";
        int i = 1;
        System.out.println(args.length);
        String word = StdIn.readString();
        System.out.println(word);
        while (StdIn.isEmpty()) {
            System.out.println("Reading word: " + output + " (index " + i + ")");
            if (StdRandom.bernoulli(1.0 / i)) {
                output = word;
            }
            i++;
        }
        System.out.println(output);

    }
}
