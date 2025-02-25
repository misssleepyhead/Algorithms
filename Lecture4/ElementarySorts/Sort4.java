package ElementarySorts;

/**
 * Web exercise 3
 * Optimal oblivious sorting networks. Create a program that
 * sorts four integers using only 5 if statements
 */
public class Sort4 {
    public static void main(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int c = Integer.parseInt(args[2]);
        int d = Integer.parseInt(args[3]);

        if (a > b) {
            int t = a;
            a = b;
            b = t;
        }
        if (c > d) {
            int t = c;
            c = d;
            d = t;
        }
        if (b > c) {
            int t = b;
            b = c;
            c = t;
        }
        if (a > b) {
            int t = a;
            a = b;
            b = t;
        }
        if (c > d) {
            int t = c;
            c = d;
            d = t;
        }

        System.out.println(a + "" + b + "" + c + "" + d);
    }
}
