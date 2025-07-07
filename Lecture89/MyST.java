import java.util.Iterator;
import java.util.TreeMap;

/**
 * Ordered Symbol Table
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

    public boolean contains(Key key){
        return st.containsKey(key);
    }

    public boolean isEmpty(){
        return st.isEmpty();
    }

    public int size(){
        return st.size();
    }

    public Key min(){
        return st.firstKey();
    }

    public Key max(){
        return st.lastKey();
    }

    public Key floor(Key key){
        return st.floorKey(key);
    }

    public Key ceiling(Key key){
        return st.ceilingKey(key);
    }

    @Override
    public Iterator<Key> iterator() {
        return st.keySet().iterator();
    }

    public Iterable<Key> keys(){ return st.keySet();}
}
