import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 1.3.34 Random Bag
 * same as bag but iterate items in random order
 */
public class RandomBag<Item> implements Iterable<Item> {
    int size;
    Item[] bag;

    public RandomBag() {
        bag = (Item[]) new Object[10];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void add(Item item) {
        bag[size] = item;
        size++;
    }

    @Override
    public Iterator<Item> iterator() {
        return new BagIterator();
    }

    private class BagIterator implements Iterator<Item> {
        private final int[] randomOrder;
        private int i = 0;

        public BagIterator() {
            randomOrder = new int[size];
            for (int j = 0; j < size; j++) {
                randomOrder[j] = j;
            }
            StdRandom.shuffle(randomOrder);
        }

        @Override
        public boolean hasNext() {
            return i < size;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return bag[randomOrder[i++]];
        }
    }

    public static void main(String[] args) {
        RandomBag<Integer> rb = new RandomBag<>();
        rb.add(1);
        rb.add(2);
        rb.add(3);
        rb.add(4);
        rb.add(5);

        for (int num : rb) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
