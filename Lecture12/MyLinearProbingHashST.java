/**
 * imp linear probing hash st
 */
public class MyLinearProbingHashST<Key, Value> {
    private int N; // number of k-v pairs in the table
    private int M; // size of linear-probing table
    private static final int INIT_CAP = 4; // must be power of 2
    private Key[] keys;
    private Value[] vals;

    public MyLinearProbingHashST() {
        this(INIT_CAP);
    }

    public MyLinearProbingHashST(int cap) {
        M = cap;
        N = 0;
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
        MyLinearProbingHashST<Key, Value> t;
        t = new MyLinearProbingHashST<>(newSize);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null) {
                t.put(keys[i], vals[i]);
            }
        }
        keys = t.keys;
        vals = t.vals;
        M = t.M;
    }

    /**
     * if a new key hash to an empty entry, store it here, if not,
     * scan sequentially to find an empty position
     */
    public void put(Key key, Value value) {
        // ensure table is at most half full
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

    public boolean contain(Key key) {
        return get(key) != null;
    }

    public void delete(Key key) {
        if (!contain(key)) return;

        //find pos of the key
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % M;
        }

        // delete key and its value
        keys[i] = null;
        vals[i] = null;

        // rehash all keys
        i = (i + 1) % M;
        while (keys[i] != null) {
            // delete keys[i] and vlas[i] and reinsert
            Key keyToRehash = keys[i];
            Value valToRehash = vals[i];
            keys[i] = null;
            vals[i] = null;
            N--;
            put(keyToRehash, valToRehash);
            i = (i + 1) % M;
        }
        N--;
        //  ensure table is at least one eight full
        if (N > 0 && N <= M / 8) resize(M / 2);
    }
}
