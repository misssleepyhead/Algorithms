/**
 * 2.2.17 Linked list sort, imp a natural mergesort for linked list
 */
public class LinkedListMergeSort {

    class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
            next = null;
        }
    }

    public Node mergeSort(Node head) {
        if (head == null || head.next == null) return head;
        Node mid = divide(head);
        Node left = mergeSort(head);
        Node right = mergeSort(mid);
        return merge(left, right);
    }

    private Node divide(Node head) {
        if (head == null || head.next == null) return head;
        // find the middle node to split the list into two halves
        Node fast = head;
        Node slow = head;
        Node prev = null; // we need prev to split the list

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;

        }
        if (prev != null) prev.next = null; // split the list, head>prev = left half, slow>end = right half
        return slow;
    }

    private Node merge(Node l1, Node l2) {
        Node dummy = new Node(0);
        Node tail = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        tail.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }
}
