package ElementarySorts;

/**c
 * Reads in three integers and prints them out in sorted order without
 * *  using a loop.
 */
public class Sort3 {
    public static void main(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int c = Integer.parseInt(args[2]);

        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }
        if (a > c) {
            int temp = a;
            a = c;
            c = temp;
        }
        if (b > c) {
            int temp = c;
            c = b;
            b = temp;
        }

        System.out.println(a + "" + b + "" + c);
    }
}
