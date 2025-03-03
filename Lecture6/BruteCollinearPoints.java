import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
    private Point[] points;
    private List<LineSegment> segments;
    private int n;


    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] inputPoints) {
        if (inputPoints == null) throw new IllegalArgumentException();
        n = inputPoints.length;
        for (Point p : inputPoints) {
            if (p == null) throw new IllegalArgumentException("Point cannot be null");
        }

        for (int i = 0; i < n - 1; i++) {
            if (inputPoints[i].compareTo(inputPoints[i + 1]) == 0) {
                throw new IllegalArgumentException("Duplicate points detected");
            }
        }
        points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = inputPoints[i];
        }

        Arrays.sort(points);
        n = points.length;
        segments = new ArrayList<>();

        // brute force
        // store all collinear points
        Point[][] allPoints = new Point[n * n][];

        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                Point p = points[i];
                Point q = points[j];
                double pqSlope = p.slopeTo(q);

                for (int k = j + 1; k < n - 1; k++) {
                    Point r = points[k];
                    double pkSlope = p.slopeTo(r);
                    if (pqSlope != pkSlope) break; // break if three points are not collinear

                    for (int m = k + 1; m < n; m++) {
                        Point s = points[m];
                        double psSlope = p.slopeTo(s);
                        if (pqSlope == psSlope) {
                            segments.add(new LineSegment(p, s));
                        } else {
                            break;
                        }

                    }
                }
            }
        }

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

