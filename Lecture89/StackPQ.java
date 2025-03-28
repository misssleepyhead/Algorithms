import java.util.PriorityQueue;

/**
 * problem 2.4.21 use pq to implement stack
 * solution:
 * 1. Stack is LIFO, and pq always remove the root, we cannot decide to remove an arbitrary element
 * 2. So, we always make sure the last item is store at the root of heap
 * 3. To achieve this, we assign each item a negative order number, start from 0, -1, -2...
 * so every time we insert(to leaf), it will swim up to where it should be, and every time we remove the root,
 * the next item will be the new root.
 */

public class StackPQ<T> {
    private class Node implements Comparable<Node> {
        T item;
        int order;

        Node(T item, int order) {
            this.item = item;
            this.order = order;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(other.order, this.order);
        }
    }

    private PriorityQueue<Node> pq;
    private int order;

    public StackPQ() {
        pq = new PriorityQueue<>();
        order = 0;
    }

    // add node with order number
    public void push(T item){
        pq.add(new Node(item,order++));
    }

    public T pop(){
        return pq.poll().item;
    }


    public static void main(String[] args) {
        StackPQ<Integer> stackPQ = new StackPQ<>();
        stackPQ.push(1);
        stackPQ.push(2);
        stackPQ.push(3);
        stackPQ.push(0);
        System.out.println(stackPQ.pop());
    }
}
