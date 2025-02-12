import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * web exercises 1: tail
 * write a program that Tail k < file.txt print the last k line of the file.txt
 *
 * Solution:
 * 1. Use queue so the order is correct (if use stack the print-out order will be reverse)
 * 2. When the queue size is larger than k, pop element out.
 *
 */

public class Tail {
    // use queue to read line
    private Deque<String> queue;

    public Tail() {
        this.queue = new ArrayDeque<>();
    }

    public void readFile(int k, String file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) { // Simplified loop
                queue.addLast(line);
                if (queue.size() > k) {
                    queue.pollFirst(); // Keep only the last k lines
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + file);
            e.printStackTrace();
        }
    }

    public void tailK(int k, String file){
        // read file and store to the stack
        readFile(k,file);

        for(String line:queue){
            System.out.println(line);
        }
    }

    public static void main(String[] args) {
        Tail t = new Tail();
        t.tailK(3,"/home/peichunc/Desktop/Algorithms/Lecture4/test.txt");
    }
}
