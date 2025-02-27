import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Web exercises 7
 * given a stack of unknown number of string, print out the 5th to the last one.
 * It's OK to destroy the stack in the process
 */
public class Last5thStack {

    Deque<String> queue;

    public Last5thStack() {
        queue = new ArrayDeque<>();
    }

    public int size() {
        return queue.size();
    }

    public String peek() {
        return queue.peek();
    }

    public void push(String s) {
        if (queue.size()==5){
            queue.removeFirst();
        }
        queue.addLast(s);
    }

    public String pop() {
        return queue.removeFirst();
    }

    public void print5() {
        System.out.println(queue.peekFirst());
    }


    public static void main(String[] args) {
        Last5thStack s = new Last5thStack();
        s.push("Hi");
        s.push("hi2");
        s.push("hi3");
        s.push("hi4");
        s.push("hi5");
        s.push("hi6");
        s.print5();

        s.push("hi7");
        s.print5();
    }
}
