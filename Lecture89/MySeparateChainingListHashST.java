/**Separate chaining to implement hash table, array of linked lists*/

public class MySeparateChainingListHashST <Key, Value>{
    private int n; // number of key-value pairs
    private int m; // hash table size
    private Node[] st;

    private static class Node{
        private Object key;
        private Object val;
        private Node next;

        public Node(Object k,Object v, Node next){
            key = k;
            val=v;
            this.next = next;
        }
    }

    public MySeparateChainingListHashST(){
        this(997);
    }
    public MySeparateChainingListHashST(int m){
        this.m = m;
        st=new Node[m];
    }

    // hash value between 0 and m-1
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    // return number of key-value pairs in symbol table
    public int size() {
        return n;
    }

    // is the symbol table empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key){
        return get(key)!=null;
    }

    public Value get(Key key){
        int i = hash(key);
        for(Node x=st[i];x!=null;x=x.next){
            if(key.equals(x.key)) return (Value) x.val;
        }
        return null;
    }

    public void put(Key key, Value val){
        if(val==null){
            delete(key);
            return;
        }
        int i=hash(key);
        for(Node x = st[i];x!=null;x=x.next){
            if(key.equals(x.key)) x.val=val; return;
        }
        n++;
        st[i]=new Node(key,val,st[i]); // prepend to the head
    }

    public void delete(Key key){
        int i=hash(key);
        Node prev = null, curr = st[i];
        while (curr != null) {
            if (curr.key == key) {
                if (prev == null) st[i] = curr.next; // remove head
                else              prev.next  = curr.next;
                n--;
                return;
            }
            prev = curr;
            curr = curr.next;
        }
    }
}
