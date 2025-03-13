package collinear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
            if (p == null) throw new IllegalArgumentException("collinear.Point cannot be null");
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

        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                double slopeIJ = points[i].slopeTo(points[j]);
                List<Point> collinearGroup = new ArrayList<>();
                collinearGroup.add(points[i]);
                collinearGroup.add(points[j]);

                for (int k = j + 1; k < n; k++) {
                    if (Double.compare(slopeIJ, points[i].slopeTo(points[k])) == 0) {
                        collinearGroup.add(points[k]);
                    }
                }

                // If 4 or more points are collinear, store the segment
                if (collinearGroup.size() >= 4) {
                    Collections.sort(collinearGroup);
                    segments.add(new LineSegment(collinearGroup.get(0), collinearGroup.get(collinearGroup.size() - 1)));
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

