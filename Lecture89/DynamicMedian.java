import java.util.PriorityQueue;

/**
 * Interview question 1 : DynamicMedian
 * Design a data type that supports insert in logarithmic time, find-the-median in constant time,
 * and remove-the-median in logarithmic time. If the number of keys in the data type is even, find/remove the lower median.
 */

public class DynamicMedian {
    private PriorityQueue<Integer> maxHeap; // Left half(store lower median)
    private PriorityQueue<Integer> minHeap; // Right half(store larger values)

    public DynamicMedian() {
        maxHeap = new PriorityQueue<>((a, b) -> b - a); // max heap
        minHeap = new PriorityQueue<>();
    }

    // Insert in o(log N)
    public void insert(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }

        // rebalance heaps. Either both heaps have equal size or maxheap(left) has exactly one more element than right
        // so that the left side always contains the median
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.add(minHeap.poll());
        }

    }

    public int findMedian() {
        return maxHeap.peek();
    }

    public void removeMedian() {
        if (!maxHeap.isEmpty()) {
            maxHeap.poll();
        }

        if (maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.poll());
        }

    }

    public static void main(String[] args) {
        DynamicMedian dm = new DynamicMedian();
        dm.insert(10);
        dm.insert(20);
        dm.insert(5);
        dm.insert(15);
        dm.insert(30);

        System.out.println(dm.findMedian()); // Output: 15
        dm.removeMedian();
        System.out.println(dm.findMedian()); // Output: 5
    }
}
