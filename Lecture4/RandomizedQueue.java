import java.util.Iterator;

/**
 * A randomized queue is similar to a stack or queue,
 * except that the item removed is chosen uniformly at random among
 * items in the data structure.
 * <p>
 * Iterator.  Each iterator must return the items in uniformly random order.
 * The order of two or more iterators to the same randomized queue must be mutually independent;
 * each iterator must maintain its own random order.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
    }

    // remove and return a random item
    public Item dequeue() {
    }

    // return a random item (but do not remove it)
    public Item sample() {
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
    }

    // unit testing (required)
    public static void main(String[] args) {
    }

}
