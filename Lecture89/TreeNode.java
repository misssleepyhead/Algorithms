public class TreeNode <Key extends Comparable<Key>, Value>{
    private Key key;
    private Value val;
    private TreeNode left, right;


    public TreeNode(Key key, Value val) {
        this.key = key;
        this.val = val;

    }
}