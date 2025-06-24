import edu.princeton.cs.algs4.SequentialSearchST;

/**
 *
 */


public class MySeparateChainingHashST<Key, Value> {
    private int N; // number of key-value pairs
    private int M; // hash table size
    private SequentialSearchST<Key, Value>[] st; //array of ST objects

    // this() called constructor chaining, this(...) means “call another constructor of the same class.”
    public MySeparateChainingHashST() {
        this(997);
    }

    public MySeparateChainingHashST(int m) {
        M = m;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
        for (int i = 0; i < m; i++) {
            st[i] = new SequentialSearchST<>();
        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        return (Value) st[hash(key)].get(key);
    }

    public void put(Key key, Value value) {
        st[hash(key)].put(key, value);
    }
}
