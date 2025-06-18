import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.List;

/**
 * Module 11 assignment: KD-Trees
 * 2d-tree implementation.
 * uses a 2d-tree to implement the same API (but replace PointSET with KdTree).
 * A 2d-tree is a generalization of a BST to two-dimensional keys. The idea is to build a BST with points in the nodes,
 * using the x- and y-coordinates of the points as keys in strictly alternating sequence.
 */
public class KdTree {

    private static class PointNode {
        private final Point2D point; // store x-, y- coordinates as Key
        private boolean oddLevel; //oddLevel -> vertical line -> use X
        private PointNode left;
        private PointNode right;

        public PointNode(Point2D point, boolean oddLevel) {
            this.point = point;
            this.oddLevel = oddLevel;
        }

        public void draw() {
            if (left != null) {
                left.draw();
            }
            point.draw();
            if (right != null) right.draw();
        }
    }

    private PointNode root;
    private int size;


    public KdTree() {

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (root == null || !contains(p)) {
            size++;
            root = insert(root, p, true);
        }

    }

    private PointNode insert(PointNode h, Point2D p, Boolean oddLevel) {
        if (h == null) return new PointNode(p, oddLevel);

        if (oddLevel) {
            if (p.x() < h.point.x()) h.left = insert(h.left, p, !oddLevel);
            else h.right = insert(h.right, p, !oddLevel);
        } else {
            if (p.y() < h.point.y()) h.left = insert(h.left, p, oddLevel);
            else h.right = insert(h.right, p, oddLevel);
        }
        return h;
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        return contains(root, p);

    }

    private boolean contains(PointNode h, Point2D p) {
        if (h == null) return false;
        if (h.point.equals(p)) return true;

        if (h.oddLevel) {
            if (p.x() < h.point.x()) return contains(h.left, p);
            else return contains(h.right, p);
        } else {
            if (p.y() < h.point.y()) return contains(h.left, p);
            else return contains(h.right, p);
        }
    }

    public void draw() {
        if (!isEmpty()) root.draw();
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        List<Point2D> list = new ArrayList<>();

        if (!isEmpty()) range(rect, root, list);
        return list;
    }

    private void range(RectHV rect, PointNode node, List<Point2D> list) {
        if (node == null) return;

        if (rect.contains(node.point)) list.add(node.point);

        if (node.oddLevel) {
            double px = node.point.x();
            if (rect.xmin() <= px) range(rect, node.left, list);
            if (rect.xmax() >= px) range(rect, node.right, list);
        } else {
            double py = node.point.y();
            if (rect.ymin() <= py) range(rect, node.left, list);
            if (rect.ymax() >= py) range(rect, node.right, list);
        }

    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        return nearest(p, root, null, Double.POSITIVE_INFINITY);


    }

    private Point2D nearest(Point2D p, PointNode node, Point2D best, double bestDist) {
        if (node == null) return best;
        if (p.distanceTo(node.point) < bestDist) {
            best = node.point;
            bestDist = p.distanceTo(node.point);
        }
    }


}
