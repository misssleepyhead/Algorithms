import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    /**
     * takes an integer k as a command-line argument;
     * reads a sequence of strings from standard input using StdIn.readString();
     * and prints exactly k of them, uniformly at random.
     * Print each item from the sequence at most once.
     */
    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        while (!StdIn.isEmpty()){
            randomizedQueue.enqueue(StdIn.readString());
        }
        int k = Integer.parseInt(args[0]);
        if(k> randomizedQueue.size() || k<0){
            throw new IllegalArgumentException();
        }
        for(int i=0;i<k;i++){
            System.out.println(randomizedQueue.dequeue());
        }

    }
}