/**
 * web exercise 1 : The great tree-list recursion problem
 * BST and a circular doubly linked list are conceptually built from the same type of nodes -
 * a data field and two references to other nodes.
 * Given a binary search tree, rearrange the references
 * so that it becomes a circular doubly-linked list (in sorted order)
 * Hint: create a circularly linked list A from the left subtree,
 * a circularly linked list B from the right subtree,
 * and make the root a one node circularly linked list. Then merge the three lists
 */
public class BSTdoublyLinkedList<Key extends Comparable<Key>> {
    class TreeNode {
        private Key key;
        private TreeNode left, right; // left = prev, right = next in doubly linked list


        public TreeNode(Key key) {
            this.key = key;

        }
    }

    public TreeNode buildFromBST(TreeNode root) {
        return build(root);

    }

    private TreeNode build(TreeNode root) {
        if (root == null) return null;

        TreeNode leftList = build(root.left);
        TreeNode rightList = build(root.right);

        // make root itself a doubly linked list itself, prev, next all itself
        root.left = root;
        root.right = root;


        // Concatenate leftList, root, and rightList
        TreeNode temp = concatenate(leftList, root);
        return concatenate(temp, rightList);


    }

    private TreeNode concatenate(TreeNode a, TreeNode b) {
        if (a == null) return b;
        if (b == null) return a;

        TreeNode aLast = a.left; // the last node in the list
        TreeNode bLast = b.left;

        aLast.right = b; // link the enn of a to the start of b
        b.left = aLast;

        bLast.right = a;
        a.left = bLast;

        return a;


    }


}
