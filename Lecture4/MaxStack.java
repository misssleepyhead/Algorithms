import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * web exercise 5
 * Stack + max. Create a data structure that efficiently supports the stack operations (pop and push)
 * and also return the maximum element. Assume the elements are integers or reals so that you can compare them.
 */
public class MaxStack {
    Stack<Integer> stack;
    Stack<Integer> maxStack;

    public MaxStack() {
        stack = new Stack<>();
        maxStack = new Stack<>();
    }

    public void push(int item) {
        stack.push(item);


        if (maxStack.isEmpty() || maxStack.peek() <= item) {
            maxStack.push(item);
        }


    }

    public int pop() {
        if (stack.isEmpty()) throw new NoSuchElementException();
        int item = stack.pop();

        if (maxStack.peek() == item) {
            maxStack.pop();

        }

        return item;
    }

    public int max() {
        if (maxStack.isEmpty()) throw new NoSuchElementException();
        return maxStack.peek();
    }

    public static void main(String[] args) {
        MaxStack maxStack = new MaxStack();
        maxStack.push(2);
        maxStack.push(1);
        maxStack.push(5);
        maxStack.push(3);
        System.out.println("Current max: " + maxStack.max()); // 5

        System.out.println("Popped: " + maxStack.pop()); // 3
        System.out.println("Current max: " + maxStack.max()); // 5

        System.out.println("Popped: " + maxStack.pop()); // 5
        System.out.println("Current max: " + maxStack.max()); // 2
    }
}
