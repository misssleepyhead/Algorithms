import java.util.TreeMap;

/**
 * 3.3 Web exercises 4: substring reversing
 * Given a string of length N, support the following operations: select(i) = get the ith character,
 * and reverse(i, j) = reverse the substring from i to j
 * <p>
 * Solution sketch. Maintain the string in a balanced search tree, where each node records the subtree count and
 * a reverse bit (that interchanges the role of the left and right children if there are an odd number of reverse bits on the path
 * from the root to the node). To implement select(i), do a binary search starting at the root,
 * using the subtree counts and reverse bits. To implement reverse(i, j), split the BST at select(i) and select(j) to
 * form three BSTs, reverse the bit of the middle BST,
 * and join them back together using a join operation. Maintain the subtree counts and reverse bits when rotating.
 * <p>
 * <p>
 * why store subtree count?
 * This is essentially a binary search based on the rank (position) of the character,
 * and the subtree counts are crucial for determining ranks.
 * for select(i), says you want the 5th character, if the left child's subtree count is 4, then root is 5th,
 * if the left subtree count is <4, then 5th must be in the right subtree, then can adjust target index.
 * <p>
 * what is reverse bit?
 * To efficiently reverse a whole substring, need to mark which subtree should be read in reverse order.
 * if rev is true, meaning the roles of the left and right subtrees are swapped for this whole subtree.
 * and whenever someone read or splits this subtree later, read it in backwards. no characters move, just a flag. speed up huge.
 */
public class SubstringOps {
    static class StringNode {
        private char c;
        StringNode left;
        StringNode right;
        private int size;
        private boolean rev;

        public StringNode(char c) {
            this.c = c;
            this.rev = false;
            this.size = 1;

        }
    }

    private StringNode root;

    public SubstringOps(String str) {
        char[] a = str.toCharArray();
        root = build(a, 0, a.length - 1);

    }

    // divide and conquer bulk build, perfectly balanced
    private StringNode build(char[] a, int lo, int hi) {
        if (lo > hi) return null;
        int mid = (lo + hi) >>> 1;
        StringNode node = new StringNode(a[mid]);
        node.left = build(a, lo, mid - 1);
        node.right = build(a, mid + 1, hi);
        update(node);
        return node;
    }

    // update new size of each node
    private void update(StringNode x) {
        x.size = 1 + size(x.left) + size(x.right);
    }

    private int size(StringNode x) {
        return (x == null) ? 0 : x.size;
    }

    public char select(int i) {
        if (i < 0 || i >= size(root)) throw new IndexOutOfBoundsException();
        return select(root, i);
    }

    private char select(StringNode x, int i) {
        int leftSize = size(x.left);
        if (i < leftSize) return select(x.left, i);
        else if (i == leftSize) return x.c;
        else return select(x.right, i - leftSize - 1); // go right, adjust rank
    }

    // reverse(i,j)
    /**
     * SPLIT:
     * 1. split at i, will separate the string into two parts: [0, i-1] and [i, N-1].
     * 2. split at j, so separate the [i, N-1] part into [i, j] and [j+1, N-1].
     * 3. after two split, we get prefix: [0, i-1], middle: [i, j], suffix: [j+1, N-1]
     *
     * toggle the reverse bit: set the rev flag true on the root of the middle BST
     * Join back: re-join the three BST
     *
     * */
    public void reverse(int i, int j){

    }
}
