import java.util.Iterator;
import java.util.TreeMap;

/**
 * Symbol table
 */
public class MyST<Key extends Comparable<Key>, Value>implements Iterable<Key> {
    private TreeMap<Key, Value> st;

    public MyST() {
        st = new TreeMap<Key,Value>();
    }

    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("calls put() with null key");
        if (key == null) st.remove(key);
        else st.put(key, val);
    }

    public Value get(Key key){
        if (key == null) throw new IllegalArgumentException("calls get() with null key");
        return st.get(key);
    }
    public void delete(Key key){
        if (key == null) throw new IllegalArgumentException("null key");
        st.remove(key);
    }
    @Override
    public Iterator<Key> iterator() {
        return null;
    }
}
