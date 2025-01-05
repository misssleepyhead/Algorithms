import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 1.2.1 exercises
 * Point2D client that takes an integer value N from the command line,
 * generates N random points in the unit square,
 * and computes the distance separating the closest pair of points.
 */
public class TestPoint2D {
    public static void main(String[] args) {
        int N = Integer.valueOf(args[0]);
        Point2D[] points = new Point2D[N];

        // Variables to track the closest pair
        double closestDistance = Double.POSITIVE_INFINITY; // Start with a large value
        Point2D closestPointA = null;
        Point2D closestPointB = null;

        // generate N random points
        for (int i = 0; i < N; i++) {
            int x = StdRandom.uniformInt(100);
            int y = StdRandom.uniformInt(100);
            Point2D newPoint = new Point2D(x, y);

            // if the point is same with the current points
            for (int j = 0; j < i; j++) {
                double distance = points[j].distanceTo(newPoint);

                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestPointA = points[j];
                    closestPointB = newPoint;
                }
            }
            // add new point to the array
            points[i] = newPoint;

        }
        // Output the closest pair and the distance
        System.out.printf("Closest pair: %s and %s\n", closestPointA, closestPointB);
        System.out.printf("Distance: %.5f\n", closestDistance);


    }
}
