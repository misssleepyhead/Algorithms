import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Stack;

public class FixedCapacityStack<Item> implements Iterable<Item> {
    private Item[] a;
    private int n;

    public FixedCapacityStack(int capacity) {
        a = (Item[]) new Object[capacity];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public void push(Item item) {
        a[n++] = item;
    }

    public Item pop() {
        return a[--n]; // decrement n first then return new updated n
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    public class ReverseArrayIterator implements Iterator<Item> {
        private int i = n - 1;

        @Override
        public boolean hasNext() {
            return i >= 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return a[i--]; // access i first, them decrement i
        }
    }

    public static void main(String[] args) {
        int max = Integer.parseInt(args[0]);
        FixedCapacityStack<String> stack = new FixedCapacityStack<String>(max);
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) stack.push(item);
            else if (stack.isEmpty()) StdOut.println("BAD INPUT");
            else StdOut.print(stack.pop() + " ");
        }
        StdOut.println();

        // print what's left on the stack
        StdOut.print("Left on stack: ");
        for (String s : stack) {
            StdOut.print(s + " ");
        }
        StdOut.println();
    }
}
