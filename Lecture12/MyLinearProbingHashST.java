/**
 * imp linear probing hash st
 */
public class MyLinearProbingHashST<Key, Value> {
    private int N; // number of k-v pairs in the table
    private int M; // size of linear-probing table
    private static final int INIT_CAP = 4; // must be power of 2
    private Key[] keys;
    private Value[] vals;
    private int tomb;
    private static final byte EMPTY = 0, OCCUPIED = 1, DELETED = 2;
    private byte[] state;

    public MyLinearProbingHashST() {
        this(INIT_CAP);
    }

    public MyLinearProbingHashST(int cap) {
        M = cap;
        N = 0;
        tomb = 0;
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
        state = new byte[M];
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
            if (state[i] == OCCUPIED) t.put(keys[i], vals[i]);
        }
        this.M = t.M;
        this.keys = t.keys;
        this.vals = t.vals;
        this.state = t.state;
        this.N = t.N;
        this.tomb = 0;

    }

    /**
     * if a new key hash to an empty entry, store it here, if not,
     * scan sequentially to find an empty position
     * for lazy deletion, store the first tomb index, then keep going, stop util either met the same key or
     * an empty spot, to avoid duplicate keys inserting.
     */
    public void put(Key key, Value value) {
        // ensure table is at most half full
        if ((N + tomb) >= M / 2) resize(2 * M);

        int firstDel = -1;
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (state[i] == OCCUPIED) {
                if (keys[i].equals(key)) {
                    vals[i] = value; // same key, update new value
                    return;
                }
            } else if (state[i] == DELETED) { // Record the first tomb then keep going
                if (firstDel < 0) firstDel = i;
            } else {
                int slot = (firstDel > -0) ? firstDel : i; // if we have met a tomb, use the tomb early spot
                keys[slot] = key;
                vals[slot] = value;
                if (state[slot] == DELETED) tomb--;
                state[slot] = OCCUPIED;
                N++;
                return;
            }
        }

    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (state[i] == EMPTY) return null; // search miss
            if (keys[i].equals(key) && state[i] == OCCUPIED) {
                return vals[i];
            }
            if (state[i] == DELETED) {
                continue;
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

    /**
     * problem 3.4.26, lazy deletion, setting deleted slot as null and later removing them from the table in the resize()
     * count tomb, if tomb plus empty is less than M/8, resize
     */
    public void lazyDelete(Key key) {
        for (int i = hash(key); ; i = (i + 1) % M) {
            if (state[i] == EMPTY) return;
            if (state[i] == OCCUPIED && keys[i].equals(key)) {
                state[i] = DELETED;
                keys[i] = null;
                vals[i] = null;
                N--;
                tomb++;
                if (tomb > M / 4) resize(M);
                return;
            }
        }
    }
}
