package collinear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {
    Point[] points;
    int n;
    List<LineSegment> segments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        n = points.length;
        this.points = Arrays.copyOf(points, n);
        segments = new ArrayList<>();

    }

    private void findSegments(Point[] sortedPoints) {
        for (Point p : sortedPoints) {
            if (p == null) throw new IllegalArgumentException("Point cannot be null");
        }

        for (int i = 0; i < n - 1; i++) {
            if (sortedPoints[i].compareTo(sortedPoints[i + 1]) == 0) {
                throw new IllegalArgumentException("Duplicate points detected");
            }
        }

        for (int i = 0; i < n; i++) {
            Point origin = sortedPoints[i];

            // 1. create point[] but exclude origin
            Point[] otherPoints = new Point[n - 1];
            int index = 0;
            for (int j = 0; j < n; j++) {
                if (j != i) otherPoints[index++] = points[j];
            }

            // 2. use quick sort to sort other points
            quickSort3way(otherPoints, 0, otherPoints.length - 1, origin);

            // 3. find groups of at least 3 points with the same slope
            int j=0;
            while (j<otherPoints.length-2){

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
            if (currentSlope < pivotSlope) exch(points, lt++, i++);
            else if (currentSlope > pivotSlope) exch(points, i, gt--);
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
}
