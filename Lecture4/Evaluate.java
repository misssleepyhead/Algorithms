import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Scanner;
import java.util.Stack;

/**
 * Book example, 1.3
 * Dijkstra's two-stack algorithm for expression evaluation
 */
public class Evaluate {

//    public static void main(String[] args) {
//        LinkedListStack<String> ops = new LinkedListStack<>();
//        LinkedListStack<Double> vals = new LinkedListStack<>();
//        Scanner scanner = new Scanner(System.in);
//
//        while (scanner.hasNext()) {
//            String s = StdIn.readString();
////            System.out.println(s);
//            if (s.equals("(")) ;
//            else if (s.equals("+")) ops.push(s);
//            else if (s.equals("-")) ops.push(s);
//            else if (s.equals("*")) ops.push(s);
//            else if (s.equals("/")) ops.push(s);
//            else if (s.equals("sqrt")) ops.push(s);
//            else if (s.equals(")")) {
//                String op = ops.pop();
////                System.out.println(op);
//                double v = vals.pop();
//                System.out.println(v);
//                if (op.equals("+")) v = vals.pop() + v;
//                else if (op.equals("-")) v = vals.pop() - v;
//                else if (op.equals("*")) v = vals.pop() * v;
//                else if (op.equals("/")) v = vals.pop() / v;
//                else if (op.equals("sqrt")) v = Math.sqrt(v);
//                vals.push(v);
//            } else vals.push(Double.parseDouble(s));
//        }
//        System.out.println(vals.pop());
//    }
    public static void main(String[] args) {
        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("(")) {
            } else if (s.equals("+")) {
                ops.push(s);
            } else if (s.equals("*")) {
                ops.push(s);
            } else if (s.equals(")")) {
                String op = ops.pop();
                if (op.equals("+")) vals.push(vals.pop() + vals.pop());
                else if (op.equals("*")) vals.push(vals.pop() * vals.pop());
            } else vals.push(Double.parseDouble(s));
        }
        System.out.println(vals.pop());
    }
}

