import java.util.Random;

/**
 * Interview question 3
 * Shuffling a linked list. Given a singly-linked list containing n items,
 * rearrange the items uniformly at random.
 * Your algorithm should consume a logarithmic (or constant) amount of extra memory and run in time proportional to
 * nlogn in the worst case.
 * <p>
 * Solution:
 * 1. Cannot use Knuth shuffle, cause linked list did not support direct indexing and swapping, will cause
 * swap O(n) and total complexity O(n^2)
 * 2. Use merge sort + randomly split
 */
class Node {
    int val;
    Node next;

    public Node(int val) {
        this.val = val;
        next = null;
    }
}

public class ShuffleLInkedList {


    private static final Random rand = new Random();

    // shuffle a linked list
    public static Node shuffle(Node head) {
        if (head == null || head.next == null) return head;

        Node[] halves = splitList(head);
        Node first = shuffle(halves[0]);
        Node second = shuffle(halves[1]);

        return mergeRandom(first, second);


    }

    // spilt the list into two halves
    private static Node[] splitList(Node head) {
        if (head == null || head.next == null) return new Node[]{head, null};

        Node slower = head, fast = head, prev = null;

        // randomly move pointers
        // fast is move twice faster than slower, use fast to control iterations
        while (fast != null && fast.next != null) {
            if (rand.nextBoolean()) { // randomly decide whether to move slow
                prev = slower;
                slower = slower.next;
            }
            fast = fast.next.next;
        }

        // cut the list into two halves
        if (prev != null) {
            prev.next = null;
        }
        return new Node[]{head, slower};
    }

    private static Node mergeRandom(Node l1, Node l2) {

        if (l1 == null) return l2;
        if (l2 == null) return l1;

        Node head;
        if (rand.nextBoolean()) {
            head = l1;
            l1 = l1.next;
        } else {
            head = l2;
            l2 = l2.next;
        }

        Node curr = head;

        while (l1 != null && l2 != null) {
            if (rand.nextBoolean()) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        if (l1 != null) curr.next = l1;
        if (l2 != null) curr.next = l2;

        return head;
    }

    private static void printList(Node head) {
        while (head != null) {
            System.out.print(head.val + "");
            head = head.next;
        }
        System.out.println();
    }

    // Test the shuffle function
    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next = new Node(7);
        head.next.next.next.next.next.next.next = new Node(8);

        System.out.println("Original List:");
        printList(head);

        Node shuffledHead = shuffle(head);

        System.out.println("Shuffled List:");
        printList(shuffledHead);
    }
}
