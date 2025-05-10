
/**
 * Binary search tree symbol table
 */
public class MyBST<Key extends Comparable<Key>, Value> {
    private class TreeNode {
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

    public boolean contains(Key key){
        return get(key)!=null;
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

    public Key min(){
        return min(root).key;
    }
    private TreeNode min(TreeNode x){
        // if left link is null, then min is the root
        if(x.left==null) return x;
        return min(x.left);
    }
    public Key max(){
        return max(root).key;
    }

    private TreeNode max(TreeNode x){
        if(x.right==null) return x;
        return max(x.right);
    }

    public Key select(int rank){
        return select(root,rank).key;
    }

    private TreeNode select(TreeNode x, int rank){
        if(x==null) return null;
        int t = size(root.left);
        if(t>rank) return select(root.left,rank);
        else if (t<rank) return select(root.right,rank-t-1);
        else return x;
    }

    public int rank(Key key){
        return rank(key,root);
    }

    private int rank(Key key, TreeNode x){
        if (x==null) return 0;
        int cmp=key.compareTo(x.key);
        if(cmp<0) return rank(key,x.left);
        else if (cmp>0) return 1+size(x.left)+rank(key,x.right);
        else return size(x.left);
    }


}
