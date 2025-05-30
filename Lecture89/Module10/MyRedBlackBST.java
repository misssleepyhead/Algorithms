package Module10;

import com.sun.jdi.Value;

/**
 * Implement red black BST
 */
public class MyRedBlackBST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    private class Node {
        // node with color representation
        Key key;
        Value value;
        Node left, right;
        int size;
        boolean color;

        public Node(Key key, Value value, int size, boolean color,) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.size = size;
        }
    }

    public MyRedBlackBST() {
    }

    private boolean isRed(Node x) {
        if (x == null) return false; // null links are black
        return x.color == RED;
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }

    public int size() {
        return size(root);
    }

    public boolean isEmpty() {
        return root == null;
    }

    private Node rotateLeft(Node h) {
        assert isRed(h.right);
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);
        return x;
    }

    private Node rotateRight(Node h) {
        assert isRed(h.left);
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = 1 + size(h.right) + size(h.left);
        return x;
    }

    private void flipColors(Node h) {
        assert !isRed(h);
        assert isRed(h.left);
        assert isRed(h.right);
        h.color = RED;
        h.right.color = BLACK;
        h.left.color = BLACK;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp > 0) return get(x.right, key);
        else if (cmp < 0) return get(x.left, key);
        else return x.value;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public void put(Key key, Value value) {
        // search for key. update value if found; grew table if new
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node h, Key key, Value val) {
        if (h == null) return new Node(key, val, 1, RED); // standard insert, with red link to parent

        int cmp = key.compareTo(h.key);
        if (cmp > 0) h.right = put(h.right, key, val);
        else if (cmp < 0) h.left = put(h.left, key, val);
        else h.value = val;

        // adjust structure if needed
        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.right) && !isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.right) && isRed(h.left)) flipColors(h);

        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }
}
