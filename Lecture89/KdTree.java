import edu.princeton.cs.algs4.Point2D;

/**
 *  Module 11 assignment: KD-Trees
 *  2d-tree implementation.
 *  uses a 2d-tree to implement the same API (but replace PointSET with KdTree).
 *  A 2d-tree is a generalization of a BST to two-dimensional keys. The idea is to build a BST with points in the nodes,
 *  using the x- and y-coordinates of the points as keys in strictly alternating sequence.*/
public class KdTree {

    private static class PointNode{
        private final Point2D point; // store x-, y- coordinates as Key
        private PointNode left;
        private PointNode right;

        public PointNode(Point2D point) {
            this.point = point;
        }
    }
    public KdTree(){

    }

}
