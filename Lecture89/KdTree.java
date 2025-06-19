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
            if (p.y() < h.point.y()) h.left = insert(h.left, p, !oddLevel);
            else h.right = insert(h.right, p, !oddLevel);
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
        if (root == null) return null;
        return nearest(p, root, root.point, root.point.distanceSquaredTo(p), new RectHV(0, 0, 1, 1));


    }

    private Point2D nearest(Point2D p, PointNode node, Point2D best, double bestDist, RectHV rect) {
        if (node == null) return best;
        double dist = node.point.distanceSquaredTo(p);

        // 1. update best with current node
        if (dist < bestDist) {
            best = node.point;
            bestDist = dist;
        }

        // 2. Decide which child is the closer side
        PointNode nearChild, farChild;
        RectHV nearRect, farRect;
        if (node.oddLevel) {
            if (p.x() < node.point.x()) {
                nearChild = node.left;
                farChild = node.right;
                nearRect = new RectHV(rect.xmin(), rect.ymin(), node.point.x(), rect.ymax());
                farRect = new RectHV(node.point.x(), rect.ymin(), rect.xmax(), rect.ymax());
            } else {
                nearChild = node.right;
                farChild = node.left;
                nearRect = new RectHV(node.point.x(), rect.ymin(), rect.xmax(), rect.ymax());
                farRect = new RectHV(rect.xmin(), rect.ymin(), node.point.x(), rect.ymax());
            }

        } else {
            if (p.y() < node.point.y()) {
                nearChild = node.left;
                farChild = node.right;
                nearRect = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.point.y());
                farRect = new RectHV(rect.xmin(), node.point.y(), rect.xmax(), rect.ymax());
            } else {
                nearChild = node.right;
                farChild = node.left;
                nearRect = new RectHV(rect.xmin(), node.point.y(), rect.xmax(), rect.ymax());
                farRect = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.point.y());
            }
        }

        // 3. Recurse into the near side first
        best = nearest(p, nearChild, best, bestDist, nearRect);
        bestDist = best.distanceSquaredTo(p);

        // 4. Explore the far side only if its rect could contain closer points
        if (farChild != null && farRect.distanceSquaredTo(p) < bestDist) {
            best = nearest(p, farChild, best, bestDist, farRect);
        }
        return best;

    }


}
