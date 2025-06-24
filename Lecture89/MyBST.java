import java.util.*;

/**
 * Binary search tree symbol table
 */
public class MyBST<Key extends Comparable<Key>, Value> {
    class TreeNode {
        private Key key;
        private Value val;
        private TreeNode left, right;
        private int N;

        public TreeNode(Key key, Value val, int n) {
            this.key = key;
            this.val = val;
            N = n;
        }
    }

    private TreeNode root;

    public MyBST() {
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size(root);
    }

    private int size(TreeNode treeNode) {
        if (treeNode == null) return 0;
        else return treeNode.N;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(TreeNode x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    // exercises 3.2.13 get() nonrecursive
    public Value get2(Key key) {
        TreeNode x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) return x.val;
            else if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
        }
        return null;
    }


    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private TreeNode put(TreeNode x, Key key, Value val) {
        if (x == null) return new TreeNode(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    // exercises 3.2.13 put() non-recursive
    public void put2(Key key, Value value) {
        if (root == null) {
            root = new TreeNode(key, value, 1);
            return;
        }

        TreeNode x = root;
        while (true) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) {
                // key already exist, update to new value
                x.val = value;
                return;
            } else if (cmp < 0) {
                if (x.left == null) {
                    int n = size(x.left) + size(x.right) + 1;
                    x.left = new TreeNode(key, value, n);
                    return;
                } else {
                    x = x.left;
                }

            } else {
                // cmp>0
                if (x.right == null) {
                    int n = size(x.left) + size(x.right) + 1;
                    x.right = new TreeNode(key, value, n);
                } else {
                    x = x.right;
                }
            }
        }
    }


    public Key min() {
        return min(root).key;
    }

    private TreeNode min(TreeNode x) {
        // if left link is null, then min is the root
        if (x.left == null) return x;
        return min(x.left);
    }

    public Key max() {
        return max(root).key;
    }

    private TreeNode max(TreeNode x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    public Key select(int rank) {
        return select(root, rank).key;
    }

    private TreeNode select(TreeNode x, int rank) {
        if (x == null) return null;
        int t = size(root.left);
        if (t > rank) return select(root.left, rank);
        else if (t < rank) return select(root.right, rank - t - 1);
        else return x;
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, TreeNode x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }

    public Key floor(Key key) {
        TreeNode x = floor(root, key);
        if (x != null) return x.key;
        else return null;
    }

    private TreeNode floor(TreeNode x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        else if (cmp < 0) return floor(x.left, key);
        TreeNode t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private TreeNode deleteMin(TreeNode x) {
        if (x.left == null) return x.right; //the min is the x itself, we return x.right but x.right may not exist
        x.left = deleteMin(x.left); // then we mark the parent node of the min node 's child >> null
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private TreeNode delete(TreeNode x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            // these two are for simple cases: no child or one child
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;

            // case 3: node has two children
            TreeNode t = x;
            x = min(t.right); // get the successor(smallest in right subtree)
            x.right = deleteMin(t.right); // remove that successor from the original spot, assign x.right to new right subtree after delete the min(the new root)
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;

    }

    private void print(TreeNode x) { // inorder traversal
        if (x == null) return;
        print(x.left); // first print all the keys in the left
        System.out.println(x.key); // print the root
        print(x.right); // print keys in the right
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    private Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new ArrayDeque<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(TreeNode x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi); // search left if needed
        if (cmplo <= 0 && cmphi >= 0)
            queue.add(x.key); // the current node is in the range[lo,hi], so we add it to the queue
        if (cmphi > 0) keys(x.right, queue, lo, hi); // search right if needed
    }

    // Exercise 6: return tree height
    public int height_a() {
        return height_a(root);

    }

    private int height_a(TreeNode node) {
        if (node == null) return 1;
        return 1 + Math.max(height_a(node.left), height_a(node.right));
    }

    // adds a field to each node in the tree (and takes linear space and constant time per query).
    public int height_b() {
        return root.N;
    }

    // creative problem 31: Certification, takes a Node as argument
    // and returns true if the argument node is the root of a binary search tree, false otherwise.
    public boolean isBST(TreeNode x) {
        return isBST(x, null, null);
    }

    // when checking left subtree, we update max to current node(x) 's key and compare
    // we don't care the min in left subtree, just the child never larger than the max
    // so is right subtree
    private boolean isBST(TreeNode x, Key min, Key max) {
        if (x == null) return true;

        if (min != null && x.key.compareTo(min) <= 0) return false;
        if (max != null && x.key.compareTo(max) >= 0) return false;

        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    // Creative problem 3.2.31 Equal key check:
    // takes a node as argument and return true if there is no equal keys in the binary tree rooted at the argument
    public boolean hasNoDuplicate(TreeNode node) {
        HashSet<Key> set = new HashSet<>();
        return dfs(node, set);

    }

    // use hashset to solve this problem
    private boolean dfs(TreeNode x, HashSet<Key> set) {
        if (x == null) return true;
        if (set.contains(x.key)) return false;
        set.add(x.key);
        return dfs(x.left, set) && dfs(x.right, set);


    }


}
