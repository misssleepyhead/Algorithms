public class OrderedArrayMaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int n;

    public OrderedArrayMaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity];
        this.n = capacity;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void insert(Key v) {
        // move larger entries one position to right
        int i = n - 1;
        while (i >= 0 && less(v, pq[i])) {
            pq[i + 1] = pq[i];
            i--;
        }
        pq[i + 1] = v;
        n++;
    }

    public Key delMax() {
        return pq[--n];
    }


    // helper functions
    private boolean less(Key i, Key j) {
        return i.compareTo(j) < 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }
}
