import java.util.Scanner;

public class Calculate {
    /**
     * implement Dijsktra's two stack
     */
//    public static void main(String[] args) {
//        LinkedListStack<String> operators = new LinkedListStack<>();
//        LinkedListStack<Double> values = new LinkedListStack<>();
//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNext()) {
//            String s = scanner.next();
//            if (s.equals("(")) {
//                //do nothing
//            } else if (s.equals("+")) {
//                operators.push("+");
//
//            } else if (s.equals("*")) {
//                operators.push("*");
//
//            } else if (s.equals(")")) {
//                String op = operators.pop();
//                double v = values.pop();
//                if (op.equals("+")) {
//                    v = values.pop() + v;
//                } else {
//                    v = values.pop() * v;
//                }
//                values.push(v);
////                System.out.println(v);
//            } else {
//                values.push(Double.parseDouble(s));
//            }
////            System.out.println(values.size());
//        }
//        System.out.println(values.pop());
//    }

    public static double cal(String e) {
        LinkedListStack<String> operators = new LinkedListStack<>();
        LinkedListStack<Double> values = new LinkedListStack<>();
        for (int i = 0; i < e.length(); i++) {
            Character c = e.charAt(i);
            if (c.equals('(')) {
                //do nothing
            } else if (c.equals('+')) {
                operators.push("+");

            } else if (c.equals('*')) {
                operators.push("*");

            } else if (c.equals(')')) {
                String op = operators.pop();
                double v = values.pop();
                if (op.equals("+")) {
                    v = values.pop() + v;
                } else {
                    v = values.pop() * v;
                }
                values.push(v);
//                System.out.println(v);
            } else {
                values.push(Double.parseDouble(String.valueOf(c)));
            }
        }
        return values.pop();
    }

    public static void main(String[] args) {
        System.out.println(cal("(1+((2+3)*(4*5)))"));
//        System.out.println("(1+1)");
    }
}
