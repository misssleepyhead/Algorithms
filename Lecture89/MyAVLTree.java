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
        int height = 1; //leaf height

        AVLNode(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }

    private AVLNode<Key, Value> root;

    public MyAVLTree() {
    }

    /*-------- utility -----------*/

    // generate balance factor of the node, balance factor should be either -1,0,1
    public int bf(AVLNode<Key, Value> node) {

        return height(node.left) - height(node.right); // keep the sign, cause can use it to check which side child is imbalance
    }

    private int height(AVLNode<Key, Value> n) {
        return (n == null) ? 0 : n.height;
    }

    private void refresh(AVLNode<Key, Value> x) {
        x.height = 1 + Math.max(height(x.left), height(x.right)); // only take the maximum height
    }

    /*-------- insert -----------*/

    public void insert(Key key, Value val) {
        root = insert(root, key, val);
    }

    private AVLNode<Key, Value> insert(AVLNode<Key, Value> node, Key key, Value value) {
        if (node == null) return new AVLNode<Key, Value>(key, value);
        int cmp = key.compareTo(node.key);
        if (cmp > 0) node.right = insert(node.right, key, value);
        else if (cmp < 0) node.left = insert(node.left, key, value);
        else node.val = value; // update
        refresh(node); // refresh the height
        return rebalance(node);


    }



    /*-------- rotations -----------*/

    private AVLNode<Key, Value> rotateRight(AVLNode<Key, Value> x) {
        AVLNode<Key, Value> h = x.left;
        x.left = h.right;
        h.right = x;
        refresh(x);
        refresh(h);

        return h;
    }

    private AVLNode<Key, Value> rotateLeft(AVLNode<Key, Value> x) {
        AVLNode<Key, Value> h = x.right;
        x.right = h.left;
        h.left = x;
        refresh(x);
        refresh(h);

        return h;
    }

    // note that there is a core rotation for left-heavy(right-rotation) and right-heavy(left-rotation),
    // but for LR, RL, have to handle little "wrinkle", so we do that little fix first,
    // then fo the core rotation in the end(return), avoid four almost-duplicate block
    // keep height update in one place, cleaner code.
    private AVLNode<Key, Value> rebalance(AVLNode<Key, Value> z) {
        if (bf(z) == 2) {                  // left-heavy
            if (bf(z.left) < 0)            // LR: child leans right
                z.left = rotateLeft(z.left);
            return rotateRight(z);         // LL or finished LR
        }
        if (bf(z) == -2) {                 // right-heavy
            if (bf(z.right) > 0)           // RL: child leans left
                z.right = rotateRight(z.right);
            return rotateLeft(z);          // RR or finished RL
        }
        return z;                          // already balanced
    }
}
