import edu.princeton.cs.algs4.BST;

import java.util.Arrays;

/**
 * Creative problem: Perfect balance
 * imp inserts a set of keys into an initially empty BST such that the tree produced is
 * equivalent to binary search, in the sense that the sequence of compares done in the search
 * for any key in the BST is the same as the sequence of compares used by binary search for the same set of keys.
 */
public class PerfectBalanceBST {
    public static void insert(BST<String, Integer>  bst, String[] keys) {
        Arrays.sort(keys);
        insert(bst, keys, 0, keys.length - 1);
    }

    private static void insert(BST<String, Integer>  bst, String[] keys, int lo, int hi) {
        if (hi < lo) return;
        int mid = lo + (hi - lo) / 2;
        bst.put(keys[mid], mid);
        insert(bst, keys, lo, mid-1);
        insert(bst, keys, mid + 1, hi);
    }

    public static void main(String[] args) {
        String[] words = new String[]{"P", "E", "R", "F", "C", "T", "B", "I", "N", "A", "Y", "S", "R", "H"};
        BST<String, Integer> bst = new BST<>();
        insert(bst, words);
        for (String key : bst.keys()) {
            System.out.println(key + bst.get(key));
        }
    }


}
