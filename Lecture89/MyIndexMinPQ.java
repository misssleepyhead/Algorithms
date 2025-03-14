public class MyIndexMinPQ<Key extends Comparable<Key>> {
    private int[] pq; // binary heap using 1-based indexing
    private int[] qp; // inverse of pq - qp[pq[i]] = pq[qp[i]] = i
    private Key[] keys; // keys[i] = priority of i
    private int n;
    private int capacity;

    public MyIndexMinPQ(int capacity) {
        pq = new int[capacity + 1];
        qp = new int[capacity + 1];
        keys = (Key[]) new Comparable[capacity + 1];
        this.capacity = capacity;
        n = 0;
        for (int i = 0; i <= capacity; i++) {
            qp[i] = -1;
        }
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public boolean contains(int i) {
        return qp[i] != -1;
    }

    public void insert(int i, Key v) {
        if (contains(i)) throw new IllegalArgumentException();
        n++;
        qp[i] = n;
        pq[n] = i;
        keys[i] = v;
        swim(n);
    }

    public void change(int i, Key v) {
        if (!contains(i)) throw new IllegalArgumentException();
        keys[i] = v;
        int index = qp[i];
        swim(index);
        sink(index);
    }

    public void delete(int k) {
        int index = qp[k];
        exch(index, n--);
        swim(index);
        sink(index);
        keys[k] = null;
        qp[k] = -1;
    }

    public Key min() {
        return keys[pq[1]];
    }

    public int minIndex() {
        return pq[1];
    }

    public int delMin() { // delete minimal item and return its index
        int index = minIndex();
        exch(1, n--);
        sink(1);
        assert index == pq[n + 1];
        qp[index] = -1;
        keys[index] = null;
        pq[n + 1] = -1;
        return index;
    }


    /***************************************************************************
     * Helper functions to restore the heap invariant.
     ***************************************************************************/

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k / 2, k);
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

    /***************************************************************************
     * Helper functions for compares and swaps.
     ***************************************************************************/
    private boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    private void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }


}
