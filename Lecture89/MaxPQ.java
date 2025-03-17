public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;

    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1]; // pq[0] is unused, and the N keys in pq[1] through pq[N+1]
    }

    public MaxPQ(Key[] keys) {
        N = keys.length;
        pq = (Key[]) new Object[keys.length + 1];
        for (int i = 0; i < N; i++)
            pq[i+1] = keys[i];
        for (int k = N/2; k >= 1; k--)
            sink(k);
        assert isMaxHeap();
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public Key max(){
        return pq[1];
    }

    // resize the underlying array to have the given capacity
    private void resize(int capacity) {
        assert capacity > N;
        Key[] temp = (Key[]) new Object[capacity];
        for (int i = 1; i <= N; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }



    public void insert(Key v) {
        // double size of array if necessary
        if (N == pq.length - 1) resize(2 * pq.length);
        pq[++N] = v; // add at the end
        swim(N); // reheapify
    }

    public Key delMax() {
        Key max = pq[1];
        exch(1, N--); // exchange max with last item, decrease size
        pq[N + 1] = null; // avoid loitering
        sink(1); // restore heap property
        if ((N> 0) && (N == (pq.length - 1) / 4)) resize(pq.length / 2);

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
            if (j < N && less(j, j + 1)) j++; // pick larger child to exchange
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    // is pq[1..N] a max heap?
    private boolean isMaxHeap() {
        for (int i = 1; i <= N; i++) {
            if (pq[i] == null) return false;
        }
        for (int i = N+1; i < pq.length; i++) {
            if (pq[i] != null) return false;
        }
        if (pq[0] != null) return false;
        return isMaxHeapOrdered(1);
    }

    // is subtree of pq[1..N] rooted at k a max heap?
    private boolean isMaxHeapOrdered(int k) {
        if (k > N) return true;
        int left = 2*k;
        int right = 2*k + 1;
        if (left  <= N && less(k, left))  return false;
        if (right <= N && less(k, right)) return false;
        return isMaxHeapOrdered(left) && isMaxHeapOrdered(right);
    }
}
