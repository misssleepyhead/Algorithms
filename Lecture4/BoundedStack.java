import java.util.Stack;

/**
 * web exercises 2
 * A bounded stack is a stack that has a capacity of at most N.
 * (Applications: undo or history with finite buffer.)
 */
public class BoundedStack {
    Stack<Integer> stack;
    int size;
    int capacity;

    public BoundedStack(int capacity) {
        stack = new Stack<>();
        size = 0;
        this.capacity = capacity;

    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(int val) {
        if (size < capacity) {
            stack.push(val);
            size++;
        } else {
            throw new IllegalStateException("Stack is full");
        }
    }

    public int peek() {
        return stack.peek();
    }

    public int pop() {
        return stack.pop();
    }
}
