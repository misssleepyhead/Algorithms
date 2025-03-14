import java.util.NoSuchElementException;

public class MinPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int n;

    public MinPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity + 1];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    // resize the underlying array to have the given capacity
    private void resize(int capacity) {
        assert capacity > n;
        Key[] temp = (Key[]) new Object[capacity];
        for (int i = 1; i <= n; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }
    public Key min(){
        return pq[1];
    }

    public void insert(Key v){
        if(n==pq.length-1) resize(2* pq.length);

        pq[++n] = v;
        swim(n);
        assert isMinHeap();
    }

    public Key delMin() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        Key min = pq[1];
        exch(1, n--);
        sink(1);
        pq[n+1] = null;     // to avoid loitering and help with garbage collection
        if ((n > 0) && (n == (pq.length - 1) / 4)) resize(pq.length / 2);
        assert isMinHeap();
        return min;
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
        return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
    }

    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    // is pq[1..n] a min heap?
    private boolean isMinHeap() {
        for (int i = 1; i <= n; i++) {
            if (pq[i] == null) return false;
        }
        for (int i = n + 1; i < pq.length; i++) {
            if (pq[i] != null) return false;
        }
        if (pq[0] != null) return false;
        return isMinHeapOrdered(1);
    }

    // is subtree of pq[1..n] rooted at k a min heap?
    private boolean isMinHeapOrdered(int k) {
        if (k > n) return true;
        int left = 2 * k;
        int right = 2 * k + 1;
        if (left <= n && greater(k, left)) return false;
        if (right <= n && greater(k, right)) return false;
        return isMinHeapOrdered(left) && isMinHeapOrdered(right);
    }

    public static void main(String[] args) {
        MinPQ<Integer> pq = new MinPQ<>(10);
        pq.insert(9);
        pq.insert(10);
        System.out.println(pq.min());
        pq.insert(3);
        pq.insert(2);
        System.out.println(pq.delMin());
        System.out.println(pq.min());
    }
}
