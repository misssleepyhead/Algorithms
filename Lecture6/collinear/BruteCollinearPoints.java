package collinear;

import java.util.Comparator;

public class BruteCollinearPoints {
    Point[] points;
    private static final Point origin = new Point(0, 0);
    double[] slopes;
    Comparator<Point> comparator;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        this.points = points;
        slopes = slopesOfPoints(points);
        comparator = origin.slopeOrder();
    }

    private double[] slopesOfPoints(Point[] points) {
        double[] slopes = new double[points.length];
        for (int i = 0; i < points.length; i++) {
            slopes[i] = origin.slopeTo(points[i]);
        }
        return slopes;
    }

    // the number of line segments
    public int numberOfSegments() {
        int counts=0;

    }



    // the line segments
    public LineSegment[] segments() {
    }
}

