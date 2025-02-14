import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A double-ended queue or deque
 * is a generalization of a stack and a queue that supports adding and removing items
 * from either the front or the back of the data structure
 * Your deque implementation must support each deque operation
 * (including construction) in constant worst-case time.
 * A deque containing n items must use at most 48n + 192 bytes of memory.
 * Additionally, your iterator implementation must
 * support each operation (including construction) in constant worst-case time.
 */
public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int size;

    private static class Node<Item> {
        Item item;
        Node<Item> next;
        Node<Item> prev;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node<Item> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        first.prev=null;
        if (isEmpty()) last = first; // If it was empty, last must also be the new node
        else oldFirst.prev=first;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node<Item> oldlast = last;
        last = new Node<>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("empty deque");
        }
        Item item = first.item;
        first = first.next;
        size--;
        if (isEmpty()) last = null;
        else first.prev = null;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("empty deque");
        }

        Item item = last.item;
        last = last.prev;
        size --;

        if(isEmpty()) first=null;
        else last.next=null;
        return item;


    }

    // return an iterator over items in order from front to back
    @Override
    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }

    private class LinkedIterator implements Iterator<Item> {
        Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {

        // test 1: stack LIFO
        Deque<Integer> stack = new Deque<>();
        stack.addFirst(1);
        stack.addFirst(2);
        stack.addFirst(3);
        System.out.println(stack.size());
        System.out.println(stack.removeFirst());
        System.out.println(stack.removeFirst());
        System.out.println(stack.removeFirst());

        // queue FIFO
        Deque<Integer> queue = new Deque<>();
        queue.addLast(1);
        queue.addLast(2);
        queue.addLast(3);
        System.out.println(queue.removeLast());
        System.out.println(queue.removeLast());
        System.out.println(queue.removeLast());


    }
}
