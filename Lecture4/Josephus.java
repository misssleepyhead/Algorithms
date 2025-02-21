import edu.princeton.cs.algs4.In;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 37. Josephus problem
 * given Integer N people, Integer M for count eliminate,
 * arrange people in 0 to N-1 proceed around the circle,
 * eliminating every Mth person until only one person is left.
 * print out the results of every elimination
 * Write a Queue client Josephus.java that takes M and N from the command line
 * and prints out the order in which people are eliminated
 * (and thus would show Josephus where to sit in the circle).
 * Example: Josephus 2 7
 * 1 3 5 0 4 2 6
 */
public class Josephus {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("usage: java josephus <M> <N>");
            return;
        }
        int M = Integer.parseInt(args[0]);
        int N = Integer.parseInt(args[1]);

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            queue.add(i);
        }

        while (queue.size() > 1) {
            for (int i = 0; i < M - 1; i++) {
                queue.add(queue.poll()); // remove front and re-add to the back except the Mth element
            }
            // Eliminate the Mth person
            System.out.println(queue.poll() + "");
        }
        System.out.println("Josephus" + queue.peek());
    }

}
