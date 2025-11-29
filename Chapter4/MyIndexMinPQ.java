import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * the data structure used to implement prims' eager
 */
public class MyIndexMinPQ<Key extends Comparable<Key>> implements Iterable<Integer> {
    private int maxN; // maximum number of elements on pq
    private int n; //number of elements on the pq
    private int[] pq; // binary heap using 1-based indexing
    private int[] qp; // inverse of pq - qp[oq[i]] = pq[qp[i]=i
    private Key[] keys; //keys[i] = priority of i

    public MyIndexMinPQ(int maxN) {
        if (maxN < 0) throw new IllegalArgumentException();
        this.maxN = maxN;
        n = 0;
        keys = (Key[]) new Comparable[maxN + 1];
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        for (int i = 0; i <= maxN; i++) qp[i] = -1;
    }

    /**
     * Returns true if this pq is empty
     *
     * @return {@code true} if this priority queue is empty;
     * {@code false} otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Is {@code i} an index on this pq?
     *
     * @param i an index
     * @return {@code true} if {@code i} is an index on this pq;
     * {@code false} otherwise.
     */
    public boolean contains(int i) {
        return pq[i] != -1;
    }

    public int size() {
        return n;
    }

    public void insert(int i, Key key) {
        if (contains(i)) throw new IllegalArgumentException();
        n++;
        pq[i] = n;
        pq[n] = i;
        keys[i] = key;
        swim(n);
    }

    /**
     * Returns an index associated with a minimum key
     */
    public int minIndex() {
        if (n == 0) throw new NoSuchElementException();
        return pq[1];
    }

    /**
     * Returns a minimum key
     */
    public Key minKey() {
        if (n == 0) throw new NoSuchElementException();
        return keys[pq[1]];
    }

    /**
     * Remove a minimum key and returns its associated index.
     *
     * @return an index associated with a minimum key.
     */
    public int delMin() {
        if (n == 0) throw new NoSuchElementException();
        int min = pq[1];
        exch(1, n--);
        sink(1);
        assert min == pq[n + 1];
        qp[min] = -1; //delete
        keys[min] = null; // to help with garbage collection
        pq[n + 1] = -1;// not needed
        return min;

    }

    /**
     * Remove the key associated with index {@code i}.
     *
     * @param i the index of the key to remove
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     * @throws NoSuchElementException   no key is associated with index {@code i}
     */
    public void delete(int i) {
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        int index = qp[i];
        exch(index, n--);
        swim(index);
        sink(index);
        keys[i] = null;
        qp[i] = -1;
    }

    /**
     * Returns the key associated with index {@code i}.
     */
    public Key keyOf(int i) {
        if (!contains(i)) throw new NoSuchElementException();
        else return keys[i];
    }

    /**
     * Change the key associated with index {@code i} to the specified value.
     */
    public void changeKey(int i, Key key) {
        keys[i] = key;
        swim(qp[i]);
        sink(qp[i]);
    }

    private boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    private void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[i];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }


    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && greater(j, j + 1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    public Iterator<Integer> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Integer> {
        private MyIndexMinPQ<Key> copy;

        public HeapIterator() {
            copy = new MyIndexMinPQ<Key>(pq.length - 1);
            for (int i = 1; i <= n; i++) {
                copy.insert(pq[i], keys[pq[i]]);
            }
        }

        public boolean hasNext() {
            return !copy.isEmpty();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMin();
        }
    }
}
