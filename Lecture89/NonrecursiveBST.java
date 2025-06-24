import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

/**
 * Exercise 13: non-recursive implementations of get(), put(), and keys() for BST
 */
public class NonrecursiveBST<Key extends Comparable<Key>, Value> {
    private class TreeNode {
        private Key key;
        private Value val;

        private TreeNode left, right;

        public TreeNode(Key key, Value val) {
            this.key = key;
            this.val = val;

        }
    }

    private TreeNode root;
    private int size = 0;


    public void put(Key key, Value value) {
        TreeNode z = new TreeNode(key, value);
        if (root == null) {
            size++;
            root = z;
            return;
        }
        TreeNode parent = null, x = root;
        while (x != null) {
            parent = x;
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else {
                x.val = value;
                return;
            }
        }

        int cmp = key.compareTo(parent.key);
        if (cmp > 0) parent.right = z;
        else parent.left = z;
        size++;


    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Value get(Key key) {
        if (isEmpty()) return null;
        TreeNode x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp > 0) x = x.right;
            else if (cmp < 0) x = x.left;
            else {
                return x.val;
            }
        }
        return null;

    }

    // inorder traversal of BST to collect keys in ascending order
    public Iterable<Key> keys() {
        Queue<Key> queue = new LinkedList<>(); // stores the result(sorted keys)
        Stack<TreeNode> stack = new Stack<>(); // help simulate recursion
        TreeNode x = root;

        while (x != null || !stack.isEmpty()) { // continue the loop while there are nodes to process
            // either going down the tree, or deferred nodes in the stack
            while (x != null) {
                stack.push(x); // save current node
                x = x.left; // inorder : go left
            }
            // after hit a null left child, process node and go right
            // 1. pop the last node
            x = stack.pop();
            // 2. store it to queue
            queue.add(x.key);
            // 3. explore the right tree
            x = x.right;

        }
        return queue;

    }

    // 3.2.14 nonrecursive min()
    public Key min() {
        if (root == null) return null;
        if (root.left == null) return root.key;
        TreeNode x = root;

        while (x.left != null) {
            x = x.left;
        }
        return x.key;
    }

    // 3.2.14 nonrecursive max()
    public Key max() {
        if (root == null) return null;
        TreeNode x = root;
        while (x.right != null) {
            x = x.right;
        }
        return x.key;
    }

    // nonrecursive floor()
    public Key floor(Key key) {
        TreeNode x = root;
        Key floor = null; // best so far

        while (x != null) {
            int cmp = key.compareTo(x.key);

            if (cmp == 0) return x.key; // exact match ➜ floor is the key itself
            else if (cmp < 0) {
                x = x.left;   // key < x.key  ➜ go left to look for smaller keys
            } else {
                floor = x.key;   // key > x.key  ➜ x.key is a candidate floor, remember it
                x = x.right;     // …and try to find a larger (but still ≤ key) candidate
            }
        }
        return floor;
    }


    public Key ceiling(Key key) {
        if(root==null) return null;
        TreeNode x = root;
        Key bestCeil = null; // best so far

        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) return x.key;
            else if (cmp > 0) {
                x = x.right;
            } else {
                bestCeil= x.key;
                x = x.left;
            }
        }
        return bestCeil;
    }

}
