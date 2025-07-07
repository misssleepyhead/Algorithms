/**
 * 3.3.32 AVL tree
 * imp a st API using AVL tree structure, this approach is
 * keep a height field in each node, use rotation to adjust the height imbalance
 */

public class MyAVLTree<Key extends Comparable<Key>, Value> {

    private static class AVLNode<Key, Value> {
        Key key;
        Value val;
        AVLNode<Key, Value> left, right;
        int height = 0;

        public AVLNode(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }

    private AVLNode<Key,Value> root;

    public MyAVLTree() {
    }

    // generate balance factor of the node
    public int bf(AVLNode node){
        int h=node.left.height-node.right.height;
        return Math.abs(h);
    }
}
