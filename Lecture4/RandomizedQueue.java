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
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int size;
    private Random random;

    private int first;      // index of first element of queue
    private int last;       // index of next available slot

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[10];
        random = new Random();
        size = 0;
        first = 0;
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
        if(item==null) throw new IllegalArgumentException("Can't insert null item");
        if(size== queue.length){
            resize(size*2);
        }
        queue[last++]=item; // move last point to the next index and assign item
        if(last== queue.length){
            last=0;
        }
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if(isEmpty()) throw new NoSuchElementException("Empty queue");
        int randomIndex = random.nextInt(size);
        Item item = queue[randomIndex];
        queue[randomIndex]=queue[size-1];
        size--;
        if (first == queue.length) first = 0;           // wrap-around
        // shrink size of array if necessary
        if (size > 0 && size == queue.length/4) resize(queue.length/2);
        return item;

    }

    // return a random item (but do not remove it)
    public Item sample() {
        if(isEmpty()) throw new NoSuchElementException("Empty queue");
        int randomIndex = random.nextInt(size);
        return queue[randomIndex];
    }

    // resize the underlying array
    private void resize(int capacity) {
        assert capacity >= size;
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = queue[(first + i) % queue.length];
        }
        queue = copy;
        first = 0;
        last  = size;
    }
    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item>{
        private int i = 0;
        @Override
        public boolean hasNext() {
            return i<size;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = queue[(i + first) % queue.length];
            i++;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
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
