import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class FastCollinearPoints {
    private Point[] points;
    private List<LineSegment> segments;
    private int n;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] inputPoints) {
        if (inputPoints == null) throw new IllegalArgumentException("Input array is null");

        n = inputPoints.length;
        points = Arrays.copyOf(inputPoints, n);
        Arrays.sort(points); // Sort to ensure lexicographic order

        // Check for null and duplicate points
        for (Point p : points) {
            if (p == null) throw new IllegalArgumentException("Point cannot be null");
        }
        for (int i = 0; i < n - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                throw new IllegalArgumentException("Duplicate points detected");
            }
        }

        segments = new ArrayList<>();
        // Find collinear segments
        findSegments();

    }

    private void findSegments() {
        for (int i = 0; i < n; i++) {
            Point origin = points[i];

            // 1. create point[] but exclude origin
            Point[] otherPoints = new Point[n - 1];
            int index = 0;
            for (int j = 0; j < n; j++) {
                if (j != i) otherPoints[index++] = points[j];
            }

            // 2. use quick sort to sort other points
            Arrays.sort(otherPoints, origin.slopeOrder());

            // 3. find groups of at least 3 points with the same slope
            int j = 0;
            while (j < otherPoints.length) {
                List<Point> group = new ArrayList<>();
                group.add(origin);
                double slope = origin.slopeTo(otherPoints[j]);

                while (j < otherPoints.length && origin.slopeTo(otherPoints[j]) == slope) {
                    group.add(otherPoints[j]);
                    j++;
                }

                // if we found 4+ collinear, add to segment
                if (group.size() >= 4) {
                    Collections.sort(group); // sort(null) = Comparator.naturalOrder()
                    Point start = group.get(0);
                    Point end = group.get(group.size() - 1);

                    // **Only add the segment if the origin is the smallest point**
                    if (origin.compareTo(start) == 0) {
                        segments.add(new LineSegment(start, end));
                    }
                }
            }
        }
    }

    // quicksort 3 way
    private void quickSort3way(Point[] points, int lo, int hi, Point origin) {
        if (hi <= lo) return;

        int lt = lo, gt = hi;
        double pivotSlope = origin.slopeTo(points[lo]);
        int i = lo + 1;

        while (i <= gt) {
            double currentSlope = origin.slopeTo(points[i]);
            if (Double.compare(currentSlope, pivotSlope) < 0) exch(points, lt++, i++);
            else if (Double.compare(currentSlope, pivotSlope) > 0) exch(points, i, gt--);
            else i++;
        }
        quickSort3way(points, lo, lt - 1, origin);
        quickSort3way(points, gt + 1, hi, origin);

    }

    private void exch(Point[] points, int i, int j) {
        Point temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[0]);
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
