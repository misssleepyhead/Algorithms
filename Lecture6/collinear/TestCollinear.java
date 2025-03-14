package collinear;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for collinear
 */
public class TestCollinear {
    Point[] input6 = {new Point(19000,10000), new Point(18000,10000),new Point(32000,10000), new Point(21000,10000),new Point(14000,10000),new Point(1234,5678)};

    @Test
    public void testNoCollinearPointsBrute() {
        Point[] points = {new Point(100, 100), new Point(200, 200), new Point(300, 500)};
        BruteCollinearPoints collinearPoints = new BruteCollinearPoints(points);
        assertEquals(0, collinearPoints.numberOfSegments());
    }

    @Test
    public void testNoCollinearPointsFast() {
        Point[] points = {new Point(100, 100), new Point(200, 200), new Point(300, 500)};
        FastCollinearPoints fp = new FastCollinearPoints(points);
        assertEquals(0, fp.numberOfSegments());
    }

    @Test
    public void testBruteBasicCollinear(){
        BruteCollinearPoints bp = new BruteCollinearPoints(input6);
        LineSegment[] segments = bp.segments();

        // expected result: (14000,10000) -> (32000,10000)
        LineSegment expected = new LineSegment(new Point(14000,10000), new Point(32000,10000));
        assertEquals(expected.toString(), segments[0].toString());

    }

    @Test
    public void testFastBasicCollinear(){
        FastCollinearPoints fp = new FastCollinearPoints(input6);
        LineSegment[] segments = fp.segments();

        // expected result: (14000,10000) -> (32000,10000)
        LineSegment expected = new LineSegment(new Point(14000,10000), new Point(32000,10000));
        assertEquals(expected.toString(), segments[0].toString());
    }
}
// Trigger GitHub Actions
// Trigger GitHub Actions
// Trigger GitHub Actions
