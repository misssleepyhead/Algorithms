/**
 * web exercises 3:
 * Delete ith element. Create a data type that supports the following operations: isEmpty, insert,
 * and remove(int i), where the deletion operation deletes and returns the ith least recently added object on the queue.
 * Do it with an array, then do it with a linked list
 */

public class DeleteQueue {
    int[] queue;
    int size;
    int last;
    int first;


    public DeleteQueue(int capacity) {
        this.queue = new int[capacity];
        size = 0;
        last = 0;
        first = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void insert(int item) {
        if (size == queue.length) throw new ArrayIndexOutOfBoundsException();
        queue[last] = item;
        size++;
        last++;
    }

    public int delete(int i) {
        if (i < 0 || i >= size) throw new IndexOutOfBoundsException();
        int item = queue[i];
        for (int j = i; j < size - 1; j++) {
            queue[j] = queue[j + 1];
        }
        last--;
        first--;
        size--;
        return item;

    }

    public void printQueue() {
        for (int i = 0; i < size; i++) {
            System.out.print(queue[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DeleteQueue dq = new DeleteQueue(5);
        dq.insert(10);
        dq.insert(20);
        dq.insert(30);
        dq.insert(40);
        dq.insert(50);

        System.out.println("Queue before deletion:");
        dq.printQueue();

        dq.delete(2); // Removes element at index 2 (30)

        System.out.println("Queue after deleting index 2:");
        dq.printQueue();
    }
}
