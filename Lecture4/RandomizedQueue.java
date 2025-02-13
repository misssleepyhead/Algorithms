import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * A randomized queue is similar to a stack or queue,
 * except that the item removed is chosen uniformly at random among
 * items in the data structure.
 * <p>
 * Iterator.  Each iterator must return the items in uniformly random order.
 * The order of two or more iterators to the same randomized queue must be mutually independent;
 * each iterator must maintain its own random order.
 * randomized queue implementation must support each randomized queue operation
 * (besides creating an iterator) in constant amortized time.
 * That is, any intermixed sequence of m randomized queue operations
 * (starting from an empty queue) must take at most cm steps in the worst case,
 * for some constant c. A randomized queue containing n items must use at most
 * 48n + 192 bytes of memory. Additionally, your iterator implementation must
 * support operations next() and hasNext() in constant worst-case time;
 * and construction in linear time;
 * you may (and will need to) use a linear amount of extra memory per iterator.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int size;
    private Random random;

    private int last;       // index of next available slot

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[10];
        random = new Random();
        size = 0;
        last = 0;
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
        if (item == null) throw new IllegalArgumentException("Can't insert null item");
        if (size == queue.length) {
            resize(size * 2);
        }
        queue[last++] = item; // move last point to the next index and assign item
        if (last == queue.length) {
            last = 0;
        }
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Empty queue");
        int randomIndex = random.nextInt(size);
        Item item = queue[randomIndex];

        queue[randomIndex] = queue[size - 1];
        queue[size - 1] = null;
        size--;

        // shrink size of array if necessary
        if (size > 0 && size == queue.length / 4) resize(queue.length / 2);
        return item;

    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Empty queue");
        int randomIndex = random.nextInt(size);
        return queue[randomIndex];
    }

    // resize the underlying array
    private void resize(int capacity) {
        assert capacity >= size;
        Item[] copy = (Item[]) new Object[capacity];
        System.arraycopy(queue, 0, copy, 0, size);
        queue = copy;
        last = size;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        private final int[] randomOrder;
        private int i = 0;

        public RandomIterator() {
            randomOrder = new int[size];
            for (int j = 0; j < size; j++) {
                randomOrder[j] = j;
            }
        }

        @Override
        public boolean hasNext() {
            return i < size;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return queue[randomOrder[i++]];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // helper function to randomize the order of array
    // randomly rearranges the elements of an array
    // using the Fisher-Yates Shuffle algorithm, which runs in O(n) time.
    private void shuffle(int[] array) {
        Random rd = new Random(); // create a random object
        for (int i = array.length; i > 0; i--) { // start from the last index
            int index = rd.nextInt(i + 1); // pick a random index between 0 and i
            int temp = array[index]; // swap array[i] with array[index]
            array[index] = array[i];
            array[i] = temp;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue(1);
        randomizedQueue.enqueue(2);
        randomizedQueue.enqueue(3);
        System.out.println(randomizedQueue.size());
        System.out.println(randomizedQueue.sample());
        System.out.println(randomizedQueue.sample());
        System.out.println(randomizedQueue.sample());
        System.out.println(randomizedQueue.dequeue());
        System.out.println(randomizedQueue.sample());
        System.out.println(randomizedQueue.size());
    }

}
