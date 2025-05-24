import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

/**
 * Exercise 3.1.2 :
 * develop ST That uses an unordered array as the underlying data structure
 */

public class ArrayST<Key, Value> {
    private static final int INIT_SIZE = 8;
    private Key[] keys;
    private Value[] values;
    private int n = 0;

    public ArrayST() {
        keys = (Key[]) new Object[INIT_SIZE];
        values = (Value[]) new Object[INIT_SIZE];

    }

    public int size(){
        return n;
    }
    public boolean isEmpty(){
        return n==0;
    }

    private void resize(int capacity){
        Key[] tempK = (Key[]) new Object[capacity];
        Value[] tempV=(Value[]) new Object[capacity];
        for(int i=0;i<n;i++){
            tempK[i]=keys[i];
            tempV[i]=values[i];
        }
        keys=tempK;
        values=tempV;
    }

    public Value get(Key key){
        if(isEmpty()) return null;
        for(int i=0;i<n;i++){
            if(keys[i].equals(key)){
                return values[i];
            }
        }
        return null; // search miss

    }

    public void put(Key key, Value val){
        delete(key);
        if(n>= values.length) resize(2*n);
        values[n]=val;
        keys[n]=key;
        n++;
    }

    public void delete(Key key){
        for(int i=0;i<n;i++){
            if(keys[i].equals(key)){
                keys[i]=keys[n-1];
                values[i]=values[n-1];
                n--;
            }
        }
    }

    public Iterable<Key> keys(){
        Queue<Key> queue = new Queue<>();
        for(int i=0;i<n;i++){
            queue.enqueue(keys[i]);
        }
        return queue;
    }

    public static void main(String[] args) {
        ArrayST<String, Integer> st= new ArrayST<>();
        st.put("first",1);
        st.put("second",2);
        st.put("third",3);

        for(String k:st.keys()){
            System.out.println(k);
        }
    }
}
