import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The {@code LinkedListQueue} class represents a first-in-first-out (FIFO)
 * queue of generic items.
 * It supports the usual <em>enqueue</em> and <em>dequeue</em>
 * operations, along with methods for peeking at the first item,
 * testing if the queue is empty, and iterating through
 * the items in FIFO order.
 * <p>
 * This implementation uses a singly linked list with a static nested class for
 * linked-list nodes.
 */

public class LinkedListQueue<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int N;

    private static class Node <Item> {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    // add item to the last of the list
    public void enqueue(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        N++;
    }

    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }

    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        N--;
        return item;

    }

    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> currrent;

        public LinkedIterator(Node<Item> first) {
            currrent = first;
        }

        @Override
        public boolean hasNext() {
            return currrent != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = currrent.item;
            currrent = currrent.next;
            return item;

        }
    }

    public static void main(String[] args) {
        LinkedListQueue<String> queue = new LinkedListQueue();
        queue.enqueue("a");
        queue.enqueue("b");
        System.out.println(queue.dequeue());
    }
}
