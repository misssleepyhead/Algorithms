import edu.princeton.cs.algs4.Point2D;

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

    public void insert(PointNode p) {
        if (p == null) throw new IllegalArgumentException();
        if (root == null) {
            root =p;
            size++;
            return;
        }



    }


}
