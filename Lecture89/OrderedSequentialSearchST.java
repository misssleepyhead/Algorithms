/**
 * Exercises 3.1.3 develop ST uses ordered linked list
 */
public class OrderedSequentialSearchST<Key extends Comparable<Key>, Value> {
    private class Node {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    private Node first;
    private int size;

    public OrderedSequentialSearchST() {
        this.first = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Value get(Key key) {
        if (isEmpty()) return null;
        Node current = first;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp == 0) return current.val;
            if (cmp < 0) return null; // key is smaller than all keys after, search miss
            current = current.next;
        }
        return null;
    }

    public void put(Key key, Value val) {
        if (isEmpty() || key.compareTo(first.key) < 0) {
            first = new Node(key, val, first);
            size++;
            return;
        }


        Node curent = first;
        while (curent.next != null && key.compareTo(curent.next.key) > 0) {
            curent = curent.next;
        }

        if (curent.next != null && key.compareTo(curent.next.key) == 0) {
            curent.next.val = val;
        } else {
            curent.next = new Node(key, val, curent.next);
        }
        size++;
    }

    public void delete(Key key) {
        if (isEmpty()) return;
        // special case: key is first
        if (key.equals(first.key)) {
            first = first.next;
            size--;
            return;
        }
        Node dummy = first;
        Node curr = first.next;

        while (curr != null) {
            if (key.equals(curr.key)) {
                dummy.next = curr.next;
                size--;
                return;
            }
            dummy = curr;
            curr = curr.next;
        }
    }

    public void printKeys() {
        Node current = first;
        while (current != null) {
            System.out.print(current.key + " ");
            current = current.next;
        }
        System.out.println();
    }


}
