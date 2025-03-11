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
        int countGroup = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int m = k + 1; m < n; m++) {
                        if (Double.compare(points[i].slopeTo(points[j]), points[i].slopeTo(points[m])) == 0 &&
                                Double.compare(points[i].slopeTo(points[j]), points[i].slopeTo(points[k])) == 0) {
                            allPoints[countGroup] = new Point[2];
                            allPoints[countGroup][0] = points[i];
                            allPoints[countGroup][1] = points[j];
                            countGroup++;
                        }
                    }
                }
            }
        }

        LineSegment[] temp = new LineSegment[countGroup];
        int countLine = 0, j = 1;
        for (int i = 0; i < countGroup; i = j, i++) {
            double firstSlope = allPoints[i][0].slopeTo(allPoints[i][1]);
            while (j < countGroup) {
                if ((Double.compare(allPoints[i][0].slopeTo(allPoints[j][0]), firstSlope) != 0
                        && Double.compare(allPoints[i][0].slopeTo(allPoints[j][0]), Double.NEGATIVE_INFINITY) != 0) ||
                        ((Double.compare(allPoints[i][1].slopeTo(allPoints[j][1]), firstSlope) != 0
                                && Double.compare(allPoints[i][1].slopeTo(allPoints[j][1]), Double.NEGATIVE_INFINITY) != 0)
            }

        }


    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.size();

    }


    // the line segments
    public LineSegment[] segments() {
        LineSegment[] outputs = new LineSegment[segments.size()];
        re
    }
}

