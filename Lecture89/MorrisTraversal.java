/**
 * Web Exercises 5. Tree traversal with constant memory
 * perform an inorder tree traversal with constant extra memory
 * (e.g., no function call stack).
 */
public class MorrisTraversal {
    class TreeNode {
        private int val;
        private TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    public void morris(TreeNode root) {
        TreeNode curr = root;

        while (curr != null) {
            if (curr.left == null) {
                System.out.println(curr.val + " ");
                curr = curr.right;
            } else {
                // find the inorder predecessor
                TreeNode pre = curr.left;
                while (pre.right != null && pre.right != curr) {
                    pre = pre.right;
                }

                // subcase 1: we haven't visited the curr's left subtree
                if (pre.right == null) {
                    pre.right = curr; // create a thread to the current node
                    curr = curr.left; // move to the start of the left sub tree
                } else {
                    // subcase 2: we already visit the left subtree,
                    // and we traverse the left subtree
                    // restore the tree
                    pre.right = null;
                    System.out.println(curr.val + " "); // visit curr
                    curr = curr.right; // move to the right subtree
                }
            }
        }
    }
}
