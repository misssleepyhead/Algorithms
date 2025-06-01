package Module10;


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

        public Node(Key key, Value value, int size, boolean color) {
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


    public boolean contains(Key key) {
        return get(key) != null;
    }

    public void put(Key key, Value value) {
        // search for key. update value if found; grew table if new
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node h, Key key, Value val) {
        if (h == null) return new Node(key, val, 1, RED); // insert at bottom and red link

        int cmp = key.compareTo(h.key);
        if (cmp > 0) h.right = put(h.right, key, val);
        else if (cmp < 0) h.left = put(h.left, key, val);
        else h.value = val;

        // adjust structure if needed
        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h); // lean left
        if (isRed(h.right) && !isRed(h.left.left)) h = rotateRight(h); // balance 4-node
        if (isRed(h.right) && isRed(h.left)) flipColors(h); // split 4 node

        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }

    // get, floor, ceiling, min, max functions are same as BST
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

    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    public Key max() {
        return max(root).key;
    }

    public Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        return x == null ? null : x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        else if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        return t == null ? x : t; // x could be the floor but maybe t is closer to the key

    }

    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        return x == null ? null : x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        else if (cmp > 0) return ceiling(x.right, key);
        Node t = ceiling(x.left, key);
        return t == null ? x : t;
    }

    // creative problem 33: Certification, check that no node is connected to two red links and that there are no right-leaning red links.
    public boolean is23() {
        return is23(root);

    }

    private boolean is23(Node x) {
        if (x == null) return true;
        if (isRed(x.right)) return false;
        if (isRed(x) && isRed(x.left)) return false;
        return is23(x.left) && is23(x.right);
    }

    // check that all paths from the root to a null link have the same number of black links.
    // text book answer
    public boolean isBalanced() {
        int black = 0;
        Node x = root;
        // first calculate the  required black-height for all root to null paths
        while (x != null) {
            if (!isRed(x)) black++;
            x = x.left;
        }
        return isBalanced(root, black);
    }

    private boolean isBalanced(Node x, int black) {
        if (x == null) return black == 0;
        if (!isRed(x)) black--;
        return isBalanced(x.left, black) && isBalanced(x.right, black);

    }

    //
    public boolean isBalanced2() {
        return blackHeight(root) != -1;
    }

    private int blackHeight(Node x) {
        if (x == null) return 0;
        int left = blackHeight(x.left);
        int right = blackHeight(x.right);

        // if any node the left and tight black height differ, return -1(unbalanced)
        if (left == -1 || right == -1) return -1;
        if (left != right) return -1; // ensure left and right paths black height are the same
        // count black links here, add 1 if x is black 0 if red
        return left + (isRed(x) ? 0 : 1);
    }

    // helper function for deletion
    private Node moveRedLeft(Node h) {
        // Assuming that h is black, and both h.left, h.left.left are black
        // make h.left or one of its children red
        flipColors(h); // turn h red and its children black, make a temp 4 node
        // if h.right.left is not red, we just color flip in this function
        // if it is red, we borrow a red link from the sibling
        if (isRed(h.right.left)) {
            h.right = rotateRight(h);
            h = rotateLeft(h);
        }
        return h;
    }

    public boolean isBST() {
        return isBST(root, null, null);
    }

    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) return true;

        if (min != null && (x.key.compareTo(min) <= 0)) return false;
        if (max != null && (x.key.compareTo(max) >= 0)) return false;

        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);

    }

    public boolean isRedBlackBST(){
        return isBST() && isBalanced()&&is23();
    }


    private Node moveRedRight(Node h) {
        // we want to borrow the red link from the left sibling
        flipColors(h);
        if (isRed(h.left.left)) {
            h.right = rotateRight(h);
            flipColors(h);
        }
        return h;
    }
}
