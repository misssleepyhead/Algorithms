
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


/**
 * web exercise 7: Level-order traversal reconstruction of a BST.
 * Given a sequence of keys, design a linear-time algorithm
 * to determine whether it is the level-order traversal of some BST (and construct the BST itself).
 */

public class LevelOrderConstruct {
    private static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    static class NodeWithBounds {
        private TreeNode treeNode;
        private int min;
        private int max;

        public NodeWithBounds(TreeNode treeNode, int min, int max) {
            this.treeNode = treeNode;
            this.min = min;
            this.max = max;
        }
    }

    public static TreeNode levelOrderConstruct(int[] keys) {
        if (keys.length == 0) return null;
        TreeNode root = new TreeNode(keys[0]);
        Queue<NodeWithBounds> queue = new LinkedList<>();
        int i = 1;

        // adding two potential child positions of the root
        queue.offer(new NodeWithBounds(root, Integer.MIN_VALUE, root.val)); // left slot
        queue.offer(new NodeWithBounds(root, root.val, Integer.MAX_VALUE)); // right

        while (i < keys.length) {
            int val = keys[i];
            while (!queue.isEmpty()) {
                NodeWithBounds spot = queue.poll();
                if (val > spot.min && val < spot.max) { // check the val is in the valid range
                    TreeNode newNode = new TreeNode(val); // valid, create a new node
                    if (val < spot.treeNode.val) {
                        spot.treeNode.left = newNode;
                    } else {
                        spot.treeNode.right = newNode;
                    }

                    queue.offer(new NodeWithBounds(newNode, spot.min, val));
                    queue.offer(new NodeWithBounds(newNode, val, spot.max));
                    i++;
                    break;

                }
            }
        }
        return root;

    }

    /**
     * Creative problem 3.2.37 Level order traversal
     */
    public static void printLevel(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            System.out.print(curr.val);
            if (curr.left != null) queue.offer(curr.left);
            if (curr.right != null) queue.offer(curr.right);
        }

    }

    /**
     * Web Exercise 6: Reverse a BST. Given a standard BST
     * design a linear-time algorithm to transform it into a reverese BST
     * (where each key is smaller than the keys in its left subtree and greater than the keys in its right subtree).
     * The resulting tree shape should be symmetric to the original one.
     */
    public TreeNode reverse(TreeNode root) {
        if (root == null) {
            return null;
        }

        // recursively reverse the left and right subtrees
        TreeNode reversedLeft = reverse(root.left);
        TreeNode reversedRight = reverse(root.right);

        root.left = reversedRight;
        root.right = reversedLeft;
        return root;

    }

    // Example Usage (for testing purposes)
    public static void main(String[] args) {
        LevelOrderConstruct sol = new LevelOrderConstruct();
        LevelOrderConstruct.TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        printInOrder(root);
        printLevel(root);
        System.out.println();
        TreeNode reversed = sol.reverse(root);
        printInOrder(reversed);
    }

    // Helper method to print the tree in-order (left-root-right)
    public static void printInOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        printInOrder(node.left);
        System.out.print(node.val + " ");
        printInOrder(node.right);
    }


}
