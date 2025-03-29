/**
 * Web exercises 17. Min-max heap
 * {@code MinMaxHeap} implement a data structure that support min and max in constant time
 * delete min, delete max in logarithmic time by putting the items in a single array of size n with the following properties:
 * 1. The array represents a complete binary tree.
 * 2. The key in a node at an even level is less than (or equal to) the keys in its subtree;
 * the key in a node at an odd level is greater than (or equal to) the keys in its subtree.
 * Note that the maximum value is stored at the root and the minimum value is stored at one of the root's children.
 */

public class MinMaxHeap<Key extends Comparable<Key>> {
    private Key[] heap;
    private int n;
    private Key max;
    private Key min;

    public MinMaxHeap(int capacity) {
        heap = (Key[]) new Comparable[capacity + 1];
        this.n = 0;
        max = null;
        min = null;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void insert(Key v) {
        if (n >= heap.length - 1) throw new IndexOutOfBoundsException();
        heap[++n] = v;
        swim(n);
        recomputeMinMax();

    }

    public Key delMax() {
        Key oldMax = heap[1];
        exch(1, n--);
        heap[n + 1] = null;
        sink(1);
        recomputeMinMax();
        return oldMax;
    }

    public Key delMin() {
        int minPos;
        if (n == 1) minPos = 1;
        else if (n == 2) minPos = 2;
        else minPos = less(2, 3) ? 2 : 3;

        Key oldMin = heap[minPos];
        exch(minPos, n--);
        heap[n + 1] = null;
        sink(minPos);
        recomputeMinMax();
        return oldMin;
    }

    public Key min() {
        return min;
    }

    public Key max() {
        return max;
    }

    // recompute min,max dynamically
    private void recomputeMinMax() {
        if (n == 0) {
            min = null;
            max = null;
        } else {
            max = heap[1];
            if (n == 1) min = heap[1];
            else if (n == 2) min = heap[2];
            else min = less(2, 3) ? heap[2] : heap[3];
        }
    }


    // given k, return its level
    private int level(int k) {
        return (int) (Math.floor(Math.log(k) / Math.log(2)));
    }


    private boolean less(int i, int j) {
        return heap[i].compareTo(heap[j]) < 0;
    }

    private void exch(int i, int j) {
        Key swap = heap[i];
        heap[i] = heap[j];
        heap[j] = swap;
    }

    private void sink(int k) {
        int level = level(k);
        if (level % 2 == 0) sinkMax(k);
        else sinkMin(k);
    }

    private void sinkMax(int k) {
        while (2 * k <= n) {
            int m = maxChildGrandchild(k);
            if (m == -1) break;

            if (m >= 4 * k) { // grandchild
                if (less(k, m)) {
                    exch(k, m);
                    if (less(m / 2, m)) exch(m / 2, m);
                    k = m;
                } else break;
            } else { // child
                if (less(k, m)) exch(k, m);
                break;
            }
        }
    }

    private void sinkMin(int k) {
        while (2 * k <= n) {
            int m = minChildGrandchild(k);
            if (m == -1) break;

            if (m >= 4 * k) { // grandchild
                if (less(m, k)) {
                    exch(k, m);
                    if (less(m, m / 2)) exch(m / 2, m);
                    k = m;
                } else break;
            } else { // child
                if (less(m, k)) exch(k, m);
                break;
            }
        }
    }

    // Helpers to find min/max of children and grandchildren
    private int maxChildGrandchild(int k) {
        int max = -1;
        int first = 2 * k;
        int last = Math.min(n, 4 * k + 3);
        for (int i = first; i <= last; i++) {
            if (max == -1 || less(max, i)) max = i;
        }
        return max;
    }

    private int minChildGrandchild(int k) {
        int min = -1;
        int first = 2 * k;
        int last = Math.min(n, 4 * k + 3);
        for (int i = first; i <= last; i++) {
            if (min == -1 || less(i, min)) min = i;
        }
        return min;
    }

    private void swim(int k) {
        int level = level(k);
        int parent = k / 2;
        if (k > 1) {
            // if k is on even level but less than its parent
            if (level % 2 == 0) {
                if (less(k, parent)) {
                    exch(k, parent);
                    swimMin(parent);
                } else {
                    swimMax(k);
                }
            } else {
                if (!less(k, parent)) {
                    exch(k, parent);
                    swimMax(parent);
                } else {
                    swimMin(k);
                }
            }
        }
    }

    private void swimMin(int k) {
        while (k > 3 && less(k, k / 4)) {
            exch(k, k / 4);
            k = k / 4;
        }
    }

    private void swimMax(int k) {
        while (k > 3 && less(k / 4, k)) {
            exch(k, k / 4);
            k = k / 4;
        }
    }

    public static void main(String[] args) {
        MinMaxHeap heap = new MinMaxHeap(15);
        heap.insert(1);
        heap.insert(10);
        System.out.println(heap.max());
        heap.insert(2);
        heap.insert(3);
        System.out.println(heap.size());
        System.out.println(heap.min());
        System.out.println(heap.delMax());
        System.out.println(heap.max());
        System.out.println(heap.min());
        heap.insert(0);
        System.out.println(heap.min());

    }


}
