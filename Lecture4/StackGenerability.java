import edu.princeton.cs.algs4.In;

import java.util.Stack;

/**
 * 45. Stack generability
 * 1. Given a sequence of intermixed push and pop operations, where the 0 to N-1 in that order(push) are
 * intermixed with N minus signs(pop), write an algorithm that determines the order will causes the stack to uderflow
 * <p>
 * 2. Generate Permutation, given a permutation of numbers from 0 to N-1, determines it is a valid permutation and
 * output the operations to generate it
 */
public class StackGenerability {
    public static boolean detectUnderflow(String[] operations) {
        int balance = 0;
        for (String op : operations) {
            if (op.equalsIgnoreCase("pop")) {
                if (balance < 1) {
                    return true; //underflow
                }
                balance--;
            } else if (op.equalsIgnoreCase("push")) {
                balance++;
            }

        }
        return false;
    }

    public static boolean generatePermutation(int[] permutation, int N) {
        Stack<Integer> stack = new Stack<>();
        int nextPush = 0;

        for (int num : permutation) {
            while (nextPush <= num) {
                stack.push(nextPush);
                System.out.println("push" + nextPush);
                nextPush++;
            }

            if (stack.peek() == num) {
                stack.pop();
                System.out.println("pop" + num);
            } else {
                return false;
            }
        }
        return stack.isEmpty();

    }

    public static void main(String[] args) {
        int[] validPermutation = {4, 3, 2, 1, 0};  // Valid
        int[] invalidPermutation = {3, 4, 2, 0, 1}; // Invalid

        System.out.println("\nChecking valid permutation:");
        boolean valid = generatePermutation(validPermutation, 5);
        System.out.println("Can be generated? " + valid);

        System.out.println("\nChecking invalid permutation:");
        boolean invalid = generatePermutation(invalidPermutation, 5);
        System.out.println("Can be generated? " + invalid);
    }
}
