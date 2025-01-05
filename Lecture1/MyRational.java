/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

/**
 * 1.2.16 Rational numbers
 * an immutable data type Rational for rational numbers
 * that support addition, subtraction, multiplication, division
 */
public class MyRational {
    private long numerator;
    private long denominator;

    public MyRational(int numerator, int denominator) {
        this((long) numerator, (long) denominator);
    }

    private MyRational(long numerator, long denominator) {
        // deal with x/0
        if (denominator == 0) {
            throw new ArithmeticException("denominator is zero");
        }

        this.numerator = numerator;
        this.denominator = denominator;
        // reduce fraction
        reduce();
        // only needed for negative numbers
        if (this.denominator < 0) {
            this.denominator = -this.denominator;
            this.numerator = -this.numerator;
        }

    }

    private void reduce() {
        long gcd = gcd(this.numerator, this.denominator);
        numerator /= gcd;
        denominator /= gcd;
    }

    private static long gcd(long m, long n) {
        if (m < 0) {
            m = -m;
        }
        if (n < 0) {
            n = -n;
        }
        if (n == 0) {
            return m;
        }
        else {
            return gcd(n, m % n);
        }
    }


    public int getDenominator() {
        return (int) denominator;
    }

    public int getNumerator() {
        return (int) numerator;
    }

    // return this+that
    public MyRational plus(MyRational that) {
        return new MyRational(this.numerator * that.denominator + that.numerator * this.denominator,
                              this.denominator * that.denominator);
    }

    // return this-that
    public MyRational minus(MyRational that) {
        return new MyRational(this.numerator * that.denominator - that.numerator * this.denominator,
                              this.denominator * that.denominator);
    }

    // product of this and that
    public MyRational times(MyRational that) {
        return new MyRational(this.numerator * that.numerator, this.denominator * that.denominator);
    }

    // quotient of this and that
    public MyRational divides(MyRational that) {
        return new MyRational(this.numerator * that.denominator, this.denominator * that.numerator);
    }

    // check equals
    public boolean equals(Object obj) { // Correct equals method
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MyRational that = (MyRational) obj;
        return numerator == that.numerator && denominator == that.denominator;
    }

    public String toString() {
        return numerator + "/" + denominator;
    }

    public static void main(String[] args) {
        MyRational r1 = new MyRational(1, 2);
        MyRational r2 = new MyRational(1, 4);
        MyRational r3 = r1.plus(r2);
        System.out.println(r3); // Output: 3/4

        MyRational r4 = new MyRational(0, 5);
        System.out.println(r4); // Output: 0/1

        MyRational r5 = new MyRational(1, -2);
        System.out.println(r5); // Output: -1/2

        MyRational r6 = new MyRational(1, 2);
        System.out.println(r1.equals(r6)); // Output: true

        MyRational r7 = new MyRational(1, 0); // ArithmeticException
    }
}
