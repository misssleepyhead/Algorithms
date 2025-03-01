package collinear;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class BruteCollinearPoints {
    Point[] points;
    private static final Point origin = new Point(0, 0);
    Comparator<Point> comparator;
    List<LineSegment> segments;

    Point[] aux;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        int n=points.length;
        this.points = Arrays.copyOf(points, n);
        comparator = origin.slopeOrder();

    }

    private double[] slopesOfPoints(Point[] points) {
        double[] slopes = new double[points.length];
        for (int i = 0; i < points.length; i++) {
            slopes[i] = origin.slopeTo(points[i]);
        }

        Arrays.sort(points,(a,b)-> Double.compare(origin.slopeTo(a),origin.slopeTo(b)));
        return slopes;
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

