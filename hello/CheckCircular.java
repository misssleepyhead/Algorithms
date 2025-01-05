/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

/**
 * 1.2.6
 * A string s is a circular rotation of a string t if it matches when the
 * characters are circularly shifted by any number of positions
 * ACTGACG is circular shift of TGACGAC
 */
public class CheckCircular {
    public static boolean checkCircular(String s, String t) {
        if (s.equals(t)) {
            return true;
        }
        if (s.length() != t.length()) {
            return false;
        }
        String cuts = s.substring(2, s.length());
        String cuts2 = s.substring(0, 2);
        String combine = cuts.concat(cuts2);
        if (combine.equals(t)) {
            return true;
        }
        return false;
    }

    public static boolean checkCircular2(String s, String t) {
        // by concatenating s with itself, create a string that contains all possible rotations of s
        return s.length() == t.length() && (s + s).indexOf(t) != -1;
    }

    public static String mystery(String s) {
        int N = s.length();
        if (N <= 1) {
            return s;
        }
        String a = s.substring(0, N / 2);
        // System.out.println(a);
        String b = s.substring(N / 2, N);
        // System.out.println(b);
        return mystery(b) + mystery(a);
    }

    public static void main(String[] args) {
        String s = "ACTGACG";
        String t = "TGACGAC";
        System.out.println(checkCircular(s, t));
        System.out.println(s);
        System.out.println(mystery("ABCD"));

    }
}
