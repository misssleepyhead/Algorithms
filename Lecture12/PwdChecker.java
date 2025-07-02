import java.util.Map;

/**
 * WEB EXERCISE 5:
 * Password checker. Write a program that reads in a string from the command line and
 * a dictionary of words from standard input, and checks whether it is a "good" password.
 * Here, assume "good" means that it (i) is at least 8 characters long, (ii) is not a word in the dictionary,
 * (iii) is not a word in the dictionary followed by a digit 0-9 (e.g., hello5),
 * (iv) is not two words separated by a digit (e.g., hello2world)
 */
public class PwdChecker {

    public boolean checker(String pwd, Map<String, String> dict) {
        if (pwd.length() < 8) return false;
        if (dict.containsKey(pwd)) return false;
        if (pwd.length() >= 2 && Character.isDigit(pwd.charAt(pwd.length() - 1))) {
            String prefix = pwd.substring(0, pwd.length() - 1);
            if (dict.containsKey(prefix)) return false;
        }

        for (int i = 0; i < pwd.length() - 1; i++) {
            if (Character.isDigit(pwd.charAt(i))) {
                String left = pwd.substring(0, i);
                String right = pwd.substring(i + 1);
                if (dict.containsKey(left) && dict.containsKey(right)) return false;
            }
        }
        return true;

    }
}
