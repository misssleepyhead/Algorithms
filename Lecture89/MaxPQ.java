public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;

    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1]; // pq[0] is unused, and the N keys in pq[1] through pq[N+1]
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(Key v) {
        pq[++N] = v; // add at the end
        swim(N); // reheapify
    }

    public Key delMax() {
        Key max = pq[1];
        exch(1, N--); // exchange max with last item, decrease size
        pq[N + 1] = null; // avoid loitering
        sink(1); // restore heap property
        return max;
    }


    // helper functions
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    // bottom up reheapify: if a node's key is larger than its parent's key
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    // top down reheapify: if a node's key is smaller than its children
    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }
}
