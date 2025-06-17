import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Module 11 assignment: KD-Trees
 * Brute-force implementation
 */


public class PointSET {
    Set<Point2D> treeSet;

    // construct an empty set of points
    public PointSET() {
        treeSet = new TreeSet<>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return treeSet.isEmpty();
    }

    // number of points in the set
    public int size() {
        return treeSet.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (!treeSet.contains(p)) {
            treeSet.add(p);
        }
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        return treeSet.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        for (Point2D p : treeSet) {
            p.draw();
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        List<Point2D> inside = new ArrayList<>();
        for (Point2D p : treeSet) {
            if (rect.contains(p)) {
                inside.add(p);
            }
        }
        return inside;

    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (treeSet.isEmpty()) return null;
        double nearest = Double.POSITIVE_INFINITY;
        Point2D neighbor = null;
        for (Point2D point : treeSet) {
            if (p.distanceTo(point) < nearest) {
                nearest = p.distanceTo(point);
                neighbor = point;
            }
        }
        return neighbor;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
    }
}
