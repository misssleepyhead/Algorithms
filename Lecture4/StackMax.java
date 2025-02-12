import java.util.ArrayDeque;
import java.util.Deque;

/** Interview question:
 * Question 2
 * Stack with max. Create a data structure that efficiently supports
 * the stack operations (push and pop) and also a return-the-maximum operation.
 * Assume the elements are real numbers so that you can compare them.
 *
 * S*/

public class StackMax {
    Deque<Integer> stack;
    Deque<Integer> maxStack;

    public StackMax() {
        stack = new ArrayDeque<>();
        maxStack = new ArrayDeque<>();
    }

    public void push(int x){
        if(maxStack.isEmpty() || x>= maxStack.peek()){
            maxStack.push(x);
        }
        stack.push(x);
    }

    public int pop(){
        if(stack.isEmpty()){
            throw new RuntimeException("Empty stack!");
        }
        if(stack.peek()== maxStack.peek()){
            maxStack.pop();
        }
        return stack.pop();
    }

    public int max(){
        if(maxStack.isEmpty()){
            throw new RuntimeException("Empty stack!");
        }
        return maxStack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        StackMax s = new StackMax();
        s.push(5);
        s.push(1);
        s.push(3);
        s.push(7);
        System.out.println(s.max()); // ✅ 7
        s.pop(); // Pops 7
        System.out.println(s.max()); // ✅ 5
        s.pop(); // Pops 3
        System.out.println(s.max()); // ✅ 5
        System.out.println(s.pop());
        System.out.println(s.max());

    }
}
