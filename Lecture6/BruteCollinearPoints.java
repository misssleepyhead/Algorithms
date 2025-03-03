import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class BruteCollinearPoints {
    Point[] points;
    List<LineSegment> segments;
    int n;


    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if(points==null) throw new IllegalArgumentException();
        n = points.length;
        this.points = Arrays.copyOf(points, n);
        Arrays.sort(this.points);
        segments = new ArrayList<>();
        slopesOfPoints(this.points);


    }

    // brute force
    private void slopesOfPoints(Point[] sortedPoints) {
        for (Point p : sortedPoints) {
            if (p == null) throw new IllegalArgumentException("Point cannot be null");
        }

        for (int i = 0; i < n - 1; i++) {
            if (sortedPoints[i].compareTo(sortedPoints[i + 1]) == 0) {
                throw new IllegalArgumentException("Duplicate points detected");
            }
        }
        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                Point p = sortedPoints[i];
                Point q = sortedPoints[j];
                double pqSlope = p.slopeTo(q);

                for (int k = j + 1; k < n - 1; k++) {
                    Point r = sortedPoints[k];
                    double pkSlope = p.slopeTo(r);
                    if (pqSlope != pkSlope) break; // break if three points are not collinear

                    for (int l = k + 1; l < n; l++) {
                        Point s = sortedPoints[l];
                        double psSlope = p.slopeTo(s);
                        if(pqSlope==psSlope){
                            segments.add(new LineSegment(p,s));
                        }else {
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

