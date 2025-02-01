
/**
 * 34. Hot or Cold
 * Your goal is the guess a secret integer between 1 and N.
 * You repeatedly guess integers between 1 and N.
 * After each guess you learn if it equals the secret integer (and the game stops);
 * otherwise (starting with the second guess), you learn if the guess is hotter (closer to)
 * or colder (farther from) the secret number than your previous guess.
 * Design an algorithm that finds the secret number in ~ 2 lg N guesses.
 * Then, design an algorithm that finds the secret number in ~ 1 lg N guesses.
 */
public class HotOrCold {
    private int secret;

    public HotOrCold(int secret) {
        this.secret = secret;
    }

    public boolean isCorrect(int guess) {
        return guess == secret;
    }

    public boolean isHotter(int prevGuess, int currGuess) {
        return Math.abs(currGuess - secret) < Math.abs(prevGuess - secret);
    }

    /**
     * solution for ~ 2lg N guesses
     */
    public int findSecret1(int N) {
        int lo = 0;
        int hi = N;
        int firstGuess = N / 2;
        int secondGuess = firstGuess + (firstGuess / 2);

        if (isCorrect(firstGuess)) return firstGuess;
        if (isCorrect(secondGuess)) return secondGuess;

        boolean hotter = isHotter(firstGuess, secondGuess);

        if (!hotter) {
            hi = firstGuess; // search in lower half
        } else {
            lo = firstGuess; // search in higher half
        }

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (isCorrect(mid)) return mid;

            if (isHotter(firstGuess, mid)) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }

            firstGuess = mid;
        }
        return -1;
    }

    /**
     * solution for ~1 log N
     */
    public int findSecret2(int N) {
        int lo = 0;
        int hi = N;
        int guess = N / 2;

        if (isCorrect(guess)) return guess;

        // Exponential search to determine direction to quickly find the correct direction (hotter/ colder)
        // narrow the range in log N steps
        int step = N / 4;
        boolean hotter = isHotter(guess, guess + step);

        while (step > 1) {
            // if guess is hotter than guess+step otherwise move left
            int newGuess = hotter ? guess + step : guess - step;
            if (isCorrect(newGuess)) return newGuess;
            if (!isHotter(guess, newGuess)) {
                step /= 2;
            }
            guess = newGuess;
        }

        // alternative exponential growth : doubling approach
//        int step = 1;  // Start small
//        while (step < N) {
//            int newGuess = guess + step;
//            if (isCorrect(newGuess)) return newGuess;
//            if (!isHotter(guess, newGuess)) break;  // Stop if getting colder
//            step *= 2;  // Exponential growth (1, 2, 4, 8, 16, ...)
//        }

        // standard bs within the range
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (isCorrect(mid)) return mid;

            if (isHotter(guess, mid)) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }

            guess = mid;
        }
        return -1;
    }

}
