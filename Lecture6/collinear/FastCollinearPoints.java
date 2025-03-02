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
        Arrays.sort(this.points);
        segments = new ArrayList<>();

    }

    private void findSegments(Point[] sortedPoints){
        for (Point p : sortedPoints) {
            if (p == null) throw new IllegalArgumentException("Point cannot be null");
        }

        for (int i = 0; i < n - 1; i++) {
            if (sortedPoints[i].compareTo(sortedPoints[i + 1]) == 0) {
                throw new IllegalArgumentException("Duplicate points detected");
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.size();
    }

    // the line segments
    public LineSegment[] segments() {
    }
}
