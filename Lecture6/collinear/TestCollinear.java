package collinear;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
/** Tests for collinear*/
public class TestCollinear {

    @Test
    public void testNoCollinearPointsBrute(){
        Point[] points={new Point(100,100),new Point(200,200),new Point(300,500)};
        BruteCollinearPoints collinearPoints = new BruteCollinearPoints(points);
        assertEquals(0,collinearPoints.numberOfSegments());
    }

    @Test
    public void testNoCollinearPointsFast(){
        Point[] points={new Point(100,100),new Point(200,200),new Point(300,500)};
        FastCollinearPoints fp=new FastCollinearPoints(points);
        assertEquals(0,fp.numberOfSegments());
    }
}
// Trigger GitHub Actions
