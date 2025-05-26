
import java.util.LinkedList;
import java.util.Queue;


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
}
