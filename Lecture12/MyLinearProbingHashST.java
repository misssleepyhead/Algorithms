/**
 * imp linear probing hash st
 */
public class MyLinearProbingHashST<Key, Value> {
    private int N; // number of k-v pairs in the table
    private int M = 16; // size of linear-probing table
    private Key[] keys;
    private Value[] vals;

    public MyLinearProbingHashST() {
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return N;
    }

    public void resize(int newSize) {
    }

    /**
     * if a new key hash to an empty entry, store it here, if not,
     * scan sequentially to find an empty position
     */
    public void put(Key key, Value value) {
        if (N >= M / 2) resize(2 * M);
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                vals[i] = value; // same key, update new value
                return;
            }
        }
        keys[i] = key;
        vals[i] = value;
        N++;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1 % M)) {
            if (keys[i].equals(key)) {
                return vals[i];
            }
        }
        return null; // search miss
    }
}
