/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 1.2.10
 * A visualCounter class that allows increment and decrement operation
 * take N and max in the constructor, N is the maximum number of operation
 * max is the maximum absolute value for the counter
 * As side effects, create a plot showing the value of the counter each time its tally change
 */
public class VisualCounter {
    private final int N;
    private final int max;

    private int operationCounts;
    private int count;


    public VisualCounter(int n, int max) {

        operationCounts = 0; // current Operation count
        count = 0;
        N = n;
        this.max = max;
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(-max, max);
        StdDraw.setPenRadius(0.01);
    }

    public void increment(double val) {
        if (operationCounts < N && Math.abs(count + val) <= max) {
            operationCounts++;
            count += val;
            StdDraw.setPenColor(StdDraw.DARK_GRAY);
            StdDraw.point(operationCounts, count);
        }
        else {
            throw new RuntimeException("Already in the maximum operation times");
        }
    }

    public void decrement(int val) {
        if (operationCounts < N && Math.abs(count - val) <= max) {
            operationCounts++;
            count -= val;
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.point(operationCounts, count);
        }
        else {
            throw new RuntimeException(
                    "Already in the maximum operation times or the count can not be lower than 0");
        }

    }

    public String toString() {
        return "Count" + count;
    }

    public static void main(String[] args) {
        VisualCounter visualCounter = new VisualCounter(2000, 3000);
        // Perform random increments and decrements
        for (int i = 0; i < 2000; i++) {
            if (StdRandom.uniform() > 0.5) {
                visualCounter.increment(StdRandom.uniformInt(10));
            }
            else {
                visualCounter.decrement(StdRandom.uniformInt(10));
            }
        }

    }
}
