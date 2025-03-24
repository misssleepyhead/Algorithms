import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Interview question 3: Taxicab Number
 * A taxicab number is an integer that can be expressed as the sum of two cubes of positive integers in two different ways:
 * a^3 +b^3 = c^3+d^3
 * For example,
 * 1729 is the smallest taxicab number:9^3+10^3 = 1^3+12^3
 * Design an algorithm to find all taxicab numbers with a,b,c,d less than n
 */
public class Pair implements Comparable<Pair> {
    private int a, b;
    long sum;

    public Pair(int a, int b) {
        this.a = a;
        this.b = b;
        this.sum = (long) a * a * a + (long) b * b * b;
    }

    @Override
    public int compareTo(Pair other) {
        return Long.compare(this.sum, other.sum);
    }

    // version1 : time n^2 log n and space n square
    public static void findTaxicabNumbersV1(int n) {
        List<Pair> pairs = new ArrayList<>();

        // step 1: generate all pairs
        for (int a = 1; a < n; a++) {
            for (int b = a; b < n; b++) {
                pairs.add(new Pair(a, b)); // we have n*n pairs
            }
        }

        // step 2: sort all by sum
        Collections.sort(pairs);

        // step 3: scan for duplicates
        for (int i = 1; i < pairs.size(); i++) {
            if (pairs.get(i).sum == pairs.get(i - 1).sum) {
                Pair p1 = pairs.get(i - 1);
                Pair p2 = pairs.get(i);
                System.out.printf("%d^3 + %d^3 = %d^3 + %d^3 = %d\n",
                        p1.a, p1.b, p2.a, p2.b, p1.sum);
            }
        }
    }

    /**
     * Think of this like a symmetric matrix, we only want the upper triangle part
     * we only generate pairs that a<=b
     */
    public static void findTaxicabNumbersV2(int n) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        // step 1: Start by same a,b pair
        for (int a = 1; a < n; a++) {
            pq.add(new Pair(a, a));
        }

        Pair prev = null;

        //step 2 : process heap
        while (!pq.isEmpty()) {
            Pair curr = pq.poll();

            // step 3 : check if current sum equals prev sum
            if (prev != null && curr.sum == prev.sum) {
                System.out.printf("Taxicab number: %d = %d^3 + %d^3 = %d^3 + %d^3\n",
                        curr.sum, prev.a, prev.b, curr.a, curr.b);
            }

            // step 4: push (a,b+1) if in bounds
            if (curr.b + 1 < n) {
                pq.add(new Pair(curr.a, curr.b + 1));
            }
            prev = curr;
        }
    }

    public static void main(String[] args) {

        int n = 100; // You can increase this to find more taxicab numbers
        findTaxicabNumbersV2(n);
    }
}
