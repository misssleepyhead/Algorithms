import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Use linked list to imp sequence search symbol table
 *
 * book practice 3.1.25 software caching, add cache instance variable to save time for client code
 */
public class MySequentialSearchST<Key, Value> {
    private class Node {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    private Node first;
    private int size;
    private Node cache;

    public MySequentialSearchST() {
        this.first = null;
        this.size = 0;
    }

    public Value get(Key key) {
        if(cache!=null && cache.key.equals(key)){
            return cache.val;
        }
        // scan through the list, using equal() to compare key, return value if we found target key otherwise return null
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                cache=x;
                return x.val;
            }
        }
        return null;
    }

    public void put(Key key, Value val) {
        if(cache!=null && key.equals(cache.key)){
            cache.val=val;
            return;
        }
        // search for the key, update val if found, create a new node if not found
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        // not found, create a new node
        first = new Node(key, val, first);
        size++;
        cache=first;
    }

    public Iterable<Key> keys() {
        List<Key> keyList = new ArrayList<>();
        for (Node x = first; x != null; x = x.next) {
            keyList.add(x.key);
        }
        return keyList;

    }

    public void eagerDelete(Key key){
        if(first==null) return;

        // special case: key is first
        if(key.equals(first.key)){
            if(cache==first) cache=null; // invalid cache
            first=first.next;
            size--;
            return;
        }

        // general case: track previous node
        Node prev=first;
        Node curr=first.next;

        while (curr!=null){
            if(key.equals(curr.key)){
                if(cache==curr) cache=null;
                prev.next=curr.next;
                size--;
                return;
            }
            prev=curr;
            curr=curr.next;
        }
    }

    public int size() {
        return size;
    }


}
