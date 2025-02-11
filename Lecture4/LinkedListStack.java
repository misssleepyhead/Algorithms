import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The {@code LinkedListStack} class represents a last-in-first-out (LIFO) stack of generic items.
 * It supports the usual <em>push</em> and <em>pop</em> operations, along with methods
 * for peeking at the top item, testing if the stack is empty, and iterating through
 * the items in LIFO order.
 * <p>
 * This implementation uses a singly linked list with a static nested class for
 * linked-list nodes.
 */
public class LinkedListStack<Item> implements Iterable<Item> {
    private Node<Item> first;
    private int n;


    private static class Node<Item> {
        Item item;
        Node next;

        Node(Node<Item> x){
            item = x.item;
            if (x.next != null) {
                next=new Node(x.next);
            }
        }
        Node(){}
    }

    /**
     * Initializes an empty stack
     */
    public LinkedListStack() {
        first = null;
        n = 0;
    }

    /** Stack t=new Stack(s)
     * makes t reference a new and independent copy of the stack s.
     * */
    public LinkedListStack(LinkedListStack<Item> s){
        first = new Node(s.first);
    }

    /**
     * Returns true if this stack is empty
     *
     * @return true of this stack is empty; false otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * @return the number of items
     */
    public int size() {
        return n;
    }

    /**
     * Adds the item to this stack
     *
     * @param item the item to add
     */
    public void push(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();

        first.item = item;
        first.next = oldfirst;
        n++;
    }

    /**
     * Removes and returns the item most recently added to this stack.
     *
     * @return the item most recently added to this stack
     * @throws java.util.NoSuchElementException if this stack is empty
     */
    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        // save item to return
        Item item = first.item;

        // delete first node
        first = first.next;
        n--;

        // return the saved item
        return item;
    }


    @Override
    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }

    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> currrent;

        public LinkedIterator(Node<Item> first) {
            this.currrent = first;
        }

        @Override
        public boolean hasNext() {
            return currrent != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = currrent.item;
            currrent = currrent.next;
            return item;

        }
    }

    // return most recently inserted item w/o popping it
    public Item peak() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return first.item;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }

    public static void main(String[] args) {
        LinkedListStack<String> stack = new LinkedListStack<String>();
        stack.push("a");
        stack.push("b");
        System.out.println(stack.size());//2
        System.out.println(stack.pop());
        System.out.println(stack.size());
    }
}
